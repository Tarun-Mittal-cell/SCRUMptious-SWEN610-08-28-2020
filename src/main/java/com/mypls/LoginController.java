package com.mypls;

import com.mypls.users.*;

import java.util.ArrayList;

public class LoginController {


    public static boolean register (String fName, String lName, String role, String email, String password)
    {
        boolean isUnique;
        switch (role)
        {
            case "Student":
                Student student = new Student(fName, lName, email,password);
                System.out.println(student);
                isUnique=DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password) VALUES ('"+student.getEmail()+"', '"+student.getPassword()+"')");
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
                DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password, Role) VALUES ('"+staff.getEmail()+"', '"+staff.getPassword()+"', Learner)");
                DatabaseController.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Role) VALUES ('"+staff.getFirstName()+"', '"+staff.getLastName()+"', '"+staff.getEmail()+"', 'Staff')");
                break;
            case "Alumni":
                Alumni alumni = new Alumni(fName, lName, email,password);
                System.out.println(alumni);
                DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password) VALUES ('"+alumni.getEmail()+"', '"+alumni.getPassword()+"')");
                DatabaseController.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Role) VALUES ('"+alumni.getFirstName()+"', '"+alumni.getLastName()+"', '"+alumni.getEmail()+"', 'Alumni')");
                break;
            case "Faculty":
                Faculty faculty = new Faculty(fName, lName, email,password);
                System.out.println(faculty);
                DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password) VALUES ('"+faculty.getEmail()+"', '"+faculty.getPassword()+"')");
                DatabaseController.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Role) VALUES ('"+faculty.getFirstName()+"', '"+faculty.getLastName()+"', '"+faculty.getEmail()+"', 'Faculty')");
                break;
            case "Professor":
                Professor professor = new Professor(fName, lName, email,password);
                System.out.println(professor);
                DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password) VALUES ('"+professor.getEmail()+"', '"+professor.getPassword()+"')");
                DatabaseController.updateDatabase("INSERT INTO Professors (FirstName, LastName, Email) VALUES ('"+professor.getFirstName()+"', '"+professor.getLastName()+"', '"+professor.getEmail()+"')");
                break;
        }
        return true;
    }


    public static String login (String email, String password)
    {
        ArrayList<String> userData = new ArrayList<String>();

        userData=DatabaseController.queryCredentials("SELECT * FROM users WHERE Email='"+email+"'");

        if (userData.size() != 0) {
            if (userData.get(0).equals(email) && userData.get(1).equals(password)) {
                return "AUTHENTICATED";
            }
            return "PASSWORD_INVALID";
        }
        return "EMAIL_INVALID";

    }

}
