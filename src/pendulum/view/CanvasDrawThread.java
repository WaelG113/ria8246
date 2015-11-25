/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum.view;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * This class originates from site
 * <a href="http://rosettacode.org/wiki/Animate_a_pendulum#Java">Rosetta
 * Code</a>
 *
 * @author jdev
 */
public class CanvasDrawThread extends Thread
{

    private double length;
    private double acceleration;
    private double angle;
    private double mass;

    private Canvas pendulumDrawCanvas;
    private boolean isOperating;

    public CanvasDrawThread (Canvas pendulumDrawCanvas, double length, double acceleration, double angle, double mass)
    {
	this.pendulumDrawCanvas = pendulumDrawCanvas;
	this.isOperating = true;
	this.length = length;
	this.acceleration = acceleration;
	this.angle = angle;

	drawOnCanvas ();
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

    private void drawOnCanvas ()
    {
	GraphicsContext gc = pendulumDrawCanvas.getGraphicsContext2D ();
	int width = (int) pendulumDrawCanvas.getWidth ();
	int height = (int) pendulumDrawCanvas.getHeight ();

	gc.setFill (Color.WHITE);
	gc.fillRect (0, 0, width, height);
	gc.setFill (Color.BLACK);

	int anchorX = width / 2;
	int anchorY = height / 4;

	int ballX = anchorX + (int) (Math.sin (angle) * length);
	int ballY = anchorY + (int) (Math.cos (angle) * length);

	gc.strokeLine (anchorX, anchorY, ballX, ballY); // "String"
	gc.fillOval (anchorX - 3, anchorY - 4, 7, 7); // "Anchor"
	gc.fillOval (ballX - 7, ballY - 7, 14, 14); // "Ball"

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
	    angleAccel = - acceleration / length * Math.sin (angle);
	    angleVelocity += angleAccel * dt;
	    angle += angleVelocity * dt;
	    Platform.runLater (new Runnable ()
	    {

		@Override
		public void run ()
		{
		    drawOnCanvas ();
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
