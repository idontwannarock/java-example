package file.csv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import file.csv.CsvRFC4180Parser;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CsvRFC4180ParserTest {

    private CsvRFC4180Parser csvRFC4180Parser;

    private final Gson gson = new GsonBuilder().serializeNulls().create();
    private final TypeToken<List<List<String>>> typeToken = new TypeToken<>() {};

    @Before
    public void init() {
        this.csvRFC4180Parser = new CsvRFC4180Parser();
    }

    @Test
    public void test() {
        // arrange
        // action
        List<List<String>> parsedRow = csvRFC4180Parser.parse("test/resources/csv/test_rfc_4180.csv", 0, 5);
        // assert
        System.out.println(parsedRow);
    }
}
