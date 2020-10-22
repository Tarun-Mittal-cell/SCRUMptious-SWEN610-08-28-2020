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
    static final String DISCUSSIONGROUP = "publicDiscussionBoard.ftlh";
    static final String CREATEDISCUSSIONGROUP = "createDiscussionGroup.ftlh";

    static final String AUTHENTICATED = "AUTHENTICATED";

    public static Professor professor;




    public static void main(String[] args) {
       staticFileLocation("/public");
       TemplateGenerator template = new TemplateGenerator();
       template.setUpConfig();





           get("/", (req, res) -> {
           if( req.session().attribute("currentUser")!= null)
           {
               res.redirect("/homepage");
           }
           template.setModel("isEmailUnique", true);
           template.setModel("loginStatus","None");

           return template.render(LOGIN);
        });


        get("/registration", (req, res) ->  template.render(REGISTRATION));

        get("/signout", (req, res) -> {
            req.session().removeAttribute("currentUser");
            template.removeModel("userData");
            res.redirect("/");
            return template.render(LOGIN);
        });


        get("/homepage", (req, res) -> {
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

        get("/homepageprof", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                System.out.println("fsdfsdfsd:"+professor);
                System.out.println("Map:"+template.getModel());
                return template.render(HOMEPROF);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/homepageadmin", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
                Administrator admin= (Administrator) template.getModel().get("userData");
                template.setModel("courseCount",admin.allCourses().size());
                template.setModel("courses",admin.allCourses());
                template.setModel("professors",admin.allProfessors());
                template.setModel("learners",admin.allLearners());
                 return template.render(HOMEADMIN);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/homepageadmin/createCourse", (req, res) -> {

            Administrator admin= (Administrator) template.getModel().get("userData");
            template.setModel("professors",admin.allProfessors());
            return template.render(CREATECOURSE);
        });

        get("/homepageadmin/updateCourse/:courseid", (request, response) -> {
            Administrator admin= (Administrator) template.getModel().get("userData");
            template.setModel("course",Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
            System.out.println(Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
            return template.render(UPDATECOURSE) ;
        });


        get("/DiscussionBoard", (request, response) -> {

            return template.render(DISCUSSIONGROUP) ;
        });

        get("/homepageadmin/createDiscussionGroup", (request, response) -> {

            return template.render(CREATEDISCUSSIONGROUP) ;
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
                    template.setModel("userData", learner);
                    req.session(true);
                    req.session().attribute("currentUser", req.queryParams("email"));
                    return template.render(HOME);
                }
                else
                {
                    info=DatabaseController.queryProfessors("SELECT * FROM professors WHERE Email='"+req.queryParams("email")+"'");
                    System.out.println(info);
                    professor = new Professor(Integer.parseInt(info.get("id")),req.queryParams("fname"), req.queryParams("lname"), req.queryParams("email"),Double.parseDouble(info.get("rating")),Integer.parseInt(info.get("numberOfRatings")));
                    template.setModel("userData", professor);
                    return template.render(HOMEPROF);
                }
            }
            else
            {
                template.setModel("isEmailUnique", false);
                return template.render(REGISTRATION);
            }
        });


        post("/login", (req, res) ->{
            HashMap<String, String> userData = LoginController.login(req.queryParams("email"),req.queryParams("password")) ;

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
                    res.redirect("/homepageprof");
                }
                else if (userData.get("type").equals("Administrator"))
                {
                    Administrator admin =new Administrator();
                    template.setModel("userData",admin);
                   res.redirect("/homepageadmin");
                }
                else
                {
                    Learner learner = new Learner(Integer.parseInt(userData.get("id")),userData.get("firstName"), userData.get("lastName"), userData.get("email"), userData.get("type"), Double.parseDouble(userData.get("rating")),Integer.parseInt(userData.get("numberOfRatings")));
                    template.setModel("userData",learner);
                    res.redirect("/homepage");
                }
            }
            else
            {
                template.setModel("loginStatus",userData.get("loginStatus"));
                return template.render(LOGIN);
            }
            return null;
        });

        post("/homepageadmin/createCourse", (req, res) -> {

            System.out.println(req.queryParams("courseName") + " " + req.queryParams("requirement") + " " + req.queryParams("objectives") + " " + req.queryParams("outcomes") + " " + req.queryParams("prerequisite") + " " + req.queryParams("professor"));
            Administrator admin= (Administrator) template.getModel().get("userData");
            admin.createNewCourse(req.queryParams("professor"),req.queryParams("courseName"),req.queryParams("objectives"),req.queryParams("outcomes"),req.queryParams("prerequisite"),req.queryParams("requirement"));
            res.redirect("/homepageadmin");
            return null;

        });

        post("/homepageadmin/deleteCourse", (req, res) -> {

            System.out.println(req.queryParams("courseid"));
            Administrator admin= (Administrator) template.getModel().get("userData");
            admin.deleteCourse(Integer.parseInt(req.queryParams("courseid")));
            res.redirect("/homepageadmin");
            return null;

        });

        post("/homepageadmin/updateCourse", (req, res) -> {

            System.out.println(req.queryParams("courseName") + " " + req.queryParams("requirement") + " " + req.queryParams("objectives") + " " + req.queryParams("outcomes") + " " + req.queryParams("prerequisite") + " " + req.queryParams("professor"));
            Administrator admin= (Administrator) template.getModel().get("userData");
            Course course=(Course) template.getModel().get("course");
            admin.updateCourse((String.valueOf(course.getCourseID())),req.queryParams("professor"),req.queryParams("courseName"),req.queryParams("objectives"),req.queryParams("outcomes"),req.queryParams("prerequisite"),req.queryParams("requirement"));
            res.redirect("/homepageadmin");
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

        post("/homepageadmin/createDiscussionGroup", (req, res) -> {

            System.out.println(req.queryParams("topic") + " " + req.queryParams("type") + " " + req.queryParams("relatedCourse") );
            Administrator admin= (Administrator) template.getModel().get("userData");
            admin.createDiscussionGroup(req.queryParams("topic"),req.queryParams("relatedCourse"),req.queryParams("type"));
            res.redirect("/homepageadmin");
            return null;

        });

    }
}
