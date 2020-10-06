package com.mypls;

import com.mypls.users.*;

import java.util.ArrayList;
import java.util.HashMap;

public class LoginController {


    public static boolean register (String fName, String lName, String role, String email, String password)
    {
        boolean isUnique;
        switch (role)
        {
            case "Student":
                Student student = new Student(fName, lName, email,password);
                System.out.println(student);
                isUnique=DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password, Role) VALUES ('"+student.getEmail()+"', '"+student.getPassword()+"', 'Learner')");
                if(isUnique)
                {
                    DatabaseController.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Role) VALUES ('" + student.getFirstName() + "', '" + student.getLastName() + "', '" + student.getEmail() + "', 'Student')");
                    return isUnique;
                }
                else
                {

                    return isUnique;
                }

            case "Staff":
                Staff staff = new Staff(fName, lName, email,password);
                System.out.println(staff);
                isUnique=DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password, Role) VALUES ('"+staff.getEmail()+"', '"+staff.getPassword()+"', 'Learner')");
                if(isUnique)
                {
                     DatabaseController.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Role) VALUES ('"+staff.getFirstName()+"', '"+staff.getLastName()+"', '"+staff.getEmail()+"', 'Staff')");
                }
                else
                {

                    return isUnique;
                }
                break;
            case "Alumni":
                Alumni alumni = new Alumni(fName, lName, email,password);
                System.out.println(alumni);
                isUnique=DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password, Role) VALUES ('"+alumni.getEmail()+"', '"+alumni.getPassword()+"', 'Learner')");
                if(isUnique)
                {
                    DatabaseController.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Role) VALUES ('"+alumni.getFirstName()+"', '"+alumni.getLastName()+"', '"+alumni.getEmail()+"', 'Alumni')");
                }
                else
                {

                    return isUnique;
                }
                break;
            case "Faculty":
                Faculty faculty = new Faculty(fName, lName, email,password);
                System.out.println(faculty);
                isUnique=DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password, Role) VALUES ('"+faculty.getEmail()+"', '"+faculty.getPassword()+"', , 'Learner')");
                if(isUnique)
                {
                    DatabaseController.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Role) VALUES ('"+faculty.getFirstName()+"', '"+faculty.getLastName()+"', '"+faculty.getEmail()+"', 'Faculty')");
                }
                else
                {
                    return isUnique;
                }
                break;
            case "Professor":
                Professor professor = new Professor(fName, lName, email,password);
                System.out.println(professor);
                isUnique=DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password, Role) VALUES ('"+professor.getEmail()+"', '"+professor.getPassword()+"', 'Professor')");
                if(isUnique)
                {
                    DatabaseController.updateDatabase("INSERT INTO Professors (FirstName, LastName, Email) VALUES ('"+professor.getFirstName()+"', '"+professor.getLastName()+"', '"+professor.getEmail()+"')");
                }
                else
                {
                    return isUnique;
                }
                break;
        }
        return true;
    }


    public static HashMap<String, String> login (String email, String password)
    {
        HashMap<String, String> userData = new HashMap<String, String>();
        String firstName = "";


        userData=DatabaseController.queryCredentials("SELECT * FROM users WHERE Email='"+email+"'");

        if (userData.size() != 0) {
            if (userData.get("email").equals(email) && userData.get("password").equals(password)) {

                if(userData.get("role").equals("Learner"))
                {
                    firstName=DatabaseController.queryLearners("SELECT * FROM learners WHERE Email='"+email+"'");
                    userData.put("firstName",firstName);
                    userData.put("status","AUTHENTICATED");
                }
                if(userData.get("role").equals("Professor"))
                {
                    String lastName=DatabaseController.queryProfessor("SELECT * FROM Professors WHERE Email='"+email+"'");
                    userData.put("lastName",lastName);
                    userData.put("status","AUTHENTICATED");

                }
               return userData;
            }
            userData.put("status","PASSWORD_INVALID");
            return userData;
        }
        userData.put("status","EMAIL_INVALID");
        return userData;

    }




}
