import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class SolutionTest {
  @Rule
 public DatabaseRule database = new DatabaseRule();

 @Test
 public void solution_instantiates_true() {
   Solution newSolution = new Solution("500 error", "500 error", "500 error");
   assertEquals(true, newSolution instanceof Solution);
 }
}
