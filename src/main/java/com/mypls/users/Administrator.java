/**
 * Administrator User class for MyPLS system.
 */
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


}
