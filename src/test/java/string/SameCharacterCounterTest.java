package string;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SameCharacterCounterTest {

    private SameCharacterCounter counter;

    @Before
    public void init() {
        this.counter = new SameCharacterCounter();
    }

    @Test
    public void testFourSameCharacters() {
        // arrange
        String first = "ababcdefg";
        String second = "abcd";
        int expected = 4;

        // act
        int actual = counter.count(first, second);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testThreeSameCharacters() {
        // arrange
        String first = "abcabcdefg";
        String second = "abc";
        int expected = 3;

        // act

        // assert
    }
}
