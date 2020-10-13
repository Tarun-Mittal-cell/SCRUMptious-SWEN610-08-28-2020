package com.mypls.users;

public class Professor {

    private int professorID;
    private String firstName;
    private String LastName;
    private String email;
    private double rating;
    private int numberOfRatings;




    public Professor(int professorID,String firstName, String lastName, String email,double rating,int numberOfRatings)
    {
       this.professorID=professorID;
        this.firstName = firstName;
        this.LastName = lastName;
        this.email = email;
        this.rating=rating;
        this.numberOfRatings=numberOfRatings;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getProfessorID() {
        return professorID;
    }

    public double getRating() {
        return rating;
    }

    public int getnumberOfRatings() {
        return numberOfRatings;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
