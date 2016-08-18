grammar Logic;
@header {
package parser;
import expression.*;
}

expression returns [Expression value]   : disjunction                     {$value = $disjunction.value;}
                                        | disjunction IMPLIES expression  {$value = new Implication($disjunction.value, $expression.value);};

disjunction returns [Expression value] : conjunction                     {$value = $conjunction.value;}
                                        | conjunction OR disjunction      {$value = new Disjunction($conjunction.value, $disjunction.value);};

conjunction returns [Expression value] : negation                        {$value = $negation.value;}
                                        | negation AND conjunction        {$value = new Conjunction($negation.value, $conjunction.value);};

negation returns [Expression value]       : variable                        {$value = $variable.value;}
                                        | NOT negation                    {$value = new Negation($negation.value);}
                                        | OB expression CB                {$value = $expression.value;};

variable returns [Expression value]       : VAR                             {$value = new Variable($VAR.text);};

IMPLIES : '->';
OR : '|';
AND : '&';
NOT : '!';
OB : '(';
CB : ')';
VAR : [A-Z]([0-9])*;