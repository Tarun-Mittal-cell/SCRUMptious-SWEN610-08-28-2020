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
    static final String ADDLESSON = "AddLesson.ftlh";



    static final String AUTHENTICATED = "AUTHENTICATED";
    public static Professor professor;
    public static Learner learner;
    public static Administrator admin;


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

                template.setModel("courses",Course.getAssignedCourses(professor.getProfessorID()));
                return template.render(HOMEPROF);
            } else {
                res.redirect("/");
                return "You are not logged in!";
            }

        });

        get("HomepageProf/AddLesson/:courseid", (request, response) -> {
            template.setModel("course", Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
            return template.render(ADDLESSON) ;
        });

        post("/AddLesson", (req, res) -> {

            return req.queryParams("courseID")+" "+req.queryParams("courseID");

                });

        get("/HomepageAdmin", (req, res) -> {
            if (req.session().attribute("currentUser") != null) {
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
            boolean isEmailUnique = UserController.register(req.queryParams("fname"), req.queryParams("lname"), req.queryParams("type"), req.queryParams("email"), req.queryParams("password_1"));
            HashMap<String, Object> userData;

            if (isEmailUnique)
            {
                System.out.println("here2");
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));

                userData= UserController.login(req.queryParams("email"),req.queryParams("password_1"));
                if(!req.queryParams("type").equals("Professor"))
                {
                    learner = (Learner) userData.get("userData");
                    System.out.println(learner);
                    template.setModel("userData", learner);
                    req.session().attribute("Type", "Learner");
                    return template.render(HOME);
                }
                else
                {
                    System.out.println("here3");
                    professor = (Professor) userData.get("userData");
                    System.out.println(professor);
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
            HashMap<String, Object> userData = UserController.login(req.queryParams("email"),req.queryParams("password")) ;

            if(userData.get("loginStatus").equals(AUTHENTICATED))
            {
                req.session(true);
                req.session().attribute("currentUser", req.queryParams("email"));
                template.setModel("isEmailUnique", true);
                template.setModel("loginStatus",userData.get("loginStatus"));
                if(userData.get("userData") instanceof Professor)
                {
                    professor=(Professor) userData.get("userData");
                    template.setModel("userData",professor);
                    req.session().attribute("Type", "Professor");
                    res.redirect("/HomepageProf");
                }
                else if (userData.get("userData") instanceof Administrator)
                {
                    admin=(Administrator) userData.get("userData");
                    template.setModel("userData",admin);
                    req.session().attribute("Type", "Admin");
                   res.redirect("/HomepageAdmin");
                }
                else
                {
                    learner=(Learner) userData.get("userData");
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
