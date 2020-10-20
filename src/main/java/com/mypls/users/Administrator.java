package com.mypls.users;

import com.mypls.Course;
import com.mypls.DatabaseController;

import java.util.ArrayList;

public class Administrator {

    public ArrayList<Professor> allProfessors() {
        ArrayList<Professor> allProfessor = DatabaseController.getAllProfessor();
        return allProfessor;
    }


    public void createNewCourse(String professorId, String name, String objectives, String outcomes, String prerequisite, String requirement) {
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

        DatabaseController.updateDatabase("INSERT INTO Courses (Name,AssignedProfessorID,PrerequisiteCourseID,Requirements,Objectives,Outcomes) VALUES ('" + name + "', '" + professorId + "', '" + prerequisite + "', '" + requirement + "', \"" + objectives + "\", \"" + outcomes + "\")");


    }

    public void updateCourse(String courseId,String professorId, String name, String objectives, String outcomes, String prerequisite, String requirement) {
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

        DatabaseController.updateDatabase("UPDATE courses SET Name = \""+name+"\",AssignedProfessorID=\""+professorId+"\", PrerequisiteCourseID = \""+prerequisite+"\", Requirements = \""+requirement+"\",Objectives=\""+objectives+"\",Outcomes=\""+outcomes+"\" WHERE (CourseID = \""+courseId+"\")");


    }

    public ArrayList<Course> allCourses() {
        ArrayList<Course> courses = DatabaseController.getAllCourses();
        return courses;
    }

    public ArrayList<Learner> allLearners() {
        ArrayList<Learner> learners = DatabaseController.getAllLearners();
        return learners;
    }




    public void deleteCourse(int id)
    {
        DatabaseController.deleteByCourseID(id);
    }


    public void createDiscussionGroup(String topic, String course,String type)
    {
        String[] temp=course.split(":");
        String courseID=temp[1];

        DatabaseController.updateDatabase("INSERT INTO DiscussionGroups (Topic,RelatedCourseID,Type) VALUES ('" + topic + "', '" + courseID + "', '" + type +"')" );
    }



}
