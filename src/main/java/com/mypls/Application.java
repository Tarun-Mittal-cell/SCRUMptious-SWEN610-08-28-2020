package com.mypls;
import com.mypls.course.Course;
import com.mypls.course.Lesson;
import com.mypls.users.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


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
    static final String PROFVIEWCOURSE = "ProfViewCourse.ftlh";
    static final String PROFVIEWLESSON = "ProfViewLesson.ftlh";
    static final String PROFUPDATELESSON = "ProfUpdatelesson.ftlh";
    static final String ADDQUIZ = "AddQuiz.ftlh";




    static final String AUTHENTICATED = "AUTHENTICATED";
    public static Professor professor;
    public static Learner learner;
    public static Administrator admin;


    public static void main(String[] args)
    {
      // staticFileLocation("/public");
       //staticFiles.location("/public");
       staticFiles.externalLocation("src/main/resources/public/");
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
            template.removeAll();
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

        get("/HomepageProf/ViewCourse/UpdateLesson/:courseid/:lessonid", (request, response) -> {
            Course course= (Course)template.getModel("course");
            int lessonID=Integer.parseInt(request.params(":lessonid"));
            System.out.print("Checking the Lesson ID: "+lessonID);
            List<Lesson>  lessons=course.getLessons();
            System.out.print("Checking the Lesson ID List: "+lessons.get(0).getLessonID());
            Lesson lesson=null;
            for (int i=0; i< lessons.size();i++)
            {
                if(lessons.get(i).getLessonID()==lessonID)
                {
                    lesson= lessons.get(i);
                    System.out.print("Checking the Lesson ID List22: "+lessons.get(0).getLessonID());
                    break;
                }
            }
            template.setModel("lesson", lesson);
            return template.render(PROFUPDATELESSON) ;
        });

        get("/HomepageProf/ViewCourse/:courseid", (request, response) -> {
            Course course=Course.getCourseByID( Integer.parseInt(request.params(":courseid")));

            template.setModel("course",course );


            return template.render(PROFVIEWCOURSE) ;
        });

        get("/HomepageProf/ViewCourse/:courseid/:lessonid/AddQuiz", (request, response) -> {
            Course course= (Course)template.getModel("course");
            int lessonID=Integer.parseInt(request.params(":lessonid"));
            List<Lesson>  lessons=course.getLessons();
            Lesson lesson=null;
            for (int i=0; i< lessons.size();i++)
            {
                if(lessons.get(i).getLessonID()==lessonID)
                {
                    lesson= lessons.get(i);
                    break;
                }
            }
            template.setModel("lesson", lesson);
                return template.render(ADDQUIZ);
                });
        post("/HomepageProf/ViewCourse/Lesson/AddQuiz", (request, response) -> {

            ArrayList<String> questions=new ArrayList<>();
            ArrayList<String> answers=new ArrayList<>();

            for(int i=0; i<5;i++)
            {
                if( request.queryParams("question"+(i+1))!=null)
                {
                    questions.add(request.queryParams("question" + (i + 1)));
                    answers.add(request.queryParams("answer" + (i + 1)));

                }
            }
            System.out.println(answers.toString());
             Lesson.createQuiz(Integer.parseInt(request.queryParams("lessonID")),questions,answers);
            return template.render(ADDQUIZ);
        });


        get("/HomepageProf/ViewCourse/ViewLesson/:courseid/:lessonid", (request, response) -> {
            Course course= (Course)template.getModel("course");
            int lessonID=Integer.parseInt(request.params(":lessonid"));
            List<Lesson>  lessons=course.getLessons();
            Lesson lesson=null;
            for (int i=0; i< lessons.size();i++)
            {
                if(lessons.get(i).getLessonID()==lessonID)
                {
                    lesson= lessons.get(i);
                    System.out.print("Checking the Lesson ID List22: "+lessons.get(0).getLessonID());
                    break;
                }
            }
            template.setModel("lesson", lesson);
            return template.render(PROFVIEWLESSON) ;
        });

        post("/AddLesson", (req, res) -> {

            Part uploadfile=null;
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            String mediaPath=" ";
            String documentPath=" ";
            try {
                uploadfile = req.raw().getPart("uploaded_pdf");
                if (uploadfile.getSize()!=0) {
                    String fileName = getFileName(req.raw().getPart("uploaded_pdf"));
                    documentPath="documents/"+fileName;
                    File uploadDir = new File("src/main/resources/public/documents/" + fileName);
                    System.out.println(uploadfile.getSize() + "document:" + uploadfile.getName() + " " + uploadfile.getHeader("Content-Disposition"));
                    InputStream input = uploadfile.getInputStream();// getPart needs to use same "name" as input field in form
                    Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);

                }
                uploadfile = req.raw().getPart("uploaded_media");
                if (uploadfile.getSize()!=0) {
                    String fileName = getFileName(req.raw().getPart("uploaded_media"));
                    mediaPath="media/"+fileName;
                    File uploadDir = new File("src/main/resources/public/media/" + fileName);
                    System.out.println(uploadfile.getSize() + "media" + uploadfile.getName() + " " + uploadfile.getHeader("Content-Disposition"));
                    InputStream input = uploadfile.getInputStream();
                    Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            Lesson.createLesson(req.queryParams("title"),Integer.parseInt(req.queryParams("courseID")),req.queryParams("requirement"),mediaPath,documentPath);
            res.redirect("/HomepageProf");

            return null;

                });

        post("/HomepageProf/ViewCourse/UpdateLesson", (req, res) -> {
            Lesson lesson= (Lesson) template.getModel("lesson");
            Part upLoadFile=null;
            req.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            String mediaPath=" ";
            String documentPath=" ";
            File uploadDir;

            try {
                upLoadFile = req.raw().getPart("uploaded_pdf");
                if (upLoadFile.getSize()!=0) {
                    String fileName = getFileName(req.raw().getPart("uploaded_pdf"));
                    documentPath="documents/"+fileName;
                    uploadDir = new File("src/main/resources/public/documents/" + fileName);
                    System.out.println(upLoadFile.getSize() + "document:" + upLoadFile.getName() + " " + upLoadFile.getHeader("Content-Disposition"));
                    InputStream input = upLoadFile.getInputStream();// getPart needs to use same "name" as input field in form
                    Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    if( !lesson.getDocumentPath().equals(" ")) {
                        uploadDir = new File("src/main/resources/public/" + lesson.getDocumentPath());
                        uploadDir.delete();
                    }
                }
                else
                {
                   documentPath= lesson.getDocumentPath();
                }
                upLoadFile = req.raw().getPart("uploaded_media");
                if (upLoadFile.getSize()!=0) {
                    String fileName = getFileName(req.raw().getPart("uploaded_media"));
                    mediaPath="media/"+fileName;
                    uploadDir = new File("src/main/resources/public/media/" + fileName);
                    System.out.println(upLoadFile.getSize() + "media" + upLoadFile.getName() + " " + upLoadFile.getHeader("Content-Disposition"));
                    InputStream input = upLoadFile.getInputStream();
                    Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    if( !lesson.getMediaPath().equals(" ")) {
                        uploadDir = new File("src/main/resources/public/" + lesson.getMediaPath());
                        uploadDir.delete();
                    }
                }
                else
                {
                    mediaPath=lesson.getMediaPath();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println(" "+req.queryParams("delete_media")+"  "+req.queryParams("delete_document"));
            Lesson.updateLesson(lesson.getLessonID(),req.queryParams("title"),Integer.parseInt(req.queryParams("courseID")),req.queryParams("requirement"),mediaPath,documentPath);
            res.redirect("/HomepageProf");
            return null;
        });

        post("/HomepageProf/ViewCourse/DeleteLesson", (req, res) -> {

            Lesson.deleteLesson(Integer.parseInt(req.queryParams("lessonID")),req.queryParams("mediaPath"),req.queryParams("documentPath"));
            res.redirect("/HomepageProf");
            return null;

        });

        post("/HomepageProf/ViewCourse/UpdateLesson/DeleteMedia", (req, res) -> {
            File uploadDir;

            Lesson lesson= (Lesson) template.getModel("lesson");
            String mediaPath=lesson.getMediaPath();
            String documentPath=lesson.getDocumentPath();

            if(req.queryParams("delete_media") != null)
            {
                uploadDir = new File("src/main/resources/public/" +req.queryParams("delete_media") );
                uploadDir.delete();
                mediaPath=" ";
                //Lesson.updateLesson(lesson.getLessonID(), lesson.getTitle(), lesson.getCoursedID(), lesson.getRequirements(),mediaPath,documentPath);
            }
            if(req.queryParams("delete_document") != null)
            {
                uploadDir = new File("src/main/resources/public/" +req.queryParams("delete_document") );
                uploadDir.delete();
                documentPath=" ";

            }
            Lesson.updateLesson(lesson.getLessonID(), lesson.getTitle(), lesson.getCoursedID(), lesson.getRequirements(),mediaPath,documentPath);

            res.redirect("/HomepageProf");
            return null;
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

            template.setModel("professors",Professor.allProfessors());
            return template.render(CREATECOURSE);
        });

        get("/HomepageAdmin/UpdateCourse/:courseid", (request, response) -> {
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
            Course course=(Course) template.getModel("course");
            Course.updateCourse((String.valueOf(course.getCourseID())),req.queryParams("professor"),req.queryParams("courseName"),req.queryParams("objectives"),req.queryParams("outcomes"),req.queryParams("prerequisite"),req.queryParams("requirement"));
            res.redirect("/HomepageAdmin");
            return null;

        });

        post("/DiscussionBoard", (req, res) -> {

            System.out.println(req.queryParams("textFromUser") );

            String user = "<b> Username </b>";
            String message = user + " : " + req.queryParams("textFromUser") ;

            if(template.getModel("textFromUser")==null)
            {
                System.out.println("1" );
                template.setModel("textFromUser", message);
            }
            else
            {
                System.out.println("2" );
                template.setModel("textFromUser", template.getModel("textFromUser")+" <hr> "+message);
            }


            return template.render(DISCUSSIONGROUP) ;

        });

        post("/HomepageAdmin/CreateDiscussionGroup", (req, res) -> {

            System.out.println(req.queryParams("topic") + " " + req.queryParams("type") + " " + req.queryParams("relatedCourse") );
            Administrator admin= (Administrator) template.getModel("userData");
            admin.createDiscussionGroup(req.queryParams("topic"),req.queryParams("relatedCourse"),req.queryParams("type"));
            res.redirect("/HomepageAdmin");
            return null;

        });

    }
    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
}
