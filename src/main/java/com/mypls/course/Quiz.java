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

    public static double takeQuiz(int learnerID,int courseID,int lessonID, ArrayList<String> choices, ArrayList<String> answers,boolean retake)
    {
        ArrayList<Boolean> results =new ArrayList<>();
        double correct =0;
        double score =0;
        if(retake)
        {
            for(int i=0; i<3;i++)
            {
                if(choices.get(i).equals(answers.get(i)))
                {
                    correct++;
                }

            }
            score=(correct/3)*100;
            DatabaseManager.updateGrade(learnerID,lessonID,score);
        }
        else
        {


            for(int i=0; i<3;i++)
            {
                if(choices.get(i).equals(answers.get(i)))
                {
                    correct++;
                }

            }
            score=(correct/3)*100;
            DatabaseManager.addGrade(learnerID,courseID,lessonID,score);
        }
        return score;

    }

}
