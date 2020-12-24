package file.csv;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@Slf4j
public class UnivocityCsvParserTest {

    private final UnivocityCsvParser parser = new UnivocityCsvParser();

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
        log.info("Univocity parse {} with no format costs {} milliseconds", filePath, System.currentTimeMillis() - start);
        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void test_small_file_read_rows() {
        // arrange
        String filePath = "/Users/wangchenghao/Desktop/train.csv";
        int skipLines = 1;
        int cellTotal = 12;
        long expected = 891;
        // act
        long start = System.currentTimeMillis();
        long actual = parser.countParsedRows(filePath, skipLines, cellTotal);
        log.info("Univocity parse {} with no format costs {} milliseconds", filePath, System.currentTimeMillis() - start);
        // assert
        assertEquals(expected, actual);
    }
}
