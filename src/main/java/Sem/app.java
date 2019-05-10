package Sem;

import DataLayer.DB_Connection;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class app
{
    /** Creates a new instance of app */
    public app()
    {
    }

    public static DB_Connection con;

    //main method
    public static void main( String[] args )
    {
        //creates a new object app
        app application = new app();
        //initiate the object
        application.init();
    }

    //method that initiate the object
    public boolean init(){
        try {
            //tries to connect to the database
            con = DB_Connection.getInstance();
            con.Connect("db",true);
            BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;

            //creates the menu from the menu class
            Menu currentMenu = Menu.createMenuSystem() ;
            currentMenu = Menu.additionalDisplay(currentMenu,br);
            //if connects returns true
            return true;
        }catch(Exception ex){
            //if does not connect returns false
            return false;
        }
    }
}