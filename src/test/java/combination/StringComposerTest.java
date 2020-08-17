package combination;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StringComposerTest {

    private StringComposer composer;

    @Before
    public void init() {
        this.composer = new StringComposer();
    }

    @Test
    public void test() {
        // arrange
        String[] combination = new String[] {"1", "2", "3", "4"};
        List<String> first = List.of("a1", "a2", "a3");
        List<String> second = List.of("b1", "b2");
        List<String> third = List.of("c1");
        List<String> forth = List.of("d1", "d2", "d3", "d4");
        List<String>[] mappings = new List[] {first, second, third, forth};

        // act
        List<String[]> sentences = composer.compose(combination, mappings);
        for (int index = 0; index < sentences.size(); index++) {
            System.out.print((index + 1) + ": ");
            System.out.println(Arrays.toString(sentences.get(index)));

        }

        // assert

    }
}
