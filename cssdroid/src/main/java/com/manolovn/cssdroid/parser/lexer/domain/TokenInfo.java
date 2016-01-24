package com.manolovn.cssdroid.parser.lexer.domain;

import java.util.regex.Pattern;

/**
 * Token info entity
 */
public class TokenInfo {

    public final Pattern regex;
    public final TokenType token;

    public TokenInfo(Pattern regex, TokenType token) {
        this.regex = regex;
        this.token = token;
    }

}
