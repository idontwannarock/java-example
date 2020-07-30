package string;

import org.junit.Before;
import org.junit.Test;
import string.HexStringConverter;

import static org.junit.Assert.assertEquals;

public class HexStringConverterTest {

    private HexStringConverter converter;

    @Before
    public void init() {
        this.converter = new HexStringConverter();
    }

    @Test
    public void testBackspaceCharacter() {
        // arrange
        String input = "\b";
        String expected = "\\u0008";

        // act
        String actual = converter.convert(input);

        // assert
        assertEquals(expected, actual);
    }
}
