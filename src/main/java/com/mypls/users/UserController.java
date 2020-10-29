package com.mypls.users;

import com.mypls.DatabaseManager;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class UserController {

    public static boolean register (String fName, String lName, String type, String email, String password)
    {
        boolean isUnique;
        if(!type.equals("Professor")) {
            isUnique = DatabaseManager.updateDatabase("INSERT INTO USERS (Email, Password, TypeUser) VALUES ('" + email + "', '" + password + "', 'Learner')");
            if (isUnique) {
                DatabaseManager.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Type) VALUES ('" + fName + "', '" + lName + "', '" + email + "', '"+type+"')");
            }
            return isUnique;
        }
        else
        {
            isUnique= DatabaseManager.updateDatabase("INSERT INTO USERS (Email, Password, TypeUser) VALUES ('"+email+"', '"+password+"', 'Professor')");
            if(isUnique)
            {
                DatabaseManager.updateDatabase("INSERT INTO Professors (FirstName, LastName, Email) VALUES ('"+fName+"', '"+lName+"', '"+email+"')");
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
        

        userLoginInfo= DatabaseManager.queryCredentials("SELECT * FROM users WHERE Email='"+email+"'");

        if (userLoginInfo.size() != 0) {
            if (userLoginInfo.get("email").equals(email) && userLoginInfo.get("password").equals(UserController.encryption(password)))
            {
                if(userLoginInfo.get("type").equals("Learner"))
                {
                    HashMap<String, String> learnerInfo= DatabaseManager.queryLearners("SELECT * FROM learners WHERE Email='"+email+"'");
                    userLoginInfo.put("firstName",learnerInfo.get("firstName"));
                    userLoginInfo.put("lastName",learnerInfo.get("lastName"));
                    userLoginInfo.put("rating",learnerInfo.get("rating"));
                    userLoginInfo.put("id",learnerInfo.get("id"));
                    userLoginInfo.put("numberOfRatings",learnerInfo.get("numberOfRatings"));
                    userLoginInfo.put("loginStatus","AUTHENTICATED");
                }
                else if(userLoginInfo.get("type").equals("Professor"))
                {
                    HashMap<String, String> professorInfo= DatabaseManager.queryProfessors("SELECT * FROM Professors WHERE Email='"+email+"'");
                    userLoginInfo.put("firstName",professorInfo.get("firstName"));
                    userLoginInfo.put("lastName",professorInfo.get("lastName"));
                    userLoginInfo.put("rating",professorInfo.get("rating"));
                    userLoginInfo.put("id",professorInfo.get("id"));
                    userLoginInfo.put("numberOfRatings",professorInfo.get("numberOfRatings"));
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
