package dataStructure.trie;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieTreeTest {

    private static FirstCharTrieTrees trees;

    @BeforeClass
    public static void init() {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();

        trees = new FirstCharTrieTrees();
        String url = "jdbc:postgresql://**:5432/**?currentSchema=public";
        @SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
        String sql = "SELECT customerno FROM **";
        try {
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(url, "**", "**");
                 Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String customerNo = resultSet.getString("customerno");
                    if (StringUtils.isNotBlank(customerNo)) {
                        trees.insert(customerNo);
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException | NullPointerException e) {
            e.printStackTrace();
        }

        long cost = System.currentTimeMillis() - start;
        System.out.println("Build trie trees cost: " + cost);
        long afterUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("Memory usage: " + ((afterUsedMem - beforeUsedMem) / (1024 * 1024)) + " mb");
    }

    @Test
    public void testShouldReturnFalse() {
        // arrange
        String input = "hello world";

        // act
        long start = System.currentTimeMillis();

        boolean actual = trees.exist(input);

        long cost = System.currentTimeMillis() - start;
        System.out.println("search cost: " + cost);

        // assert
        assertFalse(actual);
    }

    @Test
    public void testShouldReturnTrue() {
        // arrange
        String input = "TCustNo00237655";

        // act
        long start = System.currentTimeMillis();

        boolean actual = trees.exist(input);

        long cost = System.currentTimeMillis() - start;
        System.out.println("search cost: " + cost);

        // assert
        assertTrue(actual);
    }
}
