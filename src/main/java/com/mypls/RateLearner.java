package com.mypls;

public class RateLearner implements Rate{
    @Override
    public void computeRating(int ID, int newRating, double averageRating, int numberOfRating) {
        double result=((numberOfRating*averageRating)+newRating)/(numberOfRating+1);
    }
}
