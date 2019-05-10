package BusinessLayer;

import BusinessLayer.DataRow.DataRow;
import DataLayer.DB_Connection;
//import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.sql.*;

public class SQL_Manager {


    private HashMap<String,String> queries = new HashMap<String, String >();

    public SQL_Manager()
    {
        // All Queries have two kinds of variables, which refer to Where and Limit Clauses- both of which may be- or may not be present in final query
        //Initialise all query templates
        //queries.put("countryQuery","SELECT Name, Code, Continent, Region, Population, Capital FROM country %s ORDER BY Population DESC;");
        queries.put("CountryRow","SELECT country.Name AS 'Name',country.Code AS 'Code',country.Continent AS 'Continent',country.Region AS 'Region',country.Population AS 'Population',city.Name AS 'Capital' FROM city JOIN country on city.ID=country.Capital %s ORDER BY country.Population DESC %s;");
        queries.put("CityRow","SELECT city.Name AS 'Name',country.Name AS 'Country',country.Continent AS 'Continent',country.Region AS 'Region',city.District AS 'District',city.Population AS 'Population' FROM city JOIN country ON city.CountryCode=country.Code %s ORDER BY Population DESC %s;");
        queries.put("CapitalCityRow","SELECT city.Name AS 'Name',country.Name AS 'Country',city.Population AS 'Population' FROM city JOIN country ON city.ID=country.Capital %s ORDER BY city.Population DESC %s;");
        //Reports require two string inputs (or null 1st input is Where clause, second input is Limit clause)
        // queries.put("PopulationRow","SELECT country.Continent AS 'Continent',country.Region AS 'Region',country.Name AS 'Name',SUM(country.Population) AS 'Total_Population',SUM(city.Population) AS 'Cities_Population',SUM(country.Population - city.Population) AS 'Rural_Population' FROM country JOIN city ON country.code=city.CountryCode %s GROUP BY 'Total_Population','Cities_Population','Rural_Population' %s");
        queries.put("PopulationRow","SELECT country.%s, sum(country.Population) as totalPop, sum(city.Population) as cityPop, sum(city.Population) / sum(country.Population) as City_Percentage, sum(country.Population) - sum(city.Population) as Not_in_Cities, (sum(country.Population) - sum(city.Population)) / sum(country.Population) as Not_in_Cities_Percentage FROM city JOIN country ON city.CountryCode=country.Code WHERE country.%s LIKE %s GROUP BY country.%s;");
        // Population report requires 3 string inputs- type of search (Continent/ Region/ Country), again Type of search, and Than specific input (name of Continent/Region/Country)
        queries.put("LanguageRow","SELECT countrylanguage.Language, sum(country.Population * (countrylanguage.Percentage / 100)) as Speaker_pop, sum(country.Population * (countrylanguage.Percentage / 100)) / (Select sum(country.Population) FROM country) as Percent_of_World_Population FROM countrylanguage JOIN country on countrylanguage.CountryCode=country.Code GROUP by countrylanguage.Language HAVING countrylanguage.Language LIKE 'Chinese' OR countrylanguage.Language LIKE 'ENGLISH' OR countrylanguage.Language LIKE 'Hindi' OR countrylanguage.Language LIKE 'Spanish' OR countrylanguage.Language LIKE 'Arabic' ORDER BY Speaker_pop DESC;");

        // Language does not take any variable input as it should display 5 row table for all requested languages
        // queries.put("CountryRow","SELECT country.Name,country.Code,country.Continent,country.Region,country.Population,city.Name AS 'Capital' FROM city JOIN country on city.ID=country.Capital %s ORDER BY country.Population DESC %s;");

    }

    public String getQuery(String queryName, ArrayList<String> queryValues){
        Object[] objValues = (Object[]) queryValues.toArray();

        String newQuery = String.format( queries.get( queryName ), objValues );
        return newQuery;
    }
    //Main method wrapping everything together- connects to db, than creates an array list of database results (Using Data Row class) to be displayed.
    // sql statement is constructed using above getQuery method. Accepts arguments which are: queryName points to correct sql statement core stored in SQL_Manager- first part before the coma is the queryName.
    //queryValues are variable string statements which are filling in the gaps in SQL_Managers statement, wherever there is "%s" in SQL_Managers statement
    // a string can be put in based on first come first serve basis- if there are less arguments than "%s" the "%s" will be removed and nothing will be put in the sql_statement, which is useful on occasion
    // correct syntax to use is SQL_Manager.query_db("queryName","firstString" "Second String") - or at least i think it is correct at this time dunno if they should be separated with commas :)
    public void query_db(String queryName,ArrayList<String> queryValues)
    {
        System.out.println("Starting application, it can take some time. ~20sec");
        DB_Connection con = DB_Connection.getInstance();

        String newQuery = getQuery(queryName, queryValues);

        System.out.println(newQuery);

        ArrayList<DataRow> rows = con.getResult(queryName, newQuery );
        displayReport(rows);
    }
    // Martins display method originally in app file, reads ArrayList and displays each row- used above
    public static void displayReport(ArrayList<DataRow> rows)
    {

        for (DataRow row : rows)
        {
            System.out.println(row.toString());
        }
    }

}
