//import java.util.ArrayList;
/**
 * This is the SpamFilter class for the Assignement 2
 * It verifies the message to be posted if it's a spam based on a list
 * of keywords in the subject field of the message.
 * 
 * @author  Voicu Chirtes
 * @version 6/15/2014
 */
public class SpamFilter
{
    //list of start of subject line spam keywords
    private static final String[] spamWordsStart = {"spam"};
    //list of spam keywords found anywhere in the subject line
    private static final String[] spamWordsContain = {"online pharmacy", "cheap viagra", "generic viagra", "fake watches", "replica watches"};
    
    public SpamFilter()
    {
        //nothing to do here
    }
    
    /**
     * Method that verifies if an MailItem is spam.
     * This is based on the start keywords and contains keywords defined
     * at the beginning of this class.
     * 
     * @param   item    the MailItem to test if it's spam
     * @return  wether the MailItem is or not spam
     */
    public boolean isSpam(MailItem item)
    {
        boolean spam = false;
        for(int i=0;i<spamWordsStart.length;i++) {
              if((item.getSubject().toLowerCase()).startsWith((spamWordsStart[i]).toLowerCase())) { 
                spam=true;
            }
        }
        if(spam) {
            return spam;
        }
        else {
            for(int i=0;i<spamWordsContain.length;i++) {
                if((item.getSubject().toLowerCase()).contains((spamWordsContain[i]).toLowerCase()))
                    spam=true;
            }
        }
        return spam;
    }
}