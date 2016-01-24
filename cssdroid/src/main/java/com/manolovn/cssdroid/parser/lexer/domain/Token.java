package com.manolovn.cssdroid.parser.lexer.domain;

/**
 * Token entity
 */
public class Token {

    public final TokenType token;
    public final String sequence;

    public Token(TokenType token, String sequence) {
        this.token = token;
        this.sequence = sequence;
    }
}
