/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum.view;

import javafx.application.Platform;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;

/**
 *
 * @author jdev
 */
public class NodeDrawThread extends Thread
{

    private double length;
    private double acceleration;
    private double angle;
    private double mass;

    private boolean isOperating;

    private Circle pendulumBall;
    private Line pendulumString;

    public NodeDrawThread (double length, double acceleration, double angle, double mass, Circle pendulumBall, Line pendulumString)
    {
	this.length = length;
	this.acceleration = acceleration;
	this.angle = angle;
	this.mass = mass;
	this.pendulumBall = pendulumBall;
	this.pendulumString = pendulumString;

	this.isOperating = true;
	
	drawOnNode ();
    }

    public double getLength ()
    {
	return length;
    }

    public double getAcceleration ()
    {
	return acceleration;
    }

    public double getAngle ()
    {
	return angle;
    }

    public double getMass ()
    {
	return mass;
    }

    public Circle getPendulumBall ()
    {
	return pendulumBall;
    }

    public Line getPendulumString ()
    {
	return pendulumString;
    }

    public void setLength (double length)
    {
	this.length = length;
    }

    public void setAcceleration (double acceleration)
    {
	this.acceleration = acceleration;
    }

    public void setAngle (double angle)
    {
	this.angle = angle;
    }

    public void setMass (double mass)
    {
	this.mass = mass;
    }

    public void setPendulumBall (Circle pendulumBall)
    {
	this.pendulumBall = pendulumBall;
    }

    public void setPendulumString (Line pendulumString)
    {
	this.pendulumString = pendulumString;
    }

    public void drawOnNode ()
    {
	int ballOriginalX = (int) pendulumBall.getLayoutX ();
	int ballOriginalY = (int) pendulumBall.getLayoutY ();

	int anchorX = (int) pendulumString.getStartX ();
	int anchorY = (int) pendulumString.getStartY ();

	int ballX = anchorX + (int) (Math.sin (angle) * length);
	int ballY = anchorY + (int) (Math.cos (angle) * length);
	
	pendulumString.setEndX (ballX);
	pendulumString.setEndY (ballY);
	pendulumBall.translateXProperty ().set (ballX - ballOriginalX);
	pendulumBall.translateYProperty ().set (ballY - ballOriginalY);

	return;
    }

    @Override
    public void run ()
    {
	double angleAccel;
	double angleVelocity = 0;
	double dt = 0.1;

	while (isOperating)
	{
	    angleAccel = -acceleration / length * Math.sin (angle);
	    angleVelocity += angleAccel * dt;
	    angle += angleVelocity * dt;

	    Platform.runLater (new Runnable ()
	    {

		@Override
		public void run ()
		{
		    drawOnNode ();
		}
	    });

	    try
	    {
		Thread.sleep (15);
	    }
	    catch (InterruptedException ex)
	    {
		isOperating = false;
	    }
	}

	return;
    }

}
