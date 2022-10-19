package file.csv;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

@Slf4j
public class OpenCsvParserTest {

    private OpenCsvParser parser;

    private final Gson gson = new GsonBuilder().serializeNulls().create();
    private final TypeToken<List<List<String>>> typeToken = new TypeToken<>() {};

    @Before
    public void init() {
        this.parser = new OpenCsvParser();
    }

    @Test
    public void test() {
        // arrange
        List<String> firstRow = new ArrayList<>();
        firstRow.add("");
        firstRow.add("null");
        firstRow.add("2020");
        List<String> secondRow = new ArrayList<>();
        secondRow.add("");
        secondRow.add("null");
        secondRow.add(" ");
        List<List<String>> rows = new ArrayList<>();
        rows.add(firstRow);
        rows.add(secondRow);
        String expectedJson = gson.toJson(rows);
        System.out.println("Expected json: " + expectedJson);
        List<List<String>> expected = gson.fromJson(expectedJson, typeToken.getType());
        System.out.println("Expected: " + expected);
        System.out.println("Is null null: "  + (expected.get(0).get(0) == null));
        System.out.println("Is null String \"null\" String: "  + (expected.get(0).get(1).equals("null")));
        System.out.println();

        // action
        List<List<String>> parsedRow = parser.parse("test/resources/csv/test_null_date.csv", 1, 3);
        String actualJson = gson.toJson(parsedRow);
        System.out.println("Actual json: " + actualJson);
        List<List<String>> actual = gson.fromJson(actualJson, typeToken.getType());
        System.out.println("Actual: " + actual);
        System.out.println("Is null null: "  + (actual.get(0).get(0) == null));
        System.out.println("Is null String \"null\" String: "  + (actual.get(0).get(1).equals("null")));
        System.out.println();

        // assert
        assertThat(parsedRow, is(rows));
        assertThat(actualJson, is(expectedJson));
        assertThat(actual, is(expected));
    }

    @Test
    public void test_file_read_rows() {
        // arrange
        String filePath = "/Users/wangchenghao/Downloads/solar_demo_station_ten_m.csv";
        int skipLines = 1;
        int cellTotal = 28;
        long expected = 10_001_728L;
        // act
        long start = System.currentTimeMillis();
        long actual = parser.countParsedRows(filePath, skipLines, cellTotal);
        log.info("OpenCsv parse {} with no format costs {} milliseconds", filePath, System.currentTimeMillis() - start);
        // assert
        assertEquals(expected, actual);
    }
}
