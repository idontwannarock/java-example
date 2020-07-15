package regex;

import org.junit.Before;
import org.junit.Test;

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
}
