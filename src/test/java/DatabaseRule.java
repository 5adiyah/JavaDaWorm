
import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  @Override
  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/java_da_worm_test", null, null);
  }

  @Override
  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteErrorsQuery = "DELETE FROM errors *;";
      String deleteSolutionsQuery = "DELETE FROM solutions *;";
      String deleteErrorsSolutionsQuery = "DELETE FROM errors_solutions *;";
      con.createQuery(deleteErrorsQuery).executeUpdate();
      con.createQuery(deleteSolutionsQuery).executeUpdate();
      con.createQuery(deleteErrorsSolutionsQuery).executeUpdate();
    }
  }
}
