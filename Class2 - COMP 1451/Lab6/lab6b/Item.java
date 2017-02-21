/**
 * This is the Item class as required on the lab6-b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 6/8/2014
 */
public class Item
{
    private String description;
    private double weightInKilos;
    private boolean canBePickedUp;
    
    /**
     * Default constructor
     */
    public Item()
    {
        setDescription("");
        setWeightInKilos(0.0);
        setCanBePickedUp(false);
    }
    
    /**
     * Non-default constructor
     */
    public Item(String description, double weightInKilos, boolean canBePickedUp)
    {
        setDescription(description);
        setWeightInKilos(weightInKilos);
        setCanBePickedUp(canBePickedUp);
    }
    
    /**
     * Mutator method to set the description
     * 
     * @param   description the description of the Item
     */
    public void setDescription(String description)
    {
        if(description!=null) {
            this.description = description;
        }
        else {
            this.description="";
        }
    }
    
    /**
     * Mutator method for the weightInKilos
     * 
     * @param     the weight of the Item
     */
    public void setWeightInKilos(double weightInKilos)
    {
        if(weightInKilos>0) {
            this.weightInKilos=weightInKilos;
        }
        else {
            this.weightInKilos = 0;
        }
    }
    
    /**
     * Method that sets wether the Item can be picked up
     * 
     * @param canBePicekdUp wether the item can be picked up
     */
    public void setCanBePickedUp(boolean canBePickedUp)
    {
        this.canBePickedUp=canBePickedUp;
    }
    
    /**
     * Accessor method for the description of the item
     * 
     * @return  the item description
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Accessor method for the weight of the item
     * 
     * @return  the item weight
     */
    public double getWeightInKilos()
    {
        return weightInKilos;
    }
    
    /**
     * Accessor method that returns wether the item can be picked up
     * 
     * @return  wether the item can be picked up
     */
    public boolean getCanBePickedUp()
    {
        return canBePickedUp;
    }    
    
    /**
     * Overriding the toString method to return the item description
     * 
     * @return  the item description
     */
    public String toString()
    {
        return description;
    }
}