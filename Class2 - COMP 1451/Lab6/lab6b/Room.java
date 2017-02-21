import java.util.HashMap;
import java.util.Set;
import java.util.ArrayList;
import java.util.Iterator;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @author  Voicu Chirtes - modified the original
 * @version 5/10/2014
 */

public class Room 
{
    private String description;
    private HashMap <String, Room> exits;
    private ArrayList<Item> items;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
    }

    /**
     * Create a room described "description" containg the list of items "items"
     * 
     * @param   description     The room's description.
     * @param   items           the list of items in the room
     */
    public Room(String description, ArrayList<Item> items) 
    {
        this.description = description;
        this.items = items;
        exits = new HashMap<String, Room>();
        items = new ArrayList<Item>();
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * This method returns the next room in the direction passed as parameter. If no room is there it returns null
     * 
     * @return  the Room in the direction specified by the direction parameter
     */
    public Room getExit(String direction)
    {
        return exits.get(direction);
    }
    
    /**
     * Return a description of the room’s exits,
     * for example "Exits: north west".
     * @return A description of the available exits.
     */
    public String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }
    
    /**
     * Return a long description of this room, of the form:
     * You are in the kitchen.
     * Exits: north west
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getRoomItems() + ".\n"+ getExitString();
    }

    /**
     * Method that adds Items in the room. This is required per assignemnt lab6-b 
     * 
     * @param   Item    the item to add in the Room
     */
    public void putInRoom(Item item)
    {
        if(item!=null) {
            items.add(item);
        }
        //else nothing
    }
    
    /**
     * Method that returns a list of all objects in the room
     * 
     * @return  all the items in the room
     */
    public String getRoomItems()
    {
        String list="";
        Iterator it=items.iterator();
        while(it.hasNext()) {
            list+=(it.next());
            if(it.hasNext()) {
                list+=", ";
            }
        }
        
        if(items.size()>0) {
            return "This room contains: "+list;
        }
        else {
            return "This room has no items";
        }        
    }
}
