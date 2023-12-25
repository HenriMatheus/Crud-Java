import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSqlite {
  private static final String url = "jdbc:sqlite:TodoList.db";
  private static Connection connection = null;

  public static Connection getConnection() {
    if (connection != null) {
      return connection;
    } else {
      try {
        connection = DriverManager.getConnection(url);
      } catch (SQLException e) {
        System.err.println("Error: " + e);
        e.printStackTrace();
      }

      return connection;
    }
  }

  public static void closeConnection() {
    if (connection != null) {
      try {
        connection.close();
      } catch (SQLException e) {
        System.err.println("Error: " + e);
        e.printStackTrace();
      }
    }
  }
}