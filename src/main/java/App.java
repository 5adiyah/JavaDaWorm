import java.util.*;
import java.util.Map;
import java.util.HashMap;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;
import org.sql2o.*;
import java.util.List;
import java.util.ArrayList;

public class App {
  public static void main(String[] args) {
    ProcessBuilder process = new ProcessBuilder();
     Integer port;
     if (process.environment().get("PORT") != null) {
         port = Integer.parseInt(process.environment().get("PORT"));}
     else { port = 4567; }
    setPort(port);
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    String layoutPost = "templates/layout_post.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/pre/errors", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/pre_errors.vtl");
      model.put("errors", Error.allErrors());
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/post/errors", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/post_errors.vtl");
      model.put("errors", Error.allErrors());
      return new ModelAndView(model, layoutPost);
    },new VelocityTemplateEngine());

    get("/login", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("errors", Error.allErrors());
      model.put("template", "templates/login.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("errors", Error.allErrors());
      model.put("template", "templates/admin.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/email/admin", (request, response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      model.put("template", "templates/email_admin.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/email/submit", (request, response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      model.put("template", "templates/email_success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin/errors/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/admin_error_form.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    post("admin/errors", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String name = request.queryParams("name");
      String type = request.queryParams("types");
      String tags = request.queryParams("tags");
      Error newError = new Error(name, type, tags);
      newError.save();
      model.put("error", newError);
      model.put("errors", Error.allErrors());
      response.redirect("/admin");
      return null;
    });

    get("/admin/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error newError = Error.find(Integer.parseInt(request.params(":id")));
      model.put("error", newError);
      model.put("template", "templates/admin_error_solution_form.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/admin/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Integer errorId = Integer.parseInt(request.params(":id"));
      Error newError = Error.find(errorId);
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      String tag = request.queryParams("tags");
      Solution thisSolution = new Solution(name, description, tag);
      thisSolution.save();
      newError.addSolutions(thisSolution);
      model.put("error", newError);
      response.redirect("/admin/errors/" + errorId);
      return null;
    });

    get("/admin/:id/solutions/:sId", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      Solution thisSolution = Solution.find(Integer.parseInt(request.params("sId")));
      model.put("error", thisError);
      model.put("solution", thisSolution);
      model.put("template", "templates/admin_solution.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    get("/pre/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));

      Error randomSolution = Error.find(Integer.parseInt(request.params(":id")));
      Random random = new Random();

      //create a method to retrieve all solutions for one error, then set the size to your max random
      System.out.println("solution size" + randomSolution.getSolutions().size());

      int x = random.nextInt(randomSolution.getSolutions().size());
      System.out.println("random no" + x);


      model.put("error", thisError);
      model.put("errors", Error.allErrors());
      model.put("allSolutions", Solution.all());
      model.put("randomSolution", randomSolution.getSolutions().get(x));
      model.put("template", "templates/pre_error.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/post/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      model.put("error", thisError);
      model.put("allSolutions", Solution.all());
      model.put("template", "templates/post_error.vtl");
      response.redirect("/pre/errors/" + thisError);
      return null;
    });

    get("/post/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      model.put("error", thisError);
      model.put("allSolutions", Solution.all());
      model.put("template", "templates/post_error.vtl");
      return new ModelAndView(model,layoutPost);
    }, new VelocityTemplateEngine());
  }
}
