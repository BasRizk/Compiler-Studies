# Generated from Task.g4 by ANTLR 4.8
from antlr4 import *
from io import StringIO
from typing.io import TextIO
import sys



def serializedATN():
    with StringIO() as buf:
        buf.write("\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\b")
        buf.write("M\b\1\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7")
        buf.write("\4\b\t\b\4\t\t\t\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\6\4\34")
        buf.write("\n\4\r\4\16\4\35\3\4\3\4\3\5\3\5\3\5\6\5%\n\5\r\5\16\5")
        buf.write("&\3\5\3\5\3\6\7\6,\n\6\f\6\16\6/\13\6\3\6\6\6\62\n\6\r")
        buf.write("\6\16\6\63\3\6\3\6\3\7\7\79\n\7\f\7\16\7<\13\7\3\7\6\7")
        buf.write("?\n\7\r\7\16\7@\3\b\6\bD\n\b\r\b\16\bE\3\b\3\b\3\t\3\t")
        buf.write("\3\t\3\t\2\2\n\3\2\5\2\7\3\t\4\13\5\r\6\17\7\21\b\3\2")
        buf.write("\3\5\2\13\f\17\17\"\"\2Q\2\7\3\2\2\2\2\t\3\2\2\2\2\13")
        buf.write("\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\3\23\3")
        buf.write("\2\2\2\5\25\3\2\2\2\7\33\3\2\2\2\t$\3\2\2\2\13-\3\2\2")
        buf.write("\2\r:\3\2\2\2\17C\3\2\2\2\21I\3\2\2\2\23\24\7\63\2\2\24")
        buf.write("\4\3\2\2\2\25\26\7\62\2\2\26\6\3\2\2\2\27\30\5\r\7\2\30")
        buf.write("\31\5\5\3\2\31\32\5\5\3\2\32\34\3\2\2\2\33\27\3\2\2\2")
        buf.write("\34\35\3\2\2\2\35\33\3\2\2\2\35\36\3\2\2\2\36\37\3\2\2")
        buf.write("\2\37 \5\r\7\2 \b\3\2\2\2!\"\5\13\6\2\"#\5\5\3\2#%\3\2")
        buf.write("\2\2$!\3\2\2\2%&\3\2\2\2&$\3\2\2\2&\'\3\2\2\2\'(\3\2\2")
        buf.write("\2()\5\13\6\2)\n\3\2\2\2*,\5\5\3\2+*\3\2\2\2,/\3\2\2\2")
        buf.write("-+\3\2\2\2-.\3\2\2\2.\61\3\2\2\2/-\3\2\2\2\60\62\5\3\2")
        buf.write("\2\61\60\3\2\2\2\62\63\3\2\2\2\63\61\3\2\2\2\63\64\3\2")
        buf.write("\2\2\64\65\3\2\2\2\65\66\5\5\3\2\66\f\3\2\2\2\679\5\5")
        buf.write("\3\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2\2;>\3\2\2")
        buf.write("\2<:\3\2\2\2=?\5\3\2\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@")
        buf.write("A\3\2\2\2A\16\3\2\2\2BD\t\2\2\2CB\3\2\2\2DE\3\2\2\2EC")
        buf.write("\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\b\b\2\2H\20\3\2\2\2IJ\7")
        buf.write("\"\2\2JK\3\2\2\2KL\b\t\2\2L\22\3\2\2\2\n\2\35&-\63:@E")
        buf.write("\3\b\2\2")
        return buf.getvalue()


class TaskLexer(Lexer):

    atn = ATNDeserializer().deserialize(serializedATN())

    decisionsToDFA = [ DFA(ds, i) for i, ds in enumerate(atn.decisionToState) ]

    TRANS_A_L = 1
    TRANS_B_L = 2
    TRANS_B = 3
    TRANS_A = 4
    WS = 5
    WHITESPACE = 6

    channelNames = [ u"DEFAULT_TOKEN_CHANNEL", u"HIDDEN" ]

    modeNames = [ "DEFAULT_MODE" ]

    literalNames = [ "<INVALID>",
            "' '" ]

    symbolicNames = [ "<INVALID>",
            "TRANS_A_L", "TRANS_B_L", "TRANS_B", "TRANS_A", "WS", "WHITESPACE" ]

    ruleNames = [ "ONE", "ZERO", "TRANS_A_L", "TRANS_B_L", "TRANS_B", "TRANS_A", 
                  "WS", "WHITESPACE" ]

    grammarFileName = "Task.g4"

    def __init__(self, input=None, output:TextIO = sys.stdout):
        super().__init__(input, output)
        self.checkVersion("4.8")
        self._interp = LexerATNSimulator(self, self.atn, self.decisionsToDFA, PredictionContextCache())
        self._actions = None
        self._predicates = None


