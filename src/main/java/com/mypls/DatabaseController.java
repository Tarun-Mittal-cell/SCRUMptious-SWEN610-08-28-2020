package com.mypls;
import java.sql.*;

public class DatabaseController {

    //Database URL]
    static final String DB_URL = "jdbc:mysql://localhost/mypls";
    //  Database credentials
    static final String USERNAME = "mypls";
    static final String PASSWORD = "password";

   
    public static void updateDatabase(String query)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Inserting records into the table...");
            statement = connection.createStatement();
            String sql =query ;
            statement.executeUpdate(sql);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally 
        {

            try{
                if(statement!=null) 
                {
                    statement.close();
                }
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
            try
            {
                if(statement!=null) 
                {
                    statement.close();
                }
            }
            catch(SQLException se)
            {
                se.printStackTrace(); 
            }
        }

    }

    public static void queryDatabase(String query)
    {
        Connection connection = null;
        Statement statement = null;
        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.createStatement();
            String sql ="SELECT id, firstname, lastname, email, role FROM USERS"; ;
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                //Retrieve by column name
                int id  = resultSet.getInt("id");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String role = resultSet.getString("Role");

                //Display values
                System.out.print("ID: " + id);
                System.out.print(", First: " + firstName);
                System.out.print(", Last: " + lastName );
                System.out.print(", Email: " + email);
                System.out.println(", Role: " + role );
            }
            resultSet.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {

            try{
                if(statement!=null)
                {
                    connection.close();
                    System.out.println("closed!");
                }
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
            try
            {
                if(connection!=null)
                {
                    connection.close();
                }
            }
            catch(SQLException se)
            {
                se.printStackTrace();
            }
        }

    }
}
