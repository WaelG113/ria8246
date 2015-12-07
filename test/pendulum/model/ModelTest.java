/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum.model;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jdev
 */
public class ModelTest
{

    public ModelTest ()
    {
    }

    @BeforeClass
    public static void setUpClass ()
    {
    }

    @AfterClass
    public static void tearDownClass ()
    {
    }

    @Before
    public void setUp ()
    {
    }

    @After
    public void tearDown ()
    {
    }

    /**
     * Test of calcPeriodTime method, of class Model.
     */
    @Test
    public void testCalcPeriodTime ()
    {
	System.out.println ("calcPeriodTime");
	Model instance = new Model ();
	instance.setStringLength (42);
	instance.setGravityAcceleration (9.81);
	double expResult = 13.00079;
	double result = instance.calcPeriodTime ();
	assertEquals (expResult, result, 0.001);
    }

    /**
     * Test of convertDegreeToRadian method, of class Model.
     */
    @Test
    public void testConvertDegreeToRadian ()
    {
	System.out.println ("convertDegreeToRadian");
	double angle = 90;
	double expResult = 90 * (Math.PI / 180);;
	double result = Model.convertDegreeToRadian (angle);
	assertEquals (expResult, result, 0.01);
    }

    /**
     * Test of convertRadianToDegree method, of class Model.
     */
    @Test
    public void testConvertRadianToDegree ()
    {
	System.out.println ("convertRadianToDegree");
	double angle = 90;
	double expResult = angle * (180 / Math.PI);
	double result = Model.convertRadianToDegree (angle);
	assertEquals (expResult, result, 0.01);
    }

    @Test
    public void testConversionToSin ()
    {
	System.out.println ("testConversionToSin");
	double angle = Math.toRadians (0);
	double expResult = Math.sin (angle);
	double result = Math.sin (Model.convertDegreeToRadian (0));
	assertEquals (expResult, result, 0.1);
    }

    @Test
    public void testConversionToCos ()
    {
	System.out.println ("testConversionToCos");
	double angle = Math.toRadians (0);
	double expResult = Math.cos (angle);
	double result = Math.cos (Model.convertDegreeToRadian (0));
	assertEquals (expResult, result, 0.1);
    }
}
