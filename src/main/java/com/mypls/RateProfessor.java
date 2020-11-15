package com.mypls;

import com.mypls.users.Professor;

public class RateProfessor implements Rate{

    @Override
    public void computeRating(int ID, int newRating, double averageRating, int numberOfRating) {
        double result=((numberOfRating*averageRating)+newRating)/(numberOfRating+1);
        DatabaseManager.updateLesson(1,"",1,"","","");

    }
}
