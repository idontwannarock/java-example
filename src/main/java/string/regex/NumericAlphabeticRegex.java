package string.regex;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumericAlphabeticRegex {

    public List<String> regex(String question, String pattern) {
        List<String> matches = new ArrayList<>();
        Matcher matcher = Pattern.compile(pattern).matcher(question);
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        return matches;
    }
}
