/**
 * Quiz class for MyPLS system.
 */
package com.mypls.course;

import com.mypls.DatabaseManager;

import java.util.ArrayList;

public class Quiz {

    private ArrayList<String> questions;
    private ArrayList<String> answers;
    private double grade;

    public Quiz(ArrayList<String> questions, ArrayList<String> answers) {
        this.questions = questions;
        this.answers = answers;
    }

    public ArrayList<String> getQuestions() {
        return questions;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

}
