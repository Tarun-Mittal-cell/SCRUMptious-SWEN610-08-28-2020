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
     //   get("/", (req, res) -> generator.render(INDEX));


        get("/", (req, res) -> generator.render(LOGIN));
        get("/registration", (req, res) -> generator.render(REGISTRATION));
        post("/registration", (req, res) -> { System.out.println(req.queryParams("fname")+" "+req.queryParams("lname")+" "+req.queryParams("role")+" "+req.queryParams("email")+" "+req.queryParams("password_1")+" "+ req.queryParams("password_2"));
        req.session(true);
            req.session().attribute("currentUser",req.queryParams("email"));
            LoginController.register(req.queryParams("fname"),req.queryParams("lname"),req.queryParams("role"),req.queryParams("email"),req.queryParams("password_1"));
            res.redirect("/homepage");
        return "REGISTERED"; });
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

    }


}
