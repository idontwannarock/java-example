package string;

import org.apache.commons.lang3.StringUtils;

public class HexStringConverter {

    public String convert(String input) {
        input = input.replaceAll("\b", "");
        StringBuilder hex = new StringBuilder();
        for (int index = 0; index < input.length(); index++) {
            hex.append(Integer.toHexString(Character.codePointAt(input.toCharArray(), index)));
        }
        String hexString = hex.toString();
        return "\\u" + StringUtils.leftPad(hexString, 4, "0");
    }

    public static void main(String[] args) {
        System.out.println(new HexStringConverter().convert("\b"));
    }
}
