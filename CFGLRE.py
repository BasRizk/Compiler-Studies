# S -> ScT | T
# T -> aSb | iaLb | i
# L -> SdL | StopAsyncIteration

# 1)
# S  -> TS'
# s' -> cTS'
# T  -> 
class CFGLRE:

    def __init__(self):
        self.sentences_dict = None
        self.variables = None

    def LRE(self, cfg_encoding, epsilon="", verbose=False):
        print("Encoding entered: %s" % cfg_encoding)
        self.decode_sent_to_dict(cfg_encoding)

        print("$About to begin elmination..")
        self.apply_lre_algorithm(verbose=verbose)
        print("$Finished elimination.")

        return self.encode_sent_dict().replace(" ", epsilon)
            
    def unroll_line(self, v, variables_to_unroll, verbose=False):
        lhs = self.sentences_dict[v]
        new_lhs = lhs.copy()

        if verbose:
            print("..left-hand-side before unrolling: %s" % lhs)
        for t in lhs:
            if t[0] in variables_to_unroll:
                for r in self.sentences_dict[t[0]]:
                    new_lhs.insert(lhs.index(t),
                                t.replace(t[0], r))
                new_lhs.remove(t)
        self.sentences_dict[v] = new_lhs
        if verbose:
            print("..lhs after unrolling: %s" % new_lhs)

    def apply_lre_algorithm(self, verbose=False):
        def get_first(t):
            first = ""
            first += t[0]
            for i in t:
                if i != "'":
                    break
                first += i
            return first


        is_updated = False
        variables_to_unroll = []
        for v in self.variables:
            if verbose:
                print("..on variable %s" % v)
            lhs = self.sentences_dict[v]
            if verbose:
                print("..current lhs %s = %s" % (str(v), str(lhs)))

            to_be_eliminated = []
            for t in lhs:
                t_first= get_first(t)
                if t_first == v:
                    to_be_eliminated.append(t)
                elif t_first in variables_to_unroll and not v.startswith(t_first):
                    if verbose:
                        print("..about to unroll %s" % v)
                    self.unroll_line(v, variables_to_unroll, verbose=verbose)
                    is_updated = True

            if len(to_be_eliminated) > 0:
                # lhs = self.sentences_dict[v]
                if verbose:
                    print("..about to eliminate")
                    print("..lhs of %s = %s and to_be_eliminated = %s" % (str(v), str(lhs), str(to_be_eliminated)))
                
                self.eliminate(v, to_be_eliminated, lhs)
                is_updated = True
            
            variables_to_unroll.append(v)

    
        if is_updated:
            self.apply_lre_algorithm(verbose=verbose)

    def encode_sent_dict(self):
        lrecfg = ""
        for v in self.variables:
            lrecfg += v
            for t in self.sentences_dict[v]:
                lrecfg += "," + t
            lrecfg += ";"
        return lrecfg[:-1]

    def eliminate(self, v, to_be_eliminated, old_lhs):
        new_v = v[0] + "'"

        updated_old_lhs = []
        for t in old_lhs:
            if t not in to_be_eliminated:
                t = t + new_v
                updated_old_lhs.append(t)
        
        self.sentences_dict[v] = updated_old_lhs
    
        new_lhs = []
        for t in to_be_eliminated:
            t = t[1:] + new_v
            new_lhs.append(t)

        # Adding epsilon
        new_lhs.append(" ")
        
        self.sentences_dict[new_v] = new_lhs
        self.variables.insert(self.variables.index(v) + 1,new_v)

    def decode_sent_to_dict(self, cfg_encoding):
        sentences = cfg_encoding.split(";")
        self.variables = []
        self.sentences_dict = {}
        print(sentences)
        for sentence in sentences:
            sentence = sentence.split(",")
            lhs = []
            for i in range(1, len(sentence)):
                lhs.append(sentence[i])
            self.sentences_dict[sentence[0]] = lhs
            self.variables.append(sentence[0])

