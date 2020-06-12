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
        String fileName = "./test_encode.csv";
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

    @Test
    public void testDecodeHexString() {
        // arrange
        // 伺服器主體
//        String orig = "\\xE4\\xBC\\xBA\\xE6\\x9C\\x8D\\xE5\\x99\\xA8\\xE4\\xB8\\xBB\\xE9\\xAB\\x94";
        // 在目前的安全性內容下無法存取資料庫
        String orig = "\\xE5\\x9C\\xA8\\xE7\\x9B\\xAE\\xE5\\x89\\x8D\\xE7\\x9A\\x84\\xE5\\xAE\\x89\\xE5\\x85\\xA8\\xE6\\x80\\xA7\\xE5\\x85\\xA7\\xE5\\xAE\\xB9\\xE4\\xB8\\x8B\\xE7\\x84\\xA1\\xE6\\xB3\\x95\\xE5\\xAD\\x98\\xE5\\x8F\\x96\\xE8\\xB3\\x87\\xE6\\x96\\x99\\xE5\\xBA\\xAB";
        // action
        detector.decodeHexString(orig);
    }
}
