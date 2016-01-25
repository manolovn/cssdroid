package com.manolovn.cssdroid.parser.lexer;

import com.manolovn.cssdroid.parser.lexer.domain.TokenInfo;
import com.manolovn.cssdroid.parser.lexer.domain.Token;
import com.manolovn.cssdroid.parser.lexer.domain.TokenType;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Less lexer
 */
public class CssDroidLexer {

    private LinkedList<TokenInfo> tokenInfos;
    private LinkedList<Token> tokens;

    public CssDroidLexer() {
        tokenInfos = new LinkedList<>();
        tokens = new LinkedList<>();

        init();
    }

    private void init() {
        add(";", TokenType.SEMICOLON);
        add("^:", TokenType.EQUAL);
        add("^=", TokenType.EQUAL);
        add("\\{", TokenType.OPEN_RULE);
        add("\\}", TokenType.CLOSE_RULE);
        add("@([-a-zA-Z]+)", TokenType.VARIABLE);
        add("^\\.([^\\s]+).", TokenType.SELECTOR);
        add("([-a-zA-Z]+)", TokenType.PROPERTY);
        add("#([a-fA-F0-9]{6}|[a-fA-F0-9]{3})", TokenType.COLOR);
        add("[0-9]+(dp|dip|sp)", TokenType.DIMENSION);
        add("(?:\\d+\\.?|\\.\\d)\\d*(?:[Ee][-+]?\\d+)?", TokenType.NUMBER);
        add("\\(", TokenType.OPEN_BRACKET);
        add("\\)", TokenType.CLOSE_BRACKET);
    }

    public void tokenize(String input) {
        input = input.trim();
        tokens.clear();
        while (!input.equals("")) {
            boolean match = false;
            for (TokenInfo info : tokenInfos) {
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

    public void add(String regex, TokenType token) {
        tokenInfos.add(new TokenInfo(Pattern.compile("^(" + regex + ")"), token));
    }

}
