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
	PendulumApplication application = new PendulumApplication ();
	application.start (primaryStage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
	launch (args);
    }

}
