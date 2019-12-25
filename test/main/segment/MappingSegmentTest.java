package main.segment;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static main.segment.SymbolType.*;
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

        Token unknown1 = new Token().word("~!@#").type(Unknown);
        Token us =       new Token().word("美國").type(DVFilter);
        Token and =      new Token().word("和").type(Conjunction);
        Token china =    new Token().word("中國").type(DVFilter);
        Token country =  new Token().word("國家").type(DataColumn);
        Token yard =     new Token().word("庭").type(Unknown);
        Token is =       new Token().word("的").type(Conjunction);
        Token total =    new Token().word("總").type(Count);
        Token income =   new Token().word("收入").type(DataColumn);
        Token compare =  new Token().word("比較").type(Comparison);
        Token unknown2 = new Token().word("#@!~").type(Unknown);
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
