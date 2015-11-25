/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pendulum.controller.Controller;
import pendulum.model.Model;
import pendulum.view.View;

/**
 *
 * @author jdev
 */
public class PendulumApplication extends Application
{

    private Controller pendulumController;
    private View pendulumView;
    private Model pendulumModel;

    private Stage mainStage;
    private Scene mainScene;

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings ("UnnecessaryReturnStatement")
    public static void main (String[] args)
    {
	Application.setUserAgentStylesheet (STYLESHEET_CASPIAN);
	PendulumApplication.launch (args);

	return;
    }

    /**
     * Initialize resources before program loads.
     *
     * @throws Exception
     */
    @Override
    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void init () throws Exception
    {
	Font.loadFont (this.getClass ().getResource ("/pendulum/util/font/Amble-Regular.ttf").toExternalForm (), 12);
	Font.loadFont (this.getClass ().getResource ("/pendulum/util/font/Amble-Bold.ttf").toExternalForm (), 12);

	return;
    }

    /**
     * Entry point for an application
     *
     * @param primaryStage An object created by external objects to be embedded
     * application into.
     */
    @Override
    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void start (Stage primaryStage)
    {
	pendulumModel = new Model ();
	pendulumView = new View ();
	pendulumController = new Controller (pendulumView, pendulumModel);

	pendulumView.setMainStage (primaryStage);
	pendulumView.setController (pendulumController);
	pendulumView.createScene ();

	mainScene = pendulumView.getScene ();
	mainStage = primaryStage;

	mainStage.setTitle ("Pendulum Application");
	mainStage.setScene (mainScene);
	mainStage.getIcons ().add (new Image (this.getClass ().getResource ("/pendulum/util/icon/PendulumIcon.jpg").toExternalForm ()));
	mainStage.setResizable (false);

	mainStage.show ();

	return;
    }

    /**
     * Invoked by the framework when the application is beig stopped.
     *
     * @throws Exception
     */
    @Override
    public void stop () throws Exception
    {
	// Log program termination - Log4J
	return;
    }
}
