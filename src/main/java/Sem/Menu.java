package Sem;

import BusinessLayer.MenuItem;
import BusinessLayer.SQL_Manager;
import DataLayer.DB_Connection;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStreamReader;

public class Menu
{
    private List<MenuItem> items = new ArrayList<MenuItem>() ;
    private String title ;
    public static final DB_Connection con=DB_Connection.getInstance();
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

        con.Connect("db",true);
        MenuItem backLink = new MenuItem( "Go back to the previous Menu", null, null ) ;

        Menu subMenu1 = new Menu("Country Menu");

        subMenu1.addItem( new MenuItem("World", null, e -> {

            ArrayList<String> options = new ArrayList<>();
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            options.add("");
            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
            try {
                String limit = input.readLine();
                if (Integer.valueOf(limit)==0){
                    options.add("");
                }else
                {
                    options.add("LIMIT "+Integer.valueOf(limit));
                }
                con.displayCountry(SQLstatement.getQuery("CountryRow",options));
                System.out.println("\n\n");

                System.out.println("world menus is called");
            } catch (IOException e1) {
                e1.printStackTrace();
            }



        }) )
                .addItem( new MenuItem("Continent", null, e -> {

                    String continent="";
                    System.out.println("Please put in name of the Continent");
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        continent = input.readLine() ;
                        ArrayList<String> nameList1;
                        nameList1 =con.getContinents();
                        if(nameList1.contains(continent))
                        {
                            ArrayList<String>countryOption = new ArrayList<>();
                            countryOption.add("WHERE country.Continent LIKE '"+ continent +"'");
                            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
                            String limit= input.readLine();
                            if (Integer.valueOf(limit) == 0){
                                countryOption.add("");
                            }else
                            {
                                countryOption.add("LIMIT "+limit);
                            }
                            System.out.println(continent+"'s Countries Populations are as follows:");
                            System.out.println("\n\n");
                            con.displayCountry(SQLstatement.getQuery("CountryRow", countryOption));
                            System.out.println("\n\n");


                        }

                    }catch( Exception ex )
                    {
                        System.out.println( "No such Region as " + continent ) ;
                    }


                }) )
                .addItem( new MenuItem("Region", null, e -> {

                    String region="";
                    System.out.println("Please put in name of the Region");
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        region = input.readLine() ;
                        ArrayList<String> nameList12;
                        nameList12 =con.getRegions();
                        if(nameList12.contains(region))
                        {
                            ArrayList<String>countryOption = new ArrayList<>();
                            countryOption.add("WHERE country.Region LIKE '"+ region +"'");
                            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
                            String limit= input.readLine();
                            if (Integer.valueOf(limit) == 0){
                                countryOption.add("");
                            }else
                            {
                                countryOption.add("LIMIT "+limit);
                            }
                            System.out.println(region+"'s Countries Populations are as follows:");
                            System.out.println("\n\n");
                            con.displayCountry(SQLstatement.getQuery("CountryRow", countryOption));
                            System.out.println("\n\n");


                        }

                    }catch( Exception ex )
                    {
                        System.out.println( "No such Region as " + region ) ;
                    }


                }) ).addItem( backLink );

        Menu subMenu2 = new Menu("City Menu");

        subMenu2.addItem( new MenuItem("World", null, e -> {

            ArrayList<String> cityOptions = new ArrayList<>();
            cityOptions.add("");
            cityOptions.add("");
            con.displayCity(SQLstatement.getQuery("CityRow",cityOptions));
            System.out.println("\n\n");
        }) )
                .addItem( new MenuItem("Continent", null, e -> {

                    String continent="";
                    System.out.println("Please put in name of the Continent");
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        continent = input.readLine() ;
                        ArrayList<String> nameList13;
                        nameList13 =con.getContinents();
                        if(nameList13.contains(continent))
                        {
                            ArrayList<String>cityOption = new ArrayList<>();
                            cityOption.add("WHERE country.Continent LIKE '"+ continent +"'");
                            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
                            String limit= input.readLine();
                            if (Integer.valueOf(limit) == 0){
                                cityOption.add("");
                            }else
                            {
                                cityOption.add("LIMIT "+limit);
                            }
                            System.out.println(continent+"'s Cities Populations are as follows:");
                            System.out.println("\n\n");
                            con.displayCity(SQLstatement.getQuery("CityRow", cityOption));
                            System.out.println("\n\n");


                        }

                    }catch( Exception ex )
                    {
                        System.out.println( "No such Continent as " + continent ) ;
                    }

                }) )
                .addItem( new MenuItem("Region", null, e -> {

                    String region="";
                    System.out.println("Please put in name of the Continent");
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        region = input.readLine() ;
                        ArrayList<String> nameList14;
                        nameList14 =con.getRegions();
                        if(nameList14.contains(region))
                        {
                            ArrayList<String>cityOption = new ArrayList<>();
                            cityOption.add("WHERE country.Region LIKE '"+ region +"'");
                            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
                            String limit= input.readLine();
                            if (Integer.valueOf(limit) == 0){
                                cityOption.add("");
                            }else
                            {
                                cityOption.add("LIMIT "+limit);
                            }
                            System.out.println(region+"'s Cities Populations are as follows:");
                            System.out.println("\n\n");
                            con.displayCity(SQLstatement.getQuery("CityRow", cityOption));
                            System.out.println("\n\n");


                        }

                    }catch( Exception ex )
                    {
                        System.out.println( "No such Region as " + region ) ;
                    }


                }) )
                .addItem( new MenuItem("Country", null, e -> {

                    String country="";
                    System.out.println("Please put in name of the Country");
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        country = input.readLine() ;
                        ArrayList<String> nameList15;
                        nameList15 =con.getCountries();
                        if(nameList15.contains(country))
                        {
                            ArrayList<String>cityOption = new ArrayList<>();
                            cityOption.add("WHERE country.Name LIKE '"+ country +"'");
                            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
                            String limit= input.readLine();
                            if (Integer.valueOf(limit) == 0){
                                cityOption.add("");
                            }else
                            {
                                cityOption.add("LIMIT "+limit);
                            }
                            System.out.println(country+"'s Cities Populations are as follows:");
                            System.out.println("\n\n");
                            con.displayCity(SQLstatement.getQuery("CityRow", cityOption));
                            System.out.println("\n\n");


                        }

                    }catch( Exception ex )
                    {
                        System.out.println( "No such Country as " + country ) ;
                    }
                }) )
                .addItem( new MenuItem("District", null, e -> {

                    String district="";
                    System.out.println("Please put in name of the District");
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        district = input.readLine() ;
                        ArrayList<String> nameList16;
                        nameList16 =con.getDistricts();
                        if(nameList16.contains(district))
                        {
                            ArrayList<String>cityOption = new ArrayList<>();
                            cityOption.add("WHERE city.District LIKE '"+ district +"'");
                            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
                            String limit= input.readLine();
                            if (Integer.valueOf(limit) == 0){
                                cityOption.add("");
                            }else
                            {
                                cityOption.add("LIMIT "+limit);
                            }
                            System.out.println(district+"'s Cities Populations are as follows:");
                            System.out.println("\n\n");
                            con.displayCity(SQLstatement.getQuery("CityRow", cityOption));
                            System.out.println("\n\n");


                        }

                    }catch( Exception ex )
                    {
                        System.out.println( "No such District as " + district ) ;
                    }
                }) ).addItem( backLink );

        Menu subMenu3 = new Menu("Capital Menu");

        subMenu3.addItem( new MenuItem("World", null, e -> {

            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            ArrayList<String> capitalOptions = new ArrayList<>();
            capitalOptions.add("");
            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
            try {
                String limit = input.readLine();
                if (Integer.valueOf(limit) == 0) {
                    capitalOptions.add("");
                } else {
                    capitalOptions.add("LIMIT " + limit);
                }
                con.displayCapital(SQLstatement.getQuery("CapitalCityRow", capitalOptions));
                System.out.println("\n\n");
            }catch( Exception ex )
            {
                System.out.println( "no input detected" ) ;
            }

        }) )
                .addItem( new MenuItem("Continent", null, e -> {

                    String continent="";
                    System.out.println("Please put in name of the Continent");
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        continent = input.readLine() ;
                        ArrayList<String> nameList17;
                        nameList17 =con.getContinents();
                        if(nameList17.contains(continent))
                        {
                            ArrayList<String>capitalOption = new ArrayList<>();
                            capitalOption.add("WHERE country.Continent LIKE '"+ continent +"'");
                            String limit = input.readLine();
                            if (Integer.valueOf(limit) == 0) {
                                capitalOption.add("");
                            } else {
                                capitalOption.add("LIMIT " + limit);
                            }
                            System.out.println(continent+"'s Capitals Populations are as follows:");
                            System.out.println("\n\n");
                            con.displayCapital(SQLstatement.getQuery("CityRow", capitalOption));
                            System.out.println("\n\n");


                        }

                    }catch( Exception ex )
                    {
                        System.out.println( "No such Continent as " + continent ) ;
                    }
                }) )
                .addItem( new MenuItem("Region", null, e -> {

                    String region="";
                    System.out.println("Please put in name of the Region");
                    try {
                        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
                        region = input.readLine() ;
                        ArrayList<String> nameList18;
                        nameList18 =con.getRegions();
                        if(nameList18.contains(region))
                        {
                            ArrayList<String>capitalOption = new ArrayList<>();
                            capitalOption.add("WHERE country.Region LIKE '"+ region +"'");
                            System.out.println("If you would like to limit number of results, please put in to how many top results by population");
                            String limit = input.readLine();
                            if (Integer.valueOf(limit) == 0) {
                                capitalOption.add("");
                            } else {
                                capitalOption.add("LIMIT " + limit);
                            }
                            System.out.println(region+"'s Capitals Populations are as follows:");
                            System.out.println("\n\n");
                            con.displayCapital(SQLstatement.getQuery("CityRow", capitalOption));
                            System.out.println("\n\n");


                        }

                    }catch( Exception ex )
                    {
                        System.out.println( "No such Region as " + region ) ;
                    }
                }) )
                .addItem( backLink );
        Menu subMenu4 = new Menu("Population Menu");
        subMenu4.addItem( new MenuItem("Continent Population Report", null, e -> {
            String type = "Continent";
            pop(type);


        }) )
                .addItem( new MenuItem("Region Population Report", null, e -> {
                    String type = "Region";
                    pop(type);


                }) )
                .addItem( new MenuItem("Country Population Report", null, e -> {
                    String type = "Name";
                    pop(type);



                }) ).addItem( backLink );
        Menu subMenu5 = new Menu("Language Report");
        subMenu5.addItem( new MenuItem("Display Report", null, e -> {
            ArrayList<String>Lang = new ArrayList<>();
            con.displayLang(SQLstatement.getQuery("LanguageRow", Lang));


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
    // method that validates user input and displays population report
    public static void pop (String type)
    {
        String name="";
        System.out.println("Please put in name of the "+type);
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            name = input.readLine() ;
            boolean test = false;
            ArrayList<String>nameList;
            switch(type)
            {
                case "Continent":
                    nameList=con.getContinents();
                    if(nameList.contains(name))
                    {
                        test = true;
                    }

                    break;
                case "Region":
                    nameList=con.getRegions();
                    if(nameList.contains(name))
                    {
                        test = true;
                    }
                    break;
                case "Name":
                    nameList=con.getCountries();
                    if(nameList.contains(name))
                    {
                        test = true;
                    }
                    break;
            }


            if(test == true)
            {
                ArrayList<String>popOption = new ArrayList<>();
                popOption.add(type);
                popOption.add(type);
                popOption.add("'"+ name +"'");
                popOption.add(type);
                System.out.println("\n\n");
                con.displayPop(SQLstatement.getQuery("PopulationRow", popOption), type);
                System.out.println("\n\n");


            } else
                {
                    System.out.println("Name not found");
                }

        }catch( Exception ex )
        {
            System.out.println( "No such Continent as " + name ) ;
        }
    }
}
