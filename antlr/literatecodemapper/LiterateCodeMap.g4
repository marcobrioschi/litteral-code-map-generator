grammar LiterateCodeMap;

///////////////////////////////////////////////////////////////////////////////
// Parser rules

sentence
    : (directiveDeclaration)*
    ;

directiveDeclaration
    : LITERATEMAPINVOKE LPAREN param COMMA param COMMA param RPAREN
    ;

param
    : NUMBER
    | DQSTRING
    | SQSTRING
    ;

///////////////////////////////////////////////////////////////////////////////
// Tokens

LITERATEMAPINVOKE:  '@LiterateMapInvoke';

NUMBER:             [1-9][0-9]*;
DQSTRING:           '"' (~["\\\r\n])* '"';
SQSTRING:           '\'' (~['\\\r\n])* '\'';

LPAREN:             '(';
RPAREN:             ')';
COMMA:              ',';

FILLCHARS:          (~[@])              -> channel(HIDDEN);
WS:                 [ \t\r\n\u000C]+    -> channel(HIDDEN);