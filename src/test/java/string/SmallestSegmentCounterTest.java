package string;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SmallestSegmentCounterTest {

    private SmallestSegmentCounter counter;

    @Before
    public void init() {
        this.counter = new SmallestSegmentCounter();
    }

    @Test
    public void test() {
        // arrange
        String input = "Profit小於324的Mountain Bikes和Bottles and Cages和Jerseys跟Bottles and Cages和Caps和Fenders的Revenue最小值的狀況";
        int expected = 29;
        // act
        int actual = counter.countSmallestSegments(input);
        // assert
        assertEquals(expected, actual);
    }
}
