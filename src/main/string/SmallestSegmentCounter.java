package string;

import static java.lang.Character.isDigit;

public class SmallestSegmentCounter {

    // 計算 question 最小單位片段總數，連續英文、連續數字、單一中文字都各自算一個最小單位片段
    // does not count spaces
    public int countSmallestSegments(String question) {
        int count = 0;
        for (int index = 0; index < question.length(); index++) {
            if (!isAlphabet(question.charAt(index)) && !isDigit(question.charAt(index))) {
                if (question.charAt(index) != ' ') {
                    count++;
                }
            } else if (isAlphabet(question.charAt(index))) {
                for (int i = index; i < question.length(); i++) {
                    if (!isAlphabet(question.charAt(i))) {
                        count++;
                        index = i - 1;
                        break;
                    }
                }
            } else {
                for (int i = index; i < question.length(); i++) {
                    if (!isDigit(question.charAt(i))) {
                        count++;
                        index = i - 1;
                        break;
                    }
                }
            }
        }
        return count;
    }

    private boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
