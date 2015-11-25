/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls;

import balls.controller.Controller;
import balls.model.Model;
import balls.view.View;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author n0g3
 */
public class ballApp extends Application {
    View view = new View();
    Model model = new Model();
    Controller controller = new Controller(model,view);
    @Override
    public void start(Stage primaryStage) {
        view.setStage(primaryStage);
        Scene scene = view.getScene();
        Stage mainStage;
        
	mainStage = primaryStage;

	mainStage.setTitle ("Pendulum Application");
	mainStage.setScene (scene);
	
	mainStage.setResizable (false);
        mainStage.show();
        
        
        
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
