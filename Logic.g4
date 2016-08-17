grammar Logic;
@header {
package parser;
import expression.*;
}

expression returns [Expression value]   : disjunction                     {$value = new Expression("disj", $disjunction.value);}
                                        | disjunction IMPLIES expression  {$value = new Expression("impl", $disjunction.value, $expression.value);};

disjunction returns [Disjunction value] : conjunction                     {$value = new Disjunction("conj", $conjunction.value);}
                                        | conjunction OR disjunction      {$value = new Disjunction("disj", $conjunction.value, $disjunction.value);};

conjunction returns [Conjunction value] : negation                        {$value = new Conjunction("neg", $negation.value);}
                                        | negation AND conjunction        {$value = new Conjunction("conj", $negation.value, $conjunction.value);};

negation returns [Negation value]       : variable                        {$value = new Negation("var", $variable.value);}
                                        | NOT negation                    {$value = new Negation("neg", $negation.value);}
                                        | OB expression CB                {$value = new Negation("expr", $expression.value);};

variable returns [Variable value]       : VAR                             {$value = new Variable($VAR.text);};

IMPLIES : '->';
OR : '|';
AND : '&';
NOT : '!';
OB : '(';
CB : ')';
VAR : [A-Z]([0-9])*;