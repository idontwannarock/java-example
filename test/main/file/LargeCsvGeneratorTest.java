package main.file;

import main.csv.LargeCsvGenerator;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LargeCsvGeneratorTest {

    private LargeCsvGenerator largeCsvGenerator;

    @Before
    public void init() {
        this.largeCsvGenerator = new LargeCsvGenerator();
    }

    @Test
    public void testGenerate() throws IOException {
        largeCsvGenerator.generate("file/supermarket_sales.csv", "file/big.csv", 10_000);
    }
}
