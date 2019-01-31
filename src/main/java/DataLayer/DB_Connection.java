package DataLayer;

import java.sql.*;
public class DB_Connection {
    /*
    DB_Connection - Class
    Handles Database Connections,
    and importing data from the database
    Part of singleton design pattern.
    */
    private static DB_Connection instance;
    public static DB_Connection getInstance(){
        if (instance==null){
            instance = new DB_Connection();
        }
        return instance;
    }

    //Constructor
    private DB_Connection(){}

    // Connection to the database
    private static Connection con = null;
    public boolean Connect(){

        try
        {
            // Load Database driver
            Class.forName("com.mysql.jdbc.Driver");

        }
        catch (ClassNotFoundException e)
        {
            System.out.println("Could not load SQL driver");
            return false;
        }

        int retries = 100;
        for (int i = 0; i < retries; ++i)
        {
            System.out.println("Connecting to database...");
            try
            {
                // Wait a bit for db to start
                Thread.sleep(20000);
                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false&characterEncoding=latin1", "root", "example");
                System.out.println("Successfully connected, if it crashes after that then its your fault");
                // Exit for loop
                break;
            }
            catch (SQLException sqle)
            {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            catch (InterruptedException ie)
            {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }
        return true;
    }

    public boolean Disconnect(){
        if (con != null)
        {
            try
            {
                // Close connection
                con.close();
            }
            catch (Exception e)
            {
                System.out.println("Error closing connection to database");
            }
        }
        return true;
    }
    public ResultSet getResult(String sqlStmt){
        if ( con != null ){
            try
            {
                // Create an SQL statement
                Statement stmt = con.createStatement();
                // Execute SQL statement
                return stmt.executeQuery(sqlStmt);
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
                System.out.println("Failed to get the result");
                return null;
            }
        }


        return null;
    }
}
