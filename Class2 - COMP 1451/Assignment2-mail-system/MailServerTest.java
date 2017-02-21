

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class MailServerTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class MailServerTest
{
    private MailServer mailServ1;
    private MailClient mailClie1;
    private MailClient mailClie2;
    private MailClient mailClie3;

    /**
     * Default constructor for test class MailServerTest
     */
    public MailServerTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        mailServ1 = new MailServer();
        mailClie1 = new MailClient(mailServ1, "User1");
        mailClie2 = new MailClient(mailServ1, "User2");
        mailClie3 = new MailClient(mailServ1, "User3");
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testHowManyMailItemsNegative()
    {
        assertEquals(0, mailServ1.howManyMailItems("New User"));
    }

    @Test
    public void testHowManyMailItemsPositive()
    {
        mailClie1.sendMailItem("User2", "Subject1", "Message 1");
        mailClie1.sendMailItem("User2", "SpaM subject", "Message");
        mailClie1.sendMailItem("User2", "Subject2", "Message2");
        mailClie1.sendMailItem("User2", "Great fake watches deal", "Message");
        assertEquals(2, mailServ1.howManyMailItems("User2"));
    }

    @Test
    public void testGetNextMailItemPositive()
    {
        MailItem mailItem1 = new MailItem("User1", "User2", "Subject1", "Message1");
        MailItem mailItem2 = new MailItem("User1", "User3", "Subject2", "Message2");
        MailItem mailItem3 = new MailItem("User1", "User2", "Subject3", "Message3");
        mailClie1.sendMailItem("User2", "Subject1", "Message1");
        mailClie1.sendMailItem("User3", "Subject2", "Message2");
        mailClie1.sendMailItem("User2", "Subject3", "Message3");
        assertNotNull(mailServ1.getNextMailItem("User3"));
        assertNull(mailServ1.getNextMailItem("User3"));
        assertNotNull(mailServ1.getNextMailItem("User2"));
        assertNotNull(mailServ1.getNextMailItem("User2"));
        assertNull(mailServ1.getNextMailItem("User2"));
    }

    @Test
    public void testGetNextMailItemNegative()
    {
        assertNull(mailServ1.getNextMailItem("User2"));
        assertNull(mailServ1.getNextMailItem("User3"));
    }

    @Test
    public void testPostPositive()
    {
        MailItem mailItem1 = new MailItem("User1", "User2", "Subject1", "Message1");
        mailServ1.post(mailItem1);
        assertEquals(1, mailServ1.howManyMailItems("User2"));
    }

    @Test
    public void testPostNegative()
    {
        MailItem mailItem1 = new MailItem("User1", "User2", "spaM email", "Message1");
        mailServ1.post(mailItem1);
        assertEquals(0, mailServ1.howManyMailItems("User2"));
    }
}






