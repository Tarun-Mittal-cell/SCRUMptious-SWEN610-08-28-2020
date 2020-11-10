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

    public boolean createDiscussionGroup(String topic, String course, String type)
    {
            String[] temp=course.split(":");

            if(temp.length>1) {
                String courseID = temp[1];
               return DatabaseManager.updateDatabase("INSERT INTO DiscussionGroups (Topic,RelatedCourseID,Type) VALUES ('" + topic + "', '" + courseID + "', '" + type + "')");
            }
            else
            {
                return  DatabaseManager.updateDatabase("INSERT INTO DiscussionGroups (Topic,Type) VALUES ('" + topic + "', '" + type + "')");

            }
    }



}
