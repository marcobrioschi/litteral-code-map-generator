grammar LiterateCodeMap;

///////////////////////////////////////////////////////////////////////////////
// Parser rules

commentsentence
    : (directiveDeclaration)*
    ;

directiveDeclaration
    : literatemapinvoke
    | literatemapblock
    ;

literatemapinvoke
    : LITERATEMAPINVOKE params
    ;

literatemapblock
    : LITERATEMAPBLOCK params
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
LITERATEMAPBLOCK:           '@LiterateMapBlock';

NUMBER:                     [0-9]*;
DQSTRING:                   '"' (~["\\\r\n])* '"';
SQSTRING:                   '\'' (~['\\\r\n])* '\'';

LPAREN:                     '(';
RPAREN:                     ')';
COMMA:                      ',';

FILLCHARS:                  (~[@])              -> channel(HIDDEN);
WS:                         [ \t\r\n\u000C]+    -> channel(HIDDEN);