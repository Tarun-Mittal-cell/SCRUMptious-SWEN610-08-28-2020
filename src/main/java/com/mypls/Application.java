/**
 * Main application of MyPLS system running Spark.
 */
package com.mypls;
import com.mypls.course.Course;
import com.mypls.course.Lesson;
import com.mypls.course.Quiz;
import com.mypls.discussionGroup.DiscussionGroup;
import com.mypls.users.*;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;
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
    static final String REVIEWCOURSE = "ReviewCourse.ftlh";
    static final String VIEWALLCOURSES = "LearnerViewAllCourses.ftlh";

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

       // Login page of MyPLs System
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

        // Login post request handler for a user signing in
        post("/Login", (request, response) ->{
            //Method to login of user class to verify user credentials.
            HashMap<String, Object> userData = User.login(request.queryParams("email"),request.queryParams("password")) ;

            //if verified check user type to direct to correct page.
            if(userData.get("loginStatus").equals(AUTHENTICATED))
            {
                request.session(true);
                request.session().attribute("currentUser", request.queryParams("email"));
                template.setModel("isEmailUnique", true);
                template.setModel("loginStatus",userData.get("loginStatus"));
                // Based on user type direct to appropriate page
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

        //Display registration page
        get("/Registration", (request, response) -> {
            //Re-direct to correct page if already signed in
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

        //handler for post request from registration page
        post("/Registration", (request, response) -> {

            int fLength=request.queryParams("fname").trim().length() ;
            int lLength= request.queryParams("lname").trim().length() ;
            int elength=request.queryParams("email").trim().length();

            if(fLength>0&&lLength>0&&elength>0) {
                //Call to register method. Returns true if email is unique.
                boolean isEmailUnique = User.register(request.queryParams("fname"), request.queryParams("lname"), request.queryParams("type"), request.queryParams("email"), request.queryParams("password_1"));
                HashMap<String, Object> userData;
                //if email is unique, login the new user in and start session.
                if (isEmailUnique) {
                    request.session(true);
                    request.session().attribute("currentUser", request.queryParams("email"));

                    userData = User.login(request.queryParams("email"), request.queryParams("password_1"));
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

        //Sign out user
        get("/Signout", (req, response) -> {
            req.session().removeAttribute("currentUser");
            template.removeAll();
            response.redirect("/");
            return template.render(LOGIN);
        });

        //Display learner homepage
        get("/HomepageLearner", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner"))
            {
                template.removeModel("blankSpaces");
                //Retrieve all registered Course  with professors.
                ArrayList<Professor> courseProfessors=new ArrayList<>();
                ArrayList<Course> courseList =DatabaseManager.getAllRegisteredCourses(learner.getLearnerID());
                for (Course course :courseList)
                {
                    Professor professor=Professor.getProfessor(course.getAssignedProfessorId());
                    courseProfessors.add(professor);
                }

                template.setModel("registeredCourses",courseList);
                template.setModel("courseProfessors",courseProfessors);
                template.removeModel("score");
                template.removeModel("blanks");
                template.removeModel("courses");
                template.removeModel("prereqPassed");
                return template.render(HOME);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });


        //Display course to learner
        get("/HomepageLearner/ViewCourse/:courseid", (request, response) -> {

            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner"))
            {
                //retrieve course to be displayed.
                Course course = Course.getCourseByID(Integer.parseInt(request.params(":courseid")));
                //retrieve lessons and their associated quiz.
                for (Lesson lesson:course.getLessons())
                {
                    double grade=DatabaseManager.retrieveGrade(learner.getLearnerID(), lesson.getLessonID());
                    System.out.println("First score:"+grade);
                    if(lesson.getQuiz()!=null)
                    {
                        lesson.getQuiz().setGrade(grade);
                        System.out.println("Grade :"+lesson.getQuiz().getGrade());

                    }
                }

                template.removeModel("blankSpaces");
                template.removeModel("score");
                template.removeModel("blanks");
                course.setMinScore(50);
                template.setModel("course", course);
                return template.render(LEARNERVIEWCOURSE);
            }
            else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        //Display all courses to learner
        get("/HomepageLearner/ViewAllCourses", (request, response) -> {

            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner"))
            {
               //Retrieve all course and display to learner..
                template.setModel("courses",Course.getAllCourses());
                return template.render(VIEWALLCOURSES);
            }
            else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        //Display lesson to learner.
        get("/HomepageLearner/ViewCourse/ViewLesson/:courseid/:lessonid", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner")) {

                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                List<Lesson> lessons = course.getLessons();
                //Find specific lesson from list of lessons.
                Lesson lesson = null;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getLessonID() == lessonID) {
                        lesson = lessons.get(i);
                        break;
                    }
                }

                template.setModel("lesson", lesson);
                return template.render(LEARNERVIEWLESSON);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        //Display quiz to learner
        get("/HomepageLearner/ViewCourse/:courseid/:lessonid/TakeQuiz", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner")) {
                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                int lessonIndex =0;
                List<Lesson> lessons = course.getLessons();
                //Find specific lesson from list of lessons.
                Lesson lesson = null;
                for (int i = 0; i < lessons.size(); i++) {
                    if (lessons.get(i).getLessonID() == lessonID) {
                        lesson = lessons.get(i);
                        break;
                    }
                    lessonIndex++;
                }
                template.setModel("lessonIndex", lessonIndex);
                template.setModel("lesson", lesson);

                return template.render(TAKEQUIZ);
            }
            else
            {
                response.redirect("/");
                return "You are not logged in!";
            }
        });
        // Post request when user submits the quiz
        post("/HomepageProf/ViewCourse/TakeQuiz", (request, response) -> {

            Course course = (Course) template.getModel("course");
            Lesson lesson=(Lesson) template.getModel("lesson");
            ArrayList<String> choices=new ArrayList<>();
            //Get quiz from lesson
            Quiz quiz=lesson.getQuiz();
            //Retrieve answers user submitted.
            for(int i=0; i<3;i++)
            {
                String choice=request.queryParams("answer"+(i+1));
                choices.add(request.queryParams("answer" + (i + 1)));

            }
            int lessonCount=Integer.parseInt(request.queryParams("lessonCount"));
            //Compute learner quiz score
            double score= Lesson.takeQuiz(learner.getLearnerID(),lesson.getCoursedID(),lesson.getLessonID(),choices, quiz.getAnswers(),false);
           // If score greater than passmark for course and it is the last quiz mark quiz as complete.
            if(score> course.getMinScore()&&course.getLessons().size()==lessonCount)
            {
                DatabaseManager.updateLearnerCourseStatus(learner.getLearnerID(), course.getCourseID(), "Completed");
            }
            template.setModel("score",score);
            template.setModel("choices",choices);
            return template.render(TAKEQUIZ);
        });

        //Post handler for retaking a quiz
        post("/HomepageProf/ViewCourse/RetakeQuiz", (request, response) -> {

            Course course = (Course) template.getModel("course");
            Lesson lesson=(Lesson) template.getModel("lesson");
            ArrayList<String> choices=new ArrayList<>();
            //Get quiz from lesson
            Quiz quiz=lesson.getQuiz();
            //Retrieve answers user submitted.
            for(int i=0; i<3;i++)
            {
                String choice=request.queryParams("answer"+(i+1));
                choices.add(request.queryParams("answer" + (i + 1)));

            }
            int lessonCount=Integer.parseInt(request.queryParams("lessonCount"));
            //Compute learner quiz score
            double score= Lesson.takeQuiz(learner.getLearnerID(),lesson.getCoursedID(),lesson.getLessonID(),choices, quiz.getAnswers(),true);
            // If score greater than passmark for course and it is the last quiz mark quiz as complete.
            if(score> course.getMinScore()&&course.getLessons().size()==lessonCount)
            {
                DatabaseManager.updateLearnerCourseStatus(learner.getLearnerID(), course.getCourseID(), "Completed");
            }
            template.setModel("score",score);
            template.setModel("choices",choices);
            return template.render(TAKEQUIZ);
        });

        //Display learner search result for course.
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

        //Post to handle user search input
        post("/HomepageLearner/Search", (request, response) -> {

            String search=request.queryParams("search").trim();
            int courseID=Integer.parseInt(search);
            //Retrieve course by ID
            Course course= Course.getCourseByID(courseID);
            //Retrieve associated professor for course.
            Professor professor=Professor.getProfessor(course.getAssignedProfessorId());
            template.setModel("professor",professor);
            template.setModel("course",course);
            response.redirect("/HomepageLearner/Search");
            return null;
        });

        //Display course outline.
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
        //Register user for course
        post("/HomepageLearner/CourseRegister", (request, response) -> {
            //retrieve course
            Course course=Course.getCourseByID(Integer.parseInt(request.queryParams("courseID")));
            boolean prereqPassed;
            //Check if course has a prerequisite and determine if learner has already passed.
            if(course.getPrerequisiteCourseId()!=0)
            {
                prereqPassed=DatabaseManager.retrievePrereqStatus(learner.getLearnerID(),course.getPrerequisiteCourseId());
            }
            else
            {
                prereqPassed=true;
            }
            // if user has has passed register the user.
            if(prereqPassed)
            {
                boolean isAdded=Course.registerCourse(learner.getLearnerID(),Integer.parseInt(request.queryParams("courseID")));
                if(isAdded) {
                    DatabaseManager.updateCourseEnrollment(course.getCourseID(),course.getEnrollment()+1);
                    response.redirect("/HomepageLearner");
                }
            }
            else
            {
                template.setModel("prereqPassed",false);
                return template.render(SEARCH);
            }

            return null;
        });

        //Display course review page to learner.
        get("/HomepageLearner/ViewCourse/:courseid/Review", (request, response) -> {

            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Learner"))
            {
                //retrieve course and professor information.
                Course course = Course.getCourseByID(Integer.parseInt(request.params(":courseid")));
                Professor professor=Professor.getProfessor(course.getAssignedProfessorId());

                template.setModel("professor",professor);
                template.setModel("course",course);

                return template.render(REVIEWCOURSE);
            }
            else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        //Post handler for ratings submitted by the user.
        post("/HomepageLearner/ViewCourse/ReviewCourse", (request, response) -> {
            Course course= (Course) template.getModel("course");
            boolean blanks=false;
            //Check for blank spaces
            if( request.queryParams().size() != (course.getLessons().size() + 2)) {
                blanks = true;
                template.setModel("blanks", blanks);
                return template.render(REVIEWCOURSE);
            }

            int newProfessorRating = Integer.parseInt(request.queryParams("courseRating"));
            int newCourseRating = Integer.parseInt(request.queryParams("profRating"));

            //Update all lession ratings
            for (int i = 1; i <=course.getLessons().size(); i++)
            {
                Lesson.updateLessonRating(course.getLessons().get(i-1),Integer.parseInt( request.queryParams("LessonRating"+i)));
            }
            //Update  course ratings
            Course.updateCourseRating(course,newCourseRating);
            //Update professor rating.
            Professor professor1= DatabaseManager.queryProfessorByID(course.getAssignedProfessorId());
            professor1.setRate(new RateProfessor());
            professor1.updateProfessorRating(newProfessorRating);
            //Mark course as reviewed.
            Course.markAsReviewed(learner.getLearnerID(),course.getCourseID());

            response.redirect("/HomepageLearner");

            return null;
        });

        //Display professor homepage
        get("/HomepageProf", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor"))
            {
                template.removeModel("blankSpaces");
                //Retrieve assgined course
                template.setModel("courses",Course.getAssignedCourses(professor.getProfessorID()));
                return template.render(HOMEPROF);
            } else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });

        //Display add lesson to course page for professor
        get("HomepageProf/AddLesson/:courseid", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor"))
            {
                //retrieve course
                template.setModel("course", Course.getCourseByID( Integer.parseInt(request.params(":courseid"))));
                return template.render(ADDLESSON) ;
            } else {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        //Display view course page to professor
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

        //Post handler for adding lesson to course.
        post("/AddLesson", (request, response) -> {

            Part uploadfile=null;
            request.attribute("org.eclipse.jetty.multipartConfig", new MultipartConfigElement("/temp"));
            String mediaPath=" ";
            String documentPath=" ";
            int tLength=request.queryParams("title").trim().length() ;
            Course course = (Course) template.getModel("course");

            if(tLength>0) {
                try
                {
                    //Retrieve pdf file that user uploaded.
                    uploadfile = request.raw().getPart("uploaded_pdf");
                    //Check check if a file was uploaded.
                    if (uploadfile.getSize() != 0)
                    {
                        String fileName = getFileName(request.raw().getPart("uploaded_pdf"));
                        //store document path
                        documentPath = "documents/" + fileName;
                        //create file
                        File uploadDir = new File("src/main/resources/public/documents/" + fileName);
                        //get input stream
                        InputStream input = uploadfile.getInputStream();
                        //Copy stream to file created.
                        Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);

                    }
                    //Retrieve media file that user uploaded.
                    uploadfile = request.raw().getPart("uploaded_media");
                    //Check a file was uploaded.
                    if (uploadfile.getSize() != 0)
                    {
                        String fileName = getFileName(request.raw().getPart("uploaded_media"));
                        //store media path
                        mediaPath = "media/" + fileName;
                        //create file
                        File uploadDir = new File("src/main/resources/public/media/" + fileName);
                        //get input stream
                        InputStream input = uploadfile.getInputStream();
                        //Copy stream to file created.
                        Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                //Create lesson
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

        //Display update lesson page to professor
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
        //Post handler to update lesson
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
                    //Retrieve pdf file that user uploaded.
                    upLoadFile = request.raw().getPart("uploaded_pdf");
                    //Check check if a file was uploaded.
                    if (upLoadFile.getSize() != 0)
                    {
                        String fileName = getFileName(request.raw().getPart("uploaded_pdf"));
                        //store document path
                        documentPath = "documents/" + fileName;
                        //create file
                        uploadDir = new File("src/main/resources/public/documents/" + fileName);
                        //get input stream
                        InputStream input = upLoadFile.getInputStream();// getPart needs to use same "name" as input field in form
                        //Copy stream to file created.
                        Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        if (!lesson.getDocumentPath().equals(" ")) {
                            uploadDir = new File("src/main/resources/public/" + lesson.getDocumentPath());
                            uploadDir.delete();
                        }
                    }
                    else {
                        documentPath = lesson.getDocumentPath();
                    }
                    //Retrieve pdf file that user uploaded.
                    upLoadFile = request.raw().getPart("uploaded_media");
                    //Check check if a file was uploaded.
                    if (upLoadFile.getSize() != 0)
                    {
                        String fileName = getFileName(request.raw().getPart("uploaded_media"));
                        //store media path
                        mediaPath = "media/" + fileName;
                        //create file
                        uploadDir = new File("src/main/resources/public/media/" + fileName);
                        //get input stream
                        InputStream input = upLoadFile.getInputStream();
                        //Copy stream to file created.
                        Files.copy(input, uploadDir.toPath(), StandardCopyOption.REPLACE_EXISTING);
                        if (!lesson.getMediaPath().equals(" "))
                        {
                            uploadDir = new File("src/main/resources/public/" + lesson.getMediaPath());
                            uploadDir.delete();
                        }
                    } else {
                        mediaPath = lesson.getMediaPath();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

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
        //Post handler to delete lesson
        post("/HomepageProf/ViewCourse/DeleteLesson", (request, response) -> {
            Course course = (Course) template.getModel("course");
            Lesson.deleteLesson(Integer.parseInt(request.queryParams("lessonID")),request.queryParams("mediaPath"),request.queryParams("documentPath"));
            response.redirect("/HomepageProf/ViewCourse/"+course.getCourseID());
            return null;

        });
        //Post handler to delete media.
        post("/HomepageProf/ViewCourse/UpdateLesson/DeleteMedia", (request, response) -> {
            File uploadDir;
            Lesson lesson= (Lesson) template.getModel("lesson");
            String mediaPath=lesson.getMediaPath();
            String documentPath=lesson.getDocumentPath();

            if(request.queryParams("delete_media") != null)
            {
               //Delete pdf file
                uploadDir = new File("src/main/resources/public/" +request.queryParams("delete_media") );
                uploadDir.delete();
                mediaPath=" ";
            }
            if(request.queryParams("delete_document") != null)
            {
                //Delete media file
                uploadDir = new File("src/main/resources/public/" +request.queryParams("delete_document") );
                uploadDir.delete();
                documentPath=" ";

            }
            Lesson.updateLesson(lesson.getLessonID(), lesson.getTitle(), lesson.getCoursedID(), lesson.getRequirements(),mediaPath,documentPath);

            response.redirect("/HomepageProf/ViewCourse/"+lesson.getCoursedID());
            return null;
        });

        // Display add quiz page to professor
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
        //Post to handle  professor submitting a new quiz to add.
        post("/HomepageProf/ViewCourse/Lesson/AddQuiz", (request, response) -> {
            Course course = (Course) template.getModel("course");
            ArrayList<String> questions=new ArrayList<>();
            ArrayList<String> answers=new ArrayList<>();
            //retrieve questions and answers from user.
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
            //Check that all three questions were created.
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

        //Display update quiz page to professor.
        get("/HomepageProf/ViewCourse/:courseid/:lessonid/UpdateQuiz", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor")) {
                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                //Retrieve specific lesson.
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

        //Post Handler for for update quiz
        post("/HomepageProf/ViewCourse/UpdateQuiz", (request, response) -> {
            Course course = (Course) template.getModel("course");
            ArrayList<String> questions=new ArrayList<>();
            ArrayList<String> answers=new ArrayList<>();
            //retrieve questions and answers from user.
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
            //Check that all three questions were created.
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

        //Post to delete Quiz
        post("/HomepageProf/ViewCourse/DeleteQuiz", (request, response) -> {
            Course course = (Course) template.getModel("course");
            Lesson.deleteQuiz(Integer.parseInt(request.queryParams("lessonid")));
            response.redirect("/HomepageProf/ViewCourse/"+course.getCourseID());
            return null;
        });
        //Post to view lesson
        get("/HomepageProf/ViewCourse/ViewLesson/:courseid/:lessonid", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Professor")) {

                Course course = (Course) template.getModel("course");
                int lessonID = Integer.parseInt(request.params(":lessonid"));
                //Retrieve specific lesson.
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


        //Display admin homepage.
        get("/HomepageAdmin", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Admin")) {
                template.setModel("courseCount", Course.getAllCourses().size());
                template.setModel("courses",Course.getAllCourses());
                template.setModel("professors",Professor.getAllProfessors());
                template.setModel("learners",Learner.getAllLearners());
                template.removeModel("blankSpaces");
                 return template.render(HOMEADMIN);
            } else {
                response.redirect("/");
                return "You are not logged in!";
            }

        });
        //Display create course page
        get("/HomepageAdmin/CreateCourse", (request, response) -> {
            if (request.session().attribute("currentUser") != null && request.session().attribute("Type").equals("Admin")) {
                template.setModel("professors",Professor.getAllProfessors());
                return template.render(CREATECOURSE);
            } else {
                response.redirect("/");
                return "You are not logged in!";
            }
        });

        //Post for creating a course
        post("/HomepageAdmin/CreateCourse", (request, response) -> {

            int cLength=request.queryParams("courseName").trim().length() ;

            if(cLength>0) {
                //Create course
                Course.createCourse(request.queryParams("professor"), request.queryParams("courseName"), request.queryParams("objectives"), request.queryParams("outcomes"), request.queryParams("prerequisite"), request.queryParams("requirement"));
                response.redirect("/HomepageAdmin");
                return null;
            }
            else
            {
                template.setModel("blankSpaces",true);
                return template.render(CREATECOURSE);
            }
        });
        //Display course update page to admin
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
        //Post to update course .
        post("/HomepageAdmin/UpdateCourse", (request, response) -> {

            int cLength=request.queryParams("courseName").trim().length() ;

            if(cLength>0) {
                Course course = (Course) template.getModel("course");
                //Update Course
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

        //Post  to delete course
        post("/HomepageAdmin/DeleteCourse", (request, response) -> {

            Course.deleteCourse(Integer.parseInt(request.queryParams("courseid")));
            response.redirect("/HomepageAdmin");
            return null;

        });
        //Display discusson board.
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
        //Display create discussion group page.
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
        //Post to create a discussion group.
        post("/HomepageAdmin/CreateDiscussionGroup", (request, response) -> {
            int tLength=request.queryParams("topic").trim().length() ;

            if(tLength>0) {
               //Create discussion group
                boolean isCreated= DiscussionGroup.createDiscussionGroup(request.queryParams("topic"), request.queryParams("relatedCourse"), request.queryParams("type"));
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
        //Post to display messages from users
        post("/DiscussionBoard", (request, response) -> {

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

    /**
     * Get file name from Part object
     * @param part Part object recieved fom submitted file
     * @return
     */
    private static String getFileName(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                return cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }


}
