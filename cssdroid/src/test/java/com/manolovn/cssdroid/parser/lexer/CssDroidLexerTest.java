package com.manolovn.cssdroid.parser.lexer;

import com.manolovn.cssdroid.parser.lexer.domain.Token;
import com.manolovn.cssdroid.parser.lexer.domain.TokenType;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.manolovn.cssdroid.parser.lexer.Assert.equalsToken;
import static org.junit.Assert.assertTrue;

/**
 * Test for {@link CssDroidLexer}
 */
public class CssDroidLexerTest {

    private CssDroidLexer lexer;

    @Before
    public void setUp() {
        lexer = new CssDroidLexer();
    }

    @Test
    public void shouldNotGenerateTokensOnEmptyInput() {
        lexer.tokenize("");

        assertTrue(lexer.getTokens().isEmpty());
    }

    @Test
    public void shouldGenerateVariableTokens() {
        lexer.tokenize("@variable: 13sp;");

        List<Token> tokens = lexer.getTokens();
        equalsToken(tokens.get(0), new Token(TokenType.VARIABLE, "@variable"));
        equalsToken(tokens.get(1), new Token(TokenType.EQUAL, ":"));
        equalsToken(tokens.get(2), new Token(TokenType.DIMENSION, "13sp"));
        equalsToken(tokens.get(3), new Token(TokenType.SEMICOLON, ";"));
    }

    @Test(expected = LexerException.class)
    public void shouldThrowExceptionOnNoMatchToken() {
        lexer.tokenize("@@notmatch: 11xp:)");
    }
}
