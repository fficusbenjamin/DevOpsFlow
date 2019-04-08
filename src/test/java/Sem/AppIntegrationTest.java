package Sem;


import BusinessLayer.DataRow.*;
import DataLayer.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;


import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static DB_Connection con;
    static DataRow dataRow;
    static CapitalCityRow capitalCityRow;
    static CityRow cityRow;
    static CountryRow countryRow;


    // Database tests
    @BeforeAll
    public void init()
    {
        return;
        /* //TODO: Uncomment after deployment setup
        con = DB_Connection.getInstance();
        try {
            setConnection();
        }catch (Exception e){
            System.exit(0);

        }*/

    }

    //Data structure tests
    @org.junit.Test
    public void setDataRow(){
        dataRow = new DataRow();
        assertNotNull(dataRow);
    }

    @org.junit.Test
    public void setCapitalCityRow(){
        capitalCityRow = new CapitalCityRow();
        assertNotNull(capitalCityRow);
    }

    @org.junit.Test
    public void setCityRow(){
        cityRow = new CityRow();
        assertNotNull(cityRow);
    }

    @org.junit.Test
    public void setCountryRow(){
        countryRow = new CountryRow();
        assertNotNull(countryRow);
    }


    @org.junit.Test
    public void setCon()
    {
        //init(); //TODO: Uncomment after deployment setup
        assertNotNull(con);
    }

    private long startAt;

    @Before
    public void before() {
        this.startAt = System.currentTimeMillis();
    }

    @After
    public void after() {
        System.out.println(String.format("Timed out after: %1$d ms", System.currentTimeMillis() - startAt));
        System.out.println("Could not connect to the database");
    }
    @Test(timeout = 30000)
    public void setConnection() throws InterruptedException {
        return;//con.Connect("localhost"); //TODO: Uncomment after deployment setup
    }
}