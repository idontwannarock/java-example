package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PostgresqlParser {

    public List<String> parseFirstRow(String url, String username, String password, String schema, String tableName, int columnTotal) {
        List<String> row = new ArrayList<>();
        String sql = "SELECT * FROM " + tableName + " LIMIT 1";
        try (Connection connection = getConnection(url, username, password, schema);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                for (int index = 1; index <= columnTotal; index++) {
                    row.add(resultSet.getString(index));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return row;
    }

    private Connection getConnection(String url, String username, String password, String schema) throws Exception {
        String connectionUrl = url + "/" + schema;
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(connectionUrl, username, password);
    }
}
