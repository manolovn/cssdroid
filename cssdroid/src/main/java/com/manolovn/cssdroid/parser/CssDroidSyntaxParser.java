package com.manolovn.cssdroid.parser;

import com.manolovn.cssdroid.parser.domain.FunctionNode;
import com.manolovn.cssdroid.parser.domain.FunctionType;
import com.manolovn.cssdroid.parser.domain.Node;
import com.manolovn.cssdroid.parser.domain.PropertyNode;
import com.manolovn.cssdroid.parser.domain.SelectorNode;
import com.manolovn.cssdroid.parser.domain.StyleSheet;
import com.manolovn.cssdroid.parser.domain.ValueNode;
import com.manolovn.cssdroid.parser.domain.VariableNode;
import com.manolovn.cssdroid.parser.lexer.CssDroidLexer;
import com.manolovn.cssdroid.parser.lexer.ParserException;
import com.manolovn.cssdroid.parser.lexer.domain.Token;
import com.manolovn.cssdroid.parser.lexer.domain.TokenType;
import com.manolovn.cssdroid.parser.visitor.ApplyVariableVisitor;
import com.manolovn.cssdroid.parser.visitor.EvalFunctionVisitor;
import com.manolovn.cssdroid.parser.visitor.NodeVisitor;
import com.manolovn.cssdroid.util.Preconditions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * CSS parser based on regex
 */
public class CssDroidSyntaxParser {

    private final CssDroidLexer lexer;

    private StyleSheet styleSheet;
    private LinkedList<Token> tokens;
    private Token lookAhead;

    public CssDroidSyntaxParser() {
        this.lexer = new CssDroidLexer();
    }

    public StyleSheet parseTokens(String content) {
        try {
            lexer.tokenize(content);
            styleSheet = parseTokens(lexer.getTokens());
        } catch (ParserException e) {
            System.out.println(e.getMessage());
        }
        return styleSheet;
    }

    private StyleSheet parseTokens(List<Token> tokens) {
        Preconditions.checkArgument(!tokens.isEmpty(), "No tokens to parse");

        this.tokens = new LinkedList<>(tokens);
        lookAhead = this.tokens.getFirst();

        styleSheet = new StyleSheet();
        extract();

        if (lookAhead.token != TokenType.END) {
            throw new ParserException(
                    "Unexpected symbol " + lookAhead.token + " found " + lookAhead.sequence);
        }

        List<NodeVisitor> nodeVisitors = new ArrayList<>();
        for (VariableNode node : styleSheet.getVariables()) {
            nodeVisitors.add(new ApplyVariableVisitor(node.getName(), node.getValue()));
        }

        for (Node node : styleSheet.getRules()) {
            for (NodeVisitor visitor : nodeVisitors) {
                node.accept(visitor);
            }
        }

        EvalFunctionVisitor evalFunctionVisitor = new EvalFunctionVisitor();
        for (Node node : styleSheet.getRules()) {
            node.accept(evalFunctionVisitor);
        }

        return styleSheet;
    }

    private void nextToken() {
        tokens.pop();
        if (tokens.isEmpty()) {
            lookAhead = new Token(TokenType.END, "");
        } else {
            lookAhead = tokens.getFirst();
        }
    }

    private void extract() {
        styleSheet.addVariable((VariableNode) extractVar());
        styleSheet.addRule(extractRule(null));
    }

    private Node extractRule(Node node) {
        if (lookAhead.token == TokenType.SELECTOR) {
            SelectorNode selectorNode = new SelectorNode(lookAhead.sequence);
            nextToken();
            selectorNode.addChild(extractRule(selectorNode));
            return selectorNode;
        } else if (lookAhead.token == TokenType.OPEN_RULE) {
            nextToken();
            extractProperties(node);
            if (lookAhead.token != TokenType.CLOSE_RULE) {
                throw new ParserException(
                        "Closing rule expected and " + lookAhead.sequence + " found instead");
            }
            nextToken();
            extract();
        }
        return null;
    }

    private void extractProperties(Node node) {
        if (lookAhead.token == TokenType.PROPERTY) {
            String property = lookAhead.sequence;
            nextToken();
            node.addChild(new PropertyNode(property, extractVar()));
            extractProperties(node);
        }
    }

    private Node extractVar() {
        if (lookAhead.token == TokenType.VARIABLE) {
            String variableName = lookAhead.sequence;
            nextToken();
            return new VariableNode(variableName, extractVar());
        } else if (lookAhead.token == TokenType.EQUAL) {
            nextToken();
            return function(null);
        }
        return null;
    }

    private Node function(Node node) {
        if (lookAhead.token == TokenType.FUNCTION) {
            FunctionType functionType = FunctionType.fromString(lookAhead.sequence);
            nextToken();
            Node functioNode = new FunctionNode(functionType);
            function(functioNode);
            return functioNode;
        } else if (lookAhead.token == TokenType.OPEN_BRACKET) {
            nextToken();
            extractArguments(node);
            if (lookAhead.token != TokenType.CLOSE_BRACKET) {
                throw new ParserException(
                        "Closing brackets expected " + lookAhead.token + " found instead");
            }
            nextToken();
            endExpression();
            return node;
        }
        return value();
    }

    private void extractArguments(Node node) {
        if (lookAhead.token == TokenType.COMMA) {
            nextToken();
        }
        if (lookAhead.token == TokenType.NUMBER
                || lookAhead.token == TokenType.COLOR
                || lookAhead.token == TokenType.VARIABLE) {
            node.addChild(value());
            extractArguments(node);
        }
    }

    private Node value() {
        if (lookAhead.token == TokenType.NUMBER) {
            String value = lookAhead.sequence;
            nextToken();
            endExpression();
            return new ValueNode(value);
        } else if (lookAhead.token == TokenType.COLOR) {
            String value = lookAhead.sequence;
            nextToken();
            endExpression();
            return new ValueNode(value);
        } else if (lookAhead.token == TokenType.VARIABLE) {
            String value = lookAhead.sequence;
            nextToken();
            endExpression();
            return new ValueNode(value);
        } else if (lookAhead.token == TokenType.DIMENSION) {
            String value = lookAhead.sequence;
            nextToken();
            endExpression();
            return new ValueNode(value);
        } else {
            throw new ParserException("Unexpected symbol " + lookAhead.sequence + " found");
        }
    }

    private void endExpression() {
        if (lookAhead.token == TokenType.SEMICOLON) {
            nextToken();
            extract();
        }
    }
}
