
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class Solution {
  private int id;
  private String name;
  private String description;
  private String tag;


  public Solution (String name, String description, String tag) {
<<<<<<< HEAD
=======
=======
<<<<<<< HEAD
  public Solution (String name, String description, String tag) {
=======
  public Solution (int id, String name, String description, String tag) {
>>>>>>> 8a2e8ddf2adbf823d9dc9fe967e1a91ec37f1426
>>>>>>> 48cc60fdd18afc203166324b0388083b3a27ba07
    this.id = id;
>>>>>>> 0b02343b829f31daca5f4c987bcc37f1c7a18f53
    this.name = name;
    this.description = description;
    this.tag = tag;
  }

  public int getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  public String getTag() {
    return tag;
  }

  public static List<Solution> all() {
    String sql = "SELECT id, name, description, tag FROM solutions";
      try(Connection con = DB.sql2o.open()) {
        return con.createQuery(sql).executeAndFetch(Solution.class);
      }
  }

  @Override public boolean equals(Object otherSolution) {
    if(!(otherSolution instanceof Solution)) {
      return false;
    } else {
      Solution newSolution = (Solution) otherSolution;
        return this.getName().equals(newSolution.getName()) &&
               this.getDescription().equals(newSolution.getDescription()) &&
               this.getTag().equals(newSolution.getTag()) &&
               this.getId() == (newSolution.getId());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO solutions(name, description, tag) VALUES (:name, :description, :tag)";
        this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("description", this.description)
            .addParameter("tag", this.tag)
            .executeUpdate()
            .getKey();
    }
  }

  public void update(String newName, String newDescription, String newTag) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE solutions SET name = :name, description = :description, tag = :tag WHERE id = :id";
      con.createQuery(sql)
        .addParameter("name", newName)
        .addParameter("description", newDescription)
        .addParameter("tag", newTag)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }

  public void delete() {
      try(Connection con = DB.sql2o.open()) {
        String deletequery = "DELETE FROM solutions WHERE id = :id";
          con.createQuery(deletequery)
            .addParameter("id", this.id)
            .executeUpdate();
        String joinDeleteQuery = "DELETE FROM errors_solutions WHERE solution_id = :solution_id";
          con.createQuery(joinDeleteQuery)
            .addParameter("solution_id", this.id)
            .executeUpdate();
      }
  }

public static Solution find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM solutions WHERE id = :id";
        Solution solution = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Solution.class);
        return solution;
      }
}

public void addError(Error error) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "INSERT INTO errors_solutions(error_id, solution_id) VALUES (:error_id, :solution_id)";
    con.createQuery(sql)
      .addParameter("solution_id", this.getId())
      .addParameter("error_id", error.getId())
      .executeUpdate();
  }
}
public List<Error> getErrors() {
  try(Connection con = DB.sql2o.open()) {
    String joinQuery = "SELECT error_id FROM errors_solutions WHERE solution_id = :solution_id";
    List<Integer> errorIds = con.createQuery(joinQuery)
      .addParameter("solution_id", this.getId())
      .executeAndFetch(Integer.class);

    List<Error> errors = new ArrayList<Error>();

    for(Integer errorId : errorIds) {
      String errorQuery = "SELECT * FROM errors WHERE id = :errorId";
      Error error = con.createQuery(errorQuery)
        .addParameter("errorId", errorId)
        .executeAndFetchFirst(Error.class);
      errors.add(error);
    }
    return errors;
  }
}
}
