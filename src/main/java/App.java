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
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";
    String layoutPost = "templates/layout_post.vtl";

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/pre_error", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/pre_error.vtl");
      model.put("errors", Error.allErrors());
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/post_error", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/post_error.vtl");
      model.put("errors", Error.allErrors());
      return new ModelAndView(model, layoutPost);
    },new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("errors", Error.allErrors());
      model.put("template", "templates/admin_home.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/admin/errors", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/error_form.vtl");
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
      model.put("template", "templates/admin_error.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    post("/admin/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Integer errorId = Integer.parseInt(request.params(":id"));
      Error newError = Error.find(errorId);
      String name = request.queryParams("name");
      String description = request.queryParams("description");
      String tag = request.queryParams("tag");
      Solution thisSolution = new Solution(name, description, tag);
      thisSolution.save();
      newError.addSolutions(thisSolution);
      model.put("error", newError);
      response.redirect("/admin/errors/" + errorId);
      return null;
    });

    get("/admin/:id/solution/:sId", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error thisError = Error.find(Integer.parseInt(request.params(":id")));
      Solution thisSolution = Solution.find(Integer.parseInt(request.params("sId")));
      model.put("error", thisError);
      model.put("solution", thisSolution);
      model.put("template", "templates/admin_solution.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());




  }
}
