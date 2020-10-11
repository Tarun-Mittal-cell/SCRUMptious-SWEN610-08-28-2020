package com.mypls;

import com.mypls.users.*;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;

public class LoginController {

    public static boolean register (String fName, String lName, String type, String email, String password)
    {
        boolean isUnique;
        if(!type.equals("Professor")) {
            Learner learner = new Learner(fName, lName, email, password,type);
            System.out.println(learner);
            isUnique = DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password, type) VALUES ('" + learner.getEmail() + "', '" + learner.getPassword() + "', 'Learner')");
            if (isUnique) {
                DatabaseController.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, type) VALUES ('" + learner.getFirstName() + "', '" + learner.getLastName() + "', '" + learner.getEmail() + "', '"+learner.getType()+"')");
                return isUnique;
            }
            else
            {
                return isUnique;
            }
        }
        else
        {
            Professor professor = new Professor(fName, lName, email,password);
            System.out.println(professor);
            isUnique=DatabaseController.updateDatabase("INSERT INTO USERS (Email, Password, type) VALUES ('"+professor.getEmail()+"', '"+professor.getPassword()+"', 'Professor')");
            if(isUnique)
            {
                DatabaseController.updateDatabase("INSERT INTO Professors (FirstName, LastName, Email) VALUES ('"+professor.getFirstName()+"', '"+professor.getLastName()+"', '"+professor.getEmail()+"')");
            }
            else
            {
                return isUnique;
            }

        }
        return true;
    }

    public static HashMap<String, String> login (String email, String password)
    {
        HashMap<String, String> userLoginInfo= new HashMap<String, String>();
        

        userLoginInfo=DatabaseController.queryCredentials("SELECT * FROM users WHERE Email='"+email+"'");

        if (userLoginInfo.size() != 0) {
            if (userLoginInfo.get("email").equals(email) && userLoginInfo.get("password").equals(LoginController.encryption(password)))
            {
                if(userLoginInfo.get("type").equals("Learner"))
                {
                    HashMap<String, String> learnerInfo=DatabaseController.queryLearners("SELECT * FROM learners WHERE Email='"+email+"'");
                    userLoginInfo.put("firstName",learnerInfo.get("firstName"));
                    userLoginInfo.put("lastName",learnerInfo.get("lastName"));
                    userLoginInfo.put("loginStatus","AUTHENTICATED");
                }
                else if(userLoginInfo.get("type").equals("Professor"))
                {
                    HashMap<String, String> professorInfo=DatabaseController.queryProfessor("SELECT * FROM Professors WHERE Email='"+email+"'");
                    userLoginInfo.put("firstName",professorInfo.get("firstName"));
                    userLoginInfo.put("lastName",professorInfo.get("lastName"));
                    userLoginInfo.put("loginStatus","AUTHENTICATED");
                }
                else if(userLoginInfo.get("type").equals("Administrator"))
                {
                    userLoginInfo.put("loginStatus","AUTHENTICATED");
                }
                
               return userLoginInfo;
            }
            userLoginInfo.put("loginStatus","PASSWORD_INVALID");
            return userLoginInfo;
        }
        userLoginInfo.put("loginStatus","EMAIL_INVALID");
        return userLoginInfo;

    }

    public static String encryption(String input)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = md.digest(input.getBytes(StandardCharsets.UTF_8));
        md.digest(input.getBytes(StandardCharsets.UTF_8));
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 32)
        {
            hexString.insert(0, '0');
        }
        System.out.println(hexString.toString());
        return hexString.toString();
    }





}
