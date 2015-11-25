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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
private Slider gravitySlider;

public View(){
    initGui();
}

private void initGui(){
    VBox vBox = new VBox();
    GridPane gridPane = new GridPane();
    AnchorPane canPane = new AnchorPane();
    Canvas canvas = new Canvas(400,400);
    canPane.setStyle("-fx-background-color: #96bbfc");
    
    Label gravityLabel = new Label("Gravity");
        setGravitySlider(new Slider(0, 15, 9.3));
    gridPane.add(gravityLabel, 0, 0);
    gridPane.add(getGravitySlider(), 1, 0);
    
    
    canPane.getChildren().add(canvas);
    
    Label lab = new Label("Yolo");
    
    
    
    vBox.getChildren().addAll(canPane,gridPane);
    
    
    
    
         
        
        scene = new Scene(vBox);
          
    
    
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
     * @return the scene
     */
    public Scene getScene() {
        return scene;
    }

    /**
     * @param scene the scene to set
     */
    public void setScene(Scene scene) {
        this.scene = scene;
    }

    /**
     * @return the stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * @param stage the stage to set
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * @return the gravitySlider
     */
    public Slider getGravitySlider() {
        return gravitySlider;
    }

    /**
     * @param gravitySlider the gravitySlider to set
     */
    public void setGravitySlider(Slider gravitySlider) {
        this.gravitySlider = gravitySlider;
    }



}
