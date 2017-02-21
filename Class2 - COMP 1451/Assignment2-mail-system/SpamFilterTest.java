

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class SpamFilterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class SpamFilterTest
{
    private MailItem mailItem1;
    private MailItem mailItem2;
    private MailItem mailItem3;
    private MailItem mailItem4;
    private MailItem mailItem5;
    private SpamFilter spamFilt1;

    /**
     * Default constructor for test class SpamFilterTest
     */
    public SpamFilterTest()
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
        mailItem1 = new MailItem("User1", "User2", "Subject1", "Message1");
        mailItem2 = new MailItem("User1", "User2", "sPam", "Message2 - starts with spam");
        mailItem3 = new MailItem("User1", "User2", "Contains spam", "Message3 - contains word spam");
        mailItem4 = new MailItem("User1", "User2", "Great fake watches for sale", "Message4 - contains spam keywords");
        mailItem5 = new MailItem("User1", "User2", "Great fake watch for sale", "Message5 - contains partial spam keywords");
        spamFilt1 = new SpamFilter();
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
    public void testIsSpamNoMatchesPositive()
    {
        assertEquals(false, spamFilt1.isSpam(mailItem1));
    }

    @Test
    public void testIsSpamNoMatchesContainsWordSpamPositive()
    {
        assertEquals(false, spamFilt1.isSpam(mailItem3));
    }

    @Test
    public void testIsSpamStartsWithWordSpamNegative()
    {
        assertEquals(true, spamFilt1.isSpam(mailItem2));
    }

    @Test
    public void testIsSpamContainsKeywordsPositive()
    {
        assertEquals(true, spamFilt1.isSpam(mailItem4));
    }

    @Test
    public void testIsSpamContainsPartialKeywordsNegative()
    {
        assertEquals(false, spamFilt1.isSpam(mailItem5));
    }
}





