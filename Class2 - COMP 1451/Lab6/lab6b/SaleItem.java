import java.text.DecimalFormat;
/**
 * This is the SaleItem class as required on the lab6-b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 6/8/2014
 */
public class SaleItem extends Item
{
    private double costInUSD;
    
    /**
     * Default constructor
     */
    public SaleItem()
    {
        super("",0.0,true);
        setCostInUSD(0.0);
    }
    
    /**
     * Non-default constructor
     */
    public SaleItem(String description, double weightInKilos, double costInUSD)
    {
        super(description,weightInKilos,true);
        setCostInUSD(costInUSD);
    }
    
    /**
     * Mutator method for setting the cost in USD of the Item for sale
     * 
     * @param   costInUSD   the cost in USD of item for sale
     */
    public void setCostInUSD(double costInUSD)
    {
        if(costInUSD>0) {
            this.costInUSD=costInUSD;
        }
        else {
            costInUSD=0;
        }
    }
    
    /**
     * Accessor method for the costInUSD
     * 
     * @return  the cost in USD of the item
     */
    public double getCostInUSD()
    {
        return costInUSD;
    }
    
    /**
     * Overriding the toString method to return the item description and cost
     * 
     * @return  the item description and cost
     */
    public String toString()
    {
        DecimalFormat df = new DecimalFormat("#.00");        
        return getDescription()+" costing $"+df.format(costInUSD);
    }
}