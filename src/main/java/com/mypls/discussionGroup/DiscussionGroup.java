/**
 * Discussion Group class for MyPLS system.
 */
package com.mypls.discussionGroup;

import com.mypls.DatabaseManager;

public class DiscussionGroup {
    public static boolean createDiscussionGroup(String topic, String course, String type)
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
