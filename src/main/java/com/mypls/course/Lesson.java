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

    public static boolean createLesson(String title,int courseID,String requirements,String mediaPath,String documentPath)
    {
        return DatabaseManager.addNewLesson(title,courseID,requirements,mediaPath,documentPath);
    }
    public static boolean updateLesson(int lessonID, String title,int courseID,String requirements,String mediaPath,String documentPath)
    {
        return DatabaseManager.updateLesson(lessonID,title,courseID,requirements,mediaPath,documentPath);
    }

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

    public static boolean createQuiz(int lessonID, ArrayList<String> question, ArrayList<String>answer)
    {
        return DatabaseManager.addNewQuiz(lessonID,question,answer);
    }

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


}
