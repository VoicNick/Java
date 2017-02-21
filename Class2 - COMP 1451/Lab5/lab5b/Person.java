/**
 * This is the Person class needed for the lab5B homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 5/31/2014
 */
public class Person
{
    private String firstName, lastName, email, bcitId;
    private static final String BCIT_ID_PREFIX = "A000000";
    private static int BCIT_ID_INDEX = 1;
    
    /**
     * Default constructor
     */
    public Person()
    {
        setBcitId();
        setFirstName("");
        setLastName("");
        setEmail("");
    }
    
    /**
     * Non-default constructor
     */
    public Person(String firstName, String lastName, String email)
    {
        setBcitId();
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
    }
    
    /**
     * Method that calculates and assigns a uniques BCIT Id
     */
    private void setBcitId()
    {        
        String id = ""+BCIT_ID_INDEX;
        bcitId = BCIT_ID_PREFIX.substring(0,BCIT_ID_PREFIX.length()-id.length())+id;
        BCIT_ID_INDEX++;
    }
    
    /**
     * Mutator method for setting the first name
     * 
     * @param   firstName   the first name to set
     */
    public void setFirstName(String firstName)
    {
        if(firstName!=null) {
            this.firstName = firstName;
        }
        else {
            this.firstName = "";
        }
    }
    
    /**
     * Mutator method for setting the last name
     *  
     * @param   lastName    the last name to set
     */
    public void setLastName(String lastName)
    {
        if(lastName!=null) {
            this.lastName = lastName;
        }
        else {
            this.lastName = "";
        }
    }
    
    /**
     * Mutator method for setting the email address
     * 
     * @param   email   the email address to set
     */
    public void setEmail(String email)
    {
        if(email!=null) {
            this.email = email;
        }
        else {
            this.email="";
        }
    }
    
    /**
     * Accessor method for the first name field
     * 
     * @return  the first name of the person
     */
    public String getFirstName()
    {
        return firstName;
    }
    
    /**
     * Accessor method for the last name field
     * 
     * @return  the last name of the person
     */
    public String getLastName()
    {
        return lastName;
    }
    
    /**
     * Accessor method for the email field
     * 
     * @return  the email address of the person
     */
    public String getEmail()
    {
        return email;
    }
    
    /**
     * Accessor method for the BICT Id field
     * 
     * @return  the BCIT Id of the person
     */
    public String getBcitId()
    {
        return bcitId;
    }
}