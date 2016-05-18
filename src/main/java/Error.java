import java.util.*;
import java.util.List;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Error {
  private int id;
  private String name;
  private String type;
  private String tag;

  public Error(String name, String type, String tag) {
    this.name = name;
    this.type = type;
    this.tag = tag;
  }

  public static List<Error> allErrors() {
    String sql = "SELECT * FROM errors";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Error.class);
    }
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getTag() {
    return tag;
  }


  @Override
  public boolean equals(Object otherError){
    if (!(otherError instanceof Error)) {
      return false;
    } else {
      Error newError = (Error) otherError;
      return this.getName().equals(newError.getName()) &&
             this.getId() == newError.getId() &&
             this.getType().equals(newError.getType()) &&
             this.getTag().equals(newError.getTag());
    }
  }

  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO errors (name, type, tag) VALUES (:name, :type, :tag)";
      this.id = (int) con.createQuery(sql, true)
      .addParameter("name", this.name)
      .addParameter("type", this.type)
      .addParameter("tag", this.tag)
      .executeUpdate()
      .getKey();
    }
  }

  public static Error find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM errors WHERE id=:id";
      Error error = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Error.class);
      return error;
    }
  }

  public void addSolutions(Solution solution) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO errors_solutions (error_id, solution_id) VALUES (:error_id, :solution_id)";
      con.createQuery(sql)
      .addParameter("error_id", this.getId())
      .addParameter("solution_id", solution.getId())
      .executeUpdate();
    }
  }

  public List<Solution> getSolutions() {
    try(Connection con = DB.sql2o.open()) {
      String joinQuery = "SELECT solution_id FROM errors_solutions WHERE error_id = :error_id";
      List<Integer> solutionIds = con.createQuery(joinQuery)
      .addParameter("error_id", this.getId())
      .executeAndFetch(Integer.class);

      List<Solution> solutions = new ArrayList<Solution>();

      for(Integer solutionId : solutionIds) {
        String solutionQuery = "SELECT * FROM solutions WHERE id = :solutionId";
        Solution solution = con.createQuery(solutionQuery)
        .addParameter("solutionId", solutionId)
        .executeAndFetchFirst(Solution.class);
        solutions.add(solution);
      }
      return solutions;
    }
  }



  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM errors WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", this.getId())
      .executeUpdate();
    }
  }

  public void update(String newName, String newType, String newTag) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE errors SET name = :name WHERE id = :id";
      con.createQuery(sql)
      .addParameter("name", newName)
      .executeUpdate();

      String sqlTwo = "UPDATE errors SET type = :type WHERE id = :id";
      con.createQuery(sql)
      .addParameter("type", newType)
      .executeUpdate();

      String sqlThree = "UPDATE errors SET tag = :tag WHERE id = :id";
      con.createQuery(sql)
      .addParameter("tag", newTag)
      .executeUpdate();
    }
  }


  public static int randomNumber() {

    Random random = new Random();
    int x = random.nextInt(Solution.all().size()-1);
    //x = a random integer ranging from 0 to the size of my returned list, in our case, 4
    return x;
  }
}
