package string.regex;

import org.junit.Before;
import org.junit.Test;
import string.regex.NumericAlphabeticRegex;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class NumericAlphabeticRegexTest {

    private NumericAlphabeticRegex regex;

    @Before
    public void init() {
        this.regex = new NumericAlphabeticRegex();
    }

    @Test
    public void shouldReturnExpectedStrings() {
        // arrange
        String question = "2018年銷售量super useful test";
        String pattern = "\\w+";
        List<String> expected = Arrays.asList("2018", "super", "useful", "test");
        // action
        List<String> actual = regex.regex(question, pattern);
        System.out.println(actual);
        // assert
        assertEquals(expected.size(), actual.size());
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
