import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.*;
import java.util.Random;

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

  @Test
  public void addSolution_solutionAddedToError_true() {
    Solution mySolution = new Solution("Solution", "Some text", "A tag");
    mySolution.save();
    Error myError = new Error("Error Name", "Error type", "A Tag");
    myError.save();
    myError.addSolutions(mySolution);
    Solution savedSolution = myError.getSolutions().get(0);
    assertTrue(mySolution.equals(savedSolution));
  }


  @Test
  public void delete_reviewDeletedFromAllTables_true() {
    Error myError = new Error("Error Name", "Error type", "A Tag");
    myError.save();
    Solution mySolution = new Solution("Solution", "Some text", "A tag");
    mySolution.save();
    myError.addSolutions(mySolution);
    myError.delete();
    assertEquals(0, Error.allErrors().size());
  }


  @Test
  public void random_methodGeneratesRandomSolution_true() {
    Error myError = new Error("Error Name", "Error type", "A Tag");
    myError.save();
    Solution mySolution2 = new Solution("Solution JUSTIN", "Some text", "A tag");
    mySolution2.save();
    Solution mySolution3 = new Solution("Solution KENDRA", "Some text", "A tag");
    mySolution3.save();
    Solution mySolution4 = new Solution("Solution MING", "Some text", "A tag");
    mySolution4.save();
    Solution mySolution5 = new Solution("Solution SADIO", "Some text", "A tag");
    mySolution5.save();
    Solution mySolution6 = new Solution("Solution TEST3", "Some text", "A tag");
    mySolution6.save();
    myError.addSolutions(mySolution2);
    myError.addSolutions(mySolution3);
    myError.addSolutions(mySolution4);
    myError.addSolutions(mySolution5);
    myError.addSolutions(mySolution6);
    Integer number = Error.randomNumber();
    myError.getSolutions().get(number);
    System.out.println(myError.getSolutions().get(number).getName());
  }

}
