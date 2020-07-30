package string.regex;

import org.junit.Before;
import org.junit.Test;
import string.regex.RegexPrinter;

import java.util.List;

import static java.util.regex.Pattern.CASE_INSENSITIVE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class RegexPrinterTest {

    private RegexPrinter printer;

    @Before
    public void init() {
        this.printer = new RegexPrinter();
    }

    @Test
    public void testPrint() {
        // arrange
        String input = "每秒";
        String patternString = "每一?个?(?:毫?秒|[分时時季月週周日天年]|小时|分钟)";

        // act
        List<String> matches = printer.printAllMatches(input, patternString, CASE_INSENSITIVE);

        // assert
        assertNotNull(matches);
        assertEquals(0, matches.size());
    }

    @Test
    public void testWithGroupName() {
        // arrange
        String input = "19年";
        String patternString = "^" +
                "(?:(?:19|20)\\d{2} ?|(?:一九|二零)(?:[一二三四五六七八九零]{2}))年" +
                "(?: ?(?:0?[1-9]|1[012]) ?|一?十[一二]|[一二三四五六七八九十])月" +
                "(?: ?(?:[0]?[1-9]|[12][0-9]|3[01]) ?|[一二三四五六七八九十]|(?:[一二]?十|廿)[一二三四五六七八九]|(?:三十|卅[一]?))[日號号]" +
                "(?: ?(?:[01]?[1-9]|2[0-3]|0) ?|[零一二三四五六七八九十]|一?十[一二三四五六七八九]|(?:二十|廿)[一二三]?)[点时點時]" +
                "$";

        // act
        List<String> matches = printer.printAllMatches(input, patternString, CASE_INSENSITIVE);
        System.out.println(matches);

        // assert
        assertNotNull(matches);
        assertEquals(0, matches.size());
    }

    @Test
    public void testWithGroup() {
        // arrange
        String input = "2019年2月1日12时12分59秒";
        String patternString = "(" +
                "(?:(?<year>\\d{1,4})年)?(?:的?)" +
                "(?:第?(?<quarter>\\d)季)?(?:的?)" +
                "(?:(?<month>\\d{1,2})月(?:的?)(?:第?(?<mweek>\\d)[周週])?)?" +
                "(?:第?(?<week>\\d{1,2})[周週])?(?:的?)" +
                "(?:(?<day>\\d{1,2})[日号])?" +
                "(?:(?<hour>\\d{1,2})[点时點時])?" +
                "(?:(?<minute>\\d{1,2})分)?" +
                "(?:(?<second>\\d{1,2})秒)?" +
                "(?:(?<millisecond>\\d{1,3})毫秒)?" +
                ")";

        // act
        List<String> matches = printer.printAllMatches(input, patternString, CASE_INSENSITIVE);
        System.out.println(matches);

        // assert
        assertNotNull(matches);
        assertEquals(0, matches.size());
    }
}
