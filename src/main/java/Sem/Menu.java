package Sem;

import BusinessLayer.MenuItem;
import BusinessLayer.SQL_Manager;
import DataLayer.DB_Connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;

public class Menu
{
    private List<MenuItem> items = new ArrayList<MenuItem>() ;
    private String title ;
    public static DB_Connection con;
    public static SQL_Manager SQLstatement = new SQL_Manager();

    public Menu( String title )
    {
        this.title = title ;
    }

    public Menu doOption( int option )
    {
        if( option == 0 ) return null ;
        option-- ;
        if( option >= items.size() )
        {
            System.out.println( "Unknown option " + option ) ;
            return this ;
        }
        items.get( option ).select() ;
        Menu next = items.get( option ).getSubMenu() ;

        return next == null ? this : next ;
    }

    public Menu addItem( MenuItem item )
    {
        items.add( item ) ;
        return this ;
    }

    public String toString()
    {
        StringBuilder sb = new StringBuilder() ;
        sb.append( title ).append( "\n" ) ;
        for( int i = 0 ; i < title.length() ; i++ )
        {
            sb.append( "-" ) ;
        }
        sb.append( "\n" ) ;
        for( int i = 0 ; i < items.size() ; i++ )
        {
            sb.append( ( i + 1 ) ).append( ") " ).append( items.get( i ) ).append( "\n" ) ;
        }
        sb.append( "0) Quit" ) ;
        return sb.toString() ;
    }

