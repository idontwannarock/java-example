package string.regex;

import org.junit.Test;
import string.regex.PunctuationRegex;

import static org.assertj.core.api.Assertions.assertThat;

public class PunctuationRegexTest {

    private PunctuationRegex regex;

    @Test
    public void shouldReturnExpected() {
        // arrange
        String input = "2018 年銷售數量和庫存數量分析。";
        String expected = "2018年銷售數量和庫存數量分析";
        regex = new PunctuationRegex();
        // action
        String actual = regex.filterPunctuationOrWhiteSpace(input);
        // assert
        assertThat(actual).isEqualTo(expected);
    }
}
