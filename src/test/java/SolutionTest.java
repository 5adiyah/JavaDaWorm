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

@Test
public void getsolution_solutionInstantiates_String() {
  Solution newSolution = new Solution("500 error", "500 error", "500 error");
  assertEquals("500 error", newSolution.getName());
  assertEquals("500 error", newSolution.getDescription());
  assertEquals("500 error", newSolution.getTag());
 }

@Test
public void all_emptyAtFirst() {
  assertEquals(0, Solution.all().size());
 }

@Test
public void equals_returnsTrueIfNamesAretheSame() {
  Solution firstSolution = new Solution("500 error", "500 error", "500 error");
  Solution secondSolution = new Solution("500 error", "500 error", "500 error");
  assertTrue(firstSolution.equals(secondSolution));
}
@Test
public void save_solutionSaves() {
  Solution newSolution = new Solution("500 error", "500 error", "500 error");
  newSolution.save();
  assertTrue(Solution.all().get(0).equals(newSolution));
}

@Test
public void save_assignsIdToObject() {
  Solution mySolution = new Solution("500 error", "500 error", "500 error");
  mySolution.save();
  Solution savedSolution = Solution.all().get(0);
  assertEquals(mySolution.getId(), savedSolution.getId());
}

@Test
public void find_findSolutionInDatabase_true() {
  Solution mySolution = new Solution("500 error", "500 error", "500 error");
  mySolution.save();
  Solution savedSolution = Solution.find(mySolution.getId());
  assertEquals(mySolution, savedSolution);
}
@Test
public void addGenre_addsGenreToAlbum_true() {
  Solution mySolution = new Solution("500 error", "500 error", "500 error");
  mySolution.save();
  Error myError = new Error("500 error", "500 error", "500 error");
  myError.save();
  mySolution.addError(myError);
  Error savedError = mySolution.getErrors().get(0);
  assertTrue(myError.equals(savedError));
}
@Test
public void getErrors_getsAllErrors() {
  Solution mySolution = new Solution("500 error", "500 error", "500 error");
  mySolution.save();
  Error myError = new Error("500 error", "500 error", "500 error");
  myError.save();
  mySolution.addError(myError);
  List savedErrors = mySolution.getErrors();
  assertEquals(1, savedErrors.size());
}
@Test
public void delete_deletesAllSolutionsAndErrorsAssociations() {
  Solution mySolution = new Solution("500 error", "500 error", "500 error");
  mySolution.save();
  Error myError = new Error("500 error", "500 error", "500 error");
  mySolution.addError(myError);
  mySolution.delete();
  assertEquals(0, myError.getSolutions().size());
}
@Test
public void update_updatesInfo_true() {
  Solution mySolution = new Solution("500 error", "500 error", "500 error");
  mySolution.save();
  mySolution.update("404 error", "404 error", "404 error");
  assertEquals("404 error", Solution.find(mySolution.getId()).getName());
  assertEquals("404 error", Solution.find(mySolution.getId()).getDescription());
  assertEquals("404 error", Solution.find(mySolution.getId()).getTag());
}
}
