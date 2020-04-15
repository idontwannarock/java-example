package datetime;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SimpleDateFormatPatternTest {

    private SimpleDateFormatPattern simpleDateFormatPattern;

    @Before
    public void init() {
        this.simpleDateFormatPattern = new SimpleDateFormatPattern();
    }

    @Test
    public void testNormalDate() throws Exception {
        // arrange
        String parsePattern = "y-M-d H:m:s.S";
        String input = "2018-11-13 01:19:56.000";
        String formatPattern = "yyyy-MM-dd HH:mm:ss.SSS";
        String expected = "2018-11-13 01:19:56.000";

        // action
        Date date = simpleDateFormatPattern.parseString(input, parsePattern);
        String actual = simpleDateFormatPattern.formatDate(date, formatPattern);

        // assert
        assertEquals(expected, actual);
        System.out.println("Actual parsed date: " + actual);
    }

    @Test
    public void testFirstTwoDigitYear() throws Exception {
        // arrange
        String parsePattern = "y-M-d";
        String input = "18-11-13";
        String formatPattern = "yyyy-MM-dd HH:mm:ss.SSS";
        String expected = "2018-11-13 00:00:00.000";

        // action
        Date date = simpleDateFormatPattern.parseString(input, parsePattern);
        String actual = simpleDateFormatPattern.formatDate(date, formatPattern);

        // assert
        assertEquals(expected, actual);
        System.out.println("Actual parsed date: " + actual);
    }

    @Test
    public void testSecondTwoDigitYear() throws Exception {
        // arrange
        String parsePattern = "y/M/d";
        String input = "2017/9/30";
        String formatPattern = "yyyy-MM-dd HH:mm:ss.SSS";
        String expected = "2017-09-30 00:00:00.000";

        // action
        Date date = simpleDateFormatPattern.parseString(input, parsePattern);
        String actual = simpleDateFormatPattern.formatDate(date, formatPattern);

        // assert
        assertEquals(expected, actual);
        System.out.println("Actual parsed date: " + actual);
    }

    @Test
    public void testYearMonth() throws Exception {
        // arrange
        String parsePattern = "y/M";
        String input = "2017/9";
        String formatPattern = "yyyy-MM-dd HH:mm:ss.SSS";
        String expected = "2017-09-01 00:00:00.000";

        // action
        Date date = simpleDateFormatPattern.parseString(input, parsePattern);
        String actual = simpleDateFormatPattern.formatDate(date, formatPattern);

        // assert
        assertEquals(expected, actual);
        System.out.println("Actual parsed date: " + actual);
    }
}
