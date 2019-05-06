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
    public static void main( String[] args )
    {
        app application = new app();
        application.init();
    }

    public boolean init(){
        try {
            con = DB_Connection.getInstance();
            con.Connect("db",false);
            BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;
            Menu currentMenu = Menu.createMenuSystem() ;
            currentMenu = Menu.additionalDisplay(currentMenu,br);
            return true;
        }catch(Exception ex){
            return false;
        }
    }
}