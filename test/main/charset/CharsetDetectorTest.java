package charset;

import org.junit.Before;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class CharsetDetectorTest {

    private CharsetDetector detector;

    @Before
    public void init() {
        this.detector = new CharsetDetector();
    }

    @Test
    public void testBig5File() {
        // arrange
        String fileName = "/Users/wangchenghao/Downloads/華泰電子_故障頻率分析.csv";
        List<String> expected = new ArrayList<>();
        expected.add("Big5");

        // action
        List<String> actual = detector.detect(fileName);
        System.out.println(actual);

        // assert
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testUtf8File() {
        // arrange
        String fileName = "/Users/wangchenghao/Downloads/華泰_Run_Ratio.csv";
        List<String> expected = new ArrayList<>();
        expected.add("utf-8");

        // action
        List<String> actual = detector.detect(fileName);
        System.out.println(actual);

        // assert
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void testBig5String() throws UnsupportedEncodingException {
        // arrange
        String input = "$";
        String expected = "Big5";

        // action
        String actual = detector.charset(input, new String[]{"Big5"});

        // assert
        assertEquals(expected, actual);
    }
}
