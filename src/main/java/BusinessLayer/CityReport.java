package BusinessLayer;

import DataLayer.DB_Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityReport extends Report {

    protected String country;
    protected String district;
    protected int population;

    public CityReport(){
        super();
    }

    @Override
    public void loadReport(){
        DB_Connection con = DB_Connection.getInstance();
        //If the connection is successful
        if (con.Connect()){
            String sqlStatement = "SELECT Name,CountryCode,District,Population FROM city;";
            ResultSet result = con.getResult(sqlStatement);

            try {
                if (result.next()){
                    //Load data into our object
                    this.name = result.getString("Name");
                    this.country = result.getString("CountryCode");
                    this.district = result.getString("District");
                    this.population = result.getInt("Population");
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
        System.out.println("City Report:" + this.name + "\nCountryCode:" + this.country + "\nDistrict:" + this.district + "\nPopulation:" + this.population);
    }
}
