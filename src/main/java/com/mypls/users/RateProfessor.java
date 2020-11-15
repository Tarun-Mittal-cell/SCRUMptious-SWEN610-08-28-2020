package com.mypls.users;

import com.mypls.DatabaseManager;
import com.mypls.users.Rate;

public class RateProfessor implements Rate {

    @Override
    public double computeRating(int id, int newRating, double averageRating, int numberOfRating) {
        double result=((numberOfRating*averageRating)+newRating)/(numberOfRating+1);
        DatabaseManager.updateProfessorRating(id,result,numberOfRating+1);
        return result;

    }
}
