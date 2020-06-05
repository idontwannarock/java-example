package jdbc;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PostgresqlParserTest {

    private PostgresqlParser parser;

    @Before
    public void init() {
        this.parser = new PostgresqlParser();
    }

    @Test
    public void test() {
        // arrange
        String url = "jdbc:postgresql://localhost:5432";
        String username = "wangchenghao";
        String password = "";
        String schema = "postgres";
        String tableName = "test_result_set";
        int columnTotal = 4;

        // action
        List<String> actual = parser.parseFirstRow(url, username, password, schema, tableName, columnTotal);
        System.out.println(actual);

        // assert
    }
}
