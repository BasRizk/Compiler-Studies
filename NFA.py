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
        
        current_state = '0'
        for _input in list(problem_str):
            print("Current_state is %s", current_state)
            current_state =\
                self.trans_dict[(current_state, _input)]    
        print("Current_state is %s", current_state)
        return (current_state in self.accepted_states_list)

    def nfa_to_dfa(self, trans_0, trans_1, trans_e, accepted_states):
        """
        > Sample for the input:
            0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3
        Ex
        - get e-closure
        .. e(0) = {0} => A
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

        def dictionarize(_array, splitter=','):
            _dict = {}
            for t in _array:
                t = t.split(splitter)
                _dict[t[0]] = t[1]
            return _dict


        print("=> About to convert the NFA to DFA")
        dict_trans_0 = dictionarize(trans_0)
        dict_trans_1 = dictionarize(trans_1)
        dict_trans_e = dictionarize(trans_e)



        states_counter = 0
        closures = {}
        current_states = list(dict_trans_0.keys())[0]

        while(True):
            c = current_states
            for current_state in current_states:
                another_c = dict_trans_e.get(current_state)
                if another_c is not None:
                    c.append(another_c)

            if tuple(c) in list(closures):
                break

            closures[tuple(c)] = states_counter
            states_counter += 1
            current_states = c

        print('..new states are created according to eps-closures')

        new_transitions = ""
        new_accepted_states = ""
        for c in list(closures):
            all_next_states_0 = set()
            all_next_states_1 = set()
            for old_state in c:
                old_trans_0 = dict_trans_0.get(old_state)
                old_trans_1 = dict_trans_1.get(old_state)
                if old_trans_0 is not None:
                    all_next_states_0.add(old_trans_0)
                if old_trans_1 is not None:
                    all_next_states_1.add(old_trans_1)
            
            next_state_0 = closures.get(tuple(all_next_states_0))
            next_state_1 = closures.get(tuple(all_next_states_1))

            current_state = closures.get(c)
            new_transitions += str(current_state) + ","
            if next_state_0 is not None:
                new_transitions += str(next_state_0)
            else:
                new_transitions += '-1'
            new_transitions += ","
            if next_state_1 is not None:
                new_transitions += str(next_state_1)
            else:
                new_transitions += '-1'
            new_transitions += ';'

            print(set(accepted_states))
            print(set(c))
            if len(set(accepted_states).intersection(set(c))) > 0:
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


sample_1 = "0,0;1,2;3,3#0,0;0,1;2,3;3,3#1,2#3"

dfa_description = input("Enter NFA Description please: ")

while dfa_description is None or dfa_description == "":
    dfa_description = input("Choose one sample of {1}: ")
    if dfa_description == "1":
        dfa_description = sample_1

nfa_sample = NFA(dfa_description, nfa=True)

while(True):
    nfa_input = input("Enter you input: ")
    print(nfa_sample.run(nfa_input))
    nfa_continue = input("Do you want to continue? y/n :")
    if 'y' not in nfa_continue:
        break

print("The End.")