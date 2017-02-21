import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.Set;

/**
 * A simple model of a mail server. The server is able to receive
 * mail items for storage, and deliver them to clients on demand.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailServer
{
    // Storage for the arbitrary number of mail items to be stored
    // on the server.
    private HashMap<String,ArrayList<MailItem>> items;

    /**
     * Construct a mail server.
     */
    public MailServer()
    {
        items = new HashMap<String,ArrayList<MailItem>>();
    }

    /**
     * Return how many mail items are waiting for a user.
     * @param who The user to check for.
     * @return How many items are waiting.
     */
    public int howManyMailItems(String who)
    {
        int count = 0;
        ArrayList<MailItem> emails = items.get(MailItem.formatString(who));
        if(emails!=null) {
            for(MailItem item : emails) {
                count++;            
            }
        }
        return count;
    }

    /**
     * Return the next mail item for a user or null if there
     * are none.
     * @param who The user requesting their next item.
     * @return The user's next item.
     */
    public MailItem getNextMailItem(String who)
    {
        who=MailItem.formatString(who);
        ArrayList<MailItem> emails = items.remove(who);
        if(emails==null) {
            return null;
        }
        else {
            MailItem item = emails.remove(0);
            if(emails.size()!=0)
                items.put(who,emails);
            return item;
        }
    }

    /**
     * Add the given mail item to the message list if it's not spam or null.
     * @param item The mail item to be stored on the server.
     */
    public void post(MailItem item)
    {
        SpamFilter s = new SpamFilter();
        if(item!=null) {
            if(!s.isSpam(item)) {
                ArrayList<MailItem> emails = items.remove(item.getTo());
                if(emails==null) {
                    emails = new ArrayList<MailItem>();
                    emails.add(item);
                    items.put(item.getTo(),emails);
                }
                else {
                    emails.add(item);
                    items.put(item.getTo(),emails);
                }
            }
            else {
                //nothing, do not add a spam message. May be developped in future.
            }
        }
    }
    
    /**
     * Prints all messages in the server, by recepient with recepients sorted 
     * alfabetically
     */
    public void printMessagesSortedByRecipient()
    {
        TreeSet<String> usersSorted = new TreeSet<String> (items.keySet());
        for(String user:usersSorted) {
            //System.out.println(user);
            System.out.println("Mailbox for: "+user+" contains these mail messages:");
            if(howManyMailItems(user)==0) {
                System.out.println("No mail.");
            }
            else {
                for(MailItem item:items.get(user)) {
                    item.print();
                    System.out.println("-----------------------------------------");
                }
            }
        }    
    }
}
