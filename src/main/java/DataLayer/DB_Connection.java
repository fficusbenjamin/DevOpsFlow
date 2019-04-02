package DataLayer;

import BusinessLayer.DataRow.*;

import java.sql.*;
import java.util.ArrayList;

public class DB_Connection {
    /*
    DB_Connection - Class
    Handles Database Connections,
    and importing data from the database
    Part of singleton design pattern.
    */
    private String _domain;
    private static DB_Connection instance;
    public static DB_Connection getInstance(Boolean localhost){
        if (instance==null){
            if (localhost != null)
            {
                if (localhost){
                    instance = new DB_Connection("localhost");
                }
            }else{
                instance = new DB_Connection("");
            }

        }
        return instance;
    }

    //Constructor
    private DB_Connection(){
        this._domain = "";
    }
    private DB_Connection(String domain){
        this._domain = domain;
    }

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
                String url = "";
                if (this._domain != null){
                    url = "jdbc:mysql://" + this._domain + "/db:3306/world?useSSL=false&useUnicode=true&characterEncoding=utf-8";
                }else{
                    url = "jdbc:mysql://db:3306/world?useSSL=false&useUnicode=true&characterEncoding=utf-8";
                }
                // Connect to database
                con = DriverManager.getConnection(url, "root", "example");
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
                DataRow newRow = new CountryRow();
                /*
                //TODO: Currently only works with CountryRow
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
                        if (newRow.getClass().getField(prop).getType().isPrimitive())
                        {
                            newRow.setPropertyValue(prop,result.getInt(prop));
                        }else{
                            newRow.setPropertyValue(prop,result.getString(prop));
                        }




                    }else{
                        System.out.println("Given property is null");
                    }

                }
                if (newRow != null)
                {
                    list.add(newRow);
                    //System.out.println("Added value: \n ");
                    //System.out.println(((CityRow) newRow).toString() );
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
