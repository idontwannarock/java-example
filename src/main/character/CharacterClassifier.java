package character;

import org.apache.commons.lang3.StringUtils;

public class CharacterClassifier {

    public static void main(String[] args) {
        String input = "趋势";
        System.out.println(StringUtils.isAlphanumeric(input));
        System.out.println(StringUtils.isAlpha(input));
    }
}
