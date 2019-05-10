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

// method that loads and displays results of City report query- detects values by column names and loads variables with their values before printing result line
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
                    String Country= rs.getString("Country");
                    String District = rs.getString("District");
                    int Population= rs.getInt("Population");
                    String result = String.format("%s %s %s %d", Name, Country, District, Population);

                    System.out.println(result);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    // method that loads and displays results of Country report query- detects values by column names and loads variables with their values before printing result line
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
                    String Code= rs.getString("Code");
                    String Continent = rs.getString("Continent");
                    String Region= rs.getString("Region");
                    int Population= rs.getInt("Population");
                    String Capital= rs.getString("Capital");
                    String result = String.format("%s %s %s %s %d %s", Name, Code, Continent, Region, Population, Capital);

                    System.out.println(result);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
    //method that loads and displays results of Capital City report query- detects values by column names and loads variables with their values before printing result line
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
                    String Country= rs.getString("Country");
                    int Population= rs.getInt("Population");
                    String result = String.format("%s %s %d", Name, Country, Population);

                    System.out.println(result);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }
//method that loads and displays results of Population report query- detects values by column names and loads variables with their values before printing result line
    public void displayPop (String popQuery, String type)
    {
        String result="did not get result for query";
        if (con != null)
        {

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(popQuery);
                rs.next();
                String name =rs.getString(type);
                long totalPop = rs.getLong("totalPop");
                long cityPop= rs.getLong("cityPop");
                float City_Percentage = rs.getFloat("City_Percentage");
                long Not_in_Cities= rs.getLong("Not_in_Cities");
                float Not_in_Cities_Percentage = rs.getFloat("Not_in_Cities_Percentage");
                result = String.format(" %s's Name is %s \n Total Population %d \n Total Cities Population %d \n Percentage of City dwellers %f \n Total Population outside Cities %d \n Percentage of Rural population %f \n\n", type, name, totalPop, cityPop, City_Percentage, Not_in_Cities, Not_in_Cities_Percentage);
                System.out.println(result);

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }else
            {
                System.out.println(result);
            }

    }
// method that loads and displays results of Language report query- detects values by column names and loads variables with their values before printing result line
    public void displayLang (String LangQuery)
    {
        String result="did not get result for query";
        if (con != null)
        {

            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(LangQuery);
                while (rs.next())
            {
                    String name = rs.getString("Language");
                    long totalPop = rs.getLong("Speaker_pop");
                    float Percentage = rs.getFloat("Percent_of_World_Population");
                    result = String.format(" Language: %s World Population Percentage: %f Total Speaker Population %d", name, Percentage, totalPop);
                    System.out.println(result);
                }

            } catch (SQLException e) {
                e.printStackTrace();

            }
        }else
        {
            System.out.println(result);
        }
    }
// method used to validate Country name user input - creates array list of all country names in Database
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
// method used to validate Continents name user input - creates array list of all continent names in Database
    public ArrayList<String> getContinents()
    {
        ArrayList<String> continentValidation= new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Continent FROM country");
            while (rs.next())
            {
                String Name= rs.getString("Continent");
                continentValidation.add(Name);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return continentValidation;
    }
    // method used to validate Region name user input - creates array list of all region names in Database
    public ArrayList<String> getRegions()
    {
        ArrayList<String> regionValidation= new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Region FROM country");
            while (rs.next())
            {
                String Name= rs.getString("Region");
                regionValidation.add(Name);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return regionValidation;
    }
    // method used to validate District name user input - creates array list of all district names in Database
    public ArrayList<String> getDistricts()
    {
        ArrayList<String> districtValidation= new ArrayList<>();
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT District FROM city");
            while (rs.next())
            {
                String Name= rs.getString("District");
                districtValidation.add(Name);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return districtValidation;
    }

}
