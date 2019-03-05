package BusinessLayer;

import java.util.ArrayList;
import java.util.HashMap;

public class SQL_Manager {


    private HashMap<String,String> queries = new HashMap<String, String >();

    public SQL_Manager() {
        //Initialise all query templates
        queries.put("countryQuery","SELECT Name, Code, Continent, Region, Population, Capital FROM country %s ORDER BY Population DESC");


    }

    public String getQuery(String queryName, ArrayList<String> queryValues){
        Object[] objValues = (Object[]) queryValues.toArray();

        String newQuery = String.format( queries.get( queryName ), objValues );
        return newQuery;
    }
}
