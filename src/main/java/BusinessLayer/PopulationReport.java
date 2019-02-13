package BusinessLayer;

import DataLayer.DB_Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulationReport extends Report
{
    protected String world;
    protected int population;

    public PopulationReport(){
        super();
    }

    @Override
    public void loadReport(){
        DB_Connection con = DB_Connection.getInstance();
        //If the connection is successful
        if (con.Connect()) {
            String sqlStatement = "SELECT Population FROM world;";
            /*ResultSet result = con.getResult(sqlStatement);

            try {
                if (result.next()) {
                    //Load data into our object
                    this.population = result.getInt("Population");
                } else {
                    System.out.println("All results loaded");
                }
            } catch (SQLException sqlError) {
                System.out.println("Invalid SQL statement!");
            }*/
        }

    }

    @Override
    public void displayReport(){
        System.out.println("Population report\nPopulation:" + this.population);
    }

}
