package BusinessLayer;

import Sem.Menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuItem
{
    private String title ;
    private Menu submenu ;
    private ActionListener onselect ;

    public String getTitle()
    {
        return title;
    }

    public MenuItem(String title, Menu submenu, ActionListener onselect )
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