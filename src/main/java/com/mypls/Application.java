package com.mypls;
import com.mypls.course.Course;
import com.mypls.users.*;

import java.util.HashMap;


import static spark.Spark.*;

public class Application {
    //Templates
    static final String HOME = "Homepage.ftlh";
    static final String HOMEPROF = "HomepageProf.ftlh";
    static final String HOMEADMIN = "HomepageAdmin.ftlh";
    static final String REGISTRATION = "Registration.ftlh";
    static final String LOGIN = "Login.ftlh";
    static final String CREATECOURSE = "CreateCourse.ftlh";
    static final String UPDATECOURSE = "updateCourse.ftlh";
    static final String DISCUSSIONGROUP = "DiscussionBoard.ftlh";
    static final String CREATEDISCUSSIONGROUP = "CreateDiscussionGroup.ftlh";



    static final String AUTHENTICATED = "AUTHENTICATED";
    public static Professor professor;


    public static void main(String[] args)
    {
       staticFileLocation("/public");
       TemplateGenerator template = new TemplateGenerator();
       template.setUpConfig();

       get("/", (req, res) -> {
       if( req.session().attribute("currentUser")!= null)
       {
           if(req.session().attribute("Type").equals("Learner")) {
               res.redirect("/Homepage");
           }
           else if(req.session().attribute("Type").equals("Professor"))
           {
               res.redirect("/HomepageProf");
           }
           else
           {
               res.redirect("/HomepageAdmin");
           }
       }
            template.setModel("isEmailUnique", true);
            template.setModel("loginStatus","None");

           return template.render(LOGIN);
        });

        get("/Registration", (req, res) ->  template.render(REGISTRATION));

        get("/Signout", (req, res) -> {
            req.session().removeAttribute("currentUser");
            template.removeModel("userData");
            res.redirect("/");
            return template.render(LOGIN);
        });


        get("/Homepage", (req, res) -> {
            if (req.session().attribute("currentUser") != null)
            {
                return template.render(HOME);
            }
            else
            {
                res.redirect("/");
                return "You are not logged in!";
            }
        });

        get("/HomepageProf", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                System.out.println("fsdfsdfsd:"+professor);
                System.out.println("Map:"+template.getModel());
                return template.render(HOMEPROF);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/HomepageAdmin", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                Administrator admin= (Administrator) template.getModel().get("userData");
                template.setModel("courseCount", Course.allCourses().size());
                template.setModel("courses",Course.allCourses());
                template.setModel("professors",Professor.allProfessors());
                template.setModel("learners",Learner.allLearners());
                 return template.render(HOMEADMIN);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/HomepageAdmin/CreateCourse", (req, res) -> {

            Administrator admin= (Administrator) template.getModel().get("userData");
            template.setModel("professors",Professor.allProfessors());
            return template.render(CREATECOURSE);
        });

