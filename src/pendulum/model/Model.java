/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

/**
 *
 * @author jdev
 */
public class Model
{

    /**
     * Length of the pendulum string.
     */
    @SuppressWarnings ("FieldMayBeFinal")
    private double l;

    /**
     * Acceleration of gravity.
     */
    @SuppressWarnings ("FieldMayBeFinal")
    private double g;

    /**
     * Angle of the pendulum.
     */
    private double a;

    /**
     * Mass of the pendulum.
     */
    private double m;

    // Idea start
    private DoubleProperty origin;

    // Idea end
    public Model ()
    {
	g = 9.81;
	l = 50;
	a = 0;
	m = 10;

	// Separator
	origin = new SimpleDoubleProperty ();
	// origin.bind (null); // Causes Exception
	// Modelltől lekéri a Controller
    }

    /**
     * Get the length of the pendulum string.
     *
     * @return the length of the pendulum string.
     */
    public double getStringLength ()
    {
	return l;
    }

    /**
     * Get the acceleration of gravity.
     *
     * @return the acceleration of gravity.
     */
    public double getGravityAcceleration ()
    {
	return g;
    }

    /**
     * Get the angle of the pendulum.
     *
     * @return the angle of the pendulum.
     */
    public double getAngle ()
    {
	return a;
    }

    /**
     * Get mass of the pendulum.
     *
     * @return the mass of the pendulum.
     */
    public double getMass ()
    {
	return m;
    }

    /**
     * Set the length of the pendulum string.
     * 
     * @param length the length of the pendulum string.
     */
    public void setStringLength (double length)
    {
	this.l = length;

	return;
    }

    /**
     * Set the acceleration of gravity.
     * 
     * @param acceleration the acceleration of gravity.
     */
    public void setGravityAcceleration (double acceleration)
    {
	this.g = acceleration;

	return;
    }

    /**
     * Set the angle of the pendulum.
     * 
     * @param angle the angle of the pendulum.
     */
    public void setAngle (double angle)
    {
	this.a = angle;

	return;
    }

    /**
     * Set mass of the pendulum.
     * 
     * @param mass the mass of the pendulum.
     */
    public void setMass (double mass)
    {
	this.m = mass;

	return;
    }

    /**
     * Calculates period time of pendulum using internal values.
     *
     * @return the period time of the pendulum.
     */
    public double calcPeriodTime ()
    {
	double periodTime = 2 * Math.PI * Math.sqrt (l / g);

	return periodTime;
    }

    /**
     * Helper method to convert degrees to radians
     * 
     * @param angle the angle to convert
     * @return the converted value
     */
    public static double convertDegreeToRadian (double angle)
    {
	double result = Math.toRadians (angle);

	return result;
    }

    /**
     * Helper method to convert radians to degrees
     * 
     * @param angle the angle to convert
     * @return the converted value
     */
    public static double convertRadianToDegree (double angle)
    {
	double result = Math.toDegrees (angle);

	return result;
    }
}
