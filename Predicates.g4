grammar Predicates;
@header {
package predicates_parser;
import expression.*;
import expression.Predicate;
}

expression returns [Expression value]   : d1=disjunction                     {$value = $d1.value;}
                                        | d2=disjunction IMPLIES e=expression  {$value = new Implication($d2.value, $e.value);};

disjunction returns [Expression value] : c1=conjunction                     {$value = $c1.value;}
                                        | d=disjunction OR c2=conjunction      {$value = new Disjunction($d.value, $c2.value);};

conjunction returns [Expression value] :  u1=unary                        {$value = $u1.value;}
                                        | c=conjunction AND u2=unary        {$value = new Conjunction($c.value, $u2.value);};

unary returns [Expression value] :      p=predicate {$value = $p.value;}
                                      | NOT u1=unary {$value = new Negation($u1.value);}
                                      | OB e=expression CB {$value = $e.value;}
                                      | FORALL v1=variable u2=unary {$value = new Forall($v1.value, $u2.value);}
                                      | EXISTS v2=variable u3=unary {$value = new Exists($v2.value, $u3.value);};
variable returns [Variable value] : VAR {$value = new Variable($VAR.text);};

predicate returns [Expression value] : PREDVAR {$value = new Predicate($PREDVAR.text);} OB t3=term {((Predicate)$value).addTerm($t3.value);} (COMMA t4=term {((Predicate)$value).addTerm($t4.value);})* CB
                                     | t1=term EQUALS t2=term {$value = new Equality($t1.value, $t2.value);};

term returns [Expression value] : a1=adding {$value = $a1.value;} |
                                  t=term PLUS a2=adding {$value = new Sum($t.value, $a2.value);};

adding returns [Expression value] : m1=multiplying {$value = $multiplying.value;}
                                 | a=adding MUL m2=multiplying {$value = new Multiplicity($a.value, $m2.value);};

multiplying returns [Expression value] : VAR OB t1=term {$value = new Function($VAR.text); ((Function)$value).addTerm($t1.value);} (COMMA t2=term {((Function)$value).addTerm($t2.value);})* CB
                                        | v=variable {$value = $v.value;}
                                        | OB t3=term CB {$value = $t3.value;}
                                        | ZERO {$value = new Zero();}
                                        | m=multiplying NEXT {$value = new Next($m.value);};
PLUS : '+';
MUL : '*';
EQUALS : '=';
COMMA : ',';
IMPLIES : '->';
OR : '|';
AND : '&';
NOT : '!';
OB : '(';
CB : ')';
VAR : [a-z]([0-9])*;
PREDVAR : [A-Z]([0-9])*;
ZERO : '0';
NEXT : '\'';
FORALL : '@';
EXISTS : '?';