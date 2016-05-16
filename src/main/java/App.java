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

    get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    },new VelocityTemplateEngine());

    get("/admin", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/admin_home.vtl");
      model.put("errors", Error.allErrors());
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
      String type = request.queryParams("type");
      String tags = request.queryParams("tags");
      Error newError = new Error(name, type, tags);
      newError.save();
      model.put("newError", newError);
      response.redirect("/admin_home");
      return null;
    });

    get("/admin/errors/:id", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error error = Error.find(Integer.parseInt(request.params(":id")));
      model.put("errors", error);
      model.put("error", "admin_error.vtl");
      return new ModelAndView(model,layout);
    }, new VelocityTemplateEngine());

    get("/admin/errors/:eId/solutions/:sId", (request,response) -> {
      Map<String,Object> model = new HashMap<String,Object>();
      Error error = Error.find(Integer.parseInt(request.params(":eId")));
      Solution solution = Solution.find(Integer.parseInt(request,params(":sId")));
      model.put("solutions", solution);
      model.put("solution", "admin_solution.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());




  }
}
