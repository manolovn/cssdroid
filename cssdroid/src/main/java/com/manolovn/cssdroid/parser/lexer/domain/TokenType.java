package com.manolovn.cssdroid.parser.lexer.domain;

/**
 * Token type used to define lexer grammar
 */
public enum TokenType {
    VARIABLE,
    SELECTOR,
    HEX_COLOR,
    SEMICOLON,
    EQUAL,
    WHITESPACES,
    OPEN_RULE,
    CLOSE_RULE,
    PROPERTY,
    NUMBER,
    OPEN_BRACKET,
    CLOSE_BRACKET,

    PLUSMINUS,
    MULTDIV,
    RAISED,
    FUNCTION,

    END
}
