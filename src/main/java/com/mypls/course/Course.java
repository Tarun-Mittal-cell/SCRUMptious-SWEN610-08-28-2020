/**
 * Course class for MyPLS system.
 */
package com.mypls.course;

import com.mypls.DatabaseManager;
import freemarker.ext.jsp.TaglibFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Course
{
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
    private String Status;
    private List<Lesson> lessons;
    private boolean reviewed;

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
        lessons=new LinkedList<>();
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


    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void addAllLesson(List<Lesson>  lessons)
    {
        this.lessons.addAll(lessons);
    }
    public void addLesson(Lesson lesson)
    {
        lessons.add(lesson);
    }

    public void addLesson(int index,Lesson lesson)
    {
        lessons.add(index,lesson);
    }



    public List<Lesson> getLessons()
    {
        return lessons;
    }

    /**
     * Retrieve course by course id form database.
     * @param id course id.
     */
    public static Course getCourseByID(int id)
    {
        Course course =DatabaseManager.queryByCourseID(id);
        List<Lesson> lessons=DatabaseManager.getLessonsByCourse(id);
        for(int i=0;i<lessons.size();i++)
        {
            ArrayList<String>[] quizData=DatabaseManager.retrieveQuiz(lessons.get(i).getLessonID());
            System.out.println("Array :"+Arrays.toString(quizData));
            if(quizData[0]!=null && quizData[1]!=null )
            {
                System.out.println("Array :"+Arrays.toString(quizData));
                lessons.get(i).setQuiz(new Quiz(quizData[0], quizData[1]));

            }

        }
        course.addAllLesson(lessons);
        return course;
    }

    /**
     * Get all courses from the database
     */
    public static ArrayList<Course> getAllCourses() {
        ArrayList<Course> courses = DatabaseManager.getAllCourses();
        return courses;
    }

    /**
     * Create new course and store to database
     * @param professorId  professor id
     * @param name name of course.
     * @param objectives course objectes
     * @param outcomes course outcomes
     * @param prerequisite course prerequisite
     * @param requirement course requirment
     */
    public static void createCourse(String professorId, String name, String objectives, String outcomes, String prerequisite, String requirement) {
        String[] temp;

        if (prerequisite.equals("None")) {
            prerequisite = "0";
        }
        else if (! prerequisite.equals("None"))
        {
            temp=prerequisite.split("Course ID:");
            prerequisite=temp[1];
        }

        if (professorId.equals("None")) {
            professorId = "0";
        }
        else if (! professorId.equals("None"))
        {
            temp=professorId.split("Professor ID:");
            professorId=temp[1];
        }

        if (objectives == null) {
            objectives = "";
        }

        if (outcomes == null) {
            outcomes = "";
        }
        if (requirement == null) {
            requirement = "";
        }

        DatabaseManager.createNewCourse(name ,Integer.parseInt(professorId.replace(" ","")),Integer.parseInt(prerequisite.replace(" ","")) ,requirement, objectives , outcomes);

    }

    /**
     * Update course and store to database
     * @param professorId  professor id
     * @param name name of course.
     * @param objectives course objectes
     * @param outcomes course outcomes
     * @param prerequisite course prerequisite
     * @param requirement course requirement
     */
    public static void updateCourse(String courseId,String professorId, String name, String objectives, String outcomes, String prerequisite, String requirement) {
        String[] temp;

        if (prerequisite.equals("None")) {
            prerequisite = "0";
        }
        else if (! prerequisite.equals("None"))
        {
            temp=prerequisite.split("Course ID:");
            prerequisite=temp[1];
        }

        if (professorId.equals("None")) {
            professorId = "0";
        }
        else if (! professorId.equals("None"))
        {
            temp=professorId.split("Professor ID:");
            professorId=temp[1];
        }

        if (objectives == null) {
            objectives = "";
        }

        if (outcomes == null) {
            outcomes = "";
        }
        if (requirement == null) {
            requirement = "";
        }

        DatabaseManager.updateCourse(Integer.parseInt(courseId.replace(" ","")) , name , Integer.parseInt(professorId.replace(" ","")) , Integer.parseInt(prerequisite.replace(" ","")) ,requirement, objectives , outcomes);


    }

    /**
     * Delete course form database
     * @param id course id
     */
    public static void deleteCourse(int id)
    {
        DatabaseManager.deleteByCourseID(id);
    }

    /**
     * Get courses assigned to user.
     * @param professorId
     * @return
     */
    public static ArrayList<Course> getAssignedCourses(int professorId)
    {
        return DatabaseManager.queryCourseByProfessor(professorId);
    }

    /**
     * Register learner for new course and add database.
     * @param learnerID
     * @param courseID
     */
    public static boolean registerCourse(int learnerID, int courseID)
    {

        return DatabaseManager.AddLearnerCourse(learnerID,courseID);
    }

    /**
     * Update course rating
     * @param course
     * @param newRating
     * @return
     */
    public static boolean updateCourseRating(Course course, int newRating) {
        double result=((course.getNumOfRatings()*course.getRating())+newRating)/(course.getNumOfRatings()+1);
        return DatabaseManager.updateCourseRating(course.getCourseID(), result, course.getNumOfRatings()+1);
    }

    /**
     * Mark course cours completed.
     * @param learnerID
     * @param courseID
     * @return
     */
    public static boolean markAsReviewed(int learnerID,int courseID) {

        return DatabaseManager.updateLearnerCourseReviewed(learnerID, courseID);
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
