package excel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XlsParserTest {

    private XlsParser xlsParser;

    private final Gson gson = new GsonBuilder().serializeNulls().create();
    private final TypeToken<List<List<String>>> typeToken = new TypeToken<List<List<String>>>() {};

    @Before
    public void init() {
        this.xlsParser = new XlsParser();
    }

    @Test
    public void test() {
        // arrange
        List<String> header = new ArrayList<>();
        header.add("c0");
        header.add("c1");
        header.add("c2");
        header.add("c3");
        header.add("c4");
        header.add("c5");
        List<String> row = new ArrayList<>();
        row.add(null);
        row.add("null");
        row.add("2020");
        row.add("2020-03-01 00:00:00.000");
        row.add("2020-03-01 12:00:00.000");
        row.add("2020-03-01 12:00:00.000");
        List<List<String>> rows = new ArrayList<>();
//        rows.add(header);
        rows.add(row);
        String expectedJson = gson.toJson(rows);
        System.out.println("Expected json: " + expectedJson);
        List<List<String>> expected = gson.fromJson(expectedJson, typeToken.getType());
        System.out.println("Expected: " + expected);
        System.out.println("Is null null: "  + (expected.get(0).get(0) == null));
        System.out.println("Is null String \"null\" String: "  + (expected.get(0).get(1).equals("null")));
        System.out.println();

        // action
        List<List<String>> parsedRow = xlsParser.parse("test/resources/excel/test_null_date.xls", 1, 6);
        String actualJson = gson.toJson(parsedRow);
        System.out.println("Actual json: " + actualJson);
        List<List<String>> actual = gson.fromJson(actualJson, typeToken.getType());
        System.out.println("Actual: " + actual);

        // assert
        assertThat(parsedRow, is(rows));
        assertThat(actualJson, is(expectedJson));
        assertThat(actual, is(expected));
    }
}
