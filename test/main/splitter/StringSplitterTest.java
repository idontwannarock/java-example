package splitter;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testSplitAllZhCharacterBySection() {
        // arrange
        //String input = "今天我們二十八年過後的水果是不是好的";
        int expected = 32;
        String firstInput = "今天";
        String secondInput = "我們二十八年過後的水果";
        String lastInput = "是不是好的";

        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> first = splitter.split(firstInput, true);
        String[] secondCombination = new String[1];
        secondCombination[0] = secondInput;
        TreeSet<String[]> second = new TreeSet<>(ZhStringSplitter.STRING_ARRAY_COMPARATOR);
        second.add(secondCombination);
        TreeSet<String[]> third = splitter.split(lastInput, true);
        List<TreeSet<String[]>> sections = new ArrayList<>();
        sections.add(first);
        sections.add(second);
        sections.add(third);
        TreeSet<String[]> actual = splitter.join(sections, false);
        long finalTime = System.currentTimeMillis() - currentTime;
//        for (String[] combination : actual) {
//            System.out.println(Arrays.toString(combination));
//        }
//        System.out.println("Total splits: " + actual.size());
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
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testAlphanumericWithSpaces() {
        // arrange
        String input = "1998 1994 abc 1997 cde 1003";
        int expected = 32;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
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
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }

    @Test
    public void testComplexStringWithSpaces() {
        // arrange
//        String input = "1998一二三 mno 1997 三 abc1994xyz四五";
//        int expected = 2048;
        String input = "Customer Age 大於10的Revenue";
        int expected = 64;
        // act
        long currentTime = System.currentTimeMillis();
        TreeSet<String[]> actual = splitter.split(input, true);
        long finalTime = System.currentTimeMillis() - currentTime;
//        System.out.println("Total splits: " + actual.size());
        System.out.println("Total time taken: " + finalTime);
        // assert
        assertEquals(expected, actual.size());
    }
}
