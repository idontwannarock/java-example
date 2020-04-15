package zhNumConvert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ZhDateConverterTest {

    private String input;
    private String expected;

    private ZhNumberConverter converter;

    public ZhDateConverterTest(String input, String expected) {
        this.input = input;
        this.expected = expected;
    }

    @Parameterized.Parameters
    public static Collection<Object> primeNumbers() {
        return Arrays.asList(new Object[][] {
                {"二千零一十九", "2019"},
                {"二零一九", "2019"},
                {"二零一九年", "2019年"},
                {"二零一九年第一季", "2019年第1季"},
                {"二零一九年第1季", "2019年第1季"},
                {"第一季", "第1季"},
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
        String actual = converter.convert(input);
        System.out.println("Actual: " + actual);
        // assert
        assertEquals(expected, actual);
        System.out.println();
    }
}
