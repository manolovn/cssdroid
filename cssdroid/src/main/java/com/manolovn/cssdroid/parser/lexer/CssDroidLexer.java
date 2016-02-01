package com.manolovn.cssdroid.parser.lexer;

import com.manolovn.cssdroid.parser.lexer.domain.Lexem;
import com.manolovn.cssdroid.parser.lexer.domain.Token;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;

/**
 * Less lexer
 */
public class CssDroidLexer {

    private CssDroidGrammar grammar;
    private LinkedList<Token> tokens;

    public CssDroidLexer() {
        grammar = new CssDroidGrammar();
        tokens = new LinkedList<>();
    }

    public void tokenize(String input) {
        input = input.trim();
        tokens.clear();
        while (!input.isEmpty()) {
            boolean match = false;
            for (Lexem info : grammar.getLexems()) {
                Matcher matcher = info.regex.matcher(input);
                if (matcher.find()) {
                    match = true;
                    String tok = matcher.group(0).trim();
                    input = matcher.replaceFirst("").trim();
                    tokens.add(new Token(info.token, tok));
                    break;
                }
            }
            if (!match) {
                throw new ParserException("Unexpected character in input: (" + input + ")");
            }
        }
    }

    public List<Token> getTokens() {
        return tokens;
    }
}
