package com.mypls.course;

import com.mypls.DatabaseManager;

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

    public static boolean deleteLesson(int lessonID)
    {
        return DatabaseManager.deleteLesson(lessonID);
    }
}
