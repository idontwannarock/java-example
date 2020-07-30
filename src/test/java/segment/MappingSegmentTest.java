package segment;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static segment.SymbolType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class MappingSegmentTest {

    private MappingSegment segment;

    @Before
    public void init() {
        segment = new MappingSegment();
    }

    @Test
    public void shouldReturnExpectedList() {
        // arrange
        String sentence = "~!@#美國和中國家庭的總收入比較#@!~";

        Token unknown1 = new Token();
        unknown1.setWord("~!@#");
        unknown1.setType(Unknown);
        Token us =       new Token();
        us.setWord("美國");
        us.setType(DVFilter);
        Token and =      new Token();
        and.setWord("和");
        and.setType(Conjunction);
        Token china =    new Token();
        china.setWord("中國");
        china.setType(DVFilter);
        Token country =  new Token();
        country.setWord("國家");
        country.setType(DataColumn);
        Token yard =     new Token();
        yard.setWord("庭");
        yard.setType(Unknown);
        Token is =       new Token();
        is.setWord("的");
        is.setType(Conjunction);
        Token total =    new Token();
        total.setWord("總");
        total.setType(Count);
        Token income =   new Token();
        income.setWord("收入");
        income.setType(DataColumn);
        Token compare =  new Token();
        compare.setWord("比較");
        compare.setType(Comparison);
        Token unknown2 = new Token();
        unknown2.setWord("#@!~");
        unknown2.setType(Unknown);
        List<Token> expected = Arrays.asList(
                unknown1, us, and, china, country, yard, is, total, income, compare, unknown2);

        // action
        segment.addTokenList(Arrays.asList("國家", "收入"), DataColumn);
        segment.addTokenList(Arrays.asList("美國", "中國"), DVFilter);
        segment.addTokenList(Arrays.asList("總"), Count);
        segment.addTokenList(Arrays.asList("比較"), Comparison);
        segment.addTokenList(Arrays.asList("的", "和"), Conjunction);
        List<Token> actual = segment.segment(sentence);

        // assert
        assertEquals(expected.size(), actual.size());
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
