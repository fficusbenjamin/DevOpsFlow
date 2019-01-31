package BusinessLayer;

public class CountryReport extends Report{

    public CountryReport(){
        super();
    }

    @Override
    public void displayReport(){
        System.out.println("Country Report:" + this.name);
    }
}
