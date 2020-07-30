package string;

import org.junit.Before;
import org.junit.Test;
import string.ZhStringSplitter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class StringSplitterTest {

    private ZhStringSplitter splitter;

    @Before
    public void init() {
        this.splitter = new ZhStringSplitter();
    }

    @Test
    public void testAllZhCharacter() {
        // arrange
        String input = "今天我們二十八年過後的水果是不是好的";
        int expected = 131072;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testSplitAllZhCharacterBySection() {
        // arrange
        //String input = "今天我們二十八年過後的水果是不是好的";
        int expected = 256;
        String firstInput = "今天";
        String secondInput = "我們二十八年過後的水果";
        String lastInput = "是不是好的";

        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> first = splitter.split(firstInput, true);
        String[] secondSegment = new String[] {secondInput};
        TreeSet<String[]> second = new TreeSet<>(ZhStringSplitter.STRING_ARRAY_COMPARATOR);
        second.add(secondSegment);
        TreeSet<String[]> third = splitter.split(lastInput, true);
        List<TreeSet<String[]>> sections = new ArrayList<>();
        sections.add(first);
        sections.add(second);
        sections.add(third);
        TreeSet<String[]> actual = splitter.join(sections, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);

        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testAllNumeric() {
        // arrange
        String input = "1998";
        int expected = 1;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testAllAlphabet() {
        // arrange
        String input = "abc";
        int expected = 1;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testAllAlphanumeric() {
        // arrange
        String input = "1998abc";
        int expected = 2;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testAlphanumericWithSpaces() {
        // arrange
        String input = "1998 1994 abc 1997 cde 1003";
        int expected = 144;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testSplitAlphanumericWithSpacesBySection() {
        // arrange
        //String input = "1998 1994 abc 1997 cde 1003";
        int expected = 55;
        String firstInput = "1998 1994";
        String secondInput = "abc 1997";
        String thirdInput = "cde 1003";
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> first = splitter.split(firstInput, true);
        String[] secondSegment = new String[] {secondInput};
        TreeSet<String[]> second = new TreeSet<>(ZhStringSplitter.STRING_ARRAY_COMPARATOR);
        second.add(secondSegment);
        TreeSet<String[]> third = splitter.split(thirdInput, true);
        List<TreeSet<String[]>> treeSets = new ArrayList<>();
        treeSets.add(first);
        treeSets.add(second);
        treeSets.add(third);
        TreeSet<String[]> actual = splitter.join(treeSets, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testComplexString() {
        // arrange
        String input = "2019年SlQG-35256012的概況";
        int expected = 128;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testComplexStringWithSpaces() {
        // arrange
        String input = "Customer Age 大於10的Revenue";
        int expected = 128;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testSplitComplexStringWithSpacesBySection() {
        // arrange
        //String input = "Customer Age 大於10的Revenue";
        int expected = 8;
        String firstInput = "Customer Age";
        String secondInput = " 大於10的";
        String thirdInput = "Revenue";
        // act
        long currentTime = System.currentTimeMillis();
        String[] firstSegment = new String[] {firstInput};
        TreeSet<String[]> first = new TreeSet<>(ZhStringSplitter.STRING_ARRAY_COMPARATOR);
        first.add(firstSegment);
        TreeSet<String[]> second = splitter.split(secondInput, true);
        String[] thirdSegment = new String[] {thirdInput};
        TreeSet<String[]> third = new TreeSet<>(ZhStringSplitter.STRING_ARRAY_COMPARATOR);
        third.add(thirdSegment);
        List<TreeSet<String[]>> treeSets = new ArrayList<>();
        treeSets.add(first);
        treeSets.add(second);
        treeSets.add(third);
        TreeSet<String[]> actual = splitter.join(treeSets, false);
        long finalTime = System.currentTimeMillis() - currentTime;
        for (String[] combination : actual) {
            System.out.println(Arrays.toString(combination));
        }
        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }
}
