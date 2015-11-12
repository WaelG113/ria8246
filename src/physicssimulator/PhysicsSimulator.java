/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicssimulator;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import objects.Ball;
import objects.Square;

/**
 *
 * @author jdev
 */
public class PhysicsSimulator extends Application
{
    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private Pane tools;
    
    @Override
    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void start (Stage stage) throws Exception
    {
	initRootLayout ();
	// Here will be initialized other components... (Later...)
	
	this.stage = stage;
	this.stage.setScene (this.scene);
	this.stage.setTitle ("Physics Simulator");
	this.stage.show ();
        setTools();
        
	return;
    }
    
    @SuppressWarnings ("UnnecessaryReturnStatement")
    private void initRootLayout ()
    {
	try
	{
	    FXMLLoader loader = new FXMLLoader ();
	    loader.setLocation (this.getClass ().getResource ("RootLayout.fxml"));
	    root = (BorderPane) loader.load ();
	    RootLayoutController controller = loader.getController ();
	    controller.setMainApplication (this);
	    
	    Scene scene = new Scene (root);
	    this.scene = scene;
	    
	    return;
	}
	catch (IOException ex)
	{
	    Logger.getLogger (PhysicsSimulator.class.getName()).log (Level.SEVERE, null, ex);
	}
    }

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings ("UnnecessaryReturnStatement")
    public static void main (String[] args)
    {
	launch (args);
	
	return;
    }

    private void setTools() {
        tools = (Pane) root.lookup("#tools");
        Ball ball = new Ball(20, 20, 10);
        Square square = new Square(40, 20, 10, 10);
	tools.getChildren().addAll(ball, square);
        
    }
}
