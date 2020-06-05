package csv;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class LargeCsvGeneratorTest {

    private LargeCsvGenerator largeCsvGenerator;

    @Before
    public void init() {
        this.largeCsvGenerator = new LargeCsvGenerator();
    }

    @Test
    public void testGenerate() throws IOException {
        // arrange
        String fromFilePath = "/Users/wangchenghao/Desktop/選擇欄位測試.csv";
        String toFilePath = "/Users/wangchenghao/Desktop/1m_8cols.csv";
        long copyLinesPerRow = 3_000;
        // action
        largeCsvGenerator.generate(fromFilePath, toFilePath, copyLinesPerRow);
        // assert
        File fromFile = new File(fromFilePath);
        System.out.println("from file size: " + fromFile.length() + " byte");
        File toFile = new File(toFilePath);
        assertTrue(toFile.exists());
        System.out.println("to file size: " + toFile.length() + " byte");
        assertThat(toFile.length(), greaterThan(24_000L));
    }
}
