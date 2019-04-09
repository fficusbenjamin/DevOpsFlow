package DataLayer;

import BusinessLayer.DataRow.*;
import BusinessLayer.SQL_Manager;

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
               // DataRow newRow = new CountryRow();

                //TODO: Currently only works with CountryRow
                switch (classType){
                    case "CityRow":
                        DataRow newRow = new CityRow();
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
                        break;
                    case "CountryRow":
                        DataRow newRow2 = new CountryRow();
                        ArrayList<String> properties2 = newRow2.getPropertyNames();
                        for (String prop : properties2)
                        {
                            if (prop != null)
                            {
                                // System.out.println("Test value " + result.getString(prop));
                                if (newRow2.getClass().getField(prop).getType().isPrimitive())
                                {
                                    newRow2.setPropertyValue(prop,result.getInt(prop));
                                }else{
                                    newRow2.setPropertyValue(prop,result.getString(prop));
                                }




                            }else{
                                System.out.println("Given property is null");
                            }

                        }
                        if (newRow2 != null)
                        {
                            list.add(newRow2);
                            //System.out.println("Added value: \n ");
                            //System.out.println(((CityRow) newRow).toString() );
                        }
                        break;
                    case "CapitalCityRow":
                        DataRow newRow3 = new CapitalCityRow();
                        ArrayList<String> properties3 = newRow3.getPropertyNames();
                        for (String prop : properties3)
                        {
                            if (prop != null)
                            {
                                // System.out.println("Test value " + result.getString(prop));
                                if (newRow3.getClass().getField(prop).getType().isPrimitive())
                                {
                                    newRow3.setPropertyValue(prop,result.getInt(prop));
                                }else{
                                    newRow3.setPropertyValue(prop,result.getString(prop));
                                }




                            }else{
                                System.out.println("Given property is null");
                            }

                        }
                        if (newRow3 != null)
                        {
                            list.add(newRow3);
                            //System.out.println("Added value: \n ");
                            //System.out.println(((CityRow) newRow).toString() );
                        }
                        break;

                    default:
                        newRow = new DataRow();
                }
                //We add the results to the chosen DataRow


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

    public void displayPop (String popQuery, String type)
    {
        if (con != null)
        {
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(popQuery);
                while (rs.next())
                {
                   // String Name= rs.getString("Name");
                   // System.out.println(Name);
                   // String Continent= rs.getString("Continent");
                   // System.out.println(Continent);
                   // String Region = rs.getString("Region");
                   // System.out.println(Region);
                   // int totalPop= rs.getInt("Total_Population");
                   // System.out.println(totalPop);
                  //  int cityPop= rs.getInt("Cities_Population");
                  //  System.out.println(cityPop);
                  //  int ruralPop= rs.getInt("Rural_Population");
                  //  System.out.println(ruralPop);
                    SQL_Manager population = new SQL_Manager();
                    switch(type)
                    {
                        case "country":
                            String Name= rs.getString("Name");
                            int totalPop= rs.getInt("Total_Population");
                            int cityPop= rs.getInt("Cities_Population");
                            int ruralPop= rs.getInt("Rural_Population");
                            String result = String.format("%s %d %d %d", Name, totalPop, cityPop, ruralPop);
                            System.out.println(result);
                            break;
                        case "region":
                            String Region= rs.getString("Region");
                            int totalPop2= rs.getInt("Total_Population");
                            int cityPop2= rs.getInt("Cities_Population");
                            int ruralPop2= rs.getInt("Rural_Population");
                            String result2 = String.format("%s %d %d %d", Region, totalPop2, cityPop2, ruralPop2);
                            System.out.println(result2);
                            break;
                        case "continent":
                            String Continent= rs.getString("Continent");
                            int totalPop3= rs.getInt("Total_Population");
                            int cityPop3= rs.getInt("Cities_Population");
                            int ruralPop3= rs.getInt("Rural_Population");
                            String result3 = String.format("%s %d %d %d", Continent, totalPop3, cityPop3, ruralPop3);
                            System.out.println(result3);
                            break;
                        default:
                            System.out.println("Something went very wrong in switch case, type not found");
                            break;
                    }



                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

}
