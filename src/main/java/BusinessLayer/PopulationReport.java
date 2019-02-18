package BusinessLayer;

import DataLayer.DB_Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulationReport extends Report
{
    protected int population;
    protected String country;

    public PopulationReport(){
        super();
    }

    @Override
    public void loadReport(){
        DB_Connection con = DB_Connection.getInstance();
        //If the connection is successful
        if (con.Connect()) {
            String sqlStatement = "SELECT country.Name,country.Population FROM country;";
            ResultSet result = con.getResult(sqlStatement);

            try {
                if (result.next()) {
                    //Load data into our object
                    this.population = result.getInt("Population");
                    this.country = result.getString("Name");
                } else {
                    System.out.println("All results loaded");
                }
            } catch (SQLException sqlError) {
                System.out.println("Invalid SQL statement!");
            }


        }

    }

    @Override
    public void displayReport(){
        System.out.println("Population report\nCountry: " + this.country + "\nPopulation: " + this.population);
    }

}
