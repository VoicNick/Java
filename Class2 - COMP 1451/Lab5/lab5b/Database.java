import java.util.ArrayList;
/**
 * This is the Database class as required on the lab 5b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 6/1/2014
 */
public class Database
{
    private ArrayList<Person> database;
    
    /**
     * Default constructor
     */
    public Database()
    {
        database = new ArrayList<Person>();
    }
    
    /**
     * Method that adds a person, Student or Instructor to the database
     * 
     * @param   personToAdd the person to add
     */
    public void addPerson(Person personToAdd)
    {
        if(personToAdd!=null) {
            database.add(personToAdd);
        }
    }
    
    /**
     * Method that displays the names and email addresses of everyone 
     * in the database.
     */
    public void showDatabase()
    {
        if(database.isEmpty()) {
            System.out.println("The database is empty.");
        }
        else {
            System.out.println("The database contains:");
            for(Person person:database) {
                System.out.println(person.getFirstName()+" "+person.getLastName()+" "+person.getEmail());
            }
        }
    }
}
