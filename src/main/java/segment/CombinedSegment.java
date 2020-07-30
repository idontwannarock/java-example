package segment;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.stream.Collectors.toSet;

public class CombinedSegment {

    private static final Pattern sentenceSeparation =
            Pattern.compile("(?<alphanumeric>[A-Za-z0-9 _-]+)|(?<chinese>[^A-Za-z0-9 _-]+)");

    /**
     * 切分時遇到 數字|英文字母|空白|橫線|底線 會合併在一起當成一個 word 做辨認
     */
    public Set<Token> segment(String sentence) {
        Set<Token> unidentified = new HashSet<>();

        // 1. 分隔出各段中文字或英數字
        Set<Token> parts = separateChineseOrAlphanumericSentence(sentence);
        // 2. 切分中文字段
        unidentified.addAll(parts.stream()
                .filter(part -> !isAlphanumericOrBlankOrUnderScoreOrHyphen(part.getWord()))
                .map(part -> segmentChineseSentence(part.getWord(), part.getStartIndex()))
                .flatMap(Set::stream)
                .collect(toSet()));
        // 3. 切分英數字字段
        unidentified.addAll(parts.stream()
                .filter(part -> isAlphanumericOrBlankOrUnderScoreOrHyphen(part.getWord()))
                .map(part -> segmentAlphanumericSentence(part.getWord(), part.getStartIndex()))
                .flatMap(Set::stream)
                .collect(toSet()));

        return unidentified;
    }

    private Set<Token> separateChineseOrAlphanumericSentence(String sentence) {
        Set<Token> parts = new HashSet<>();

        Matcher matcher = sentenceSeparation.matcher(sentence);
        while (matcher.find()) {
            if (isNotEmpty(matcher.group())) {
                if (isNotEmpty(matcher.group("alphanumeric"))) {
                    parts.add(new Token(matcher.group("alphanumeric"), matcher.start("alphanumeric")));
                } else {
                    parts.add(new Token(matcher.group("chinese"), matcher.start("chinese")));
                }
            }
        }

        return parts;
    }

    private Set<Token> segmentChineseSentence(String sentence, int startIndex) {
        Set<Token> tokens = new HashSet<>();

        for (int start = 0; start < sentence.length(); start++) {
            for (int end = start + 1; end <= sentence.length(); end++) {
                tokens.add(new Token(sentence.substring(start, end), startIndex + start));
            }
        }

        return tokens;
    }

    private Set<Token> segmentAlphanumericSentence(String sentence, int startIndex) {
        Set<Token> tokens = new HashSet<>();

        tokens.add(new Token(sentence.trim(), startIndex + sentence.indexOf(sentence.trim())));

        for (String part : sentence.split(" ")) {
            tokens.add(new Token(part, startIndex + sentence.indexOf(part)));
        }

        return tokens;
    }

    private boolean isAlphanumericOrBlankOrUnderScoreOrHyphen(String input) {
        if (input != null) {
            return input.matches("[A-Za-z0-9 _-]+");
        } else {
            return false;
        }
    }

    private boolean isNotEmpty(String input) {
        return input != null && input.length() > 0;
    }
}
