package datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Date;

public class DateTimeFormatterPattern {

    public Date parseString(String dateString, String pattern) {
        Date result = null;

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                .toFormatter();
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
            result = Date.from(localDateTime.atZone(ZoneId.of("UTC")).toInstant());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            System.out.println("Parsing exception using pattern " + formatter.toString());
        }
        return result;
    }

    public String formatDate(Date date, String pattern) {
        String result = null;

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern(pattern)
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                .parseDefaulting(ChronoField.SECOND_OF_MINUTE, 0)
                .parseDefaulting(ChronoField.MILLI_OF_SECOND, 0)
                .toFormatter();
        try {
            Instant instant = date.toInstant();
            LocalDateTime ldt = instant.atOffset(ZoneOffset.UTC).toLocalDateTime();
            result = ldt.format(formatter);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            System.out.println("Format exception using pattern " + formatter.toString());
        }

        return result;
    }
}
