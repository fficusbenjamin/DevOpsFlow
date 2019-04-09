package Sem;

import BusinessLayer.SQL_Manager;
import DataLayer.DB_Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class app
{
    /** Creates a new instance of app */
    public app()
    {
    }

    public static DB_Connection con;
    public static void main( String[] args )
    {
        SQL_Manager SQLstatement = new SQL_Manager();
        con = DB_Connection.getInstance();
        con.Connect();
        app application = new app();
        application.init();
        /*SQL_Manager SQLstatement = new SQL_Manager();
        con = DB_Connection.getInstance();
        con.Connect();
        ArrayList<String> popOptions = new ArrayList<>();
        popOptions.add("WHERE country.Continent Like 'Europe'");
        popOptions.add("");
        con.displayPop(SQLstatement.getQuery("PopulationRow",popOptions));
        System.out.println("\n\n");
        */
    }

    public boolean init(){
        try {
            con = DB_Connection.getInstance();
            con.Connect();
            BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;
            Menu currentMenu = Menu.createMenuSystem() ;
            currentMenu = Menu.additionalDisplay(currentMenu,br);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}