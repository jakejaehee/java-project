package org.example;

import org.jetbrains.annotations.NotNull;

import java.util.Stack;

public class BracketChecker {
    private Stack stack = new Stack();

    private static final String OPEN_BRACKETS = "({<[";
    private static final String CLOSE_BRACKETS = ")}>]";

    private boolean isOpenBracket(char bracket) {
        return OPEN_BRACKETS.contains(String.valueOf(bracket));
    }

    private boolean isCloseBracket(char bracket) {
        return CLOSE_BRACKETS.contains(String.valueOf(bracket));
    }

    private boolean isMatched(char openBracket, char closeBracket) {
        int openIdx = OPEN_BRACKETS.indexOf(openBracket);
        int closeIdx = CLOSE_BRACKETS.indexOf(closeBracket);
        return openIdx == closeIdx && openIdx != -1;
    }

    public boolean isCorrectBracketString(@NotNull String brackets) {
        for (int i = 0; i < brackets.length(); i++) {
            char bracket = brackets.charAt(i);

            if (isOpenBracket(bracket)) {
                stack.push(bracket);
            } else if (isCloseBracket(bracket)) {
                if (stack.size() == 0) {
                    return false;
                }
                char openBracket = (char) stack.pop();
                if (isMatched(openBracket, bracket) == false) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String brackets = "{}[{<>fdaf}]";
        BracketChecker bracketChecker = new BracketChecker();
        if (bracketChecker.isCorrectBracketString(brackets)) {
            System.out.println(brackets + " is correct");
        } else {
            System.out.println(brackets + " is incorrect");
        }
    }
}

