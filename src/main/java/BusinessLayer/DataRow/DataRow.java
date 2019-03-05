package BusinessLayer.DataRow;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class DataRow {


    /**
     * getPropertyNames
     *
     *  This function gives back a list of
     *  the property names of this object
     * @return ArrayList<String> of property names
     */
    public ArrayList<String> getPropertyNames() {
        Field[] fields = this.getClass().getFields();

        ArrayList<String> names = new ArrayList<>();

        for (Field field : fields)
        {
            field.setAccessible(true);
            try {

                names.add(field.getName());
            } catch (final Exception e) {
                names.add(field.getName() + " > " + e.getClass().getSimpleName());
            }
        }
        return names;
    }

    /**
     * getPropertyValues
     *
     *  This function gives back a list of
     *  the property values of this object
     * @return ArrayList<String> of property values
     */
    public ArrayList<String> getPropertyValues() {

        Field[] fields = this.getClass().getFields();

        ArrayList<String> values = new ArrayList<>();

        for (Field field : fields)
        {
            if (field != null)
            {
                field.setAccessible(true);
                try {
                    if (field.get(this) != null)
                    {
                        values.add(field.get(this).toString());
                    }

                } catch (final IllegalAccessException e) {
                   // values.add(e.getClass().getSimpleName());
                }
            }

        }
        return values;
    }

    /**
     * setPropertyValue
     *
     * Dynamically changes values of this object,
     * only public values can be used this way
     * @param fieldName
     * @param newValue
     */
    public void setPropertyValue(String fieldName, Object newValue)  {
        try {
            Field[] fields = this.getClass().getFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getName().equals(fieldName))
                {

                    field.set(this, newValue);
                    //System.out.println("value set to:" + newValue);
                }
            }
        }catch(IllegalAccessException e){
            System.out.println("Illegal Access during changing values of object");
        }catch (Exception e){
            System.out.println("Error setting a value");
        }
    }


    @Override
    public String toString() {

        //String displayString = "";
        String display = "";
        System.out.println(String.format("%-10s %-15s %-20s %-8s", "value", "value", "value", "value"));

        for (String value : this.getPropertyValues()) {
            if (value != null)
            {
                //displayString += (value.toString() + "\t\t\t");

                display = String.format("%-10s %-15s %-20s %-8s"
                        , value.toString()
                        , value.toString()
                        , value.toString()
                        , value.toString());
            }
        }
        //return displayString;
        return display;
    }

}
