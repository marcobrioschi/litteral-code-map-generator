grammar LiterateCodeMap;

///////////////////////////////////////////////////////////////////////////////
// Parser rules

commentsentence
    : (directiveDeclaration)*
    ;

directiveDeclaration
    : literatemapconnection
    ;

literatemapconnection
    : LITERATEMAPCONNECTION params
    ;

params
    : LPAREN (param (COMMA param)+)? RPAREN
    ;

param
    : NUMBER
    | DQSTRING
    | SQSTRING
    ;

///////////////////////////////////////////////////////////////////////////////
// Tokens

LITERATEMAPCONNECTION:      '@LiterateMapConnection';

NUMBER:                     [1-9][0-9]*;
DQSTRING:                   '"' (~["\\\r\n])* '"';
SQSTRING:                   '\'' (~['\\\r\n])* '\'';

LPAREN:                     '(';
RPAREN:                     ')';
COMMA:                      ',';

FILLCHARS:                  (~[@])              -> channel(HIDDEN);
WS:                         [ \t\r\n\u000C]+    -> channel(HIDDEN);