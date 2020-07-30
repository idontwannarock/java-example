package string.regex;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OverlappingRegex {

    public Set<String> findBySubstring(String regex, String input) {
        Pattern pattern = Pattern.compile(regex);
        Set<String> matches = new LinkedHashSet<>();
        for (int i = 0; i < input.length(); i++) {
            String tempInput = input.substring(i);
            System.out.println("Temp input: " + tempInput);
            Matcher matcher = pattern.matcher(tempInput);
            if (matcher.find()) {
                System.out.println("Match fount: \"" + (matcher.group()) + "\" start at " + i);
                matches.add(matcher.group());
                System.out.println("Regex result: " + matches.toString());
            }
        }
        System.out.println("Input :" + input);
        return matches;
    }

    public List<String> findByFlexPattern(String regex, String input) {
        List<String> matches = new ArrayList<>();
        for (int i = 0; i < input.length(); ++i) {
            for (int j = i + 1; j <= input.length(); ++j) {
                String positionSpecificPattern = "((?<=^.{" + i + "})(" + regex + ")(?=.{" + (input.length() - j) + "}$))";
                Matcher m = Pattern.compile(positionSpecificPattern).matcher(input);
                if (m.find()) {
                    System.out.println("Match found: \"" + (m.group()) + "\" at position [" + i + ", " + j + ")");
                    matches.add(m.group());
                    System.out.println("regex result: " + matches.toString());
                }
            }
        }
        return matches;
    }
}
