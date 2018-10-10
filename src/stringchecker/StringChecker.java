package stringchecker;

import java.util.ArrayList;
import java.util.List;

public class StringChecker {

    private static final String OPENING_BRACKETS = "{([";
    private static final String CLOSING_BRACKETS = "})]";

    public static boolean isValid(String str) {
        if (str == null) {
            System.err.println("stringchecker.StringChecker.isValid()"
                    + " Invalid argument: " + str);
            return false;
        }

        boolean openBracket = false;
        List<Character> brackets = new ArrayList<>();

        for (char ch : str.toCharArray()) {
            if (OPENING_BRACKETS.indexOf(ch) != -1) {
                openBracket = true;
                brackets.add(ch);
            } else if (CLOSING_BRACKETS.indexOf(ch) != -1) {
                if (openBracket) {
                    int bracketsCount = brackets.size() - 1;
                    char lastOpenBrackes = brackets.get(bracketsCount);
                    if (reverseBracket(lastOpenBrackes) == ch) {
                        brackets.remove(bracketsCount);
                        if (brackets.isEmpty()) {
                            openBracket = false;
                        }
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return brackets.isEmpty();
    }

    private static char reverseBracket(char bracket) {
        int index;
        if ((index = OPENING_BRACKETS.indexOf(bracket)) != -1) {
            return CLOSING_BRACKETS.charAt(index);
        }
        if ((index = CLOSING_BRACKETS.indexOf(bracket)) != -1) {
            return OPENING_BRACKETS.charAt(index);
        }
        return bracket;
    }
}
