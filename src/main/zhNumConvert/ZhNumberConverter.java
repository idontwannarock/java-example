package main.zhNumConvert;

import java.util.*;

public class ZhNumberConverter {

    private static final List<String> zhNumericWords = new ArrayList<>(Arrays.asList(
            "一", "二", "两", "三", "四", "五", "六", "七", "八", "九",
            "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖",
            "貳", "参", "陸", "〇", "零",
            "十", "廿", "卅", "百", "千", "万", "亿"));

    private static Map<Character, Integer> zhDigitToArabicDigitMap = new HashMap<>();

    static {
        zhDigitToArabicDigitMap.put('一', 1);
        zhDigitToArabicDigitMap.put('二', 2);
        zhDigitToArabicDigitMap.put('两', 2);
        zhDigitToArabicDigitMap.put('三', 3);
        zhDigitToArabicDigitMap.put('四', 4);
        zhDigitToArabicDigitMap.put('五', 5);
        zhDigitToArabicDigitMap.put('六', 6);
        zhDigitToArabicDigitMap.put('七', 7);
        zhDigitToArabicDigitMap.put('八', 8);
        zhDigitToArabicDigitMap.put('九', 9);
        zhDigitToArabicDigitMap.put('壹', 1);
        zhDigitToArabicDigitMap.put('贰', 2);
        zhDigitToArabicDigitMap.put('叁', 3);
        zhDigitToArabicDigitMap.put('肆', 4);
        zhDigitToArabicDigitMap.put('伍', 5);
        zhDigitToArabicDigitMap.put('陆', 6);
        zhDigitToArabicDigitMap.put('柒', 7);
        zhDigitToArabicDigitMap.put('捌', 8);
        zhDigitToArabicDigitMap.put('玖', 9);
        zhDigitToArabicDigitMap.put('〇', 0);
        zhDigitToArabicDigitMap.put('零', 0);
    }

    /**
     * Replace all Chinese numbers with Arabic numbers in the input
     * @param input Original string
     * @return A converted string with Chinese numbers replaced with Arabic numbers
     */
    public String convert(String input) {
        StringBuilder convertedDate = new StringBuilder();
        StringBuilder numeric = new StringBuilder();
        for (String word : input.split("")) {
            if (zhNumericWords.contains(word)) {
                numeric.append(word);
                if (input.endsWith(word)) {
                    convertedDate.append(convertZhNumberString(numeric.toString()));
                }
            } else {
                if (numeric.length() > 0) {
                    convertedDate.append(convertZhNumberString(numeric.toString()));
                    numeric = new StringBuilder();
                }
                convertedDate.append(word);
            }
        }
        return convertedDate.toString();
    }

    /**
     * Convert a string of Chinese number
     * @param zhNumberString Original string of Chinese number
     * @return A numeric representation of the Chinese number
     */
    public long convertZhNumberString(String zhNumberString){
        if (isPureNumber(zhNumberString)) {
            return convertPureZhNumber(zhNumberString);
        } else {
            return convertZhNumberWithUnit(zhNumberString);
        }
    }

    /**
     * Check if input string consists only Chinese digital word,
     * which means it will be deemed as a pure number
     * only if it does not contains any of 亿万千百十廿卅
     */
    private boolean isPureNumber(String input) {
        return !input.matches(".*[亿万千百十廿卅].*");
    }

    /**
     * Convert pure Chinese number
     * @param zhNumber A string that does not contain any of 亿万千百十廿卅
     * @return A converted number in long
     */
    private long convertPureZhNumber(String zhNumber) {
        StringBuilder numericString = new StringBuilder();
        for (char c : zhNumber.toCharArray()) {
            numericString.append(zhDigitToArabicDigitMap.get(c));
        }
        return Long.parseLong(numericString.toString());
    }

    /**
     * <p>Convert Chinese number with unit like 亿万千百十廿卅</p>
     *
     * <p>First normalize the number to a certain format</p>
     * <p>Second to the conversion</p>
     * @param zhNumberString Original Chinese number with unit like 亿万千百十廿卅
     * @return A converted number in long
     */
    private long convertZhNumberWithUnit(String zhNumberString) {
        zhNumberString = normalizeZhNumber(zhNumberString);
        long result = 0;
        long resultWithHundredMillion = 0;
        int currentDigit = 0;
        for (char c : zhNumberString.toCharArray()) {
            switch (c) {
                case '亿':
                    resultWithHundredMillion = (result + currentDigit) * 1_0000_0000;
                    result = 0;
                    currentDigit = 0;
                    break;
                case '万':
                    result = (result + currentDigit) * 1_0000;
                    currentDigit = 0;
                    break;
                case '千':
                    result += currentDigit * 1000;
                    currentDigit = 0;
                    break;
                case '百':
                    result += currentDigit * 100;
                    currentDigit = 0;
                    break;
                case '十':
                    result += currentDigit * 10;
                    currentDigit = 0;
                    break;
                case '个':
                    result += currentDigit;
                    break;
                default:
                    currentDigit = zhDigitToArabicDigitMap.get(c);
            }
        }
        return result + resultWithHundredMillion;
    }

    /**
     * <p>A normalized Chinese number should be in format of:</p>
     * <p>一千一百一十一万一千一百一十一亿一千一百一十一万一千一百一十一个</p>
     *
     * <p>rule 1: "十八" should be "一十八"; same for "十万", "十亿"</p>
     * <p>rule 2: ends with "亿, 万, 千, 百, 十, 个"; "一千一" should be converted to "一千一百"</p>
     * <p>rule 3: no "零"</p>
     * <p>rule 4: "廿|卅" should be converted to "二十|三十"</p>
     * @param zhNumber Original Chinese number
     * @return A normalized Chinese number
     */
    private String normalizeZhNumber(String zhNumber) {
        // rule 1
        if (zhNumber.startsWith("十")){
            zhNumber = "一" + zhNumber;
        }
        // rule 2
        if (zhNumber.matches(".*[一二三四五六七八九]$")) {
            if (zhNumber.length() == 1) {
                // for case of "一"
                zhNumber += '个';
            } else {
                char needToCheck = zhNumber.charAt(zhNumber.length() - 2);
                if (needToCheck == '零'){
                    // for case of "一百零一"
                    zhNumber += '个';
                } else {
                    switch (needToCheck) {
                        case '萬':
                        case '万':
                            zhNumber += '千'; break;
                        case '千':
                            zhNumber += '百'; break;
                        case '百':
                            zhNumber += '十'; break;
                        case '十':
                            zhNumber += '个'; break;
                    }
                }
            }
        }
        // rule 3
        zhNumber = zhNumber.replaceAll("[零〇]", "");
        // rule 4
        zhNumber = zhNumber.replaceAll("廿", "二十");
        zhNumber = zhNumber.replaceAll("卅", "三十");
        return zhNumber;
    }
}
