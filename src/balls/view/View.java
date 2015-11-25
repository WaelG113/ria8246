/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls.view;

import balls.controller.Controller;
import balls.model.Model;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import balls.view.LayoutController;
import javafx.stage.Stage;

/**
 *
 * @author n0g3
 */


public class View {

private Model model;
private Controller controller;
private LayoutController layoutController;
private AnchorPane root;
private Scene scene;
private Stage stage;

public void createScene ()
    {
	Scene scene = null;

	try
	{
	    FXMLLoader loader = new FXMLLoader ();
	    loader.setLocation (this.getClass ().getResource ("layout.fxml"));
	    root = (AnchorPane) loader.load ();

	    LayoutController experimentController = loader.getController ();
	    experimentController.setMain (this);
	    experimentController.initialise ();
	    setLayoutController(experimentController);  
	}
	catch (IOException ex)
	{
	    Logger.getLogger (pendulum.view.View.class.getName ()).log (Level.SEVERE, null, ex);
	}

	scene = new Scene (root);
	setMainScene(scene);

	return;

    }

    /**
     * @return the model
     */
    public Model getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(Model model) {
        this.model = model;
    }

    /**
     * @return the controller
     */
    public Controller getController() {
        return controller;
    }

    /**
     * @param controller the controller to set
     */
    public void setController(Controller controller) {
        this.controller = controller;
    }

    /**
     * @return the layoutController
     */
    public LayoutController getLayoutController() {
        return layoutController;
    }

    /**
     * @param layoutController the layoutController to set
     */
    public void setLayoutController(LayoutController layoutController) {
        this.layoutController = layoutController;
    }

    /**
     * @return the mainScene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @param mainScene the mainScene to set
     */
    public void setScene(Scene mainScene) {
        this.scene = mainScene;
    }

    /**
     * @return the mainStage
     */
    public Stage getMainStage() {
        return stage;
    }

    /**
     * @param mainStage the mainStage to set
     */
    public void setMainStage(Stage mainStage) {
        this.stage = mainStage;
    }

    private void setMainScene(Scene scene) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
