import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.*;
import java.util.Random;

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void User_instantiatesCorrectly_true() {
    User myUser = new User("500 error", "I dunno");
    assertEquals(true, myUser instanceof User);
  }

  @Test
  public void methods_getsAllInfo_true() {
    User myUser = new User("500 error", "I dunno");
    assertEquals("500 error", myUser.getError());
    assertEquals("I dunno", myUser.getSolution());
}

@Test
  public void save_savesIntoDatabaseAndCreatesId_true() {
    User myUser = new User("500 error", "I dunno");
    myUser.save();
    User savedUser = User.all().get(0);
    assertTrue(User.all().get(0).equals(myUser));
    assertEquals(myUser.getId(), savedUser.getId());
  }

  @Test
  public void delete_userInputDeleted_true() {
    User myUser = new User("500 error", "I dunno");
    myUser.save();
    myUser.delete();
    assertEquals(0, User.all().size());
  }
}
