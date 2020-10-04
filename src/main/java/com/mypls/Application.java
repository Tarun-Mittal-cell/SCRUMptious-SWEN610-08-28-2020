package com.mypls;
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

        //DatabaseController.updateDatabase("INSERT INTO USERS (`FirstName`, `LastName`, `Email`, `Role`) VALUES ('Kemar', 'James', 'kj@rit.edu', 'Student')");

        generator.setModelNull();
        get("/", (req, res) -> generator.render(INDEX));

        get("/homepage", (req, res) -> {
           if(req.session().attribute("currentUser")!=null)
           {
               return generator.render(HOME);
           }
           else
           {
               return "You are not logged in!";
           }

        });

        get("/registration", (req, res) -> generator.render(REGISTRATION));
        get("/login", (req, res) -> generator.render(LOGIN));
        post("/registration", (req, res) -> { System.out.println(req.queryParams("fname")+" "+req.queryParams("lname")+" "+req.queryParams("roles")+" "+req.queryParams("email")+" "+req.queryParams("username")+" "+ req.queryParams("password"));
        req.session(true);
            req.session().attribute("currentUser",req.queryParams("username"));
            res.redirect("/homepage");
        return "REGISTERED"; });

    }


}
