package com.mypls;
import com.mypls.users.Administrator;
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
    static final String UPDATECOURSE = "updateCourse.ftlh";
    static final String AUTHENTICATED = "AUTHENTICATED";
    public static Professor professor;




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
            return generator.render(LOGIN);
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
                System.out.println("fsdfsdfsd:"+professor);
                System.out.println("Map:"+generator.getModel());
                return generator.render(HOMEPROF);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/homepageadmin", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                Administrator admin= (Administrator) generator.getModel().get("userData");
                generator.setModel("courseCount",admin.allCourses().size());
                generator.setModel("courses",admin.allCourses());
                System.out.println("checkeee");
                return generator.render(HOMEADMIN);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/homepageadmin/createCourse", (req, res) -> {

            Administrator admin= (Administrator) generator.getModel().get("userData");
            generator.setModel("allProfessors",admin.allProfessors());
            return generator.render(CREATECOURSE);
        });

        get("/homepageadmin/updateCourse/:courseid", (request, response) -> {
            Administrator admin= (Administrator) generator.getModel().get("userData");
            generator.setModel("professors",admin.allProfessors());
            generator.setModel("course",Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
            System.out.println(Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
            return generator.render(UPDATECOURSE) ;
        });

        post("/registration", (req, res) -> {
            System.out.println(req.queryParams("fname") + " " + req.queryParams("lname") + " " + req.queryParams("type") + " " + req.queryParams("email") + " " + req.queryParams("password_1") + " " + req.queryParams("password_2"));
            boolean isEmailUnique = LoginController.register(req.queryParams("fname"), req.queryParams("lname"), req.queryParams("type"), req.queryParams("email"), LoginController.encryption(req.queryParams("password_1")));
            HashMap<String, String> info;
            if (isEmailUnique)
            {
                if(!req.queryParams("type").equals("Professor")) {
                    info=DatabaseController.queryLearners("SELECT * FROM learners WHERE Email='"+req.queryParams("email")+"'");
                    Learner learner = new Learner(Integer.parseInt(info.get("id")),req.queryParams("fname"), req.queryParams("lname"), req.queryParams("email"), req.queryParams("type"), Double.parseDouble(info.get("rating")),Integer.parseInt(info.get("numberOfRatings")));
                    generator.setModel("userData", learner);
                    req.session(true);
                    req.session().attribute("currentUser", req.queryParams("email"));
                    return generator.render(HOME);
                }
                else
                {
                    info=DatabaseController.queryLearners("SELECT * FROM learners WHERE Email='"+req.queryParams("email")+"'");
                    professor = new Professor(Integer.parseInt(info.get("id")),req.queryParams("fname"), req.queryParams("lname"), req.queryParams("email"),Double.parseDouble(info.get("rating")),Integer.parseInt(info.get("numberOfRatings")));
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
                    Professor professor = new Professor(Integer.parseInt(userData.get("id")),userData.get("firstName"), userData.get("lastName"), userData.get("email"), Double.parseDouble(userData.get("rating")),Integer.parseInt(userData.get("numberOfRatings")));
                    generator.setModel("userData",professor);
                    res.redirect("/homepageprof");
                }
                else if (userData.get("type").equals("Administrator"))
                {
                    Administrator admin =new Administrator();
                    generator.setModel("userData",admin);
                   res.redirect("/homepageadmin");
                }
                else
                {
                    Learner learner = new Learner(Integer.parseInt(userData.get("id")),userData.get("firstName"), userData.get("lastName"), userData.get("email"), userData.get("type"), Double.parseDouble(userData.get("rating")),Integer.parseInt(userData.get("numberOfRatings")));
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

        post("/homepageadmin/createCourse", (req, res) -> {

            System.out.println(req.queryParams("courseName") + " " + req.queryParams("requirement") + " " + req.queryParams("objectives") + " " + req.queryParams("outcomes") + " " + req.queryParams("prerequisite") + " " + req.queryParams("professor"));
            Administrator admin= (Administrator) generator.getModel().get("userData");
            String[] temp=((String) req.queryParams("professor")).split(" ");
            String profId=temp[2];
            admin.createNewCourse(profId,req.queryParams("courseName"),req.queryParams("objectives"),req.queryParams("outcomes"),req.queryParams("prerequisite"),req.queryParams("requirement"));
            res.redirect("/homepageadmin");
            return null;

        });

        post("/homepageadmin/", (req, res) -> {

            System.out.println(req.queryParams("courseid"));
            Administrator admin= (Administrator) generator.getModel().get("userData");
            admin.deleteCourse(Integer.parseInt(req.queryParams("courseid")));
            res.redirect("/homepageadmin");
            return null;

        });

    }
}
