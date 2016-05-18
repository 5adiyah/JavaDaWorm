// import org.sql2o.*;
// import java.util.List;
// import java.util.ArrayList;

// public class User {
//   private int id;
//   private String error;
//   private String solution;

//   public User(String error, String solution) {
//     this.error = error;
//     this.solution = solution;
//   }

//   public int getId() {
//     return id;
//   }
//   public String getError() {
//     return error;
//   }
//   public String getSolution() {
//     return solution;
//   }

//   public List<User> all() {
//     String sql = "SELECT id, error, solution FROM userinputs";
//     try(Connection con = DB.sql2o.open()) {
//       return con.createQuery(sql).executeAndFetch(User.class);
//     }
//   }
//   @Override public boolean equals (Object otherUser) {
//     if(!(otherUser instanceof User)) {
//       return false;
//     } else  {
//       User newUser = (User)otherUser;
//         return this.getError().equals(newUser.getError()) &&
//                this.getSolution().equals(newUser.getSolution) &&
//                this.getId() == (newUser.getSolution());
//       }
//   }

//   public void save() {
//     try(Connection con = DB.sql2o.open()) {
//       String sql = "INSERT INTO userinputs(error, solution) WHERE(:error, :solution)";
//       this.id = (int) con.createQuery(sql, true)
//         .addParameter("error", this.error)
//         .addParameter("solution", this.solution)
//         .executeUpdate()
//         .getKey();
//     }
//   }

//   public void delete() {
//     try (Connection con = DB.sql2o(open)) {
//       String deletequery = "DELETE FROM userinputs WHERE :id > 0";
//       con.createQuery(deletequery)
//         .addParameter("id", this.id)
//         .executeUpdate();
//     }
//   }
// }
