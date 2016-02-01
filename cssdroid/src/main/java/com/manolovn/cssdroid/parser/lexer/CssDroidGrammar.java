package com.manolovn.cssdroid.parser.lexer;

import com.manolovn.cssdroid.parser.domain.FunctionType;
import com.manolovn.cssdroid.parser.lexer.domain.Lexem;
import com.manolovn.cssdroid.parser.lexer.domain.TokenType;
import com.manolovn.cssdroid.util.StringUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * CssDroid grammar
 */
public class CssDroidGrammar {

    private final LinkedList<Lexem> lexems;

    public CssDroidGrammar() {
        lexems = new LinkedList<>();

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

    public void add(String regex, TokenType token) {
        lexems.add(new Lexem(Pattern.compile("^(" + regex + ")"), token));
    }

    public List<Lexem> getLexems() {
        return lexems;
    }
}