def run_samples(samples, correct_answers=None, match=False, verbose=False):

    def get_rule(v, rules):
        for rule in rules:
            if rule.startswith(v):
                return rule
        return None

    def match_rule(rule1, rule2):
        rule1 = rule1.split(",")
        rule2 = rule2.split(",")
        if len(rule1) != len(rule2):
            return False
        for t in rule1:
            if t not in rule2:
                return False
        return True 

    print()
    eliminator = CFGLRE()
    outputs = []
    for i, sample in enumerate(samples):
        print("\nSample %s" % i)
        output = eliminator.LRE(sample, verbose=verbose)
        outputs.append(output)
        print("Output is (  %s  )" % output)

    if match and (correct_answers is not None) and (len(correct_answers) == len(samples)):
        print("\n$About to match with entered correct_answers")
        for output, answer in zip(outputs, correct_answers):
            output_rules = output.split(";")
            answer_rules = answer.split(";")
            
            correct = False
            for ans_rule in answer_rules:
                # print(output_rules)
                out_rule = get_rule(ans_rule.split(",")[0], output_rules)
                # print(ans_rule)
                # print(out_rule)
                if out_rule is not None:
                    correct = match_rule(ans_rule, out_rule)

                if not correct:
                    print("Sample (%s); Incorrect; rule (%s) does not match" 
                            % (str(outputs.index(output)), ans_rule))
                    print("Correct Answer %s" % str(answer))
                    break
                correct = False

            if correct:
                print("Sample (%s); Correct" % str(outputs.index(output)))
            print("Original Output %s\n" % str(output))


if __name__ == "__main__":
    # samples = [
    #     "S,ScT,T;T,aSb,iaLb,i;L,SdL,S",
    #     "S,Sa,b",
    #     "S,Sab,cd",
    #     "S,SuS,SS,Ss,lSr,a",
    #     "S,SuT,T;T,TF,F;F,Fs,P;P,a,b",
    #     "S,z,To;T,o,Sz",
    #     "S,lLr,a;L,LbS,S",
    #     "S,BC,C;B,Bb,b;C,SC,a"
    # ]

    # correct_answers = [
    #     "S,TS';S',cTS',;T,aSb,iaLb,i;L,aSbS'dL,iaLbS'dL,iS'dL,aSbS',iaLbS',iS'",
    #     "S,bS';S',aS',",
    #     "S,cdS';S',abS',",
    #     "S,lSrS',aS';S',uSS',SS',sS',",
    #     "S,TS';S',uTS',;T,FT';T',FT',;F,PF';F',sF',;P,a,b",
    #     "S,z,To;T,oT',zzT';T',ozT',",
    #     "S,lLr,a;L,lLrL',aL';L',bSL',",
    #     "S,BC,C;B,bB';B',bB',;C,bB'CCC',aC';C',CC',"
    # ]

    samples = [
        "S,E;E,E+T,T;T,E-T,F;F,E*F,i",
        "S,Sa,Sb,T;T,Tc,F;F,Fd,S",
        "S,Sn,Sa,T,F;T,Tr,F;F,Fd,S,e,n",
        "S,S+T,T;T,T*F,F;F,(S),x,y",
        "S,(L),a;L,L.S,S"
    ]

    correct_answers = [
        "S,E;E,TE';E',+TE',;T,FT';T',E'-TT',;F,iF';F',T'E'*FF',",
        "S,TS';S',aS',bS',;T,FT';T',cT',;F,;F',dF',T'S'F',",
        "S,TS',FS';S',nS',aS',;T,FT';T',rT',;F,eF',nF';F',dF',T'S'F',S'F',",
        "S,TS';S',+TS',;T,FT';T',*FT',;F,(S),x,y",
        "S,(L),a;L,(L)L',aL';L',.SL',"
    ]

    if len(samples) > 0:
        run_samples(samples, correct_answers=correct_answers, match=False, verbose=False)

    print("The End.")
