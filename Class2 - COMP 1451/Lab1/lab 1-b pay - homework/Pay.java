
/**
 * This is the Pay class as required on the Lab 1b homework assignment.
 * 
 * @author  Voicu Chirtes 
 * @version 4/18/2014
 */
public class Pay
{
    private static final double FIRST_DAY_EARNINGS_IN_USD = 0.01;
    private int numberOfDays;
    
    /**
     * Default constructor for the Pay class
     */
    public Pay()
    {
        calculatePay();
    }
       
    /**
     * Method that calculates and displays the daily pay and the total pay
     */
    public void calculatePay()
    {
        InputReader r = new InputReader();
        int n = 0;
        while(n<=0) {
            System.out.println("Enter number of days (must be greater than 0):");
            n=r.readInt();
            if (n<=0) {
                System.out.println("Error, please type in a number greater than 0!");
            }
        }
        numberOfDays = n;
        double currentDayEarnings = FIRST_DAY_EARNINGS_IN_USD;
        double total = 0;
        for(int i=1;i<=numberOfDays;i++) {
            System.out.println("Day "+i+" earning is: $ "+currentDayEarnings);
            total+=currentDayEarnings;
            currentDayEarnings*=2;        
        }
        System.out.printf("Total earnings for "+numberOfDays+" days is: $ %.2f%n",total);
    }
}
