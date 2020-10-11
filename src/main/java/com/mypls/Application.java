package com.mypls;
import com.mypls.users.Learner;
import com.mypls.users.Professor;

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
    static final String CREATECOURSE = "createCourse.ftlh";
    static final String AUTHENTICATED = "AUTHENTICATED";



    public static void main(String[] args) {
        staticFileLocation("/public");
        TemplateGenerator generator = new TemplateGenerator();
        generator.setUpConfig();


        get("/", (req, res) -> {
           if( req.session().attribute("currentUser")!= null)
           {
               res.redirect("/homepage");
           }
           generator.setModel("isEmailUnique", true);
           generator.setModel("loginStatus","None");

           return generator.render(LOGIN);
        });


        get("/registration", (req, res) ->  generator.render(REGISTRATION));

        get("/signout", (req, res) -> {
            req.session().removeAttribute("currentUser");
            generator.removeModel("userData");
            res.redirect("/");
            return generator.render(HOME);
        });


        get("/homepage", (req, res) -> {
            if (req.session().attribute("currentUser") != null)
            {
                return generator.render(HOME);
            }
            else
            {
                res.redirect("/");
                return "You are not logged in!";
            }
        });

        get("/homepageprof", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                return generator.render(HOMEPROF);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/homepageadmin", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                return generator.render(HOMEADMIN);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/homepageadmin/createCourse", (req, res) ->  generator.render(CREATECOURSE));

        post("/registration", (req, res) -> {
            System.out.println(req.queryParams("fname") + " " + req.queryParams("lname") + " " + req.queryParams("type") + " " + req.queryParams("email") + " " + req.queryParams("password_1") + " " + req.queryParams("password_2"));
            boolean isEmailUnique = LoginController.register(req.queryParams("fname"), req.queryParams("lname"), req.queryParams("type"), req.queryParams("email"), LoginController.encryption(req.queryParams("password_1")));
            if (isEmailUnique)
            {
                if(!req.queryParams("type").equals("Professor")) {
                    Learner learner = new Learner(req.queryParams("fname"), req.queryParams("lname"), req.queryParams("email"), LoginController.encryption(req.queryParams("password_1")), req.queryParams("type"));
                    generator.setModel("userData", learner);
                    req.session(true);
                    req.session().attribute("currentUser", req.queryParams("email"));
                    return generator.render(HOME);
                }
                else
                {
                    Professor professor = new Professor(req.queryParams("fname"), req.queryParams("lname"), req.queryParams("email"),LoginController.encryption(req.queryParams("password_1")));
                    generator.setModel("userData", professor);
                    return generator.render(HOMEPROF);
                }
            }
            else
            {
                generator.setModel("isEmailUnique", false);
                return generator.render(REGISTRATION);
            }
        });


        post("/login", (req, res) ->{
            HashMap<String, String> userData = LoginController.login(req.queryParams("email"),req.queryParams("password")) ;

            if(userData.get("loginStatus").equals(AUTHENTICATED))
            {
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                generator.setModel("isEmailUnique", true);
                generator.setModel("loginStatus",userData.get("loginStatus"));
                if(userData.get("type").equals("Professor"))
                {
                    Professor professor = new Professor(userData.get("firstName"), userData.get("lastName"), userData.get("email"),userData.get("password"));
                    generator.setModel("userData",professor);
                    res.redirect("/homepageprof");
                }
                else if (userData.get("type").equals("Administrator"))
                {
                   res.redirect("/homepageadmin");
                }
                else
                {
                    Learner learner = new Learner(userData.get("firstName"), userData.get("lastName"), userData.get("email"), userData.get("password"), userData.get("type"));
                    generator.setModel("userData",learner);
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
