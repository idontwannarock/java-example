package main.charset;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CharsetDetectorTest {

    private CharsetDetector detector;

    @Before
    public void init() {
        this.detector = new CharsetDetector();
    }

    @Test
    public void shouldReturnBig5() {
        // arrange
        String fileName = "/Users/wangchenghao/Downloads/原物料進銷存10701-09.csv";
        List<String> expected = new ArrayList<>();
        expected.add("Big5");

        // action
        List<String> actual = detector.detect(fileName);
        System.out.println(actual);

        // assert
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @Test
    public void shouldReturnUtf8() {
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
}
