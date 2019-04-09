package DataLayer;

import BusinessLayer.DataRow.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public boolean Connect(String domain, boolean local){
        boolean status = false;

            try
            {
                // Load Database driver
                Class.forName("com.mysql.jdbc.Driver");

            }
            catch (ClassNotFoundException e)
            {
                System.out.println("Could not load SQL driver");

            }
            if(local==false){

            int retries = 100;
            for (int i = 0; i < retries; ++i)
            {
                System.out.println("Connecting to database...");
                try
                {
                    // Wait a bit for db to start
                    Thread.sleep(20000);
                    String port = "3306";
                    if (domain == "localhost"){
                        port += "0";
                    }
                    // Connect to database
                    con = DriverManager.getConnection("jdbc:mysql://35.242.134.40:3306/world?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "example");
                    //con = DriverManager.getConnection("jdbc:mysql://" + domain + ":" + port +"/world?useSSL=false&useUnicode=true&characterEncoding=utf-8", "root", "example");
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
            status = true;
            return status;
        }else{
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
                status = true;
                return status;
            }


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
            this.Connect("db",false);
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
    public void displayCity (String cityQuery)
    {
        if (con != null)
        {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(cityQuery);
                while (rs.next())
                {
                    String Name= rs.getString("Name");
                    //   System.out.println(Name);
                    String Country= rs.getString("Country");
                    //   System.out.println(Country);
                    String District = rs.getString("District");
                    //  System.out.println(District);
                    //  String Region= rs.getString("Region");
                    //  System.out.println(Region);
                    int Population= rs.getInt("Population");
                    //   System.out.println(Population);
                    //   String Capital= rs.getString("Capital");
                    //    System.out.println(Capital);
                    String result = String.format("%s %s %s %d", Name, Country, District, Population);

                    System.out.println(result);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    public void displayCountry (String countryQuery)
    {
        if (con != null)
        {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(countryQuery);
                while (rs.next())
                {
                    String Name= rs.getString("Name");
                    // System.out.println(Name);
                    String Code= rs.getString("Code");
                    // System.out.println(Code);
                    String Continent = rs.getString("Continent");
                    //  System.out.println(Continent);
                    String Region= rs.getString("Region");
                    //  System.out.println(Region);
                    int Population= rs.getInt("Population");
                    //   System.out.println(Population);
                    String Capital= rs.getString("Capital");
                    //    System.out.println(Capital);
                    String result = String.format("%s %s %s %s %d %s", Name, Code, Continent, Region, Population, Capital);

                    System.out.println(result);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    public void displayCapital (String capitalQuery)
    {
        if (con != null)
        {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(capitalQuery);
                while (rs.next())
                {
                    String Name= rs.getString("Name");
                    //   System.out.println(Name);
                    String Country= rs.getString("Country");
                    //   System.out.println(Country);
                    //  String District = rs.getString("District");
                    //  System.out.println(District);
                    //  String Region= rs.getString("Region");
                    //  System.out.println(Region);
                    int Population= rs.getInt("Population");
                    //   System.out.println(Population);
                    //   String Capital= rs.getString("Capital");
                    //    System.out.println(Capital);
                    String result = String.format("%s %s %d", Name, Country, Population);

                    System.out.println(result);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public String displayPop (String popQuery)
    {
        String result="did not get result for query";
        if (con != null)
        {

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(popQuery);
                rs.next();
                long Population= rs.getLong("Population");
                result = String.valueOf(Population);
                return result;

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return result;
    }
    public ArrayList<String> getCountries()
    {
        ArrayList<String> countryValidation= new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Name FROM country");
            while (rs.next())
            {
                String Name= rs.getString("Name");
                countryValidation.add(Name);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return countryValidation;
    }
}
