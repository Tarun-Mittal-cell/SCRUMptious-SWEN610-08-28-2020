package com.mypls.users;

import com.mypls.DatabaseManager;

import java.util.ArrayList;

public class Professor extends User {

    private int professorID;
    private double rating;
    private int numberOfRatings;




    public Professor(int professorID,String firstName, String lastName, String email,double rating,int numberOfRatings)
    {
        super(firstName,lastName,email);
        this.professorID=professorID;
        this.rating=rating;
        this.numberOfRatings=numberOfRatings;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getProfessorID() {
        return professorID;
    }

    public double getRating() {
        return rating;
    }

    public int getNumberOfRatings() {
        return numberOfRatings;
    }

    public static ArrayList<Professor> allProfessors() {
        ArrayList<Professor> allProfessor = DatabaseManager.getAllProfessor();
        return allProfessor;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
