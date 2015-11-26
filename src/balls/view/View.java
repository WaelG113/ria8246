/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls.view;

import balls.controller.Controller;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

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
    private Slider elaSlider;
    private Slider xSlider;
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
        AnchorPane canPane = new AnchorPane();
        
        
        ball2 = new Circle(20, 20, 20);
        ball2.setFill(Color.GREEN);
        canvas2 = new Pane(ball2);
        canvas2.setPrefSize(400, 400);
//        Canvas canvas = new Canvas(400, 400);
//        gc = canvas.getGraphicsContext2D();
//        canPane.setStyle("-fx-background-color: blue");
        Label gravityLabel = new Label("Gravity");
        setGravitySlider(new Slider(0, 15, 9));
        
        
        Label elaLabel = new Label("Elasticity");
        Label xLabel = new Label("xChange");
        elaSlider = new Slider(0,5,0.9);
        setxSlider(new Slider(0,2,0.1));
        
        
        startButton = new Button("START");
        
        setGravityValueLabel(new Label(String.valueOf(gravitySlider.getValue())));        
        gridPane.add(gravityLabel, 1, 0);
        gridPane.add(getGravitySlider(), 0, 0);
        gridPane.add(getGravityValueLabel(), 2, 0);
        gridPane.add(startButton, 3, 1);
        gridPane.add(elaSlider, 0, 1);
        gridPane.add(elaLabel,1,1);
        gridPane.add(getxSlider(),0,2);
        gridPane.add(xLabel,1,2);

//        canPane.getChildren().add(canvas);
        vBox.getChildren().addAll(canvas2, gridPane);
        //       vBox.getChildren().addAll(canPane, gridPane);

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
    
    public void startAnim(double changeX, double cY, double gravity, double elasticity) {
        //      gc.setFill(Color.BLUE);
        //      gc.fillRect(0, 0, 400, 400);
        //      gc.setFill(Color.BLACK);
        //      gc.fillOval(x, y, r, r);
        
        System.out.println(changeX);
        double changeY=cY;
        
        AnimationTimer timer = new AnimationTimer() {
            long lastUpdate;
            double changeY;
            @Override
            public void handle(long now) {
                if (lastUpdate == 0) {
                    lastUpdate = now ;
                    return ;
                }
                long elapsedNanos = now - lastUpdate;
                double elapsedSeconds = elapsedNanos / 1_000_000_000.0 ;
                lastUpdate = now ;
                ball2.setCenterX(ball2.getCenterX() + changeX);
                if (ball2.getCenterY() + changeY + ball2.getRadius() >= canvas2.getHeight()) {
                    changeY = - changeY * elasticity;
                } else {
                    changeY = changeY + gravity * elapsedSeconds ;
                }
                ball2.setCenterY(Math.min(ball2.getCenterY() + changeY, canvas2.getHeight() - ball2.getRadius()));
            }
            
        };
        timer.start();
        
//        ball2.setTranslateY(y);
//        ball2.setCenterY(ball2.getCenterY()+ball2.getTranslateY());
//        System.out.println(ball2.getCenterY());
        
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

    /**
     * @return the elaSlider
     */
    public Slider getElaSlider() {
        return elaSlider;
    }

    /**
     * @param elaSlider the elaSlider to set
     */
    public void setElaSlider(Slider elaSlider) {
        this.elaSlider = elaSlider;
    }

    /**
     * @return the xSlider
     */
    public Slider getxSlider() {
        return xSlider;
    }

    /**
     * @param xSlider the xSlider to set
     */
    public void setxSlider(Slider xSlider) {
        this.xSlider = xSlider;
    }
    
}
