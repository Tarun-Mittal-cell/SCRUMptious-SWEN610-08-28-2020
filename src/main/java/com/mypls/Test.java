package com.mypls;

public class Test {

    public static void main(String[] args) {

       String test="Business Foundations Specialization Part 1 Course ID: 1001";

        String[] temp=test.split("Course ID:");
        test=temp[1];
        System.out.println("INSERT INTO DiscussionGroups (Topic,RelatedCourseID,Type) VALUES ('" + "topic" + "', '" + "courseID" + "', '" + "type" +"'");
    }
}
