package string.segment;

import org.junit.Before;
import org.junit.Test;
import string.segment.CombinedSegment;
import string.segment.Token;

import java.util.Set;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;
import static org.junit.Assert.assertEquals;

public class CombinedSegmentTest {

    private CombinedSegment segmenter;

    @Before
    public void init() {
        this.segmenter = new CombinedSegment();
    }

    @Test
    public void testSegmentChinese() {
        // arrange
        String sentence = "事件";
        int expected = 3;

        // action
        Set<Token> result = segmenter.segment(sentence);
        int actual = result.size();

        // assert
        System.out.println(result.stream()
                .sorted(comparingInt(Token::getStartIndex).thenComparingInt(t -> t.getWord().length()))
                .map(Token::getWord)
                .collect(joining(", ")));
        assertEquals(expected, actual);
    }

    @Test
    public void testSegmentNumeric() {
        // arrange
        String sentence = "2019";
        int expected = 1;

        // action
        Set<Token> result = segmenter.segment(sentence);
        int actual = result.size();

        // assert
        System.out.println(result.stream()
                .sorted(comparingInt(Token::getStartIndex).thenComparingInt(t -> t.getWord().length()))
                .map(Token::getWord)
                .collect(joining(", ")));
        assertEquals(expected, actual);
    }

    @Test
    public void testSegmentChineseStartNumericEnd() {
        // arrange
        String sentence = "销中2019";
        int expected = 4;

        // action
        Set<Token> result = segmenter.segment(sentence);
        int actual = result.size();

        // assert
        System.out.println(result.stream()
                .sorted(comparingInt(Token::getStartIndex).thenComparingInt(t -> t.getWord().length()))
                .map(Token::getWord)
                .collect(joining(", ")));
        assertEquals(expected, actual);
    }

    @Test
    public void testSegmentNumericStartChineseEnd() {
        // arrange
        String sentence = "30日类";
        int expected = 4;

        // action
        Set<Token> result = segmenter.segment(sentence);
        int actual = result.size();

        // assert
        System.out.println(result.stream()
                .sorted(comparingInt(Token::getStartIndex).thenComparingInt(t -> t.getWord().length()))
                .map(Token::getWord)
                .collect(joining(", ")));
        assertEquals(expected, actual);
    }

    @Test
    public void testSegmentNumericInTheMiddle() {
        // arrange
        String sentence = "销中2019年日";
        int expected = 7;

        // action
        Set<Token> result = segmenter.segment(sentence);
        int actual = result.size();

        // assert
        System.out.println(result.stream()
                .sorted(comparingInt(Token::getStartIndex).thenComparingInt(t -> t.getWord().length()))
                .map(Token::getWord)
                .collect(joining(", ")));
        assertEquals(expected, actual);
    }

    @Test
    public void testSegmentChineseInTheMiddle() {
        // arrange
        String sentence = "2019年月830";
        int expected = 5;

        // action
        Set<Token> result = segmenter.segment(sentence);
        int actual = result.size();

        // assert
        System.out.println(result.stream()
                .sorted(comparingInt(Token::getStartIndex).thenComparingInt(t -> t.getWord().length()))
                .map(Token::getWord)
                .collect(joining(", ")));
        assertEquals(expected, actual);
    }
}
