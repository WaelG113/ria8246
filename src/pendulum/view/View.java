/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum.view;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pendulum.controller.Controller;

/**
 *
 * @author jdev
 */
public class View
{

    private Controller controller;
    private Scene mainScene;
    private Stage mainStage;
    private AnchorPane root;
    private ExperimentLayoutController experimentLayoutController;

    public View ()
    {
    }

    public void setController (Controller controller)
    {
	this.controller = controller;

	return;
    }

    public Controller getController ()
    {
	return controller;
    }

    public Stage getMainStage ()
    {
	return mainStage;
    }

    public void setMainStage (Stage mainStage)
    {
	this.mainStage = mainStage;

	return;
    }

    public ExperimentLayoutController getExperimentLayoutController ()
    {
	return experimentLayoutController;
    }

    public Scene getScene ()
    {
	return mainScene;
    }

    public void createScene ()
    {
	Scene scene = null;

	try
	{
	    FXMLLoader loader = new FXMLLoader ();
	    loader.setLocation (this.getClass ().getResource ("ExperimentLayout.fxml"));
	    root = (AnchorPane) loader.load ();

	    ExperimentLayoutController experimentController = loader.getController ();
	    experimentController.setMain (this);
	    experimentController.initialise ();
	    experimentLayoutController = experimentController;  
	}
	catch (IOException ex)
	{
	    Logger.getLogger (View.class.getName ()).log (Level.SEVERE, null, ex);
	}

	scene = new Scene (root);
	mainScene = scene;

	return;

    }
}
