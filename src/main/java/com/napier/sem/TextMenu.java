package com.napier.sem;

import BusinessLayer.SQL_Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TextMenu
{
    class Menu
    {
        private List<MenuItem> items = new ArrayList<MenuItem>() ;
        private String title ;

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
    }

    class MenuItem
    {
        private String title ;
        private Menu submenu ;
        private ActionListener onselect ;

        public String getTitle()
        {
            return title;
        }

        public MenuItem( String title, Menu submenu, ActionListener onselect )
        {
            this.title = title ;
            this.submenu = submenu ;
            this.onselect = onselect ;
        }

        public void setSubMenu( Menu submenu )
        {
            this.submenu = submenu ;
        }

        public void select()
        {
            if( onselect != null )
                onselect.actionPerformed( new ActionEvent( this, 0, "select" ) ) ;
        }

        public Menu getSubMenu()
        {
            return submenu ;
        }

        public String toString()
        {
            return title ;
        }
    }

    public Menu createMenuSystem()
    {
        MenuItem backLink = new MenuItem( "Go back to the previous Menu", null, null ) ;

        Menu subMenu1 = new Menu("Country Menu");

        subMenu1.addItem( new MenuItem("World", null, new ActionListener(){
            public void actionPerformed(ActionEvent e) {


                SQL_Manager SQLstatement = new SQL_Manager();
                SQLstatement.query_db("CountryRow", null);
            }
        }) )
                .addItem( new MenuItem("Continent", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //code for the actual query
                    }
                }) )
                .addItem( new MenuItem("Region", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //code for the actual query
                    }
                }) ).addItem( backLink );

        Menu subMenu2 = new Menu("City Menu");

        subMenu2.addItem( new MenuItem("World", null, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //code for the actual query
            }
        }) )
                .addItem( new MenuItem("Continent", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //code for the actual query
                    }
                }) )
                .addItem( new MenuItem("Region", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //code for the actual query
                    }
                }) ).addItem( backLink );

        Menu subMenu3 = new Menu("Capital Menu");

        subMenu3.addItem( new MenuItem("World", null, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //code for the actual query
            }
        }) )
                .addItem( new MenuItem("Continent", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //code for the actual query
                    }
                }) )
                .addItem( new MenuItem("Region", null, new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //code for the actual query
                    }
                }) ).addItem( backLink );

        Menu rootMenu = new Menu( "Create a report" ) ;
        rootMenu.addItem( new MenuItem( "Enter into the Country section to choose a report to print (Country)", subMenu1, null ) )
                .addItem( new MenuItem( "Enter into the City section to choose a report to print (City)", subMenu2, null ) )
                .addItem( new MenuItem( "Enter into the Capital section to choose a report to print (Capital)", subMenu3, null) );

        // Tie the backlink up
        backLink.setSubMenu( rootMenu ) ;

        return rootMenu ;
    }

    /** Creates a new instance of TextMenu */
    public TextMenu()
    {
    }

    public static void main( String[] args )
    {
        BufferedReader br = new BufferedReader( new InputStreamReader( System.in ) ) ;
        TextMenu t = new TextMenu() ;
        Menu currentMenu = t.createMenuSystem() ;
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
    }
}