/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import balls.controller.Controller;
import balls.model.Model;
import balls.view.View;

/**
 *
 * @author n0g3
 */
public class ballApp extends Application {
    private Controller ballController;
    private View ballView;
    private Model ballModel;

    private Stage mainStage;
    private Scene mainScene;
    
     
   @Override
    public void start(Stage primaryStage) {
        ballModel = new Model ();
	ballView = new View ();
	ballController = new Controller (ballModel);

	ballView.setMainStage (primaryStage);
	ballView.setController (ballController);
	ballView.createScene ();

	mainScene = ballView.getScene ();
	mainStage = primaryStage;

	mainStage.setTitle ("Pendulum Application");
	mainStage.setScene (mainScene);
	
	mainStage.setResizable (false);

	mainStage.show ();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
