package string.combination;

import org.apache.commons.lang3.NotImplementedException;
import org.junit.Before;
import org.junit.Test;
import string.combination.ProperOrderSelector;
import string.combination.Token;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class ProperOrderSelectorTest {

    private ProperOrderSelector selector;

    @Before
    public void init() {
        this.selector = new ProperOrderSelector();
    }

    @Test(expected = NotImplementedException.class)
    public void shouldReturnNull() {
        // arrange
        String statement = "2018年銷售數量和庫存數量分析";

        Token twoThousandEighteenYear = Token.builder().word("2018年").build();
        Token twoThousandEighteen =     Token.builder().word("2018").build();
        Token yearSale =                Token.builder().word("年銷售").build();
        Token sale =                    Token.builder().word("銷售").build();
        Token saleQty =                 Token.builder().word("銷售數量").build();
        Token qty =                     Token.builder().word("數量").build();
        Token and =                     Token.builder().word("和").build();
        Token stock =                   Token.builder().word("庫存").build();
        Token stockQty =                Token.builder().word("庫存數量").build();
        Token analyze =                 Token.builder().word("分析").build();

        List<Token> tokens = Arrays.asList(
                twoThousandEighteenYear, twoThousandEighteen, yearSale, sale, saleQty,
                qty, and, stock, stockQty, analyze);

        twoThousandEighteenYear.setStartIndex(0);
        twoThousandEighteen.setStartIndex(0);
        yearSale.setStartIndex(4);
        sale.setStartIndex(5);
        saleQty.setStartIndex(5);
        qty.setStartIndex(7);
        and.setStartIndex(9);
        stock.setStartIndex(10);
        stockQty.setStartIndex(10);
        analyze.setStartIndex(14);
        Token qty2 = Token.builder().word("數量").startIndex(12).build();

        List<List<Token>> expected = Arrays.asList(
                Arrays.asList(twoThousandEighteenYear, sale, qty, and, stock, qty2, analyze),
                Arrays.asList(twoThousandEighteenYear, sale, qty, and, stockQty, analyze),
                Arrays.asList(twoThousandEighteenYear, saleQty, and, stock, qty2, analyze),
                Arrays.asList(twoThousandEighteenYear, saleQty, and, stockQty, analyze),
                Arrays.asList(twoThousandEighteen, yearSale, qty, and, stock, qty2, analyze),
                Arrays.asList(twoThousandEighteen, yearSale, qty, and, stockQty, analyze)
        );

        // action
        List<List<Token>> actual = selector.select(statement, tokens);

        // assert
        assertEquals(expected.size(), actual.size());
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }
}
