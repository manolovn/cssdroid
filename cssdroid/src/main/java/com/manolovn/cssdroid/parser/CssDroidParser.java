package com.manolovn.cssdroid.parser;

import com.manolovn.cssdroid.parser.domain.Rule;
import com.manolovn.cssdroid.parser.lexer.CssDroidLexer;
import com.manolovn.cssdroid.parser.lexer.ParserException;
import com.manolovn.cssdroid.parser.lexer.domain.Token;
import com.manolovn.cssdroid.parser.lexer.domain.TokenType;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * CSS parser based on regex
 */
public class CssDroidParser {

    private final CssDroidLexer lexer;

    private LinkedList<Token> tokens;
    private Token lookAhead;

    public CssDroidParser() {
        this.lexer = new CssDroidLexer();
    }

    public Collection<Rule> parse(File source) throws IOException {
        final String content = FileUtils.readFileToString(source);
        try {
            lexer.tokenize(content);
            for (Token tok : lexer.getTokens()) {
                System.out.println(tok.token + " " + tok.sequence);
            }
            parse(lexer.getTokens());
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
        return Collections.EMPTY_LIST;
    }

    private void parse(List<Token> tokens) {
        //this.tokens = tokens.clone();
        this.tokens = new LinkedList<>(tokens);
        lookAhead = this.tokens.getFirst();

        expression();

        if (lookAhead.token != TokenType.END) {
            throw new ParserException(
                    "Unexpected symbol " + lookAhead.token + " found " + lookAhead.sequence);
        }
    }

    private void nextToken() {
        tokens.pop();
        if (tokens.isEmpty()) {
            lookAhead = new Token(TokenType.END, "");
        } else {
            lookAhead = tokens.getFirst();
        }
    }

    private void expression() {
        extractVar();
        extractSelector();
    }

    private void extractSelector() {
        if (lookAhead.token == TokenType.SELECTOR) {
            nextToken();
            extractSelector();
        } else if (lookAhead.token == TokenType.OPEN_RULE) {
            nextToken();
            extractProperty();
            if (lookAhead.token != TokenType.CLOSE_RULE) {
                throw new ParserException(
                        "Closing rule expected and " + lookAhead.sequence + " found instead");
            }
            nextToken();
            expression();
        }
    }

    private void extractProperty() {
        if (lookAhead.token == TokenType.PROPERTY) {
            nextToken();
            extractVar();
            extractProperty();
        }
    }

    private void extractVar() {
        if (lookAhead.token == TokenType.VARIABLE) {
            nextToken();
            extractVar();
        } else if (lookAhead.token == TokenType.EQUAL) {
            nextToken();
            value();
        }
    }

    private void endExpression() {
        if (lookAhead.token == TokenType.SEMICOLON) {
            nextToken();
            expression();
        }
    }

    private void value() {
        if (lookAhead.token == TokenType.NUMBER) {
            nextToken();
            endExpression();
        } else if (lookAhead.token == TokenType.COLOR) {
            nextToken();
            endExpression();
        } else if (lookAhead.token == TokenType.VARIABLE) {
            nextToken();
            endExpression();
        } else if (lookAhead.token == TokenType.DIMENSION) {
            nextToken();
            endExpression();
        } else {
            throw new ParserException("Unexpected symbol " + lookAhead.sequence + " found");
        }
    }
}
