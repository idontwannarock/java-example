package string.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPrinter {

    public List<String> printAllMatches(String input, String patternString, int patternFlag) {
        if (input == null || input.isEmpty()) {
            return new ArrayList<>();
        }
        List<String> matches = new ArrayList<>();
        Pattern pattern = Pattern.compile(patternString, patternFlag);
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            System.out.println("Input, " + input + ", has match");
            do {
                System.out.println("Group count of the match, " + matcher.group() + ": " + matcher.groupCount());
                if (matcher.groupCount() > 0) {
                    for (int index = 1; index < matcher.groupCount(); index++) {
                        System.out.println("Match " + matcher.group() + " of group " + index + ": " + matcher.group(index));
                        matches.add(matcher.group(index));
                    }
                }
            } while (matcher.find());
        } else {
            System.out.println("Input, " + input + ", has no match");
        }
        return matches;
    }
}
