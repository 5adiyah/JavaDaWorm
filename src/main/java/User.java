import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class User {
  private int id;
  private String error;
  private String solution;

  public User(String error, String solution) {
    this.error = error;
    this.solution = solution;
  }

  public int getId() {
    return id;
  }
  public String getError() {
    return error;
  }
  public String getSolution() {
    return solution;
  }

  public static List<User> all() {
    String sql = "SELECT * FROM userinputs";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(User.class);
    }
  }
  @Override public boolean equals (Object otherUser) {
    if(!(otherUser instanceof User)) {
      return false;
    } else  {
      User newUser = (User)otherUser;
        return this.getError().equals(newUser.getError()) &&
               this.getSolution().equals(newUser.getSolution()) &&
               this.getId() == (newUser.getId());
      }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO userinputs(error, solution) VALUES(:error, :solution)";
      this.id = (int) con.createQuery(sql, true)
        .addParameter("error", this.error)
        .addParameter("solution", this.solution)
        .executeUpdate()
        .getKey();
    }
  }

  public static boolean delete() {
    try (Connection con = DB.sql2o.open()) {
      String deletequery = "DELETE FROM userinputs";
      con.createQuery(deletequery)
        .executeUpdate();
    }
    return true;
  }
}
