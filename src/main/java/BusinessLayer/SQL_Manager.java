package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class SQL_Manager {


    private HashMap<String,String> queries = new HashMap<String, String >();

    public SQL_Manager()
    {
        // All Queries have two kinds of variables, which refer to Where and Limit Clauses- both of which may be- or may not be present in final query
        //Initialise all query templates
       // queries.put("countryQuery","SELECT Name, Code, Continent, Region, Population, Capital FROM country %s ORDER BY Population DESC");
        queries.put("country","SELECT country.Name,country.Code,country.Continent,country.Region,country.Population,city.Name AS 'Capital' FROM city JOIN country on city.ID=country.Capital %s ORDER BY country.Population DESC %s;");
        queries.put("City","SELECT city.Name,Country.Name,city.District,city.Population FROM city JOIN country ON city.CountryCode=country.Code %s ORDER BY Population DESC %s;");
        queries.put("CapitalCity","SELECT city.Name,country.Name,city.Population FROM city JOIN country ON city.CountryCode=country.Code %s ORDER BY city.Poplation %s");
                //Reports require two string inputs (or null 1st input is Where clause, second input is Limit clause)
        queries.put("Population","SELECT %s,SUM(city.Population) AS 'Cities Population',SUM(country.Population - city.population) AS 'Rural Population' FROM country JOIN city ON country.code=city.CountryCode %s GROUP BY 'Cities Population','Rural Population'");
                // Population report requires 2 string inputs- first will be name of Region, continent or country, second will be Where clause limiting operation to said region, country or continent
        queries.put("Language","SELECT countrylanguage.Language,SUM(country.Population*(countrylanguage.Percentage/100),2) AS 'Speakers', SUM(SUM(country.Population*(countrylanguage.Percentage/100),2)/(SELECT SUM(country.Population) FROM country) AS 'World_Percentage' FROM country JOIN countrylanguage ON country.Code=countrylanguage.CountryCode WHERE countrylanguage.Language Like 'Chinese' AND countrylanguage.Language Like 'English' AND countrylanguage.Language Like 'Hindi' AND countrylanguage.Language Like 'Spanish' AND countrylanguage.Language Like 'Arabic' GROUP by Speakers,World_Percentage, countrylanguage.Language ORDER BY Speakers DESC)");

        // Language does not take any variable input as it should display 5 row table for all requested languages

    }

    public String getQuery(String queryName, ArrayList<String> queryValues){
        Object[] objValues = (Object[]) queryValues.toArray();

        String newQuery = String.format( queries.get( queryName ), objValues );
        return newQuery;
    }
}
