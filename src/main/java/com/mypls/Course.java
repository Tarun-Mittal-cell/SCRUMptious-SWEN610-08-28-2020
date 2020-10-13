package com.mypls;

public class Course {
    private String name;
    private int courseID;
    private int assignedProfessorId;
    private int prerequisiteCourseId;
    private String requirements;
    private String objectives;
    private String outcomes;
    private double rating;
    private int numOfRatings;
    private int enrollment;
    private double minScore;

    public Course(String name, int courseID, int assignedProfessorId, int prerequisiteCourseId, String requirements, String objectives, String outcomes, double rating, int numOfRatings, int enrollment, double minScore) {
        this.name = name;
        this.courseID = courseID;
        this.assignedProfessorId = assignedProfessorId;
        this.prerequisiteCourseId = prerequisiteCourseId;
        this.requirements = requirements;
        this.objectives = objectives;
        this.outcomes = outcomes;
        this.rating = rating;
        this.numOfRatings = numOfRatings;
        this.enrollment = enrollment;
        this.minScore=minScore;
    }


    public Course(String name, int courseID, int assignedProfessorId) {
        this(name,courseID,assignedProfessorId,0,"","","",0,0,0,0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getAssignedProfessorId() {
        return assignedProfessorId;
    }

    public void setAssignedProfessorId(int assignedProfessorId) {
        this.assignedProfessorId = assignedProfessorId;
    }

    public int getPrerequisiteCourseId() {
        return prerequisiteCourseId;
    }

    public void setPrerequisiteCourseId(int prerequisiteCourseId) {
        this.prerequisiteCourseId = prerequisiteCourseId;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getObjectives() {
        return objectives;
    }

    public void setObjectives(String objectives) {
        this.objectives = objectives;
    }

    public String getOutcomes() {
        return outcomes;
    }

    public void setOutcomes(String outcomes) {
        this.outcomes = outcomes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public int getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(int enrollment) {
        this.enrollment = enrollment;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getMinScore() {
        return minScore;
    }

    public void setMinScore(double minScore) {
        this.minScore = minScore;
    }

    public static Course getCourseByID(int id)
    {
        return DatabaseController.queryByCourseID(id);
    }

    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", courseID=" + courseID +
                ", assignedProfessorId=" + assignedProfessorId +
                ", prerequisiteCourseId=" + prerequisiteCourseId +
                '}';
    }
}
