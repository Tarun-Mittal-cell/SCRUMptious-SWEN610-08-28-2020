package com.mypls;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Application {
    static final String INDEX = "index.ftlh";
    static final String HOME = "homepage.ftlh";
    static final String REGISTRATION = "registration.ftlh";
    static final String LOGIN = "login.ftlh";


    public static void main(String[] args)
    {
        staticFileLocation("/public");
        TemplateGenerator generator= new TemplateGenerator();
        generator.setUpConfig();
        generator.setModel("isUnique",true);



        get("/", (req, res) ->{
            generator.setModel("isUnique",true);
            return generator.render(LOGIN);
        });
        get("/registration", (req, res) ->{
            generator.setModel("isUnique",true);
            return generator.render(REGISTRATION);
        });
        post("/registration", (req, res) -> { System.out.println(req.queryParams("fname")+" "+req.queryParams("lname")+" "+req.queryParams("role")+" "+req.queryParams("email")+" "+req.queryParams("password_1")+" "+ req.queryParams("password_2"));
           boolean isUnique= LoginController.register(req.queryParams("fname"),req.queryParams("lname"),req.queryParams("role"),req.queryParams("email"),req.queryParams("password_1"));
            if(isUnique) {
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                res.redirect("/homepage");
            }
            else
            {

                generator.setModel("isUnique",false);
                return generator.render(REGISTRATION);

            }
        return "REGISTERED"; });
        get("/homepage", (req, res) -> {
            if(req.session().attribute("currentUser")!=null)
            {
                generator.setModel("isUnique",true);
                return generator.render(HOME);
            }
            else
            {
                return "You are not logged in!";
            }

        });

    }


}
