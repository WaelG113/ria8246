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
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.animation.TranslateTransitionBuilder;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author n0g3
 */
public class View {

    
    private Controller controller;

    private AnchorPane root;
    private Scene scene;
    private Stage stage;
    private Slider gravitySlider;
    private Label gravityValueLabel;
    private GraphicsContext gc;
    private Button startButton;
    private Circle ball2;
    private Pane canvas2;
    public View() {
        initGui();
    }

    private void initGui() {
        VBox vBox = new VBox();
        GridPane gridPane = new GridPane();
       // AnchorPane canPane = new AnchorPane();
        canvas2 = new Pane();
       canvas2.setPrefSize(400, 400);
         ball2 = new Circle(20,20,20);
        ball2.setStyle("-fx-border: solid,-fx-color:#000000");
        canvas2.getChildren().add(ball2);
        
        //Canvas canvas = new Canvas(400, 400);
        //gc = canvas.getGraphicsContext2D();
        //canPane.setStyle("-fx-background-color: light-blue");
       
        Label gravityLabel = new Label("Gravity");
        setGravitySlider(new Slider(1, 30,9));
        
        startButton = new Button("START");
        
        setGravityValueLabel(new Label(String.valueOf(gravitySlider.getValue())));     
        gridPane.add(gravityLabel, 0, 0);
        gridPane.add(getGravitySlider(), 1, 0);
        gridPane.add(getGravityValueLabel(), 2, 0);
        gridPane.add(startButton,1,1);

       // canPane.getChildren().add(canvas);
       vBox.getChildren().addAll(canvas2, gridPane);
      //  vBox.getChildren().addAll(canPane, gridPane);

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

    /**
     * @return the gravityValueLabel
     */
    public Label getGravityValueLabel() {
        return gravityValueLabel;
    }

    /**
     * @param gravityValueLabel the gravityValueLabel to set
     */
    public void setGravityValueLabel(Label gravityValueLabel) {
        this.gravityValueLabel = gravityValueLabel;
    }

  
}
    public void drawBall(float x, float y,float r) {
//        gc.setFill(Color.LIGHTBLUE);
 //       gc.fillRect(0, 0, 400, 400);
 //       gc.setFill(Color.BLACK);
 //       gc.fillOval(x, y, r, r);
 intpol ASD = new intpol();
        TranslateTransition trans;
        trans = TranslateTransitionBuilder.create()
                .duration(new Duration(10000))
                .node(ball2)
                .toY(400)
                .autoReverse(true)
                .cycleCount(99)
                .interpolator()
                .build();
      
        trans.setOnFinished(event -> {
            ball2.setCenterY(ball2.getCenterY()+ball2.getTranslateY());
            ball2.setTranslateY(0);
            Circle tmp = (Circle)canvas2.getChildren().get(0);
        System.out.println(tmp.getCenterY());
        });
          trans.play();
        
                
    }

    /**
     * @return the button
     */
    public Button getButton() {
        return startButton;
    }

    /**
     * @param button the button to set
     */
    public void setButton(Button button) {
        this.startButton = button;
    }

}
