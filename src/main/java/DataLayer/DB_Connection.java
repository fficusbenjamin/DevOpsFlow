package DataLayer;

import BusinessLayer.CapitalReport;
import BusinessLayer.DataRow.*;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

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
                con = DriverManager.getConnection("jdbc:mysql://db:3306/world?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "example");
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

    public ArrayList<DataRow> getResult(String classType, String sqlStmt){
        ArrayList<DataRow> list = new ArrayList<>();

        //We make sure we have a working connection
        if (con == null)
        {
            this.Connect();
        }

        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Execute SQL statement
            ResultSet result = stmt.executeQuery(sqlStmt);
            //Loop through each row inside result
            while (result.next()) {
                DataRow newRow = new CityRow();
                /*
                switch (classType){
                    case "CityRow":
                        newRow = new CityRow();

                    default:
                        newRow = new DataRow();
                }*/
                //We add the results to the chosen DataRow
                ArrayList<String> properties = newRow.getPropertyNames();
                for (String prop : properties)
                {
                    if (prop != null)
                    {
                       // System.out.println("Test value " + result.getString(prop));
                        newRow.setPropertyValue(prop,result.getString(prop));



                    }else{
                        System.out.println("Given property is null");
                    }

                }
                if (newRow != null)
                {
                    list.add(newRow);
                    System.out.println("Added value: \n ");
                    System.out.println(((CityRow) newRow).toString() );
                }

            }
            System.out.println("Number of rows loaded:" + list.size() );
            return list;

        }catch (SQLException sqlError){
            System.out.println("Invalid SQL statement!");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get the result");
            return null;
        }
        return list;
    }
}
