/**
 * Rate interface to implement strategy pattern for rating users.
 */
package com.mypls.users;

public interface Rate {
    /**
     * Compute the rating of user(Professor or Learner) and update the respective database tables.
     * @param id user id
     * @param newRating new rating for user
     * @param averageRating previous average rating of user,
     * @param numberOfRating number of pervious ratings.
     * @return
     */
    double computeRating(int id, int newRating, double averageRating, int numberOfRating);
}
