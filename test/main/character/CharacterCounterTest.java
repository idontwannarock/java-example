package character;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterCounterTest {

    private CharacterCounter characterCounter;

    @Before
    public void init() {
        this.characterCounter = new CharacterCounter();
    }

    @Test
    public void testWordFromDb() {
        // arrange
        String input = "$";
        int expected = 1;

        // action
        int actual = characterCounter.count(input);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testWordFromGoogleTranslate() {
        // arrange
        String input = "七贤分行";
        int expected = 4;

        // action
        int actual = characterCounter.count(input);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testInvisibleWord() {
        // arrange
        String input = "\b";
        int expected = 1;

        // act
        int actual = characterCounter.count(input);

        // assert
        assertEquals(expected, actual);
    }
}
