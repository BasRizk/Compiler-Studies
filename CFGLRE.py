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

    def LRE(self, cfg_encoding):
        print("Encoding entered: %s" % cfg_encoding)
        self.decode_sent_to_dict(cfg_encoding)

        # print("$About to begin unrolling cycles..")
        # self.unroll_cycles()
        print("$About to begin elmination..")
        self.apply_lre_algorithm()

        print("$Finished elimination.")
        return self.encode_sent_dict()

    # def unroll_cycles(self):
    #     variables_to_unroll = []
    #     for v in self.variables:
    #         if len(variables_to_unroll) <= 0:
    #             variables_to_unroll.append(v)
    #             continue
    #         print("..on variable %s" % v)
    #         self.unroll_line(v, variables_to_unroll)
    #         variables_to_unroll.append(v)
            
                    
    def unroll_line(self, v, variables_to_unroll):
        lhs = self.sentences_dict[v]
        new_lhs = lhs.copy()
        # for t in lhs:
        #     print("..term to unroll %s" % t)
        #     for unrolling_v in variables_to_unroll:
        #         print("unrolling variable %s" % unrolling_v)
        #         if unrolling_v in t:
        #             for r in self.sentences_dict[unrolling_v]:
        #                 new_lhs.insert(lhs.index(t),
        #                             t.replace(unrolling_v, r))
        #             new_lhs.remove(t)

        print("..lhs before unrolling: %s" % lhs)
        for t in lhs:
            if t[0] in variables_to_unroll:
                for r in self.sentences_dict[t[0]]:
                    new_lhs.insert(lhs.index(t),
                                t.replace(t[0], r))
                new_lhs.remove(t)
        self.sentences_dict[v] = new_lhs
        print("..lhs after unrolling: %s" % new_lhs)

    def apply_lre_algorithm(self):
        is_updated = False
        variables_to_unroll = []
        for v in self.variables:
            print()
            print("..on variable %s" % v)
            lhs = self.sentences_dict[v]
            print("..current lhs %s" % str(lhs))

            to_be_eliminated = []
            for t in lhs:
                if t[0] == v:
                    to_be_eliminated.append(t)
                elif t[0] in variables_to_unroll:
                    print("..about to unroll %s" % v)
                    self.unroll_line(v, variables_to_unroll)

            if len(to_be_eliminated) > 0:
                print("..about to eliminate")
                self.eliminate(v, to_be_eliminated, lhs)
                is_updated = True
            
            variables_to_unroll.append(v)

        if is_updated:
            self.apply_lre_algorithm()

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
        
        self.sentences_dict[new_v] = new_lhs

        new_variables = []
        for _v in self.variables:
            new_variables.append(_v)
            if _v == v:
                new_variables.append(new_v)
        self.variables = new_variables

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


samples = [
    "S,ScT,T;T,aSb,iaLb,i;L,SdL,S"
]

correct_answers = [
    "S,TS';S',cTS';T,aSb,iaLb,i;L,aSbS'dL,iaLbS'dL,iS'dL,aSbS',iaLbS',iS'"
]

eliminator = CFGLRE()
for sample in samples:
    print("Output is '%s'" % eliminator.LRE(sample))  

print("The End.")