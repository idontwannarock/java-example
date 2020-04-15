package character;

public class CharacterValidator {

    public boolean isAlphabetic(String input) {
        return Character.isAlphabetic(input.charAt(0));
    }

    public boolean isNumeric(String input) {
        return Character.isDigit(input.charAt(0));
    }

    public boolean isLetterOrDigit(String input) {
        return Character.isLetterOrDigit(input.charAt(0));
    }

    public boolean isWhiteSpace(String input) {
        return Character.isWhitespace(input.charAt(0));
    }
}
