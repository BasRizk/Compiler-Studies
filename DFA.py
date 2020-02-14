"""
Assumptions:
a) The alphabet § is always the binary alphabet {0, 1}.
b) The set of states Q is always of the form {0, . . . , n}, for some n 2 N.
c) The start state is always state 0.
"""

class DFA:

    """
    A string describing a DFA is of the form P#S, where P is a prefix representing the
    transition function ± and S is a suffix representing the set F of accept state.
    """
    def __init__(self, description):
        print("\n=> Init the DFA with description ' %s '" % description)

        description = description.split("#")
        transitions = description[0].split(";")
        self.trans_dict = self.construct_trans_dict(transitions)
        self.accepted_states_list = description[1].split(",")

        print("..Trans_dict = ", self.trans_dict)

    def run(self, problem_str):
        print("\n=> Running DFA on input ' %s ' " % problem_str)
        
        current_state = '0'
        for _input in problem_str.split(","):
            print("Current_state is %s", current_state)
            current_state =\
                self.trans_dict[(current_state, _input)]    
        print("Current_state is %s", current_state)
        return (current_state in self.accepted_states_list)
            


    def construct_trans_dict(self, transitions):
        trans_dict = {}
        for transition in transitions:
            print(tuple(transition.split(",")))
            current_state, zero_state, one_state =\
                 tuple(transition.split(","))
            trans_dict[(current_state, '0')] = zero_state
            trans_dict[(current_state, '1')] = one_state
        return trans_dict

sample_1 = "0,0,1;1,2,3;2,3,2;3,3,3#0,3"
sample_2 = "0,0,1;1,1,2;2,2,1#2"
sample_3 = "0,0,1;1,2,1;2,0,3;3,3,3#1,3"

dfa_description = input("Enter DFA Description please: ")

while dfa_description is None or dfa_description == "":
    dfa_description = input("Choose one sample of {1,2,3}: ")
    if dfa_description == "1":
        dfa_description = sample_1
    elif dfa_description == "2":
        dfa_description = sample_2
    elif dfa_description == "3":
        dfa_description = sample_3

dfa_sample = DFA(dfa_description)

while(True):
    dfa_input = input("Enter you input: ")
    print(dfa_sample.run(dfa_input))
    dfa_continue = input("Do you want to continue? y/n :")
    if 'y' not in dfa_continue:
        break

# print(dfa_sample.run("1,0,1,0,0"))
# print(dfa_sample.run("0,0,0,1,0"))
# print(dfa_sample.run("0,1,0,1,0,0,0"))
# print(dfa_sample.run("1,0,1,1,1,1,1"))
# print(dfa_sample.run("1,1,0,1,1"))

# print(dfa_sample.run("1,1,0,1,1"))
# print(dfa_sample.run("1,0,1,1,0,1,1"))
# print(dfa_sample.run("1,0,1,0,1,0,1"))
# print(dfa_sample.run("1,1,0,0,1,1,0,0,1,1"))
# print(dfa_sample.run("1,1,1,1,0,1"))


print("The End.")