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

    get("/login", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("errors", Error.allErrors());
      model.put("template", "templates/login.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    post("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      String user = request.queryParams("userid");
      String password = request.queryParams("pswrd");
      model.put("errors", Error.allErrors());
      String template;
      if(user.equals("admin") && password.equals("admin")) {
         template = "templates/admin.vtl";
      }
      else {
        response.redirect("/login");
        return null;
      }
      model.put("template", template);
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("errors", Error.allErrors());
      model.put("template", "templates/admin.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());
    

    get("/admin/errors/new", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/admin_error_form.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    post("/admin/errors", (request, response) -> {
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

    get("/admin/errors/:id/delete", (request,response) -> {
      HashMap<String,Object> model = new HashMap<String,Object>();
      Error error = Error.find(Integer.parseInt(request.params(":id")));
      error.delete();
      model.put("error", error);
      model.put("template", "templates/delete_success.vtl");
      return new ModelAndView(model, layout);
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

    get("/admin/:id/solutions/:sId/delete", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      Solution thisSolution = Solution.find(Integer.parseInt(request.params("sId")));
      thisSolution.delete();
      model.put("error", thisError);
      model.put("solution", thisSolution);
      model.put("template", "templates/delete_success.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    get("/admin/users/inputs", (request, response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      model.put("template", "templates/user_errors.vtl");
      model.put("users", User.all());
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/admin/users/delete", (request, response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      User.delete();
      response.redirect("/admin");
      return null;
    });

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

    get("/pre/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      Error errorToBeSolved = Error.find(Integer.parseInt(request.params(":id")));
      Random random = new Random();
      int x = random.nextInt(errorToBeSolved.getSolutions().size());
      model.put("error", thisError);
      model.put("errors", Error.allErrors());
      model.put("allSolutions", Solution.all());
      model.put("errorToBeSolved", errorToBeSolved.getSolutions().get(x));
      model.put("template", "templates/pre_error.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    get("/post/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      Error errorToBeSolved = Error.find(Integer.parseInt(request.params(":id")));
      Random random = new Random();
      int x = random.nextInt(errorToBeSolved.getSolutions().size());
      model.put("error", thisError);
      model.put("errors", Error.allErrors());
      model.put("allSolutions", Solution.all());
      model.put("errorToBeSolved", errorToBeSolved.getSolutions().get(x));
      model.put("template", "templates/post_error.vtl");
      return new ModelAndView(model,layoutPost);
    }, new VelocityTemplateEngine());

    post("/pre/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      model.put("error", thisError);
      model.put("allSolutions", Solution.all());
      model.put("template", "templates/post_error.vtl");
      response.redirect("/pre/errors/" + thisError);
      return null;
    });

    post("/post/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      model.put("error", thisError);
      model.put("allSolutions", Solution.all());
      model.put("template", "templates/post_error.vtl");
      response.redirect("/post/errors/" + thisError);
      return null;
    });

    get("/user/submit", (request, response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      model.put("template", "templates/user_form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/user/submit", (request, response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      String error = request.queryParams("error");
      String solution = request.queryParams("solution");
      User newUser = new User(error, solution);
      newUser.save();
      model.put("user", newUser);
      model.put("users", User.all());
      model.put("template", "templates/user_success.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

  }
}
