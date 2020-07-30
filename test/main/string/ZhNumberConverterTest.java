package string;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import string.ZhNumberConverter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ZhNumberConverterTest {

    private String input;
    private long expected;

    private ZhNumberConverter converter;

    public ZhNumberConverterTest(String input, long expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object> primeNumbers() {
        return Arrays.asList(new Object[][] {
                {"零", 0},
                {"十", 10},
                {"廿", 20},
                {"卅", 30},
                {"十八", 18},
                {"一十八", 18},
                {"十万", 100000},
                {"十亿", 1000000000},
                {"二千零一十九", 2019},
                {"二零一九", 2019}
        });
    }

    @Before
    public void init() {
        this.converter = new ZhNumberConverter();
    }

    @Test
    public void tests() {
        // arrange
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        // action
        long actual = converter.convertZhNumberString(input);
        System.out.println("Actual: " + actual);
        // assert
        assertEquals(expected, actual);
        System.out.println();
    }
}
