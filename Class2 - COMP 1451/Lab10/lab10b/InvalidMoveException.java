/**
 * This is the InvalidMoveException as required on the lab 10b homework assignment
 * 
 * @author  Voicu Chirtes
 * @version 7/5/2014
 */
public class InvalidMoveException extends Exception
{
    private String message;
    
    public InvalidMoveException(String message)
    {
        setMessage(message);
    }
    
    public void setMessage(String message)
    {
        if(message!=null) {
            this.message=message;
        }
        else{
            this.message="";
        }
    }
    
    public String getMessage()
    {
        return message;
    }    
}