    public static Menu createMenuSystem()
    {
        con = DB_Connection.getInstance();
        con.Connect("db",true);
        MenuItem backLink = new MenuItem( "Go back to the previous Menu", null, null ) ;

        Menu subMenu1 = new Menu("Country Menu");

        subMenu1.addItem( new MenuItem("World", null, new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> options = new ArrayList<>();
                options.add("");
                options.add("");
                con.displayCountry(SQLstatement.getQuery("CountryRow",options));
                System.out.println("\n\n");

                System.out.println("world menus is called- this time only poland");


            }
        }) )
                .addItem( new MenuItem("Continent", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> options = new ArrayList<>();
                        options.add("WHERE country.Continent Like 'Europe'");
                        options.add("");
                        con.displayCountry(SQLstatement.getQuery("CountryRow",options));
                        System.out.println("\n\n");
                    }
                }) )
                .addItem( new MenuItem("Region", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> options = new ArrayList<>();
                        options.add("WHERE country.Region Like 'Southern Europe'");
                        options.add("");
                        con.displayCountry(SQLstatement.getQuery("CountryRow",options));
                        System.out.println("\n\n");
                    }
                }) ).addItem( backLink );

        Menu subMenu2 = new Menu("City Menu");

        subMenu2.addItem( new MenuItem("World", null, new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> cityOptions = new ArrayList<>();
                cityOptions.add("");
                cityOptions.add("");
                con.displayCity(SQLstatement.getQuery("CityRow",cityOptions));
                System.out.println("\n\n");
            }
        }) )
                .addItem( new MenuItem("Continent", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> cityOptions = new ArrayList<>();
                        cityOptions.add("WHERE country.Continent Like 'Europe'");
                        cityOptions.add("");
                        con.displayCity(SQLstatement.getQuery("CityRow",cityOptions));
                        System.out.println("\n\n");
                    }
                }) )
                .addItem( new MenuItem("Region", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> cityOptions = new ArrayList<>();
                        cityOptions.add("WHERE country.Region Like 'Southern Europe'");
                        cityOptions.add("");
                        con.displayCity(SQLstatement.getQuery("CityRow",cityOptions));
                        System.out.println("\n\n");
                    }
                }) )
                .addItem( new MenuItem("Country", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> cityOptions = new ArrayList<>();
                        cityOptions.add("WHERE country.Name Like 'France'");
                        cityOptions.add("");
                        con.displayCity(SQLstatement.getQuery("CityRow",cityOptions));
                        System.out.println("\n\n");
                    }
                }) )
                .addItem( new MenuItem("District", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> cityOptions = new ArrayList<>();
                        cityOptions.add("WHERE city.District Like 'Noord-Holland'");
                        cityOptions.add("");
                        con.displayCity(SQLstatement.getQuery("CityRow",cityOptions));
                        System.out.println("\n\n");
                    }
                }) ).addItem( backLink );

        Menu subMenu3 = new Menu("Capital Menu");

        subMenu3.addItem( new MenuItem("World", null, new ActionListener(){
            public void actionPerformed(ActionEvent e) {

                ArrayList<String> capitalOptions = new ArrayList<>();
                capitalOptions.add("");
                capitalOptions.add("");
                con.displayCapital(SQLstatement.getQuery("CapitalCityRow",capitalOptions));
                System.out.println("\n\n");
            }
        }) )
                .addItem( new MenuItem("Continent", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> capitalOptions = new ArrayList<>();
                        capitalOptions.add("WHERE country.Continent Like 'Europe'");
                        capitalOptions.add("");
                        con.displayCapital(SQLstatement.getQuery("CapitalCityRow",capitalOptions));
                        System.out.println("\n\n");
                    }
                }) )
                .addItem( new MenuItem("Region", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> capitalOptions = new ArrayList<>();
                        capitalOptions.add("WHERE country.Region Like 'Southern Europe'");
                        capitalOptions.add("");
                        con.displayCapital(SQLstatement.getQuery("CapitalCityRow",capitalOptions));
                        System.out.println("\n\n");
                    }
                }) )
                .addItem( backLink );
        Menu subMenu4 = new Menu("Population Menu");
        subMenu4.addItem( new MenuItem("City population for Continent", null, new ActionListener(){
            public void actionPerformed(ActionEvent e) {


            }
        }) )
                .addItem( new MenuItem("City population for Region", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {


                    }
                }) )
                .addItem( new MenuItem("City population for Country", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {


                    }
                }) )
                .addItem( new MenuItem("World Population", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        ArrayList<String> popOptions = new ArrayList<>();
                        popOptions.add("");
                        popOptions.add("");
                        System.out.println("\nWorld Population is "+con.displayPop(SQLstatement.getQuery("PopulationRow",popOptions))+"\n");
                        System.out.println("\n\n");

                    }
                }) )
                .addItem( new MenuItem("Continent Population", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        String continent="";
                        System.out.println("Please put in name of the Country");
                        try {
                            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                            continent = input.readLine() ;
                            ArrayList<String>nameList=new ArrayList<>();
                            nameList=con.getContinents();
                            if(nameList.contains(continent))
                            {
                                ArrayList<String>countryOption = new ArrayList<>();
                                countryOption.add("Where country.Continent Like '"+continent+"'");
                                System.out.println(continent+" Population is "+con.displayPop(SQLstatement.getQuery("PopulationRow",countryOption)));

                            }

                        }catch( Exception ex )
                        {
                            System.out.println( "No such Region as " + continent ) ;
                        }

                    }
                }) )
                .addItem( new MenuItem("Region Population", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        String region="";
                        System.out.println("Please put in name of the Country");
                        try {
                            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                            region = input.readLine() ;
                            ArrayList<String>nameList=new ArrayList<>();
                            nameList=con.getRegions();
                            if(nameList.contains(region))
                            {
                                ArrayList<String>countryOption = new ArrayList<>();
                                countryOption.add("Where country.Region Like '"+region+"'");
                                System.out.println(region+" Population is "+con.displayPop(SQLstatement.getQuery("PopulationRow",countryOption)));

                            }

                        }catch( Exception ex )
                        {
                            System.out.println( "No such Region as " + region ) ;
                        }

                    }
                }) )
                .addItem( new MenuItem("Country Population", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        String country="";
                        System.out.println("Please put in name of the Country");
                        try {
                            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                            country = input.readLine() ;
                            ArrayList<String>nameList=new ArrayList<>();
                            nameList=con.getCountries();
                            if(nameList.contains(country))
                            {
                                ArrayList<String>countryOption = new ArrayList<>();
                                countryOption.add("Where country.Name Like '"+country+"'");
                                System.out.println(country+" Population is "+con.displayPop(SQLstatement.getQuery("PopulationRow",countryOption)));

                            }

                        }catch( Exception ex )
                        {
                            System.out.println( "No such country as " + country ) ;
                        }

                    }
                }) )
                .addItem( new MenuItem("District Population", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        String district="";
                        System.out.println("Please put in name of the Country");
                        try {
                            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                            district = input.readLine() ;
                            ArrayList<String>nameList=new ArrayList<>();
                            nameList=con.getDistricts();
                            if(nameList.contains(district))
                            {
                                ArrayList<String>countryOption = new ArrayList<>();
                                countryOption.add("Where city.District Like '"+district+"'");
                                System.out.println(district+" Population is "+con.displayPop(SQLstatement.getQuery("CityPopRow",countryOption)));

                            }

                        }catch( Exception ex )
                        {
                            System.out.println( "No such Region as " + district ) ;
                        }

                    }
                }) )
                .addItem( new MenuItem("City Population", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {

                        String city="";
                        System.out.println("Please put in name of the Country");
                        try {
                            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                            city = input.readLine() ;
                            ArrayList<String>nameList=new ArrayList<>();
                            nameList=con.getCities();
                            if(nameList.contains(city))
                            {
                                ArrayList<String>countryOption = new ArrayList<>();
                                countryOption.add("Where city.Name Like '"+city+"'");
                                System.out.println(city+" Population is "+con.displayPop(SQLstatement.getQuery("CityPopRow",countryOption)));

                            }

                        }catch( Exception ex )
                        {
                            System.out.println( "No such Region as " + city ) ;
                        }

                    }
                }) ).addItem( backLink );
        Menu subMenu5 = new Menu("Language Report");
        subMenu5.addItem( new MenuItem("Display Report", null, new ActionListener(){
            public void actionPerformed(ActionEvent e) {


            }
        }) ).addItem( backLink );

        Menu rootMenu = new Menu( "Create a report" ) ;
        rootMenu.addItem( new MenuItem( "Enter into the Country section to choose a report to print (Country)", subMenu1, null ) )
                .addItem( new MenuItem( "Enter into the City section to choose a report to print (City)", subMenu2, null ) )
                .addItem( new MenuItem( "Enter into the Capital section to choose a report to print (Capital)", subMenu3, null) )
                .addItem( new MenuItem( "Enter into the Population section to choose a report to print (Capital)", subMenu4, null) )
                .addItem( new MenuItem( "Language Report", subMenu5, null) );

        // Tie the backlink up
        backLink.setSubMenu( rootMenu ) ;

        return rootMenu ;
    }

    public static Menu additionalDisplay(Menu currentMenu, BufferedReader br){
        //TODO: Find a better way so static is not required
        while( currentMenu != null )
        {
            System.out.println( currentMenu ) ;
            System.out.print( "Your Selection : " ) ;
            String inp = "" ;
            try
            {
                inp = br.readLine() ;
                currentMenu = currentMenu.doOption( Integer.parseInt( inp ) ) ;
            }
            catch( Exception ex )
            {
                System.out.println( "Didn't understand " + inp ) ;
            }
        }

        //TODO: currentMenu is always null here, find better solution
        return currentMenu;
    }
}
