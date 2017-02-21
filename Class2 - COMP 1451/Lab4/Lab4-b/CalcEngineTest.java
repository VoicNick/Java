

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CalcEngineTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CalcEngineTest
{
    private CalcEngine calcEngi1;

    /**
     * Default constructor for test class CalcEngineTest
     */
    public CalcEngineTest()
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
        calcEngi1 = new CalcEngine();
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
    
    /**
     * This is to test the plus() method with single digits
     * clears the calculator and then calculates 3+4 evaluates if result is corect, 7
     */
    @Test
    public void testPlusSingleDigit()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(3);
        calcEngi1.plus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        assertEquals(7, calcEngi1.getDisplayValue());
    }

    /**
     * This is to test two consecutive additions with clear() method call in between.
     * It evaluates the second addition with data 3+4 asserts result equals to 7
     * 
     */
    @Test
    public void testTwoConsecutiveAdditions()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(3);
        calcEngi1.plus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        calcEngi1.clear();
        calcEngi1.numberPressed(3);
        calcEngi1.plus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        assertEquals(7, calcEngi1.getDisplayValue());
    }

    /**
     * This method evaluates the usage of the plus() method twice 
     * It calculates 3+4+5 and asserts that to value 15
     */
    @Test
    public void testAddingThreeNumbers()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(3);
        calcEngi1.plus();
        calcEngi1.numberPressed(4);
        calcEngi1.plus();
        calcEngi1.numberPressed(5);
        calcEngi1.equals();
        assertEquals(12, calcEngi1.getDisplayValue());
    }

    /**
     * This tests adding two digit numbers.
     * 12 + 25 and it asserts the result with 37
     */
    @Test
    public void testAddingTwoDigitNumbers()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(1);
        calcEngi1.numberPressed(2);
        calcEngi1.plus();
        calcEngi1.numberPressed(2);
        calcEngi1.numberPressed(5);
        calcEngi1.equals();
        assertEquals(37, calcEngi1.getDisplayValue());
    }

    /**
     * This method tests single digit substractions.
     * It calculates 9-4 and assert the result to 5
     */
    @Test
    public void testMinusSingleDigit()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(9);
        calcEngi1.minus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        assertEquals(5, calcEngi1.getDisplayValue());
    }

    /**
     * This method calculates two consecutive substractions. It uses the clear
     * method in between to reset teh state of the calculator.
     * On second operation 9-5 asserts that equals to 4
     */
    @Test
    public void testTwoConscutiveSubstractions()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(9);
        calcEngi1.minus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        calcEngi1.clear();
        calcEngi1.numberPressed(9);
        calcEngi1.minus();
        calcEngi1.numberPressed(5);
        calcEngi1.equals();
        assertEquals(5, calcEngi1.getDisplayValue());
    }

    /**
     * This method tests substracting three numbers
     * 9-2-3 asserts teh result equals to 4
     */
    @Test
    public void testSubstractingThreeNumbers()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(9);
        calcEngi1.minus();
        calcEngi1.numberPressed(2);
        calcEngi1.minus();
        calcEngi1.numberPressed(3);
        calcEngi1.equals();
        assertEquals(4, calcEngi1.getDisplayValue());
    }

    /**
     * This method tests substracting two digit numbers.
     * It tests 25-13 and asserts it equals 12
     */
    @Test
    public void testSubstractingTwoDigitNumbers()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(2);
        calcEngi1.numberPressed(5);
        calcEngi1.minus();
        calcEngi1.numberPressed(1);
        calcEngi1.numberPressed(3);
        calcEngi1.equals();
        assertEquals(12, calcEngi1.getDisplayValue());
    }

    /**
     * This method tests an addition operation after a substraction using
     * the clear() method in between.
     * It calculates 9-4 = then calls clear() method and then
     * calculates 3+4 and asserts if result equals 7
     */
    @Test
    public void testAdditionAfterSubstraction()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(9);
        calcEngi1.minus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        calcEngi1.clear();
        calcEngi1.numberPressed(3);
        calcEngi1.plus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        assertEquals(7, calcEngi1.getDisplayValue());
    }

     /**
     * This method tests a substraction operation after an addition using
     * the clear() method in between.
     * It calculates 3+4 = then calls clear() method and then
     * calculates 9-4 and asserts if result equals 5
     */
    @Test
    public void testSubstractionAfterAddition()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(3);
        calcEngi1.plus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        calcEngi1.clear();
        calcEngi1.numberPressed(9);
        calcEngi1.minus();
        calcEngi1.numberPressed(4);
        calcEngi1.equals();
        assertEquals(5, calcEngi1.getDisplayValue());
    }

    /**
     * This tests the plus() method with adding two negative numbers.
     * It calculates (-3) + (-4) = and asserts the result equals to -7
     */
    @Test
    public void testAddingNegativeNumbers()
    {
        calcEngi1.clear();
        calcEngi1.numberPressed(-3);
        calcEngi1.plus();
        calcEngi1.numberPressed(-4);
        calcEngi1.equals();
        assertEquals(-7, calcEngi1.getDisplayValue());
    }

    /**
     * This tests the minus() method with substracting two negative numbers.
     * It calculates (-9) - (-4) = and asserts the result equals to -5
     */ 
    @Test
    public void testSubstractingNegativeNumbers()
    {
        calcEngi1.numberPressed(-9);
        calcEngi1.minus();
        calcEngi1.numberPressed(-4);
        calcEngi1.equals();
        assertEquals(-5, calcEngi1.getDisplayValue());
    }
}












