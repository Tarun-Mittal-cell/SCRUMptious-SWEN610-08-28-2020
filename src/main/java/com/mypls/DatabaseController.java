package com.mypls;
import java.sql.*;
import java.util.ArrayList;

public class DatabaseController {

    //Database URL]
    static final String DB_URL = "jdbc:mysql://localhost/mypls";
    //  Database credentials
    static final String USERNAME = "mypls";
    static final String PASSWORD = "password";

   
    public static boolean updateDatabase(String query)
    {
        boolean isUnique=true;
        Connection connection = null;
        Statement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MYPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.createStatement();
            String sql =query ;
            statement.executeUpdate(sql);
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");
            isUnique=false;
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

        return isUnique;

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
            String sql =query; ;
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

    public static ArrayList<String> queryCredentials(String query)
    {
        ArrayList<String> userData = new ArrayList<String>();
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
            String sql =query; ;
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                //Retrieve by column name
                userData.add(resultSet.getString("Email"));
                userData.add( resultSet.getString("Password"));
                userData.add(resultSet.getString("Role"));

                //Display values
                System.out.print(", Last: " + userData.get(0));
                System.out.print(", Email: " + userData.get(1));
                System.out.println(", Role: " + userData.get(2) );
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

        return userData;
    }
}
