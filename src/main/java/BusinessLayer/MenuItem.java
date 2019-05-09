package BusinessLayer;

import Sem.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuItem
{
    //variables declaration
    private String title ;
    private Menu submenu ;
    private ActionListener onselect ;

    public String getTitle()
    {
        return title;
    }

    //method to obtain the title, submenu number and action from the user choice
    public MenuItem(String title, Menu submenu, ActionListener onselect )
    {
        this.title = title ;
        this.submenu = submenu ;
        this.onselect = onselect ;
    }

    //method to get the submenu number from the user choice
    public void setSubMenu( Menu submenu )
    {
        this.submenu = submenu ;
    }

    //method to get the action performed by the user in the menu
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