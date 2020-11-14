package com.mypls;
import com.mypls.course.Course;
import com.mypls.course.Lesson;
import com.mypls.course.Quiz;
import com.mypls.users.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
import javax.swing.plaf.IconUIResource;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import static spark.Spark.*;

public class Application {

    //Freemarker Templates
    static final String HOME = "HomepageLearner.ftlh";
    static final String SEARCH = "Search.ftlh";
    static final String HOMEPROF = "HomepageProf.ftlh";
    static final String COURSEOUTLINE = "CourseOutline.ftlh";
    static final String LEARNERVIEWCOURSE = "LearnerViewCourse.ftlh";
    static final String LEARNERVIEWLESSON = "LearnerViewLesson.ftlh";
    static final String TAKEQUIZ = "TakeQuiz.ftlh";


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
    static final String UPDATEQUIZ = "UpdateQuiz.ftlh";

    static final String AUTHENTICATED = "AUTHENTICATED";

    //User Objects
    public static Professor professor;
    public static Learner learner;
    public static Administrator admin;


    public static void main(String[] args)
    {

       staticFiles.externalLocation("src/main/resources/public/");
       TemplateGenerator template = new TemplateGenerator();
       template.setUpConfig();

       get("/", (request, response) -> {
       if( request.session().attribute("currentUser")!= null)
       {
           if(request.session().attribute("Type").equals("Learner")) {
               response.redirect("/HomepageLearner");
           }
           else if(request.session().attribute("Type").equals("Professor"))
           {
               response.redirect("/HomepageProf");
           }
           else
           {
               response.redirect("/HomepageAdmin");
           }
       }
            template.setModel("isEmailUnique", true);
            template.setModel("loginStatus","None");
           template.removeModel("blankSpaces");

           return template.render(LOGIN);
        });

        post("/Login", (request, response) ->{
            HashMap<String, Object> userData = UserController.login(request.queryParams("email"),request.queryParams("password")) ;

            if(userData.get("loginStatus").equals(AUTHENTICATED))
            {
                request.session(true);
                request.session().attribute("currentUser", request.queryParams("email"));
                template.setModel("isEmailUnique", true);
                template.setModel("loginStatus",userData.get("loginStatus"));
                if(userData.get("userData") instanceof Professor)
                {
                    professor=(Professor) userData.get("userData");
                    template.setModel("userData",professor);
                    request.session().attribute("Type", "Professor");
                    response.redirect("/HomepageProf");
                }
                else if (userData.get("userData") instanceof Administrator)
                {
                    admin=(Administrator) userData.get("userData");
                    template.setModel("userData",admin);
                    request.session().attribute("Type", "Admin");
                    response.redirect("/HomepageAdmin");
                }
                else
                {
                    learner=(Learner) userData.get("userData");
                    template.setModel("userData",learner);
                    request.session().attribute("Type", "Learner");
                    response.redirect("/HomepageLearner");
                }
            }
            else
            {
                template.setModel("loginStatus",userData.get("loginStatus"));
                return template.render(LOGIN);
            }
            return null;
        });

        get("/Registration", (request, response) -> {
            if( request.session().attribute("currentUser")!= null)
            {
                if(request.session().attribute("Type").equals("Learner")) {
                    response.redirect("/HomepageLearner");
                }
                else if(request.session().attribute("Type").equals("Professor"))
                {
                    response.redirect("/HomepageProf");
                }
                else
                {
                    response.redirect("/HomepageAdmin");
                }
            }

            return template.render(REGISTRATION);
        });

        post("/Registration", (request, response) -> {

            int fLength=request.queryParams("fname").trim().length() ;
            int lLength= request.queryParams("lname").trim().length() ;
            int elength=request.queryParams("email").trim().length();

            if(fLength>0&&lLength>0&&elength>0) {
                boolean isEmailUnique = UserController.register(request.queryParams("fname"), request.queryParams("lname"), request.queryParams("type"), request.queryParams("email"), request.queryParams("password_1"));
                HashMap<String, Object> userData;

                if (isEmailUnique) {
                    request.session(true);
                    request.session().attribute("currentUser", request.queryParams("email"));

                    userData = UserController.login(request.queryParams("email"), request.queryParams("password_1"));
                    if (!request.queryParams("type").equals("Professor")) {
                        learner = (Learner) userData.get("userData");
                        System.out.println(learner);
                        template.setModel("userData", learner);
                        request.session().attribute("Type", "Learner");
                        return template.render(HOME);
                    } else {
                        professor = (Professor) userData.get("userData");
                        System.out.println(professor);
                        template.setModel("userData", professor);
                        request.session().attribute("Type", "Professor");
                        return template.render(HOMEPROF);
                    }
                } else {
                    template.setModel("isEmailUnique", false);
                    return template.render(REGISTRATION);
                }
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(REGISTRATION);
            }
        });

        get("/Signout", (req, response) -> {
            req.session().removeAttribute("currentUser");
            template.removeAll();
            response.redirect("/");
            return template.render(LOGIN);
        });


        get("/HomepageLearner", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner"))
            {
                template.removeModel("blankSpaces");
                ArrayList<Professor> courseProfessors=new ArrayList<>();
                ArrayList<Course> courseList =DatabaseManager.getAllRegisteredCourses(learner.getLearnerID());
                for (Course course :courseList)
                {
                    Professor professor=Professor.getProfessor(course.getAssignedProfessorId());
                    courseProfessors.add(professor);
                }

                template.setModel("registeredCourses",courseList);
                template.setModel("courseProfessors",courseProfessors);
                return template.render(HOME);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        get("/HomepageLearner/ViewCourse/:courseid", (request, response) -> {

            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner"))
            {
                Course course = Course.getCourseByID(Integer.parseInt(request.params(":courseid")));
                for (Lesson lesson:course.getLessons())
                {
                    double grade=DatabaseManager.retrieveGrade(learner.getLearnerID(), lesson.getLessonID());
                    System.out.println("First score:"+grade);
                    lesson.getQuiz().setGrade(grade);

                    System.out.println("Grade :"+lesson.getQuiz().getGrade());
                }

                System.out.println("First score:"+course.getLessons().get(0).getQuiz().getGrade());
                template.removeModel("blankSpaces");
                course.setMinScore(75);
                template.setModel("course", course);
                return template.render(LEARNERVIEWCOURSE);
            }
            else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/HomepageLearner/ViewCourse/ViewLesson/:courseid/:lessonid", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner")) {

                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                List<Lesson> lessons = course.getLessons();
                Lesson lesson = null;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getLessonID() == lessonID) {
                        lesson = lessons.get(i);
                        System.out.print("Checking the Lesson ID List22: " + lessons.get(0).getLessonID());
                        break;
                    }
                }
               /* double grade=DatabaseManager.retrieveGrade(learner.getLearnerID(), lesson.getLessonID());
                System.out.println("First score:"+grade);
                lesson.getQuiz().setGrade(grade);

                System.out.println("Grade :"+lesson.getQuiz().getGrade());*/
                template.setModel("lesson", lesson);
                return template.render(LEARNERVIEWLESSON);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        get("/HomepageLearner/ViewCourse/:courseid/:lessonid/TakeQuiz", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner")) {
                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                List<Lesson> lessons = course.getLessons();
                Lesson lesson = null;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getLessonID() == lessonID) {
                        lesson = lessons.get(i);
                        break;
                    }
                }
                template.setModel("lesson", lesson);

                return template.render(TAKEQUIZ);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        post("/HomepageProf/ViewCourse/TakeQuiz", (request, response) -> {

            Course course = (Course) template.getModel("course");
            Lesson lesson=(Lesson) template.getModel("lesson");
            ArrayList<String> choices=new ArrayList<>();
            Quiz quiz=lesson.getQuiz();

            for(int i=0; i<3;i++)
            {
                String choice=request.queryParams("answer"+(i+1));
                choices.add(request.queryParams("answer" + (i + 1)));

            }
            double score=Quiz.takeQuiz(learner.getLearnerID(),lesson.getCoursedID(),lesson.getLessonID(),choices, quiz.getAnswers());
            template.setModel("score",score);
            return template.render(TAKEQUIZ);
        });


        get("/HomepageLearner/Search", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner"))
            {

                return template.render(SEARCH);
            }
            else
            {

                response.redirect("/");
                return null;
            }
        });

