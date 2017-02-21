/**
 * A class to model a simple mail item. The item has sender and recipient
 * addresses and a message string.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailItem
{
    // The sender of the item.
    private String from;
    // The intended recipient.
    private String to;
    // The text of the message.
    private String message;
    // The subject of the message - per Assignemnt 2
    private String subject;

    /**
     * Create a mail item from sender to the given recipient,
     * containing the given message.
     * @param from The sender of this item.
     * @param to The intended recipient of this item.
     * @param subject The subject of the message to be sent.
     * @param message The text of the message to be sent.
     */
    public MailItem(String from, String to, String subject, String message)
    {
        this.from = formatString(from);
        this.to = formatString(to);
        this.subject = subject;
        this.message = message;
    }

    /**
     * Method used to format all senders and recepients into appropriate format
     * The name of the senders and recepients should be upper case on the 
     * first letter and the rest of the letters lower case.
     * 
     * @param s The string to format
     * @return The formatted string
     */
    public static String formatString(String s)
    {
        if (s!=null)
            return ((s.substring(0,1)).toUpperCase())+(s.substring(1)).toLowerCase();
        return null;
    }
    
    /**
     * @return The sender of this message.
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * @return The intended recipient of this message.
     */
    public String getTo()
    {
        return to;
    }

    /**
     * @return The subject of the message.
     */
    public String getSubject()
    {
        return subject;
    }
      
    /**
     * @return The text of the message.
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Print this mail message to the text terminal.
     */
    public void print()
    {
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        System.out.println("Message: " + message);
    }
}
