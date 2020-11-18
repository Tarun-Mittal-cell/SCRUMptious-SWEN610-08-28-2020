package com.mypls.users;

import com.mypls.DatabaseManager;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class User {
    protected String firstName;
    protected String lastName;
    protected String email;
    protected Rate rate;

    public User(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }


    public Rate getRate() {
        return rate;
    }

    public void setRate(Rate rate) {
        this.rate = rate;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Register new user to the system
     * @param firstName First name of user
     * @param lastName Last Name of user
     * @param type Type of user
     * @param email Email of address of user
     * @param password password of user
     * @return
     */
    public static boolean register (String firstName, String lastName, String type, String email, String password)
    {
        boolean isUnique;
        if(!type.equals("Professor")) {
            isUnique = DatabaseManager.registerUser(email,encrypt(password),type);
            if (isUnique) {
                DatabaseManager.updateDatabase("INSERT INTO LEARNERS (FirstName, LastName, Email, Type) VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '"+type+"')");
            }
            return isUnique;
        }
        else
        {
            isUnique= DatabaseManager.registerUser(email,encrypt(password),type);
            if(isUnique)
            {
                DatabaseManager.updateDatabase("INSERT INTO Professors (FirstName, LastName, Email) VALUES ('"+firstName+"', '"+lastName+"', '"+email+"')");
            }
            else
            {
                return isUnique;
            }

        }
        return true;
    }

    /**
     * Login authentication of user to the system.
     * @param email Email address of user.
     * @param password Password for user.
     * @return
     */
    public static HashMap<String, Object> login (String email, String password)
    {
        HashMap<String, Object> userLoginInfo= new HashMap<String, Object>();

        userLoginInfo= DatabaseManager.queryCredentials(email);

        if (userLoginInfo.size() != 0) {
            if (userLoginInfo.get("email").equals(email) && userLoginInfo.get("password").equals(encrypt(password)))
            {

                if(userLoginInfo.get("type").equals("Learner"))
                {
                    Learner learner= DatabaseManager.queryLearner(email);
                    userLoginInfo.put("userData",learner);
                    userLoginInfo.put("loginStatus","AUTHENTICATED");

                }
                else if(userLoginInfo.get("type").equals("Professor"))
                {
                    Professor professor= DatabaseManager.queryProfessor(email);
                    userLoginInfo.put("userData",professor);
                    userLoginInfo.put("loginStatus","AUTHENTICATED");
                }
                else if(userLoginInfo.get("type").equals("Administrator"))
                {
                    Administrator admin=new Administrator();
                    userLoginInfo.put("userData",admin);
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

    /**
     * Encryption of passowrd
     * @param password user entered password.
     */
    private static String encrypt(String password)
    {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
        md.digest(password.getBytes(StandardCharsets.UTF_8));
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
