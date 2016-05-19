import org.sql2o.*;
import org.junit.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import static org.assertj.core.api.Assertions.assertThat;
import static org.fluentlenium.core.filter.FilterConstructor.*;
import static org.junit.Assert.*;


public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();

  @Override
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Admin");
  }

  @Test
  public void adminLinkCorrect() {
    goTo("http://localhost:4567/");
    click("a", withText ("Admin"));
    assertThat(pageSource()).contains("Admin Login Page");
  }

  @Test
  public void adminLoginPageFunctions() {
    goTo("http://localhost:4567/");
    click("a", withText ("Admin"));
    fill("#userid").with("admin");
    fill("#pswrd").with("admin");
    click("button", withText ("Log In"));
    assertThat(pageSource()).contains("Add Error");
  }

  @Test
  public void errorsShowOnAdminPage() {
    Error testError = new Error("500 error", "pre", "typos");
    testError.save();
    String url = String.format("http://localhost:4567/admin");
    goTo(url);
    assertThat(pageSource()).contains("500 error");
  }

  @Test
  public void errorIsCreated() {
    goTo("http://localhost:4567/admin");
    click("a", withText ("Add Error"));
    fill("#name").with("500 Error");
    fillSelect("#types").withText("Post Error");
    fillSelect("#tags").withText("typos");
    submit("btn");
    assertThat(pageSource()).contains("500 Error");
  }

  @Test
  public void solutionAddedToError() {
    Error testError = new Error("Null Pointer Error", "post", "semi-colons");
    testError.save();
    String url = String.format("http://localhost:4567/admin/errors/%d", testError.getId());
    goTo(url);
    fill("#name").with("semicolon");
    fill("#description").with("you numbnuts");
    fillSelect("#tags").withText("semi-colons");
    submit("btn");
    assertThat(pageSource()).contains("semicolon");
  }

  @Test
  public void preErrorShowsOnPreErrorPage() {
    Error testError = new Error("500 error", "pre", "typos");
    testError.save();
    String url = String.format("http://localhost:4567/");
    goTo(url);
    click("a", withText ("Pre Testing"));
    assertThat(pageSource()).contains("500 error");
  }
  @Test
  public void postErrorShowsOnPostErrorPage() {
    Error testError = new Error("Null Pointer Error", "post", "semi-colons");
    testError.save();
    String url = String.format("http://localhost:4567/");
    goTo(url);
    click("a", withText("Post Testing"));
    assertThat(pageSource()).contains("Null Pointer Error");
  }

  @Test
  public void postErrorShowsThatErrorsSolutions() {
    Error testError = new Error("Null Pointer Error", "post", "semi-colons");
    testError.save();
    String url = String.format("http://localhost:4567/", testError.getId());
    goTo(url);
    fill("#name").with("Null Pointer Error");
    fill("#description").with("you numbnuts");
    fillSelect("#tags").withText("typos");
    submit(".add");
    assertThat(pageSource()).contains("you numbnuts");
  }
  @Test
  public void userAddsError() {
    User newUser = new User("500 error", "I dunno");
    newUser.save();
    String url = String.format("http://localhost:4567/admin/users/inputs");
    goTo(url);
    assertThat(pageSource()).contains("500 error");
  }
  // @Test
  // public void userInputsDeleted() {
  //   User newUser = new User("500 error", "dunno");
  //   newUser.save();
  //   String url = String.format("http://localhost:4567/admin/user_errors");
  //   goTo(url);
  //   click("a", withText("**DELETE ALL**"));
  //   assertThat(pageSource()).contains("Add Error");
  // }
}
