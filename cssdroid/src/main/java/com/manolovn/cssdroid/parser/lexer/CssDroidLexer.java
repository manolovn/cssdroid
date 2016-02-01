package com.manolovn.cssdroid.parser.lexer;

import com.manolovn.cssdroid.parser.domain.FunctionType;
import com.manolovn.cssdroid.parser.lexer.domain.Lexem;
import com.manolovn.cssdroid.parser.lexer.domain.Token;
import com.manolovn.cssdroid.parser.lexer.domain.TokenType;
import com.manolovn.cssdroid.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Less lexer
 */
public class CssDroidLexer {

    private static final String EMPTY = "";

    private LinkedList<Lexem> lexems;
    private LinkedList<Token> tokens;

    public CssDroidLexer() {
        lexems = new LinkedList<>();
        tokens = new LinkedList<>();

        initGrammar();
    }

    private void initGrammar() {
        add("(" + StringUtils.join(FunctionType.values(), "|") + ")", TokenType.FUNCTION);
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
        add("^,", TokenType.COMMA);
    }

    public void tokenize(String input) {
        input = input.trim();
        tokens.clear();
        while (inputIsNotEmpty(input)) {
            boolean match = false;
            for (Lexem info : lexems) {
                Matcher matcher = info.regex.matcher(input);
                if (matcher.find()) {
                    match = true;
                    String tok = matcher.group(0).trim();
                    input = matcher.replaceFirst(EMPTY).trim();
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
        lexems.add(new Lexem(Pattern.compile("^(" + regex + ")"), token));
    }

    private boolean inputIsNotEmpty(String input) {
        return !input.equals(EMPTY);
    }

}
