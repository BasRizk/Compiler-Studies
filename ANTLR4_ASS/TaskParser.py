# Generated from Task.g4 by ANTLR 4.8
# encoding: utf-8
from antlr4 import *
from io import StringIO
import sys
if sys.version_info[1] > 5:
	from typing import TextIO
else:
	from typing.io import TextIO


def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\b")
        buf.write("\25\4\2\t\2\4\3\t\3\4\4\t\4\3\2\3\2\6\2\13\n\2\r\2\16")
        buf.write("\2\f\3\3\3\3\3\3\3\4\3\4\3\4\3\4\2\2\5\2\4\6\2\4\4\2\3")
        buf.write("\3\6\6\3\2\4\5\2\23\2\n\3\2\2\2\4\16\3\2\2\2\6\21\3\2")
        buf.write("\2\2\b\13\5\4\3\2\t\13\5\6\4\2\n\b\3\2\2\2\n\t\3\2\2\2")
        buf.write("\13\f\3\2\2\2\f\n\3\2\2\2\f\r\3\2\2\2\r\3\3\2\2\2\16\17")
        buf.write("\t\2\2\2\17\20\b\3\1\2\20\5\3\2\2\2\21\22\t\3\2\2\22\23")
        buf.write("\b\4\1\2\23\7\3\2\2\2\4\n\f")
        return buf.getvalue()


class TaskParser ( Parser ):

    grammarFileName = "Task.g4"

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    sharedContextCache = PredictionContextCache()

    literalNames = [ "<INVALID>", "<INVALID>", "<INVALID>", "<INVALID>", 
                     "<INVALID>", "<INVALID>", "' '" ]

    symbolicNames = [ "<INVALID>", "TRANS_A_L", "TRANS_B_L", "TRANS_B", 
                      "TRANS_A", "WS", "WHITESPACE" ]

    RULE_prog = 0
    RULE_state_1 = 1
    RULE_state_2 = 2

    ruleNames =  [ "prog", "state_1", "state_2" ]

    EOF = Token.EOF
    TRANS_A_L=1
    TRANS_B_L=2
    TRANS_B=3
    TRANS_A=4
    WS=5
    WHITESPACE=6

    def __init__(self, input:TokenStream, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.8")
        self._interp = ParserATNSimulator(self, self.atn, self.decisionsToDFA, self.sharedContextCache)
        self._predicates = None




    class ProgContext(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def state_1(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TaskParser.State_1Context)
            else:
                return self.getTypedRuleContext(TaskParser.State_1Context,i)


        def state_2(self, i:int=None):
            if i is None:
                return self.getTypedRuleContexts(TaskParser.State_2Context)
            else:
                return self.getTypedRuleContext(TaskParser.State_2Context,i)


        def getRuleIndex(self):
            return TaskParser.RULE_prog

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterProg" ):
                listener.enterProg(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitProg" ):
                listener.exitProg(self)




    def prog(self):

        localctx = TaskParser.ProgContext(self, self._ctx, self.state)
        self.enterRule(localctx, 0, self.RULE_prog)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 8 
            self._errHandler.sync(self)
            _la = self._input.LA(1)
            while True:
                self.state = 8
                self._errHandler.sync(self)
                token = self._input.LA(1)
                if token in [TaskParser.TRANS_A_L, TaskParser.TRANS_A]:
                    self.state = 6
                    self.state_1()
                    pass
                elif token in [TaskParser.TRANS_B_L, TaskParser.TRANS_B]:
                    self.state = 7
                    self.state_2()
                    pass
                else:
                    raise NoViableAltException(self)

                self.state = 10 
                self._errHandler.sync(self)
                _la = self._input.LA(1)
                if not ((((_la) & ~0x3f) == 0 and ((1 << _la) & ((1 << TaskParser.TRANS_A_L) | (1 << TaskParser.TRANS_B_L) | (1 << TaskParser.TRANS_B) | (1 << TaskParser.TRANS_A))) != 0)):
                    break

        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class State_1Context(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def TRANS_A(self):
            return self.getToken(TaskParser.TRANS_A, 0)

        def TRANS_A_L(self):
            return self.getToken(TaskParser.TRANS_A_L, 0)

        def getRuleIndex(self):
            return TaskParser.RULE_state_1

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterState_1" ):
                listener.enterState_1(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitState_1" ):
                listener.exitState_1(self)




    def state_1(self):

        localctx = TaskParser.State_1Context(self, self._ctx, self.state)
        self.enterRule(localctx, 2, self.RULE_state_1)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 12
            _la = self._input.LA(1)
            if not(_la==TaskParser.TRANS_A_L or _la==TaskParser.TRANS_A):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
            print('01')
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx


    class State_2Context(ParserRuleContext):

        def __init__(self, parser, parent:ParserRuleContext=None, invokingState:int=-1):
            super().__init__(parent, invokingState)
            self.parser = parser

        def TRANS_B(self):
            return self.getToken(TaskParser.TRANS_B, 0)

        def TRANS_B_L(self):
            return self.getToken(TaskParser.TRANS_B_L, 0)

        def getRuleIndex(self):
            return TaskParser.RULE_state_2

        def enterRule(self, listener:ParseTreeListener):
            if hasattr( listener, "enterState_2" ):
                listener.enterState_2(self)

        def exitRule(self, listener:ParseTreeListener):
            if hasattr( listener, "exitState_2" ):
                listener.exitState_2(self)




    def state_2(self):

        localctx = TaskParser.State_2Context(self, self._ctx, self.state)
        self.enterRule(localctx, 4, self.RULE_state_2)
        self._la = 0 # Token type
        try:
            self.enterOuterAlt(localctx, 1)
            self.state = 15
            _la = self._input.LA(1)
            if not(_la==TaskParser.TRANS_B_L or _la==TaskParser.TRANS_B):
                self._errHandler.recoverInline(self)
            else:
                self._errHandler.reportMatch(self)
                self.consume()
            print('10')
        except RecognitionException as re:
            localctx.exception = re
            self._errHandler.reportError(self, re)
            self._errHandler.recover(self, re)
        finally:
            self.exitRule()
        return localctx





