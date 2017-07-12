grammar Calc;

calc: expr EOF;

expr: BR_OPEN expr BR_CLOSE
|expr TIMES expr
|expr DIV expr
|expr PLUS expr
|expr MINUS expr
|number ;

number: NUMBER;

PLUS: 	'+';
MINUS: 	'-';
TIMES: 	'*';
DIV: 	'/';

NUMBER: '-'? [0-9]+;
BR_OPEN: '(';
BR_CLOSE: ')';

WS: [ \t\r\n]+ -> skip;
