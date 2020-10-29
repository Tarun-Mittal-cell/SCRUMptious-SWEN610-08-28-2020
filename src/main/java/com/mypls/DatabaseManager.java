package com.mypls;
import com.mypls.course.Course;
import com.mypls.users.Learner;
import com.mypls.users.Professor;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseManager {

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

            //Execute a query to MyPLS database
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

    public static HashMap<String, String>  queryCredentials(String query)
    {
        HashMap<String, String> userData = new HashMap();

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
                userData.put("email",resultSet.getString("Email"));
                userData.put("password", resultSet.getString("Password"));
                userData.put("type",resultSet.getString("TypeUser"));


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

    public static  HashMap<String, String> queryLearners(String query)
    {
        HashMap<String, String> learnerInfo = new HashMap();
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
                learnerInfo.put("id" ,resultSet.getString("LearnerID"));
                learnerInfo.put("firstName" ,resultSet.getString("FirstName"));
                learnerInfo.put("lastName" ,resultSet.getString("lastName"));
                learnerInfo.put("type" ,resultSet.getString("Type"));
                learnerInfo.put("rating", resultSet.getString("Rating"));
                learnerInfo.put("numberOfRatings",resultSet.getString("NumberOfRatings"));

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

        return learnerInfo;

    }


    public static HashMap<String, String> queryProfessors(String query)
    {
        HashMap<String, String> professorInfo = new HashMap();
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
                professorInfo.put("id",resultSet.getString("ProfessorID"));
                professorInfo.put("firstName",resultSet.getString("FirstName"));
                professorInfo.put("lastName",resultSet.getString("LastName"));
                professorInfo.put("rating",resultSet.getString("Rating"));
                professorInfo.put("numberOfRatings",resultSet.getString("NumberOfRatings"));

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
        System.out.println("Checking: "+professorInfo);
        return professorInfo;

    }

    public static ArrayList<Professor> getAllProfessor()
    {
        ArrayList<Professor> professorList= new ArrayList<>();
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
            String sql ="SELECT * FROM professors"; ;
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                //Retrieve by column name
                Professor professor = new Professor(resultSet.getInt("ProfessorID"),resultSet.getString("FirstName"), resultSet.getString("lastName"), resultSet.getString("Email"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"));
                professorList.add(professor);


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

        return professorList;

    }

    public static ArrayList<Course> getAllCourses()
    {
        ArrayList<Course> courses = new ArrayList<>();
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
            String sql ="SELECT * FROM courses"; ;
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                //Retrieve by column name
                Course course=new Course(resultSet.getString("Name"),resultSet.getInt("CourseID"),resultSet.getInt("AssignedProfessorID"),resultSet.getInt("PrerequisiteCourseID"),resultSet.getString("Requirements"),resultSet.getString("Objectives"),resultSet.getString("Outcomes"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"),resultSet.getInt("Enrollment"),resultSet.getDouble("MinScore"));
                courses.add(course);

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

        return courses;

    }

    public static ArrayList<Learner> getAllLearners()
    {
        ArrayList<Learner> learners = new ArrayList<>();
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
            String sql ="SELECT * FROM learners";
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                //Retrieve by column name
                Learner learner=new Learner(resultSet.getInt("LearnerID"),resultSet.getString("FirstName"),resultSet.getString("LastName"),resultSet.getString("Email"),resultSet.getString("Type"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"));
                learners.add(learner);

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

        return learners;

    }



    public static Course queryByCourseID(int id)
    {
        Course course = null;
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
            String sql ="SELECT * FROM courses WHERE CourseID='"+id+"'" ;
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next())
            {
                //Retrieve by column name
                course=new Course(resultSet.getString("Name"),resultSet.getInt("CourseID"),resultSet.getInt("AssignedProfessorID"),resultSet.getInt("PrerequisiteCourseID"),resultSet.getString("Requirements"),resultSet.getString("Objectives"),resultSet.getString("Outcomes"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"),resultSet.getInt("Enrollment"),resultSet.getDouble("MinScore"));


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


        return course;

    }

    public static void deleteByCourseID(int id)
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
            String sql ="Delete FROM courses WHERE CourseID='"+id+"'" ;
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
