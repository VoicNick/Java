
/**
 * List interface.
 * 
 * @author Colleen 
 * @version 2013.11.16
 */
public interface MyList
{
    /**
     * Adds a new element at the end of the list.
     * @param the object to add
     * @return true if element successfully added, otherwise false
     */
    boolean add(Object toAdd);
       
    /**
     * Gets the object at the specified index.
     * @param index value of object to get
     * @return object at that index
     */
    Object get(int index);
    
    /**
     * Removes specified object from list.
     * @param index value of object to remove
     * @return the object removed
     */
    Object remove(int index);
    
    /**
     * Returns size of the list
     * @return number of elements in the list
     */
    int size();
    
    /**
     * @return true if the list has no elements, false otherwise
     */
    boolean isEmpty();
    

}
