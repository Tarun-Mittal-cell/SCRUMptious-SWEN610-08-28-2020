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
        if (prerequisite == null) {
            prerequisite = "0";
        }
        if (objectives == null) {
            prerequisite = "";
        }
        if (outcomes == null) {
            outcomes = "";
        }
        if (requirement == null) {
            requirement = "";
        }

        DatabaseController.updateDatabase("INSERT INTO Courses (Name,AssignedProfessorID,PrerequisiteCourseID,Requirements,Objectives,Outcomes) VALUES ('" + name + "', '" + professorId + "', '" + prerequisite + "', '" + requirement + "', \"" + objectives + "\", \"" + outcomes + "\")");


    }

    public ArrayList<Course> allCourses() {
        ArrayList<Course> courses = DatabaseController.getAllCourses();
        return courses;
    }

    public void deleteCourse(int id)
    {
        DatabaseController.deleteByCourseID(id);
    }






}
