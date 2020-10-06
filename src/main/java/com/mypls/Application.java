package com.mypls;
import java.util.HashMap;
import java.util.Map;


import static spark.Spark.*;

public class Application {
    static final String INDEX = "index.ftlh";
    static final String HOME = "homepage.ftlh";
    static final String HOMEPROF = "homepageprof.ftlh";
    static final String HOMEADMIN = "homepageadmin.ftlh";
    static final String REGISTRATION = "registration.ftlh";
    static final String LOGIN = "login.ftlh";
    static final String AUTHENTICATED = "AUTHENTICATED";


    public static void main(String[] args) {
        staticFileLocation("/public");
        TemplateGenerator generator = new TemplateGenerator();
        generator.setUpConfig();
        generator.setModel("isEmailUnique", true);


        get("/", (req, res) -> {
           if( req.session().attribute("currentUser")!= null)
           {
               res.redirect("/homepage");
           }
           generator.setModel("isEmailUnique", true);
           generator.setModel("loginStatus","None");
           return generator.render(LOGIN);
        });


        get("/registration", (req, res) -> {
            generator.setModel("isEmailUnique", true);
            return generator.render(REGISTRATION);
        });


        get("/signout", (req, res) -> {
            generator.setModel("isEmailUnique", true);
            req.session().removeAttribute("currentUser");
            res.redirect("/");
            return generator.render(HOME);
        });

        post("/registration", (req, res) -> {
            System.out.println(req.queryParams("fname") + " " + req.queryParams("lname") + " " + req.queryParams("role") + " " + req.queryParams("email") + " " + req.queryParams("password_1") + " " + req.queryParams("password_2"));
            HashMap<String, String> userData = new HashMap();

            boolean isEmailUnique = LoginController.register(req.queryParams("fname"), req.queryParams("lname"), req.queryParams("role"), req.queryParams("email"), LoginController.encryption(req.queryParams("password_1")));
            if (isEmailUnique)
            {
                userData.put("role",req.queryParams("role"));
                userData.put("firstName",req.queryParams("fname"));
                userData.put("lastName",req.queryParams("lname"));
                userData.put("email",req.queryParams("email"));
                generator.setModel("userData",userData);
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                if(userData.get("role").equals("Professor"))
                {
                    return generator.render(HOMEPROF);
                   // res.redirect("/homepageprof");
                }
                else
                {
                    return generator.render(HOME);
                   // res.redirect("/homepage");
                }
            }
            else
            {

                generator.setModel("isEmailUnique", false);
                return generator.render(REGISTRATION);

            }

        });

        get("/homepage", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                generator.setModel("isEmailUnique", true);
                return generator.render(HOME);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/homepageprof", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                generator.setModel("isEmailUnique", true);
                return generator.render(HOMEPROF);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/homepageadmin", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                generator.setModel("isEmailUnique", true);
                return generator.render(HOMEADMIN);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        post("/login", (req, res) ->{
            HashMap<String, String> userData = LoginController.login(req.queryParams("email"),req.queryParams("password")) ;

            if(userData.get("loginStatus").equals(AUTHENTICATED))
            {
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                generator.setModel("loginStatus",userData.get("loginStatus"));
                generator.setModel("userData",userData);
                generator.setModel("isEmailUnique", true);
                if(userData.get("role").equals("Professor"))
                {
                   // return generator.render(HOMEPROF);
                    res.redirect("/homepageprof");
                }
                else if (userData.get("role").equals("Administrator"))
                {
                   // return generator.render(HOMEPROF);
                    res.redirect("/homepageadmin");
                }
                else
                {
                    //return generator.render(HOME);
                    res.redirect("/homepage");
                }
            }
            else
            {
                generator.setModel("loginStatus",userData.get("loginStatus"));
                return generator.render(LOGIN);
            }
            return null;
        });

    }
}
