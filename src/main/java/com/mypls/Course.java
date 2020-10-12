package com.mypls;

public class Course {
    private String name;
    private int courseID;
    private int assignedProfessorId;
    private int prerequisiteCourseId;
    private String requirements;
    private String objectives;
    private String outcomes;


    public Course(String name, int courseID, int assignedProfessorId, int prerequisiteCourseId, String requirements, String objectives, String outcomes) {
        this.name = name;
        this.courseID = courseID;
        this.assignedProfessorId = assignedProfessorId;
        this.prerequisiteCourseId = prerequisiteCourseId;
        this.requirements = requirements;
        this.objectives = objectives;
        this.outcomes = outcomes;
    }

    public Course(String name, int courseID, int assignedProfessorId) {
        this(name,courseID,assignedProfessorId,0,"","","");
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
}
