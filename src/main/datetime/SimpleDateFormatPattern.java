package main.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateFormatPattern {

    public Date parseString(String dateString, String pattern) throws Exception {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.parse(dateString);
    }

    public String formatDate(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }
}
