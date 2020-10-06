package com.mypls;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;

public class Application {
    static final String INDEX = "index.ftlh";
    static final String HOME = "homepage.ftlh";
    static final String REGISTRATION = "registration.ftlh";
    static final String LOGIN = "login.ftlh";
    static final String AUTHENTICATED = "AUTHENTICATED";


    public static void main(String[] args) {
        staticFileLocation("/public");
        TemplateGenerator generator = new TemplateGenerator();
        generator.setUpConfig();
        generator.setModel("isUnique", true);


        get("/", (req, res) -> {
           if( req.session().attribute("currentUser")!= null)
           {
               res.redirect("/homepage");
           }
           generator.setModel("isUnique", true);
            generator.setModel("status","None");
           return generator.render(LOGIN);
        });

        get("/registration", (req, res) -> {
            generator.setModel("isUnique", true);
            return generator.render(REGISTRATION);
        });

        get("/signout", (req, res) -> {
            generator.setModel("isUnique", true);
            req.session().removeAttribute("currentUser");
            res.redirect("/");
            return generator.render(HOME);
        });

        post("/registration", (req, res) -> {
            System.out.println(req.queryParams("fname") + " " + req.queryParams("lname") + " " + req.queryParams("role") + " " + req.queryParams("email") + " " + req.queryParams("password_1") + " " + req.queryParams("password_2"));
            boolean isUnique = LoginController.register(req.queryParams("fname"), req.queryParams("lname"), req.queryParams("role"), req.queryParams("email"), req.queryParams("password_1"));
            if (isUnique) {
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                res.redirect("/homepage");
            } else {

                generator.setModel("isUnique", false);
                return generator.render(REGISTRATION);

            }
            return "REGISTERED";
        });

        get("/homepage", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                generator.setModel("isUnique", true);
                return generator.render(HOME);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        post("/login", (req, res) ->{
            HashMap<String, String> userData = LoginController.login(req.queryParams("email"),req.queryParams("password")) ;

            if(userData.get("status").equals(AUTHENTICATED))
            {
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                generator.setModel("status",userData.get("status"));
                generator.setModel("userData",userData);
                return generator.render(HOME);
            }
            else
            {
                generator.setModel("status",userData.get("status"));
                return generator.render(LOGIN);
            }

        });

    }
}
