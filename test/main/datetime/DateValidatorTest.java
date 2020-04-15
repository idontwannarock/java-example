package datetime;

import org.junit.Test;

import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateValidatorTest {

    private DateValidator validator;

    @Test
    public void simpleDateFormatReturnValid() {
        // arrange
        String validInput = "20180930";
        validator = new SimpleDateFormatValidator("yyyyMMdd");
        // action
        boolean isValid = validator.isValid(validInput);
        // assert
        assertTrue(isValid);
    }

    @Test
    public void simpleDateFormatReturnInvalid() {
        // arrange
        String invalidInput = "2018930";
        validator = new SimpleDateFormatValidator("yyyyMMdd");
        // action
        boolean isValid = validator.isValid(invalidInput);
        // assert
        assertFalse(isValid);
    }

    @Test
    public void localDateShouldReturnValid() {
        // arrange
        validator = new LocalDateValidator(DateTimeFormatter.BASIC_ISO_DATE);
        // action
        // assert
        assertTrue(validator.isValid("20190228"));
        assertTrue(validator.isValid("2019930"));
    }

    @Test
    public void localDateShouldReturnInvalid() {
        // arrange
        String invalidInput = "20190230";
        validator = new LocalDateValidator(DateTimeFormatter.BASIC_ISO_DATE);
        // action
        boolean isValid = validator.isValid(invalidInput);
        // assert
        assertFalse(isValid);
    }
}
