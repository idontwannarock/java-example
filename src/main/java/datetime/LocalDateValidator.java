package datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateValidator implements DateValidator {

    private final DateTimeFormatter dateFormatter;

    public LocalDateValidator(DateTimeFormatter dateFormatter) {
        this.dateFormatter = dateFormatter;
    }

    @Override
    public boolean isValid(String dateString) {
        try {
            LocalDate.parse(dateString, this.dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
