/**
 * Learner class representing a learner for MyPLS system.
 */
package com.mypls.users;

import com.mypls.DatabaseManager;

import java.util.ArrayList;

public class Learner extends User {

    private int  learnerID;
    private String type;
    private double rating;
    private int numOfRatings;





    public Learner(int  learnerID,String firstName, String lastName, String email,String type, double rating, int numOfRatings) {
        super(firstName,lastName,email);
        this.learnerID=learnerID;
        this.type=type;
        this.rating=rating;
        this.numOfRatings=numOfRatings;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLearnerID() {
        return learnerID;
    }

    public void setLearnerID(int learnerID) {
        this.learnerID = learnerID;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    /**
     * Returns list of learners from database
     */
    public static ArrayList<Learner> getAllLearners() {
        ArrayList<Learner> learners = DatabaseManager.getAllLearners();
        return learners;
    }

    /**
     * Update rating for professor and increment number of rating.
     * @param newRating new rating form professor.
     */
    public  double updateLearnerRating(int newRating)
    {
        this.rating= rate.computeRating(learnerID,newRating,rating,numOfRatings);
        this.numOfRatings++;
        return rating;
    }


    @Override
    public String toString() {
        return "Learner{" +
                "firstName='" + firstName + '\'' +
                ", LastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
