package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

//Author: Lech Psztyk
// 10/05/2019
// Basic SQL handling Class

public class SQL_Manager {


    private HashMap<String,String> queries = new HashMap<String, String >();
    // Stores All relevant SQL queries in a Hash map as formatted springs which take in variables
    public SQL_Manager()
    {

        queries.put("CountryRow","SELECT country.Name AS 'Name',country.Code AS 'Code',country.Continent AS 'Continent',country.Region AS 'Region',country.Population AS 'Population',city.Name AS 'Capital' FROM city JOIN country on city.ID=country.Capital %s ORDER BY country.Population DESC %s;");
        queries.put("CityRow","SELECT city.Name AS 'Name',country.Name AS 'Country',country.Continent AS 'Continent',country.Region AS 'Region',city.District AS 'District',city.Population AS 'Population' FROM city JOIN country ON city.CountryCode=country.Code %s ORDER BY Population DESC %s;");
        queries.put("CapitalCityRow","SELECT city.Name AS 'Name',country.Name AS 'Country',city.Population AS 'Population' FROM city JOIN country ON city.ID=country.Capital %s ORDER BY city.Population DESC %s;");
        queries.put("PopulationRow","SELECT country.%s, sum(country.Population) as totalPop, sum(city.Population) as cityPop, sum(city.Population) / sum(country.Population) as City_Percentage, sum(country.Population) - sum(city.Population) as Not_in_Cities, (sum(country.Population) - sum(city.Population)) / sum(country.Population) as Not_in_Cities_Percentage FROM city JOIN country ON city.CountryCode=country.Code WHERE country.%s LIKE %s GROUP BY country.%s;");
        queries.put("LanguageRow","SELECT countrylanguage.Language, sum(country.Population * (countrylanguage.Percentage / 100)) as Speaker_pop, sum(country.Population * (countrylanguage.Percentage / 100)) / (Select sum(country.Population) FROM country) as Percent_of_World_Population FROM countrylanguage JOIN country on countrylanguage.CountryCode=country.Code GROUP by countrylanguage.Language HAVING countrylanguage.Language LIKE 'Chinese' OR countrylanguage.Language LIKE 'ENGLISH' OR countrylanguage.Language LIKE 'Hindi' OR countrylanguage.Language LIKE 'Spanish' OR countrylanguage.Language LIKE 'Arabic' ORDER BY Speaker_pop DESC;");
        queries.put("WorldPop","SELECT sum(country.Population) as 'World' FROM country");
        queries.put("cityquery","SELECT sum(city.Population) as pop FROM city %s");
    }
    // method that formats final SQL statement based on hashmap recovered basic query and arrayList of variable input
    public String getQuery(String queryName, ArrayList<String> queryValues){
        Object[] objValues = (Object[]) queryValues.toArray();

        String newQuery = String.format( queries.get( queryName ), objValues );
        return newQuery;
    }


}
