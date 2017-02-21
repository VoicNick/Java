/**
 * This is the Instructor class as required on the lab5b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 5/31/2014
 */

public class Instructor extends Person 
{
    private double hourlyWageInUSD;
    
    /**
     * Default constructor
     */
    public Instructor()
    {
        super();
        setHourlyWageInUSD(0);
    }
    
    /**
     * Non-default costructor
     */
    public Instructor(String firstName, String lastName, String email, double hourlyWageInUSD)
    {
        super(firstName,lastName,email);
        setHourlyWageInUSD(hourlyWageInUSD);
    }
    
    /**
     * Muthator method for setting the hourly wage
     * 
     * @param   hourlyWageInUSD  the hourly wage of the Instructor
     */
    public void setHourlyWageInUSD(double hourlyWageInUSD)
    {
        if(hourlyWageInUSD>0) {
            this.hourlyWageInUSD=hourlyWageInUSD;
        }
        else {
            hourlyWageInUSD=0;
        }
    }
    
    /**
     * Accessor method for the hourly wage in USD
     * 
     * @return  teh hourly wage in USD
     */
    public double getHourlyWageInUSD()
    {
        return hourlyWageInUSD;
    }
}
