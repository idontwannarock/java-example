package datetime;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class DateTimeFormatterPatternTest {

    private DateTimeFormatterPattern dateTimeFormatterPattern;

    @Before
    public void init() {
        this.dateTimeFormatterPattern = new DateTimeFormatterPattern();
    }

    @Test
    public void testYearDashMonth() {
        // arrange
        String parsePattern = "u-M";
        String input = "2018-11";
        String formatPattern = "uuuu-MM-dd HH:mm:ss.SSS";
        String expected = "2018-11-01 00:00:00.000";

        // action
        Date date = dateTimeFormatterPattern.parseString(input, parsePattern);
        String actual = dateTimeFormatterPattern.formatDate(date, formatPattern);

        // assert
        System.out.println("Actual parsed date: " + actual);
        assertEquals(expected, actual);
    }

    @Test
    public void testYearOfEraDashMonth() {
        // arrange
        String parsePattern = "yy-M";
        String input = "18-11";
        String formatPattern = "uuuu-MM-dd HH:mm:ss.SSS";
        String expected = "2018-11-01 00:00:00.000";

        // action
        Date date = dateTimeFormatterPattern.parseString(input, parsePattern);
        String actual = dateTimeFormatterPattern.formatDate(date, formatPattern);

        // assert
        System.out.println("Actual parsed date: " + actual);
        assertEquals(expected, actual);
    }
}