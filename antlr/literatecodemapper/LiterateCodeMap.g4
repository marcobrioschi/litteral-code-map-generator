grammar LiterateCodeMap;

///////////////////////////////////////////////////////////////////////////////
// Parser rules

commentsentence
    : (directiveDeclaration)*
    ;

directiveDeclaration
    : literatemapinvoke
    ;

literatemapinvoke
    : LITERATEMAPINVOKE params
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

LITERATEMAPINVOKE:          '@LiterateMapInvoke';

NUMBER:                     [0-9]*;
DQSTRING:                   '"' (~["\\\r\n])* '"';
SQSTRING:                   '\'' (~['\\\r\n])* '\'';

LPAREN:                     '(';
RPAREN:                     ')';
COMMA:                      ',';

FILLCHARS:                  (~[@])              -> channel(HIDDEN);
WS:                         [ \t\r\n\u000C]+    -> channel(HIDDEN);