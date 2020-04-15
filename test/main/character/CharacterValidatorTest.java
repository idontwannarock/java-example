package character;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CharacterValidatorTest {

    private CharacterValidator validator;

    @Before
    public void init() {
        this.validator = new CharacterValidator();
    }

    @Test
    public void shouldReturnIsAlphabetic() {
        // arrange
        String input = "好";
        System.out.println("Input: " + input);
        // action
        boolean actual = validator.isAlphabetic(input);
        System.out.println("Actual: " + actual);
        // assert
        assertTrue(actual);
        System.out.println();
    }

    @Test
    public void shouldReturnIsNumeric() {
        // arrange
        String input = "5.0";
        System.out.println("Input: " + input);
        // action
        boolean actual = validator.isNumeric(input);
        System.out.println("Actual: " + actual);
        // assert
        assertTrue(actual);
        System.out.println();
    }

    @Test
    public void shouldReturnIsLetterOrDigit() {
        // arrange
        String input = "好";
        System.out.println("Input: " + input);
        // action
        boolean actual = validator.isLetterOrDigit(input);
        System.out.println("Actual: " + actual);
        // assert
        assertTrue(actual);
        System.out.println();
    }

    @Test
    public void shouldReturnIsWhiteSpace() {
        // arrange
        String input = " ";
        System.out.println("Input: " + input);
        // action
        boolean actual = validator.isWhiteSpace(input);
        System.out.println("Actual: " + actual);
        // assert
        assertTrue(actual);
        System.out.println();
    }
}
