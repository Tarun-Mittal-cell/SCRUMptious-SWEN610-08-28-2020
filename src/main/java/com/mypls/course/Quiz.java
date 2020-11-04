package com.mypls.course;

import java.util.ArrayList;

public class Quiz {

    private ArrayList<String> questions;
    private ArrayList<String> answers;

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
}
