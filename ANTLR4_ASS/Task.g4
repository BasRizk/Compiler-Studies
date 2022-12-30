grammar Task;

/*
 * Parser Rules
 */
prog : (state_1 | state_2)+;

state_1 : (TRANS_A | TRANS_A_L) {print('01')};
state_2 : (TRANS_B | TRANS_B_L) {print('10')};

/*
 * Lexer Rules
 */
fragment ONE : '1' ;
fragment ZERO : '0' ;
TRANS_A_L: (TRANS_A ZERO ZERO)+ TRANS_A;
TRANS_B_L: (TRANS_B ZERO)+ TRANS_B;
TRANS_B: ZERO* ONE+ ZERO;
TRANS_A: ZERO* ONE+;

WS : [ \t\r\n]+ -> skip ; // skip spaces, tabs, newlines
WHITESPACE : ' ' -> skip ;