package com.mypls.course;

import com.mypls.DatabaseManager;

import java.io.File;
import java.util.ArrayList;

public class Lesson {
    private int LessonID;
    private String title;
    private int coursedID;
    private String requirements;
    private double rating;
    private int numberOfRatings;
    private String mediaPath;
    private String documentPath;
    private  Quiz quiz;

    public Lesson(int lessonID, String title, int coursedID, String requirements,double rating, int numberOfRatings, String mediaPath, String documentPath) {
        LessonID = lessonID;
        this.title = title;
        this.coursedID = coursedID;
        this.requirements = requirements;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
        this.mediaPath = mediaPath;
        this.documentPath = documentPath;
        this.quiz=null;
    }


    public int getLessonID() {
        return LessonID;
    }

    public void setLessonID(int lessonID) {
        LessonID = lessonID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCoursedID() {
        return coursedID;
    }

    public void setCoursedID(int coursedID) {
        this.coursedID = coursedID;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public void setNumberOfRatings(int numberOfRatings) {
        this.numberOfRatings = numberOfRatings;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     * Create lessson in database
     * @param title lesson title
     * @param courseID associated course id
     * @param requirements lesson requirements
     * @param mediaPath path to media for lesson
     * @param documentPath document path for lesson
     * @return
     */
    public static boolean createLesson(String title,int courseID,String requirements,String mediaPath,String documentPath)
    {
        return DatabaseManager.addNewLesson(title,courseID,requirements,mediaPath,documentPath);
    }

    /**
     * Update lessson in database
     * @param title lesson title
     * @param courseID associated course id
     * @param requirements lesson requirements
     * @param mediaPath path to media for lesson
     * @param documentPath document path for lesson
     * @return
     */
    public static boolean updateLesson(int lessonID, String title,int courseID,String requirements,String mediaPath,String documentPath)
    {
        return DatabaseManager.updateLesson(lessonID,title,courseID,requirements,mediaPath,documentPath);
    }

    /**
     * Update Quiz in database.
     * @param lessonID lesson id
     * @param questions list of questions for quiz
     * @param answers list of answers for quiz
     */
    public static boolean updateQuiz(int lessonID, ArrayList<String> questions, ArrayList<String>answers)
    {
        boolean delete= DatabaseManager.removeQuiz(lessonID);
       boolean add= DatabaseManager.addNewQuiz(lessonID,questions,answers);

        return delete&&add;
    }

    public static boolean deleteQuiz(int lessonID)
    {
        return DatabaseManager.removeQuiz(lessonID);
    }

    /**
     * Create Quiz in database.
     * @param lessonID lesson id
     * @param questions list of questions for quiz
     * @param answers list of answers for quiz
     */
    public static boolean createQuiz(int lessonID, ArrayList<String> questions, ArrayList<String>answers)
    {
        return DatabaseManager.addNewQuiz(lessonID,questions,answers);
    }

    /**
     * Delete lesson and associated material.
     * @param lessonID lesson id
     * @param mediaPath path where the media is stored
     * @param documentPath path where the document is stored
     * @return
     */
    public static boolean deleteLesson(int lessonID, String mediaPath,String documentPath)
    {
        File uploadDir;
        try {
            uploadDir = new File("src/main/resources/public/"+mediaPath );
            uploadDir.delete();
            uploadDir = new File("src/main/resources/public/"+documentPath );
            uploadDir.delete();
        }
        catch (Exception e)
        {
            e.printStackTrace();;
        }
        DatabaseManager.removeQuiz(lessonID);
        return DatabaseManager.deleteLesson(lessonID);
    }

    /**
     * Update lesson rating.
     * @param lesson lesson to be updated.
     * @param newRating new rating from learner
     */
    public static boolean updateLessonRating(Lesson lesson, int newRating)
    {
        double result=((lesson.getNumberOfRatings()*lesson.getRating())+newRating)/(lesson.getNumberOfRatings()+1);

        return DatabaseManager.updateLessonRating(lesson.getLessonID(),result,(lesson.getNumberOfRatings())+1);
    }

    /**
     * Compute results for learner taking quiz
     * @param learnerID learner taking quiz
     * @param courseID course for the quiz
     * @param lessonID the particular lesson the quiz is for
     * @param choices the answers provided by the learner
     * @param answers the answer set by the professor
     * @param retake whether or not this is a retaken quiz
     * @return
     */
    public static double takeQuiz(int learnerID, int courseID, int lessonID, ArrayList<String> choices, ArrayList<String> answers, boolean retake)
    {
        ArrayList<Boolean> results =new ArrayList<>();
        double correct =0;
        double score =0;
        if(retake)
        {
            for(int i=0; i<3;i++)
            {
                if(choices.get(i).equals(answers.get(i)))
                {
                    correct++;
                }

            }
            score=(correct/3)*100;
            DatabaseManager.updateGrade(learnerID,lessonID,score);
        }
        else
        {


            for(int i=0; i<3;i++)
            {
                if(choices.get(i).equals(answers.get(i)))
                {
                    correct++;
                }

            }
            score=(correct/3)*100;
            DatabaseManager.addGrade(learnerID,courseID,lessonID,score);
        }
        return score;

    }

    @Override
    public String toString() {
        return "Lesson{" +
                "LessonID=" + LessonID +
                ", title='" + title + '\'' +
                ", coursedID=" + coursedID +
                ", requirements='" + requirements + '\'' +
                ", rating=" + rating +
                ", numberOfRatings=" + numberOfRatings +
                ", mediaPath='" + mediaPath + '\'' +
                ", documentPath='" + documentPath + '\'' +
                '}';
    }

}
