import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class ErrorTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Error_instantiatesCorrectly_true() {
    Error myError = new Error("Error Name", "Error type", "A Tag");
    assertEquals(true, myError instanceof Error);
  }

  @Test
  public void methods_getsAllInfo_true() {
    Error myError = new Error("Error Name", "Error type", "A Tag");
    assertEquals("Error Name", myError.getName());
    assertEquals("Error type", myError.getType());
    assertEquals("A Tag", myError.getTag());
}

@Test
  public void save_savesIntoDatabaseAndCreatesId_true() {
    Error myError = new Error("Error Name", "Error type", "A Tag");
    myError.save();
    Error savedError = Error.allErrors().get(0);
    assertTrue(Error.allErrors().get(0).equals(myError));
    assertEquals(myError.getId(), savedError.getId());
  }

  @Test
  public void find_findErrorInDatabase_true() {
    Error myError = new Error("Error Name", "Error type", "A Tag");
    myError.save();
    Error savedError = Error.find(myError.getId());
    assertTrue(myError.equals(savedError));
  }

  // @Test
  // public void addSolution_solutionAddedToError_true() {
  //   Solution mySolution = new Solution("Solution", "Some text", "A tag");
  //   mySolution.save();
  //   Error myError = new Error("This recipe sucks.");
  //   myError.save();
  //   myError.addSolution(mySolution);
  //   Solution savedSolution = myError.getSolution().get(0);
  //   assertTrue(mySolution.equals(savedSolution));
  // }


  // @Test
  // public void delete_reviewDeletedFromAllTables_true() {
  //   Error myError = new Error("Error Name", "Error type", "A Tag");
  //   myError.save();
  //   Solution mySolution = new Solution("Solution", "Some text", "A tag");
  //   mySolution.save();
  //   myError.addSolution(mySolution);
  //   myError.delete();
  //   assertEquals(0, Error.all().size());
  // }

}