        post("/HomepageLearner/Search", (request, response) -> {

            String search=request.queryParams("search").trim();
            int courseID=Integer.parseInt(search);
            Course course= Course.getCourseByID(courseID);
            Professor professor=Professor.getProfessor(course.getAssignedProfessorId());
            System.out.println("hello "+professor);
            template.setModel("professor",professor);
            template.setModel("course",course);
            response.redirect("/HomepageLearner/Search");
            return null;
        });

        get("/HomepageLearner/ViewCourseOutline/:courseid", (request, response) -> {

            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner"))
            {
                return template.render(COURSEOUTLINE);
            }
            else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        post("/HomepageLearner/CourseRegister", (request, response) -> {


            boolean isAdded=Course.registerCourse(learner.getLearnerID(),Integer.parseInt(request.queryParams("courseID")));
            if(isAdded) {
                response.redirect("/HomepageLearner");
            }
            return null;
        });

        get("/HomepageProf", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor"))
            {
                template.removeModel("blankSpaces");
                template.setModel("courses",Course.getAssignedCourses(professor.getProfessorID()));
                return template.render(HOMEPROF);
            } else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        get("HomepageProf/AddLesson/:courseid", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor"))
            {
            template.setModel("course", Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
            return template.render(ADDLESSON) ;
            } else {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        get("/HomepageProf/ViewCourse/:courseid", (request, response) -> {

            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor"))
            {
                Course course = Course.getCourseByID(Integer.parseInt(request.params(":courseid")));
                template.removeModel("blankSpaces");
                template.setModel("course", course);
                return template.render(PROFVIEWCOURSE);
            }
            else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        post("/AddLesson", (request, response) -> {

            Part uploadfile=null;
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            String mediaPath=" ";
            String documentPath=" ";
            int tLength=request.queryParams("title").trim().length() ;
            Course course = (Course) template.getModel("course");

            if(tLength>0) {
                try {
                    uploadfile = request.raw().getPart("uploaded_pdf");
                    if (uploadfile.getSize() != 0) {
                        String fileName = getFileName(request.raw().getPart("uploaded_pdf"));
                        documentPath = "documents/" + fileName;
                        File uploadDir = new File("src/main/resources/public/documents/" + fileName);
                        System.out.println(uploadfile.getSize() + "document:" + uploadfile.getName() + " " + uploadfile.getHeader("Content-Disposition"));
                        InputStream input = uploadfile.getInputStream();// getPart needs to use same "name" as input field in form
                        Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    }
                    uploadfile = request.raw().getPart("uploaded_media");
                    if (uploadfile.getSize() != 0) {
                        String fileName = getFileName(request.raw().getPart("uploaded_media"));
                        mediaPath = "media/" + fileName;
                        File uploadDir = new File("src/main/resources/public/media/" + fileName);
                        System.out.println(uploadfile.getSize() + "media" + uploadfile.getName() + " " + uploadfile.getHeader("Content-Disposition"));
                        InputStream input = uploadfile.getInputStream();
                        Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                Lesson.createLesson(request.queryParams("title"), Integer.parseInt(request.queryParams("courseID")), request.queryParams("requirement"), mediaPath, documentPath);
                response.redirect("/HomepageProf/ViewCourse/"+course.getCourseID());

                return null;
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(ADDLESSON) ;
            }
        });

        get("/HomepageProf/ViewCourse/UpdateLesson/:courseid/:lessonid", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor"))
            {
                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                List<Lesson> lessons = course.getLessons();
                Lesson lesson = null;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getLessonID() == lessonID) {
                        lesson = lessons.get(i);
                        break;
                    }
                }
                template.setModel("lesson", lesson);
                return template.render(PROFUPDATELESSON);
            }
            else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        post("/HomepageProf/ViewCourse/UpdateLesson", (request, response) -> {
            Lesson lesson= (Lesson) template.getModel("lesson");
            Course course = (Course) template.getModel("course");
            Part upLoadFile=null;
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            String mediaPath=" ";
            String documentPath=" ";
            File uploadDir;
            int tLength=request.queryParams("title").trim().length() ;

            if(tLength>0) {
                try {
                    upLoadFile = request.raw().getPart("uploaded_pdf");
                    if (upLoadFile.getSize() != 0) {
                        String fileName = getFileName(request.raw().getPart("uploaded_pdf"));
                        documentPath = "documents/" + fileName;
                        uploadDir = new File("src/main/resources/public/documents/" + fileName);
                        System.out.println(upLoadFile.getSize() + "document:" + upLoadFile.getName() + " " + upLoadFile.getHeader("Content-Disposition"));
                        InputStream input = upLoadFile.getInputStream();// getPart needs to use same "name" as input field in form
                        Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        if (!lesson.getDocumentPath().equals(" ")) {
                            uploadDir = new File("src/main/resources/public/" + lesson.getDocumentPath());
                            uploadDir.delete();
                        }
                    } else {
                        documentPath = lesson.getDocumentPath();
                    }
                    upLoadFile = request.raw().getPart("uploaded_media");
                    if (upLoadFile.getSize() != 0) {
                        String fileName = getFileName(request.raw().getPart("uploaded_media"));
                        mediaPath = "media/" + fileName;
                        uploadDir = new File("src/main/resources/public/media/" + fileName);
                        System.out.println(upLoadFile.getSize() + "media" + upLoadFile.getName() + " " + upLoadFile.getHeader("Content-Disposition"));
                        InputStream input = upLoadFile.getInputStream();
                        Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        if (!lesson.getMediaPath().equals(" ")) {
                            uploadDir = new File("src/main/resources/public/" + lesson.getMediaPath());
                            uploadDir.delete();
                        }
                    } else {
                        mediaPath = lesson.getMediaPath();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println(" " + request.queryParams("delete_media") + "  " + request.queryParams("delete_document"));
                Lesson.updateLesson(lesson.getLessonID(), request.queryParams("title"), Integer.parseInt(request.queryParams("courseID")), request.queryParams("requirement"), mediaPath, documentPath);
                response.redirect("/HomepageProf/ViewCourse/"+course.getCourseID());
                return null;
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(PROFUPDATELESSON);
            }
        });

        post("/HomepageProf/ViewCourse/DeleteLesson", (request, response) -> {
            Course course = (Course) template.getModel("course");
            Lesson.deleteLesson(Integer.parseInt(request.queryParams("lessonID")),request.queryParams("mediaPath"),request.queryParams("documentPath"));
            response.redirect("/HomepageProf/ViewCourse/"+course.getCourseID());
            return null;

        });

        post("/HomepageProf/ViewCourse/UpdateLesson/DeleteMedia", (request, response) -> {
            File uploadDir;
            Lesson lesson= (Lesson) template.getModel("lesson");
            String mediaPath=lesson.getMediaPath();
            String documentPath=lesson.getDocumentPath();

            if(request.queryParams("delete_media") != null)
            {
                uploadDir = new File("src/main/resources/public/" +request.queryParams("delete_media") );
                uploadDir.delete();
                mediaPath=" ";
            }
            if(request.queryParams("delete_document") != null)
            {
                uploadDir = new File("src/main/resources/public/" +request.queryParams("delete_document") );
                uploadDir.delete();
                documentPath=" ";

            }
            Lesson.updateLesson(lesson.getLessonID(), lesson.getTitle(), lesson.getCoursedID(), lesson.getRequirements(),mediaPath,documentPath);

            response.redirect("/HomepageProf/ViewCourse/"+lesson.getCoursedID());
            return null;
        });


        get("/HomepageProf/ViewCourse/:courseid/:lessonid/AddQuiz", (request, response) -> {

            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor"))
            {
                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                List<Lesson> lessons = course.getLessons();
                Lesson lesson = null;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getLessonID() == lessonID) {
                        lesson = lessons.get(i);
                        break;
                    }
                }
                template.setModel("lesson", lesson);
                return template.render(ADDQUIZ);
            }
            else {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        post("/HomepageProf/ViewCourse/Lesson/AddQuiz", (request, response) -> {
            Course course = (Course) template.getModel("course");
            ArrayList<String> questions=new ArrayList<>();
            ArrayList<String> answers=new ArrayList<>();

            for(int i=0; i<3;i++)
            {
                String question=request.queryParams("question"+(i+1));
                question=question.trim();
                if( question.length()!=0)
                {
                    questions.add(question);
                    answers.add(request.queryParams("answer" + (i + 1)));

                }
            }
            if(questions.size()==3) {
                Lesson.createQuiz(Integer.parseInt(request.queryParams("lessonID")), questions, answers);
                response.redirect("/HomepageProf/ViewCourse/"+course.getCourseID());
                return null;
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(ADDQUIZ);
            }
        });

        get("/HomepageProf/ViewCourse/:courseid/:lessonid/UpdateQuiz", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor")) {
                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                List<Lesson> lessons = course.getLessons();
                Lesson lesson = null;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getLessonID() == lessonID) {
                        lesson = lessons.get(i);
                        break;
                    }
                }
                template.setModel("lesson", lesson);
                return template.render(UPDATEQUIZ);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        post("/HomepageProf/ViewCourse/UpdateQuiz", (request, response) -> {
            Course course = (Course) template.getModel("course");
            ArrayList<String> questions=new ArrayList<>();
            ArrayList<String> answers=new ArrayList<>();
            for(int i=0; i<3;i++)
            {
                String question=request.queryParams("question"+(i+1));
                question=question.trim();
                if( question.length()!=0)
                {
                    questions.add(question);
                    answers.add(request.queryParams("answer" + (i + 1)));

                }
            }
            if(questions.size()==3) {
                Lesson.updateQuiz(Integer.parseInt(request.queryParams("lessonID")), questions, answers);
                response.redirect("/HomepageProf/ViewCourse/" + course.getCourseID());
                return null;
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(UPDATEQUIZ);
            }
                });

        post("/HomepageProf/ViewCourse/DeleteQuiz", (request, response) -> {
            Course course = (Course) template.getModel("course");
            Lesson.deleteQuiz(Integer.parseInt(request.queryParams("lessonid")));
            response.redirect("/HomepageProf/ViewCourse/"+course.getCourseID());
            return null;
        });

        get("/HomepageProf/ViewCourse/ViewLesson/:courseid/:lessonid", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor")) {

                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                List<Lesson> lessons = course.getLessons();
                Lesson lesson = null;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getLessonID() == lessonID) {
                        lesson = lessons.get(i);
                        System.out.print("Checking the Lesson ID List22: " + lessons.get(0).getLessonID());
                        break;
                    }
                }
                template.setModel("lesson", lesson);
                return template.render(PROFVIEWLESSON);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });



        get("/HomepageAdmin", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Admin")) {
                template.setModel("courseCount", Course.allCourses().size());
                template.setModel("courses",Course.allCourses());
                template.setModel("professors",Professor.allProfessors());
                template.setModel("learners",Learner.allLearners());
                template.removeModel("blankSpaces");
                 return template.render(HOMEADMIN);
            } else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        get("/HomepageAdmin/CreateCourse", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Admin")) {
                template.setModel("professors",Professor.allProfessors());
                return template.render(CREATECOURSE);
            } else {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        post("/HomepageAdmin/CreateCourse", (request, response) -> {

            int cLength=request.queryParams("courseName").trim().length() ;

            if(cLength>0) {
                System.out.println(request.queryParams("courseName") + " " + request.queryParams("requirement") + " " + request.queryParams("objectives") + " " + request.queryParams("outcomes") + " " + request.queryParams("prerequisite") + " " + request.queryParams("professor"));
                Course.createNewCourse(request.queryParams("professor"), request.queryParams("courseName"), request.queryParams("objectives"), request.queryParams("outcomes"), request.queryParams("prerequisite"), request.queryParams("requirement"));
                response.redirect("/HomepageAdmin");
                return null;
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(CREATECOURSE);
            }
        });

        get("/HomepageAdmin/UpdateCourse/:courseid", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Admin")) {
                template.setModel("course", Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
                System.out.println(Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
                return template.render(UPDATECOURSE) ;
             } else {
                    response.redirect("/");
                    return "You are not logged in!";
                 }
        });

        post("/HomepageAdmin/UpdateCourse", (request, response) -> {

            int cLength=request.queryParams("courseName").trim().length() ;

            if(cLength>0) {
                System.out.println(request.queryParams("courseName") + " " + request.queryParams("requirement") + " " + request.queryParams("objectives") + " " + request.queryParams("outcomes") + " " + request.queryParams("prerequisite") + " " + request.queryParams("professor"));
                Course course = (Course) template.getModel("course");
                Course.updateCourse((String.valueOf(course.getCourseID())), request.queryParams("professor"), request.queryParams("courseName"), request.queryParams("objectives"), request.queryParams("outcomes"), request.queryParams("prerequisite"), request.queryParams("requirement"));
                response.redirect("/HomepageAdmin");
                return null;
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(UPDATECOURSE);
            }

        });

        post("/HomepageAdmin/DeleteCourse", (request, response) -> {

            System.out.println(request.queryParams("courseid"));
            Course.deleteCourse(Integer.parseInt(request.queryParams("courseid")));
            response.redirect("/HomepageAdmin");
            return null;

        });



        get("/DiscussionBoard", (request, response) -> {
            if (request.session().attribute("currentUser") != null) {
                return template.render(DISCUSSIONGROUP);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";

            }
        });

        get("/HomepageAdmin/CreateDiscussionGroup", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Admin")) {

                return template.render(CREATEDISCUSSIONGROUP);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        post("/HomepageAdmin/CreateDiscussionGroup", (request, response) -> {
            int tLength=request.queryParams("topic").trim().length() ;

            if(tLength>0) {
                System.out.println(request.queryParams("topic") + " " + request.queryParams("type") + " " + request.queryParams("relatedCourse"));
                Administrator admin = (Administrator) template.getModel("userData");
                boolean isCreated=admin.createDiscussionGroup(request.queryParams("topic"), request.queryParams("relatedCourse"), request.queryParams("type"));
                if(isCreated) {
                    response.redirect("/HomepageAdmin");
                    return null;
                }
                else
                {
                    template.setModel("topicUnique",true);
                    return template.render(CREATEDISCUSSIONGROUP);
                }
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(CREATEDISCUSSIONGROUP);
            }
        });


        post("/DiscussionBoard", (request, response) -> {

            System.out.println(request.queryParams("textFromUser") );

            String user = "<b> Username </b>";
            String message = user + " : " + request.queryParams("textFromUser") ;

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
