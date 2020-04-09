package main.character;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CharacterInspectorTest {

    private CharacterInspector characterInspector;

    @Before
    public void init() {
        this.characterInspector = new CharacterInspector();
    }

    @Test
    public void testWordFromDb() {
        // arrange
        String input = "七贤分行";
        int expected = 4;

        // action
        int actual = characterInspector.count(input);

        // assert
        assertEquals(expected, actual);
    }

    @Test
    public void testWordFromGoogleTranslate() {
        // arrange
        String input = "七贤分行";
        int expected = 4;

        // action
        int actual = characterInspector.count(input);

        // assert
        assertEquals(expected, actual);
    }
}
