package com.mypls;
import com.mypls.course.Course;
import com.mypls.course.Lesson;
import com.mypls.users.Learner;
import com.mypls.users.Professor;


import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class DatabaseManager {

    //Database URL]
    static final String DB_URL = "jdbc:mysql://localhost/mypls";
    //  Database credentials
    static final String USERNAME = "mypls";
    static final String PASSWORD = "password";

    public static final String INSERT_USER_QUERY = "INSERT INTO USERS(Email,Password,UserType) VALUES(?,?,?)";
    public static final String INSERT_LEARNER_QUERY = "INSERT INTO LEARNERS(FirstName,LastName,Email) VALUES(?,?,?)";
    public static final String VALIDATE_USER    = "SELECT * FROM USERS WHERE Email=?";
    public static final String RETRIEVE_LEARNER = "SELECT * FROM LEARNERS WHERE Email=?";
    public static final String REGISTER_COURSE = "INSERT INTO LEARNERCOURSE (LearnerID,CourseID,Status) VALUES(?,?,?)";
    public static final String RETRIEVE_LEARNER_COURSES = "SELECT * FROM COURSES  INNER JOIN LEARNERCOURSE ON LEARNERCOURSE.COURSEID=COURSES.COURSEID WHERE LEARNERID=?;";
    public static final String ADD_GRADE = "INSERT INTO GRADES(LearnerID,CourseID,LessonID,Grade) VALUES(?,?,?,?);";
    public static final String RETRIEVE_GRADE = "SELECT * FROM GRADES WHERE  LEARNERID=? AND LessonID=?";

    public static final String UPDATE_LEARNER_RATING      = "UPDATE LEARNERS SET RATING=?,NumberOfRatings=? WHERE (LearnerID =?)";
    public static final String UPDATE_PROFESSOR_RATING = "UPDATE PROFESSOR SET RATING=?,NumberOfRatings=? WHERE (ProfessorID =?)";
    public static final String UPDATE_LESSONS_RATING      = "UPDATE LESSONS SET RATING=?,NumberOfRatings=? (LessonID =?)";
    public static final String UPDATE_COURSE_RATING      = "UPDATE COURSES SET RATING=?,NumberOfRatings=? WHERE (CourseID =?)";




    public static final String RETRIEVE_PROFESSOR = "SELECT * FROM PROFESSORS WHERE Email=?";
    public static final String RETRIEVE_PROFESSOR_BY_ID = "SELECT * FROM PROFESSORS WHERE ProfessorID=?";
    public static final String RETRIEVE_ALL_LEARNERS = "SELECT * FROM LEARNERS";
    public static final String RETRIEVE_ALL_PROFESSORS = "SELECT * FROM PROFESSORS";

    public static final String ADD_NEW_COURSE= "INSERT INTO Courses (Name,AssignedProfessorID,PrerequisiteCourseID,Requirements,Objectives,Outcomes) VALUES (?,?,?,?,?,?)";

    public static final String UPDATE_COURSE    = "UPDATE COURSES SET Name=?,AssignedProfessorID=?,PrerequisiteCourseID=?,Requirements=?,Objectives=?, Outcomes=? WHERE (courseID =?)";
    public static final String DELETE_COURSE     = "DELETE FROM COURSES WHERE (CourseID =?)";
    public static final String RETRIEVE_ASSIGNED_COURSES = "SELECT * FROM COURSES WHERE AssignedProfessorID=?";
    public static final String RETRIEVE_COURSE_BY_COURSEID = "SELECT * FROM COURSES WHERE CourseID=?";
    public static final String RETRIEVE_ALL_COURSES = "SELECT * FROM COURSES";

    public static final String ADD_NEW_LESSON     = "INSERT INTO LESSONS (Title,CourseID,Requirements,MediaPath,DocumentPath) VALUES(?,?,?,?,?)";
    public static final String UPDATE_LESSON     = "UPDATE LESSONS SET Title=?,CourseID=?,Requirements=?,MediaPath=?,DocumentPath=? WHERE (LessonID =?)";
    public static final String DELETE_LESSON     = "DELETE FROM LESSONS WHERE (LessonID =?)";
    public static final String RETRIEVE_LESSONS_BY_COURSE= "SELECT * FROM LESSONS WHERE CourseID=?";

    public static final String ADD_NEW_QUIZ    = "INSERT INTO QUIZZES (LessonID,Question,Answer) VALUES(?,?,?)";
    public static final String RETRIEVE_QUIZ = "SELECT * FROM QUIZZES WHERE LessonID=?";
    public static final String DELETE_QUIZ = "DELETE FROM QUIZZES WHERE (LessonID =?)";



    public static boolean registerUser(String email, String password,String type )
    {
        boolean isUnique=true;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(INSERT_USER_QUERY);
            statement.setString(1,email);
            statement.setString(2,password);
            statement.setString(3,type);
            statement.executeUpdate();
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

    public static void insertLearner(String firstName, String lastName, String email)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(INSERT_LEARNER_QUERY);
            statement.setString(1,firstName);
            statement.setString(2,lastName);
            statement.setString(3,email);
            statement.executeUpdate();
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");

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



    public static boolean AddLearnerCourse(int learnerID, int courseID)
    {
        boolean isAdded=true;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MyPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(REGISTER_COURSE);
            statement.setInt(1,learnerID);
            statement.setInt(2,courseID);
            statement.setString(3,"Registered");
            statement.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            isAdded=false;
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
        return isAdded;
    }





    public static HashMap<String, Object>  queryCredentials(String email)
    {
        HashMap<String, Object> userData = new HashMap();

        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(VALIDATE_USER);

            statement.setString(1,email);

            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                //Retrieve by column name
                userData.put("email",resultSet.getString("Email"));
                userData.put("password", resultSet.getString("Password"));
                userData.put("type",resultSet.getString("UserType"));

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


    public static  Learner queryLearner(String email)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        Learner learner=null;
        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_LEARNER);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                //Retrieve by column name
                learner=new Learner(resultSet.getInt("LearnerID"),resultSet.getString("FirstName"),resultSet.getString("lastName"),resultSet.getString("Email"),resultSet.getString("Type"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"));
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

        return learner;

    }

    public static  Professor queryProfessor(String email)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        Professor professor=null;
        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_PROFESSOR);
            statement.setString(1,email);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                //Retrieve professor
                professor=new Professor(resultSet.getInt("ProfessorID"),resultSet.getString("FirstName"),resultSet.getString("lastName"),resultSet.getString("Email"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"));
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

        return professor;

    }


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
            e.printStackTrace();

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

    public static ArrayList<Professor> getAllProfessor()
    {
        ArrayList<Professor> professorList= new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_ALL_PROFESSORS);
            ResultSet resultSet = statement.executeQuery();

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

    public static Professor queryProfessorByID(int id)
    {
        Professor professor = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_PROFESSOR_BY_ID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                //Retrieve by column name
                professor = new Professor(resultSet.getInt("ProfessorID"),resultSet.getString("FirstName"), resultSet.getString("lastName"), resultSet.getString("Email"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"));


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
        return professor;
    }

    public static boolean createNewCourse(String name , int professorId ,int prerequisite ,String requirement,String objectives ,String outcomes)
    {
        boolean isCreated=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(ADD_NEW_COURSE);
            statement.setString(1,name);
            statement.setInt(2,professorId);
            statement.setInt(3,prerequisite);
            statement.setString(4,requirement);
            statement.setString(5,objectives);
            statement.setString(6,outcomes);
            statement.executeUpdate();
            isCreated=true;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");
            isCreated=false;
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

        return isCreated;
    }

    public static boolean updateCourse(int courseID,String name , int professorId ,int prerequisite ,String requirement,String objectives ,String outcomes)
    {
        boolean isUpdated=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(UPDATE_COURSE);
            statement.setString(1,name);
            statement.setInt(2,professorId);
            statement.setInt(3,prerequisite);
            statement.setString(4,requirement);
            statement.setString(5,objectives);
            statement.setString(6,outcomes);
            statement.setInt(7,courseID);
            statement.executeUpdate();
            isUpdated=true;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");
            isUpdated=false;
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

        return isUpdated;
    }

    public static ArrayList<Course> getAllCourses()
    {
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_ALL_COURSES);
            ResultSet resultSet = statement.executeQuery();

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

    public static ArrayList<Course> getAllRegisteredCourses(int learnerID)
    {
        ArrayList<Course> courses = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;

        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_LEARNER_COURSES);
            statement.setInt(1,learnerID);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                //Retrieve by column name
                Course course=new Course(resultSet.getString("Name"),resultSet.getInt("CourseID"),resultSet.getInt("AssignedProfessorID"),resultSet.getInt("PrerequisiteCourseID"),resultSet.getString("Requirements"),resultSet.getString("Objectives"),resultSet.getString("Outcomes"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"),resultSet.getInt("Enrollment"),resultSet.getDouble("MinScore"));
                course.setStatus(resultSet.getString("Status"));
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
        PreparedStatement statement = null;

        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_ALL_LEARNERS);
            ResultSet resultSet = statement.executeQuery();

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

    public static void addGrade(int learnerID,int courseID, int lessonID, double grade)
    {

        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(ADD_GRADE);
            statement.setInt(1,learnerID);
            statement.setInt(2,courseID);
            statement.setInt(3,lessonID);
            statement.setDouble(4,grade);
            statement.executeUpdate();
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");

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


    public static double retrieveGrade(int learnerID,int lessonID)
    {
        double grade=0;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(RETRIEVE_GRADE);
            statement.setInt(1,learnerID);
            statement.setInt(2,lessonID);

            ResultSet resultSet=statement.executeQuery();
           if( resultSet.next())
           {
               grade= resultSet.getDouble("Grade");
               System.out.println("grades");
           }
           else
           {
               grade=-1;
               System.out.println("no grades");
           }

        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");

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

        return grade;
    }





    public static Course queryByCourseID(int id)
    {
        Course course = null;
        Connection connection = null;
        PreparedStatement statement = null;

        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_COURSE_BY_COURSEID);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();

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

    public static boolean deleteByCourseID(int id)
    {
        boolean isDeleted=false;
        Connection connection = null;
        PreparedStatement statement = null;

        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(DELETE_COURSE);
            statement.setInt(1,id);
            statement.executeUpdate();
            isDeleted=true;

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
        return isDeleted;
    }

    public static ArrayList<Course> queryCourseByProfessor(int professorID)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<Course> courses=new ArrayList<>();
        Course course =null;
        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(RETRIEVE_ASSIGNED_COURSES);
            statement.setInt(1,professorID);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next())
            {
                //Retrieve by column name
                course=new Course(resultSet.getString("Name"),resultSet.getInt("CourseID"),resultSet.getInt("AssignedProfessorID"),resultSet.getInt("PrerequisiteCourseID"),resultSet.getString("Requirements"),resultSet.getString("Objectives"),resultSet.getString("Outcomes"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"),resultSet.getInt("Enrollment"),resultSet.getDouble("MinScore"));
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

    public static boolean addNewLesson(String title,int courseID,String requirements,String mediaPath,String documentPath )
    {
        boolean isAdded=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(ADD_NEW_LESSON);
            statement.setString(1,title);
            statement.setInt(2,courseID);
            statement.setString(3,requirements);
            statement.setString(4,mediaPath);
            statement.setString(5,documentPath);
            statement.executeUpdate();
            isAdded=true;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");
            isAdded=false;
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
        return isAdded;
    }

    public static boolean updateLesson(int lessonID, String title,int courseID,String requirements,String mediaPath,String documentPath )
    {
        boolean isUpdated=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(UPDATE_LESSON);
            statement.setString(1,title);
            statement.setInt(2,courseID);
            statement.setString(3,requirements);
            statement.setString(4,mediaPath);
            statement.setString(5,documentPath);
            statement.setInt(6,lessonID);
            statement.executeUpdate();
            isUpdated=true;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");
            isUpdated=false;
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
        return isUpdated;
    }

    public static boolean deleteLesson(int lessonID)
    {
        boolean isDeleted=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(DELETE_LESSON);
            statement.setInt(1,lessonID);
            statement.executeUpdate();
            isDeleted=true;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");
            isDeleted=false;
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
        return isDeleted;
    }

    public static List<Lesson> getLessonsByCourse(int courseID)
    {
        List<Lesson> lessons=new LinkedList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(RETRIEVE_LESSONS_BY_COURSE);
            statement.setInt(1,courseID);
            ResultSet resultSet=statement.executeQuery();

            while(resultSet.next())
            {
                //Retrieve by column name
                Lesson lesson=new Lesson(resultSet.getInt("lessonID"),resultSet.getString("Title"),resultSet.getInt("CourseID"),resultSet.getString("Requirements"),resultSet.getDouble("Rating"),resultSet.getInt("NumberOfRatings"),resultSet.getString("MediaPath"),resultSet.getString("DocumentPath"));
                lessons.add(lesson);

            }

        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");

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

        return lessons;
    }

    public static boolean addNewQuiz(int lessonID, ArrayList<String> questions,ArrayList<String> answers)
    {
        boolean isAdded=true;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(ADD_NEW_QUIZ);
            for(int i=0;i<questions.size();i++)
            {
                statement.setInt(1, lessonID);
                statement.setString(2, questions.get(i));
                statement.setString(3, answers.get(i));
                statement.addBatch();
            }
            statement.executeBatch();

        }

        catch(Exception e)
        {
            e.printStackTrace();
            isAdded=false;

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

        return isAdded;
    }

    public static ArrayList<String>[] retrieveQuiz(int lessonID)
    {
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<String>[] quizData= new ArrayList[2];
        ArrayList<String> questions=new ArrayList<>();
        ArrayList<String> answers=new ArrayList<>();
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(RETRIEVE_QUIZ);
            statement.setInt(1,lessonID);
            ResultSet resultSet=statement.executeQuery();

            while(resultSet.next())
            {

                //Retrieve by column name
                questions.add(resultSet.getString("Question"));
                answers.add(resultSet.getString("Answer"));
            }
            if(!questions.isEmpty()) {
                quizData[0] = questions;
                quizData[1] = answers;
            }

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
        return quizData;
    }



    public static boolean removeQuiz(int id)
    {
        boolean isDeleted=false;
        Connection connection = null;
        PreparedStatement statement = null;

        try
        {
            //Open a connection
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a statement
            System.out.println("Reading records from table...");
            statement = connection.prepareStatement(DELETE_QUIZ);
            statement.setInt(1,id);
            statement.executeUpdate();
            isDeleted=true;

        }
        catch(Exception e)
        {
            e.printStackTrace();
            isDeleted=false;
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
        return isDeleted;
    }

    public static boolean updateCourseRating(int courseID, double rating, int numberOfRating)
    {
        boolean isUpdated=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            //Open a connection to MYPLS database
            System.out.println("Connecting to a selected database...");
            connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            System.out.println("Connected database successfully...");

            //Execute a query to MyPLS database
            System.out.println("Inserting records into the table...");
            statement = connection.prepareStatement(UPDATE_COURSE_RATING);
            statement.setDouble(1,rating);
            statement.setInt(2,numberOfRating);
            statement.setInt(3,courseID);

            statement.executeUpdate();
            isUpdated=true;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
            System.out.println("Email already assigned to an account");
            isUpdated=false;
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

        return isUpdated;
    }

    public static boolean updateLearnerRating(int lessonID, double rating, int numberOfRating)
    {
        boolean isUpdated=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
                {
                //Open a connection to MYPLS database
                System.out.println("Connecting to a selected database...");
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                System.out.println("Connected database successfully...");

                //Execute a query to MyPLS database
                System.out.println("Inserting records into the table...");
                statement = connection.prepareStatement(UPDATE_LEARNER_RATING);
                statement.setDouble(1,rating);
                statement.setInt(2,numberOfRating);
                statement.setInt(3,lessonID);

                statement.executeUpdate();
                isUpdated=true;
                }
                catch (SQLIntegrityConstraintViolationException e)
                {
                System.out.println("Email already assigned to an account");
                isUpdated=false;
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

                return isUpdated;
                }

public static boolean updateProfessorRating(int professorID, double rating, int numberOfRating)
{
        boolean isUpdated=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
        //Open a connection to MYPLS database
        System.out.println("Connecting to a selected database...");
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        System.out.println("Connected database successfully...");

        //Execute a query to MyPLS database
        System.out.println("Inserting records into the table...");
        statement = connection.prepareStatement(UPDATE_PROFESSOR_RATING);
        statement.setDouble(1,rating);
        statement.setInt(2,numberOfRating);
        statement.setInt(3,professorID);

        statement.executeUpdate();
        isUpdated=true;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
        System.out.println("Email already assigned to an account");
        isUpdated=false;
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

        return isUpdated;
        }

public static boolean updateLessonRating(int lessonID, double rating, int numberOfRating)
{
        boolean isUpdated=false;
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
        //Open a connection to MYPLS database
        System.out.println("Connecting to a selected database...");
        connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
        System.out.println("Connected database successfully...");

        //Execute a query to MyPLS database
        System.out.println("Inserting records into the table...");
        statement = connection.prepareStatement(UPDATE_LESSONS_RATING);
        statement.setDouble(1,rating);
        statement.setInt(2,numberOfRating);
        statement.setInt(3,lessonID);

        statement.executeUpdate();
        isUpdated=true;
        }
        catch (SQLIntegrityConstraintViolationException e)
        {
        System.out.println("Email already assigned to an account");
        isUpdated=false;
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

        return isUpdated;
        }



}
