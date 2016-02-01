package com.manolovn.cssdroid.parser.lexer;

import com.manolovn.cssdroid.parser.lexer.domain.Token;

/**
 * Custom assert
 */
public class Assert {

    public static boolean equalsToken(Token actual, Token expected) {
        return actual.sequence.equals(expected.sequence)
                && actual.token.equals(expected.token);
    }
}
