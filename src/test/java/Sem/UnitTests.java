package Sem;

import BusinessLayer.DataRow.*;
import Sem.*;
import DataLayer.*;

import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;




public class UnitTests {

    static DB_Connection con;
    static DataRow dataRow;
    static CapitalCityRow capitalCityRow;
    static CityRow cityRow;
    static CountryRow countryRow;
    static Menu mainmenu;


    // Database tests
    @BeforeAll
    public void init()
    {
        con = DB_Connection.getInstance(false);
    }

    //Data structure tests
    @Test
    public void setDataRow(){
        dataRow = new DataRow();
        assertNotNull(dataRow);
    }

    @Test
    public void setCapitalCityRow(){
        capitalCityRow = new CapitalCityRow();
        assertNotNull(capitalCityRow);
    }

    @Test
    public void setCityRow(){
        cityRow = new CityRow();
        assertNotNull(cityRow);
    }

    @Test
    public void setCountryRow(){
        countryRow = new CountryRow();
        assertNotNull(countryRow);
    }


    @Test
    public void setCon()
    {
        init();
        assertNotNull(con);
    }

    private long startAt;

    /*@Before
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
        init();
        con.Connect();
    }*/

    //Menu tests
    @Test
    public void setMainmenu()
    {
        mainmenu = new Menu("Main Menu");
        assertNotNull(mainmenu);
    }
    @Test
    public void createMenuSystem()
    {
        mainmenu = Menu.createMenuSystem();
        assertNotNull(mainmenu);
        String menuDisplay = mainmenu.toString();
        assertNotEquals(menuDisplay.length(),0);
    }



}

