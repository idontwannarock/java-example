package main.regex;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class OverlappingRegexTest {

    @Test
    public void shouldReturnTwoMatches() {
        // arrange
        String pattern = "(?:12|13)[0-9]{2}";
        String input = "1201314";
        Set<String> expectedMatches = new LinkedHashSet<>(Arrays.asList("1201", "1314"));
        OverlappingRegex overlappingRegex = new OverlappingRegex();
        // action
        Set<String> substringMatches = overlappingRegex.findBySubstring(pattern, input);
        Set<String> flexPatternMatches = new LinkedHashSet<>(overlappingRegex.findByFlexPattern(pattern, input));
        // assert
        assertEquals(expectedMatches.size(), substringMatches.size());
        assertEquals(expectedMatches, substringMatches);
        assertEquals(expectedMatches.size(), flexPatternMatches.size());
        assertEquals(expectedMatches, flexPatternMatches);
    }
}