        get("/HomepageAdmin/UpdateCourse/:courseid", (request, response) -> {
            Administrator admin= (Administrator) template.getModel().get("userData");
            template.setModel("course", Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
            System.out.println(Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
            return template.render(UPDATECOURSE) ;
        });


        get("/DiscussionBoard", (request, response) -> {
            return template.render(DISCUSSIONGROUP) ;
        });

        get("/HomepageAdmin/CreateDiscussionGroup", (request, response) -> {
            return template.render(CREATEDISCUSSIONGROUP) ;
        });


        post("/Registration", (req, res) -> {
            System.out.println(req.queryParams("fname") + " " + req.queryParams("lname") + " " + req.queryParams("type") + " " + req.queryParams("email") + " " + req.queryParams("password_1") + " " + req.queryParams("password_2"));
            boolean isEmailUnique = UserController.register(req.queryParams("fname"), req.queryParams("lname"), req.queryParams("type"), req.queryParams("email"), UserController.encryption(req.queryParams("password_1")));
            HashMap<String, String> info;
            if (isEmailUnique)
            {
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                if(!req.queryParams("type").equals("Professor")) {
                    info= DatabaseManager.queryLearners("SELECT * FROM learners WHERE Email='"+req.queryParams("email")+"'");
                    Learner learner = new Learner(Integer.parseInt(info.get("id")),req.queryParams("fname"), req.queryParams("lname"), req.queryParams("email"), req.queryParams("type"), Double.parseDouble(info.get("rating")),Integer.parseInt(info.get("numberOfRatings")));
                    template.setModel("userData", learner);
                    req.session().attribute("Type", "Learner");
                    return template.render(HOME);
                }
                else
                {
                    info= DatabaseManager.queryProfessors("SELECT * FROM professors WHERE Email='"+req.queryParams("email")+"'");
                    System.out.println(info);
                    professor = new Professor(Integer.parseInt(info.get("id")),req.queryParams("fname"), req.queryParams("lname"), req.queryParams("email"),Double.parseDouble(info.get("rating")),Integer.parseInt(info.get("numberOfRatings")));
                    template.setModel("userData", professor);
                    req.session().attribute("Type", "Professor");
                    return template.render(HOMEPROF);
                }
            }
            else
            {
                template.setModel("isEmailUnique", false);
                return template.render(REGISTRATION);
            }
        });


        post("/Login", (req, res) ->{
            HashMap<String, String> userData = UserController.login(req.queryParams("email"),req.queryParams("password")) ;

            if(userData.get("loginStatus").equals(AUTHENTICATED))
            {
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                template.setModel("isEmailUnique", true);
                template.setModel("loginStatus",userData.get("loginStatus"));
                if(userData.get("type").equals("Professor"))
                {
                    Professor professor = new Professor(Integer.parseInt(userData.get("id")),userData.get("firstName"), userData.get("lastName"), userData.get("email"), Double.parseDouble(userData.get("rating")),Integer.parseInt(userData.get("numberOfRatings")));
                    template.setModel("userData",professor);
                    req.session().attribute("Type", "Professor");
                    res.redirect("/HomepageProf");
                }
                else if (userData.get("type").equals("Administrator"))
                {
                    Administrator admin =new Administrator();
                    template.setModel("userData",admin);
                    req.session().attribute("Type", "Admin");
                   res.redirect("/HomepageAdmin");
                }
                else
                {
                    Learner learner = new Learner(Integer.parseInt(userData.get("id")),userData.get("firstName"), userData.get("lastName"), userData.get("email"), userData.get("type"), Double.parseDouble(userData.get("rating")),Integer.parseInt(userData.get("numberOfRatings")));
                    template.setModel("userData",learner);
                    req.session().attribute("Type", "Learner");
                    res.redirect("/Homepage");
                }
            }
            else
            {
                template.setModel("loginStatus",userData.get("loginStatus"));
                return template.render(LOGIN);
            }
            return null;
        });

        post("/HomepageAdmin/CreateCourse", (req, res) -> {

            System.out.println(req.queryParams("courseName") + " " + req.queryParams("requirement") + " " + req.queryParams("objectives") + " " + req.queryParams("outcomes") + " " + req.queryParams("prerequisite") + " " + req.queryParams("professor"));
            Course.createNewCourse(req.queryParams("professor"),req.queryParams("courseName"),req.queryParams("objectives"),req.queryParams("outcomes"),req.queryParams("prerequisite"),req.queryParams("requirement"));
            res.redirect("/HomepageAdmin");
            return null;

        });

        post("/HomepageAdmin/DeleteCourse", (req, res) -> {

            System.out.println(req.queryParams("courseid"));
            Administrator admin= (Administrator) template.getModel().get("userData");
            Course.deleteCourse(Integer.parseInt(req.queryParams("courseid")));
            res.redirect("/HomepageAdmin");
            return null;

        });

        post("/HomepageAdmin/UpdateCourse", (req, res) -> {

            System.out.println(req.queryParams("courseName") + " " + req.queryParams("requirement") + " " + req.queryParams("objectives") + " " + req.queryParams("outcomes") + " " + req.queryParams("prerequisite") + " " + req.queryParams("professor"));
            Course course=(Course) template.getModel().get("course");
            Course.updateCourse((String.valueOf(course.getCourseID())),req.queryParams("professor"),req.queryParams("courseName"),req.queryParams("objectives"),req.queryParams("outcomes"),req.queryParams("prerequisite"),req.queryParams("requirement"));
            res.redirect("/HomepageAdmin");
            return null;

        });

        post("/DiscussionBoard", (req, res) -> {

            System.out.println(req.queryParams("textFromUser") );

            String user = "<b> Username </b>";
            String message = user + " : " + req.queryParams("textFromUser") ;

            if(template.getModel().get("textFromUser")==null)
            {
                System.out.println("1" );
                template.setModel("textFromUser", message);
            }
            else
            {
                System.out.println("2" );
                template.setModel("textFromUser", template.getModel().get("textFromUser")+" <hr> "+message);
            }


            return template.render(DISCUSSIONGROUP) ;

        });

        post("/HomepageAdmin/CreateDiscussionGroup", (req, res) -> {

            System.out.println(req.queryParams("topic") + " " + req.queryParams("type") + " " + req.queryParams("relatedCourse") );
            Administrator admin= (Administrator) template.getModel().get("userData");
            admin.createDiscussionGroup(req.queryParams("topic"),req.queryParams("relatedCourse"),req.queryParams("type"));
            res.redirect("/HomepageAdmin");
            return null;

        });

    }
}
