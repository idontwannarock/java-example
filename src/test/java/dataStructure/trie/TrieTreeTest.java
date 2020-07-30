package dataStructure.trie;

import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"SqlResolve", "SqlNoDataSourceInspection"})
public class TrieTreeTest {

    private static final String URL = "jdbc:postgresql://***:***/***?currentSchema=public";
    private static final String USER = "***";
    private static final String PASSWORD = "***";

    private static FirstCharTrieTrees trees;

    @BeforeClass
    public static void init() {
        long beforeUsedMem = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        long start = System.currentTimeMillis();
        try {
            long dataSourceId = 43;
            Class.forName("org.postgresql.Driver");
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                String findDataFrameNameSql = "SELECT name FROM dataframe WHERE datasource_id = ? AND (state = 'Enable' OR state = 'Warn')";
                List<String> tableNames = new ArrayList<>();
                try (PreparedStatement ps = connection.prepareStatement(findDataFrameNameSql)) {
                    ps.setLong(1, dataSourceId);
                    try (ResultSet resultSet = ps.executeQuery()) {
                        while (resultSet.next()) {
                            String tableName = resultSet.getString("name");
                            if (StringUtils.isNotBlank(tableName)) {
                                tableNames.add(tableName);
                            }
                        }
                    }
                }
                System.out.println("Found table: " + tableNames);
                if (tableNames.isEmpty()) {
                    throw new IllegalArgumentException("No table were found");
                }
                trees = new FirstCharTrieTrees();
                for (String tableName : tableNames) {
                    String findDistinctiveColumnNamesSql = "SELECT dc.name FROM datacolumn dc INNER JOIN dataframe df ON df.id = dc.dataframe_id WHERE dc.active = true AND dc.stats_type = 'CATEGORY' AND (df.state = 'Enable' OR df.state = 'Warn') AND df.datasource_id = ? AND df.name = ? ";
                    List<String> columnNames = new ArrayList<>();
                    try (PreparedStatement ps = connection.prepareStatement(findDistinctiveColumnNamesSql)) {
                        ps.setLong(1, dataSourceId);
                        ps.setString(2, tableName);
                        try (ResultSet resultSet = ps.executeQuery()) {
                            while (resultSet.next()) {
                                String columnName = resultSet.getString("name");
                                if (StringUtils.isNotBlank(columnName)) {
                                    columnNames.add(columnName);
                                }
                            }
                        }
                    }
                    if (columnNames.isEmpty()) {
                        continue;
                    }
                    StringBuilder selectDataValueSql = new StringBuilder("SELECT ");
                    for (int index = 0; index < columnNames.size(); index++) {
                        if (index != 0) {
                            selectDataValueSql.append(", ");
                        }
                        selectDataValueSql.append(columnNames.get(index));
                    }
                    selectDataValueSql.append(" FROM ").append(tableName);
                    try (Statement statement = connection.createStatement();
                         ResultSet resultSet = statement.executeQuery(selectDataValueSql.toString())) {
                        while (resultSet.next()) {
                            for (String columnName : columnNames) {
                                String value = resultSet.getString(columnName);
                                if (StringUtils.isNotBlank(value)) {
                                    trees.insert(value);
                                }
                            }
                        }
                    }
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
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
