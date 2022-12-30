//Task 8

grammar Task_8;

@parser::header
{
    import java.lang.Math;
}

start: operation EOF {System.out.println($operation.val);};

operation returns [double val]
    : left DOT right {$val = $left.val + $right.val;};

left returns [double n, double val]
    : b_ left {$n = $left.n + 1; $val = $b_.val * Math.pow(2, $n) + $left.val;} 
    | b_ {$n = 0; $val = $b_.val;};

right returns [double val]
    : b_ right {$val = ($right.val * 0.5) + ($b_.val * 0.5);}
    | b_ {$val = $b_.val * 0.5;};

b_ returns [double val] 
    : ZERO {$val = 0;}
    | ONE {$val = 1;};


/*
* LEXER RULES
*/

DOT : '.';
ZERO : '0';
ONE : '1';
// DIGIT : ZERO | ONE;
WS : [ \r\n\t] + -> skip;