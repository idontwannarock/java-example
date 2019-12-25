package main.datetime;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>若設定 lenient 為 false，則必須嚴格按照 format，否則跳 ParseException</p>
 * <p>若設定 lenient 為 true，則可能 parse 日期不正確</p>
 */
public class SimpleDateFormatValidator implements DateValidator {

    private String dateFormat;

    public SimpleDateFormatValidator(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    @Override
    public boolean isValid(String input) {
        DateFormat df = new SimpleDateFormat(dateFormat);
        df.setLenient(false);
        try {
            Date date = df.parse(input);
            System.out.println(date.toString());
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}
