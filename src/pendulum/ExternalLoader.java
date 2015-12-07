/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author jdev
 */
public class ExternalLoader extends Application
{

    @Override
    public void start (Stage primaryStage)
    {
	// Option 1 (part)
	// Does not perform init ()
	// PendulumApplication application = new PendulumApplication ();
	// application.start (primaryStage);

	// Option 2 (part)
	// Causes Exception - Calls launch () more than once
	// PendulumApplication.main (args);
	// Option 3 (part)
	/*
	try
	{
	    runAnotherApp (PendulumApplication.class);
	}
	catch (Exception e)
	{
	    e.printStackTrace ();
	}
	*/
	
	
	
	return;
    }

    public void runAnotherApp (Class<? extends Application> anotherApplication) throws Exception
    {
	Application newApp = anotherApplication.newInstance ();
	Stage newStage = new Stage ();
	newApp.start (newStage);

	return;
    }
    
    public void launchAnotherApp (String[] args)
    {
	PendulumApplication.launch (PendulumApplication.class, args);
	
	return;
    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
	// Option 1
	// Application.launch (args);

	// Option 2
	// PendulumApplication.main (args);
	// Option 3
	// Application.launch (args);
	// Option 4
	// Does not perform init ()
	// PendulumApplication.launch (PendulumApplication.class, args);
	
	// Option 5
	// ExternalLoader externalLoader = new ExternalLoader ();
	// externalLoader.launchAnotherApp (args);
	
	

	return;
    }

}
