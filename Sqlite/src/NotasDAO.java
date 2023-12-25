import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NotasDAO {
  static Connection connection = ConexaoSqlite.getConnection();

  public static List<Notes> getNotes() {
    String query = "select * from notes;";
    List<Notes> notes = new ArrayList<>();

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();

      while(resultSet.next()) {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");

        notes.add(new Notes(id, title, description));
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      ConexaoSqlite.closeConnection();
    }

    return notes;
  }

  public static Notes getNotesById(int idNote) {
    String query = "select * from notes where id = ?;";
    Notes notes = null;

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setInt(1, idNote);

      ResultSet resultSet = preparedStatement.executeQuery();

      while(resultSet.next()) {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String description = resultSet.getString("description");

        notes = new Notes(id, title, description);
      }
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      ConexaoSqlite.closeConnection();
    }

    return notes;
  }

  public static void addNote(String title, String description) {
    String query = "insert into notes (title, description) values (?, ?);";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setString(1, title);
      preparedStatement.setString(2, description);

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConexaoSqlite.closeConnection();
    }
  }

  public static void updateNote(int id, String title, String description) {
    String query = "update notes set title = ?, description = ? where id = ?";
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);

      preparedStatement.setString(1, title);
      preparedStatement.setString(2, description);
      preparedStatement.setInt(3, id);

      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      ConexaoSqlite.closeConnection();
    }
  }

  public static void dellNote(int id) {
    String query = "delete from notes where id = ?";

    try {
      PreparedStatement preparedStatement = connection.prepareStatement(query);
      preparedStatement.setInt(1, id);

      preparedStatement.executeUpdate();
    } catch(SQLException e) {
      e.printStackTrace();
    } finally {
      ConexaoSqlite.closeConnection();
    }
  }
}