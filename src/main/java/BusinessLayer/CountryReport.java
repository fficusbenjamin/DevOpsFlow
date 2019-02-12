package BusinessLayer;

import DataLayer.DB_Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
/// Author: Lech Psztyk
/// Student no: 40404708
/// Description: Country Report Class and methods for country report feature
///
///Date: 11/02/2019
public class CountryReport extends Report{

    // basic object extension
    protected String Code;
    protected String Continent;
    protected String Region;
    protected int Population;
    protected String Capital;


    public CountryReport(){
        super();
    }

    @Override
    public void loadReport(){
        // specific override for country report using country reports attributes
        DB_Connection con = DB_Connection.getInstance();
        //If the connection is successful
        if (con.Connect()){
            String sqlStatement = "SELECT country.Name,country.Code,country.Continent,country.Region,country.Population,city.Name AS 'Capital' FROM city JOIN country on city.ID=country.Capital Where country.Name like 'Poland';";
            ResultSet result = con.getResult(sqlStatement);

            try {
                if (result.next()){
                    //Loads results of sql statement into Country report object
                    this.name = result.getString("Name");
                    this.Code = result.getString("Code");
                    this.Continent = result.getString("Continent");
                    this.Region = result.getString("Region");
                    this.Population = result.getInt("Population");
                    this.Capital = result.getString("Capital");

                }else{
                    System.out.println("All results loaded");
                }
            }catch (SQLException sqlError){
                System.out.println("Invalid SQL statement!");
            }
        }





    }
    @Override
    public void displayReport(){
        //country report override, displays country report
        System.out.println("Country Report: " + this.name + "\nCode: " + this.Code + "\nContinent: " + this.Continent + "\nRegion: "+ this.Region +"\nPopulation: " + this.Population + "\nCapital: "+ this.Capital);
    }
    public void printAll()
    {
        DB_Connection con = DB_Connection.getInstance();
        String sqlStatement = "SELECT TABLE_NAME FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_TYPE='BASE TABLE';";
        ResultSet Result = con.getResult(sqlStatement);
        System.out.println(Result);
    }
}
