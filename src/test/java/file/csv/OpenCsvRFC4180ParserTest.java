package file.csv;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class OpenCsvRFC4180ParserTest {

    private OpenCsvRFC4180Parser parser;

    @Before
    public void init() {
        this.parser = new OpenCsvRFC4180Parser();
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
        log.info("OpenCsv parse {} with RFC4180 costs {} milliseconds", filePath, System.currentTimeMillis() - start);
        // assert
        assertEquals(expected, actual);
    }
}
