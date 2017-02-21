import java.util.HashMap;
import java.util.HashSet;

/**
 * This is the EmailInfo class as required for the project 2B homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 5/4/2014
 */

public class EmailInfo 
{
    private HashMap<String, String> addressBook;
    
    /**
     * This is the default constructor that initialize our address book
     * by creating a new HashMap object with its default constructor.
     */
    public EmailInfo() 
    {
        addressBook = new HashMap<String, String>();
    }
    
    /**
     * This method is used to keep a consistent string format for the string key of our HashMap.
     * It returns the string parameter formated like this:
     * -first letter is converted to upper case
     * -all remaining letters to lower case
     * 
     * @param   name    the name to be formatted
     * @return  the formatted version of the name
     */
    private String formatName(String name)
    {
        return (name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase());
    }
    
    /**
     * This method adds a friend record to our address book. The name of hte friend 
     * is properlly formatted to keep consistency.
     * 
     * @param   name    the name of the friend to add
     * @param   email   the email of the friend to add
     */
    public void addFriend(String name, String email)
    {
        if(name!=null && email!=null)
        addressBook.put(formatName(name),email);
    }
    
    /**
     * This method removes a friend record form our address book if he exists
     * 
     * @param   name    the name of the friend to remove
     */
    public void removeFriend(String name)
    {
        if(name!=null)
        addressBook.remove(formatName(name));
    }
    
    /**
     * This method returns the email address of a friend based on his name
     * 
     * @param   name    the name to search
     */
    public String getAddress(String name)
    {
        if(name!=null)
        return addressBook.get(formatName(name));
        else
        System.out.println("Cannot search for a null object");
        return null;
    }
    
    /**
     * This method finds and displays friends based on their email address. 
     * There might be more than one friend using the same email.
     * 
     * @param   email   the email to search
     */
    public void findFriends(String email)
    {
        if(email != null) {
            System.out.println("Names of friends that have the email "+email+" :");
            Boolean found = false;
            for(String name:addressBook.keySet()) {
                if(email.equalsIgnoreCase(addressBook.get(name))) {
                    System.out.println(name);
                    found = true;
                }
            }
            if (!found)
            System.out.println("No matches found.");
        }
    }
    
    /**
     * List all friends in the address book and their email addresses
     */
    public void listAll()
    {
        if(addressBook.size()>0) {
            System.out.println("Here are all the entries in our address book: ");
            for(String name:addressBook.keySet()) {
                System.out.println("Name: "+name+"\nEmail: "+addressBook.get(name)+ "\n--------------------------------------");
            }
        }
        else {
            System.out.println("The address book is empty.");
        }
    }
}