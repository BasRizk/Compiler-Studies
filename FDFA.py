# -*- coding: utf-8 -*-

class FDFA:
    
    def __init__(self, description, init_state='0'):
        self.init_state = init_state
        print("\n=> Init the DFA with description ' %s '" % description)
        description = description.split("#")
        transitions = description[0].split(";")
        self.trans_dict, self.action_dict =\
            self.construct_trans_dict(transitions)
        self.accepted_states_list = description[1].split(",")
        print("..Trans_dict = ", self.trans_dict)


    def run(self, problem_str):
        print("\n=> Running DFA on input ' %s ' " % problem_str)
        # current_state = self.init_state
        output_string = ""
        last_rejected_state = None

        tokens_counter = 0
        while len(problem_str) > 0:
            # print("\nAbout to retrieve a token")
            accepted_state_i = -1
            latest_accepted_state = None   
            current_state = self.init_state 
            print("Left of problem_str is %s" % problem_str)        
            for i in range(len(problem_str)):
                _input = problem_str[i]
                print("..Current_state is %s while input is %s" % (current_state, _input))
                current_state =\
                    self.trans_dict[(current_state, _input)]  
                if current_state in self.accepted_states_list:
                    print("..Accepted State -->")
                    latest_accepted_state = current_state
                    accepted_state_i = i
    
            problem_str = problem_str[accepted_state_i+1:]
            print("..Current_state finally is %s" % current_state)

            if latest_accepted_state is None:
                # Technically not accepted final one to print
                last_rejected_state = current_state
                break

            return_val = self.action_dict[latest_accepted_state]
            output_string += return_val
            print("..Returned token is %s" % return_val)
            tokens_counter += 1
            print("Retrieved %s token" % str(tokens_counter))
            
            
        if last_rejected_state is not None:
            return_val = self.action_dict[last_rejected_state]
            output_string += return_val
            print("..Token added for rejected state %s is %s" %
                (str(last_rejected_state), str(return_val)))

        return output_string


    def construct_trans_dict(self, transitions):
        trans_dict = {}
        action_dict = {}
        for transition in transitions:
            print(tuple(transition.split(",")))
            current_state, zero_state, one_state, action =\
                 tuple(transition.split(","))
            trans_dict[(current_state, '0')] = zero_state
            trans_dict[(current_state, '1')] = one_state
            action_dict[current_state] = action
        return trans_dict, action_dict


samples = [
    "0,0,1,00;1,0,2,01;2,3,2,10;3,2,3,11#1,3",
    "0,1,0,00;1,3,0,01;2,1,3,10;3,2,3,11#3",
    "0,0,1,00;1,2,1,01;2,0,3,10;3,3,3,11#0,1,2"
    ]

sample_inputs = [
    [
        "00111", "0011100", "110101", "1101010", "000"
    ],
    [
        "10000", "00", "00001", "10101", "10"
    ],
    [
        "1011100"
    ]
    ]

dfa_description = input("Enter FDFA Description please: ")

while dfa_description is None or dfa_description == "":
    _sample_input_query = "Choose one sample of \n"
    for i in range(len(samples)):
        _sample_input_query += str(i+1) + ". "
        _sample_input_query += samples[i] + "\n"
    dfa_description = input(_sample_input_query)
    
    try:
        sample_num = int(dfa_description)
        dfa_description = samples[sample_num -1]
    except:
        print("Incorrect input..")
        break

dfa_sample = FDFA(dfa_description)

dfa_input_query_sample_or_not = input ("Run samples (y/n): ")
if dfa_input_query_sample_or_not:
    sample_num = input("which (1 or 2)?: ")
    if "1" in sample_num:
        sample_num = 0
    elif "2" in sample_num:
        sample_num = 1
    else:
        sample_num = 2
    print("Running samples: " + str(sample_inputs[sample_num]))
    for _input in sample_inputs[sample_num]:
        # print(dfa_sample.run(_input))
        print("\nDone - INPUT (%s) - OUTPUT (%s)" %\
             (str(_input), str(dfa_sample.run(_input))))

while(True):
    dfa_input = input("Enter you input: ")
    print("\nDone - INPUT (%s) - OUTPUT (%s)" %\
             (str(dfa_input), str(dfa_sample.run(dfa_input))))
    dfa_continue = input("Do you want to continue? y/n :")
    if 'y' not in dfa_continue:
        break

print("The End.")