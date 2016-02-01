package com.manolovn.cssdroid.parser.lexer.domain;

import java.util.regex.Pattern;

/**
 * Token info entity
 */
public class Lexem {

    public final Pattern regex;
    public final TokenType token;

    public Lexem(Pattern regex, TokenType token) {
        this.regex = regex;
        this.token = token;
    }

}
