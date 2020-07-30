package string.regex;

public class PunctuationRegex {

    public String filterPunctuationOrWhiteSpace(String input) {
        return input.replaceAll("\\W", "");
    }
}
