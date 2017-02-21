
/**
 * Write a description of class MyArrayList here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyArrayList implements MyList
{
    private Object[] theList;
    private int listSize = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int WILL_EMPTY_SIZE = 1;
    
    /**
     * Constructor for objects of class MyArrayList
     */
    public MyArrayList()
    {
        theList = new Object[DEFAULT_SIZE];
        listSize = 0;
    }

    protected void increaseCapacity()
    {
        Object[] newList = new Object[listSize+DEFAULT_SIZE];
        for(int i=0;i<listSize;i++) {
            newList[i]=theList[i];
        }
        theList=newList;
    }
    
    /**
     * Adds a new element at the end of the list.
     * @param the object to add
     * @return true if element successfully added, otherwise false
     */
    public boolean add(Object toAdd)    
    {
        if(theList.length <= listSize) {
            increaseCapacity();
        }
        theList[listSize]=toAdd;    
        listSize++;        
        return true;
    }
       
    /**
     * Gets the object at the specified index.
     * @param index value of object to get
     * @return object at that index
     */
    public Object get(int index)
    {
        if(index>=0 && index<listSize) {
            return theList[index];
        }
        else {
            System.out.println("Invalid index");
            return null;
        }
    }
    
    /**
     * Removes specified object from list.
     * @param index value of object to remove
     * @return the object removed
     */
    public Object remove(int index)
    {
        if(index>=0 && index<listSize) {
            Object o = theList[index];            
            if(listSize==WILL_EMPTY_SIZE) {                               
                theList = new Object[DEFAULT_SIZE];
            }
            else {
                for(int i=index;i<listSize;i++) {
                    theList[i]=theList[i+1];
                }
                theList[listSize]=null;
            }
            listSize--;
            return o;
        }   
        else {
            System.out.println("Invalid index");
            return null;
        }
    }
    
    /**
     * Returns size of the list
     * @return number of elements in the list
     */
    public int size()
    {
        return listSize;
    }
    
    /**
     * @return true if the list has no elements, false otherwise
     */
    public boolean isEmpty()
    {
        return (listSize==0);
    }
}
