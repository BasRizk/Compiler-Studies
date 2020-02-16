class NFA:

    def __init__(self, description, nfa=True):
        
        if nfa:
            print("\nNFA is observed, with description ' %s '" % description)
            description = description.split("#")
            trans_0 = description[0].split(";")
            trans_1 = description[1].split(";")
            trans_e = description[2].split(";")
            accepted_states = description[3].split(",")
            description = self.nfa_to_dfa(trans_0, trans_1, trans_e, accepted_states)

        print("\n=> Init the DFA with description ' %s '" % description)
        description = description.split("#")
        transitions = description[0].split(";")
        self.trans_dict = self.construct_trans_dict(transitions)
        self.accepted_states_list = description[1].split(",")

        print("..Trans_dict = ", self.trans_dict)


    def run(self, problem_str):
        print("\n=> Running DFA on input ' %s ' " % problem_str)
        
        current_state = self.init_state
        for _input in list(problem_str):
            print("Current_state is %s" % current_state)
            current_state =\
                self.trans_dict[(current_state, _input)]    
        print("Current_state is %s" % current_state)
        return (current_state in self.accepted_states_list)

    def nfa_to_dfa(self, trans_0, trans_1, trans_e, accepted_states):
        """
        > Sample for the input:
            0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3
        Ex
        ..get e-closure
        - e(0) = {0} => A
        - e(0,1) = {0,1,2} => B
        - e(0,2) = {0,2} => C
        - e(0,1,3) = {0,1,2,3} => D
        - e(0,2,3) = {0,2,3} => E
        - e(0,3) = {0,3} => F

        state | 0 | 1
           A  | A | B
           B  | C | D
           C  | A | D
           D  | E | D
           E  | F | D
           F  | F | D
        """
        def dict_trans(trans_pairs, alphabet = [0, 1] ):
            trans_dict = {}
            for trans_i in range(len(trans_pairs)):
                for current_state, next_state in trans_pairs[trans_i]:
                    trans = trans_dict.get((current_state, alphabet[trans_i]))
                    if trans is None:
                        trans_dict[(current_state, alphabet[trans_i])] = []
                        trans = trans_dict.get((current_state, alphabet[trans_i]))
                    trans.append(next_state)
            return trans_dict
            

        def dictionarize(_array, splitter=','):
            _dict = {}
            for t in _array:
                if len(t) <= 0:
                    continue
                t = t.split(splitter)
                this_dict = _dict.get(t[0])
                if this_dict is None:
                    _dict[t[0]] = []
                    this_dict = _dict.get(t[0])
                this_dict.append(t[1])
            return _dict

        def listify(_array, splitter=','):
            _list = []
            for t in _array:
                if len(t) <= 0:
                    continue
                t = t.split(splitter)
                _list.append((t[0], t[1]))
            return _list

        def get_current_states(current_possible_e):
            
            new_possible_e_group = current_possible_e.copy()
            for state in current_possible_e:
                other_possible_e = dict_e.get(state)
                if other_possible_e is not None:
                    for possible_e in other_possible_e:
                        new_possible_e_group.add(possible_e)
            print('current_possible_e')
            print(current_possible_e)
            if len(current_possible_e) == len(new_possible_e_group):
                return sorted(list(current_possible_e))
            else:
                print('but new is ')
                print(new_possible_e_group)
                return get_current_states(new_possible_e_group)

        print("=> About to convert the NFA to DFA")
        alphabet = ['0', '1']
        trans_0 = listify(trans_0)
        trans_1 = listify(trans_1)
        trans_dict = dict_trans([trans_0, trans_1], alphabet=alphabet)
        
        dict_e = dictionarize(trans_e)
        print('dict_e')
        print(dict_e)
        unproc_closures = [['0']]
        
        current_big_state_name = 'A'
        self.init_state = current_big_state_name
        e_closures = {}
        big_states = {}
        while True:
            
            if len(unproc_closures) <= 0:
                break
            current_states = unproc_closures.pop()
            current_possible_e = set(current_states.copy())
            print('Current states now are ')
            print(current_states)
            print('Current possible_e are ')
            print(current_possible_e)
                        
            # Loop over current states and discover all possible eps-transitions
            # These eps-trans, all define the big state closure
            current_possible_e = get_current_states(current_possible_e)
            if len(current_possible_e) <= 0:
                print('current_possible_e is empty')
                print(current_possible_e)
                continue
            
            # Is this closure already created
            closure = e_closures.get(tuple(current_states))
            if closure is not None:
                # Stop if already created
                continue
            # Otherwise add it and keep on discovering closures
            e_closures[tuple(current_states)] = tuple(current_possible_e)
            big_states[tuple(current_states)] = current_big_state_name
            current_big_state_name = chr(ord(current_big_state_name) + 1)
            
            # Find the possible trans from latest found closure,
            # to create a new closure
            for a in alphabet:
                next_states = set()
                for state in current_possible_e:
                    other_next_states = trans_dict.get((state, a))
                    if other_next_states is not None:
                        next_states = next_states.union(set(other_next_states))
                unproc_closure = sorted(list(next_states))
                print('unproc_closure')
                print('alphabet = ' + a)
                print(unproc_closure)
                unproc_closures.append(unproc_closure)
                
        print('..connected new states according to eps-closure')

        new_transitions = ""
        new_accepted_states = ""
        
        print('trans_dict')
        print(trans_dict)
        print('closures')
        print(e_closures)
        print('big_states')
        print(big_states)
        
        for big_state in list(big_states):
            current_state = big_states.get(big_state)
            new_transitions += str(current_state) + ","
            closure = e_closures.get(big_state)
            for a in alphabet:
                next_states = set()
                # print('\nclosure')
                # print(closure)
                for old_state in closure:
                    # print('alphabet with closure trans indexing')
                    # print((old_state, a))
                    old_trans = trans_dict.get((old_state, a))
                    if old_trans is not None:
                        next_states = next_states.union(set(old_trans))
                next_states = tuple(sorted(list(next_states)))
                # print('next_states')
                # print(next_states)
                big_next_state = big_states.get(next_states)
                if big_next_state is not None:
                    new_transitions += str(big_next_state)
                else:
                    new_transitions += '-1'
                new_transitions += ","
            new_transitions = new_transitions[:-1] + ';'

            if len(set(accepted_states).intersection(set(closure))) > 0:
                new_accepted_states += str(current_state) + ','
        # Dead state transitions
        new_transitions += "-1,-1,-1"

        print('..connected new states according to original transitions')

        dfa_description = new_transitions + '#' + new_accepted_states[:-1]

        return dfa_description

    def construct_trans_dict(self, transitions):
        trans_dict = {}
        for transition in transitions:
            print(tuple(transition.split(",")))
            current_state, zero_state, one_state =\
                 tuple(transition.split(","))
            trans_dict[(current_state, '0')] = zero_state
            trans_dict[(current_state, '1')] = one_state
        return trans_dict


samples = [ "0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3",
            "0,0;0,1#0,1;1,0##0",
            "0,1;2,1#0,3;3,2#1,0;3,2#2"]
dfa_description = input("Enter NFA Description please: ")

while dfa_description is None or dfa_description == "":
    _sample_input_query = "Choose one sample of \n"
    for i in range(len(samples)):
        _sample_input_query += str(i+1) + ". "
        _sample_input_query += samples[i] + "\n"
    dfa_description = input(_sample_input_query)
    for sample in samples:
        print("-" + str(sample))
    try:
        sample_num = int(dfa_description)
        dfa_description = samples[sample_num -1]
    except:
        print("Incorrect input..")
        break

nfa_sample = NFA(dfa_description, nfa=True)

while(True):
    nfa_input = input("Enter you input: ")
    print(nfa_sample.run(nfa_input))
    nfa_continue = input("Do you want to continue? y/n :")
    if 'y' not in nfa_continue:
        break

print("The End.")