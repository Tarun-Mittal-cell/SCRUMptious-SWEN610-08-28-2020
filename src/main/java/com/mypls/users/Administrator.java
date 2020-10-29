package com.mypls.users;

import com.mypls.DatabaseManager;

import java.util.ArrayList;

public class Administrator extends User{

    public Administrator() {
        super(null, null, null);
    }
    public Administrator(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void createDiscussionGroup(String topic, String course, String type)
    {
        String[] temp=course.split(":");
        String courseID=temp[1];

        DatabaseManager.updateDatabase("INSERT INTO DiscussionGroups (Topic,RelatedCourseID,Type) VALUES ('" + topic + "', '" + courseID + "', '" + type +"')" );
    }



}
