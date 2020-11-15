package com.mypls.users;

public interface Rate {

    double computeRating(int id, int newRating, double averageRating, int numberOfRating);
}
