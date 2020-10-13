package com.mypls.users;

public class Learner {

    private int  learnerID;
    private String firstName;
    private String LastName;
    private String email;
    private String type;
    private double rating;
    private int numOfRatings;





    public Learner(int  learnerID,String firstName, String lastName, String email,String type, double rating, int numOfRatings) {
        this.learnerID=learnerID;
        this.firstName = firstName;
        this.LastName = lastName;
        this.email = email;
        this.type=type;
        this.rating=0;
        this.numOfRatings=0;
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



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Learner{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + LastName + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
