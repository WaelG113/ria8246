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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.Node;
import objects.Ball;
import objects.Square;

/**
 *
 * @author jdev
 */
public class PhysicsSimulator extends Application {

    private Stage stage;
    private Scene scene;
    private BorderPane root;
    private Pane tools, main, properties, results, menu;
    private Ball ball;
    private Square square;
    private RootLayoutController rootLayoutController;

    @Override
    @SuppressWarnings("UnnecessaryReturnStatement")
    public void start(Stage stage) throws Exception {
        initRootLayout();
        // Here will be initialized other components... (Later...)

        stage.setResizable(false);
        setPanes();
        setTools();
        setListeners();
        this.stage = stage;
        this.stage.setScene(this.scene);
        this.stage.setTitle("Physics Simulator");
        this.stage.show();

        return;
    }

    @SuppressWarnings("UnnecessaryReturnStatement")
    private void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(this.getClass().getResource("RootLayout.fxml"));
            root = (BorderPane) loader.load();
            RootLayoutController controller = loader.getController();
            controller.setMainApplication(this);
	    rootLayoutController = controller;

            Scene scene = new Scene(root);
            this.scene = scene;

            return;
        } catch (IOException ex) {
            Logger.getLogger(PhysicsSimulator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    @SuppressWarnings("UnnecessaryReturnStatement")
    public static void main(String[] args) {
        launch(args);

        return;
    }

    private void setTools() {
        ball = new Ball(20, 20, 10);
        square = new Square(40, 20, 10, 10);
        tools.getChildren().addAll(ball, square);
    }

    private void setPanes() {
        tools = (Pane) root.lookup("#tools");
        main = (Pane) root.lookup("#main");
        properties = (Pane) root.lookup("#properties");
        results = (Pane) root.lookup("#results");
        menu = (Pane) root.lookup("#menu");
        root.setLeft(tools);
        root.setCenter(main);
        root.setRight(properties);
        root.setBottom(results);
        root.setTop(menu);
    }

    private void setListeners() {
        //toolsban a golyora kattintas
        ball.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse click detected! " + mouseEvent.getSource());
                main.getChildren().add(new Ball(100, 100, 20));
                setDrag((Shape) main.getChildren().get(main.getChildren().size() - 1));
            }
        });
        //toolsban a negyzetre kattintas
        square.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse click detected! " + mouseEvent.getSource());
                main.getChildren().add(new Square(0, 0, 20, 20));
                setDrag((Node) main.getChildren().get(main.getChildren().size() - 1));

            }
        });

        //dragListener a main-en levo shape-kre
    }

    public void setDrag(Node s) {
        s.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (s.getClass().equals(ball.getClass())) {
                    Ball lBall = (Ball) s;
//                    if(lBall.getLayoutX() > (main.getLayoutX() - lBall.getRadius())){
                    System.out.println(lBall.getParent().getId() + " " + main.getId());
                    try {
                        main.addEventFilter(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {

                            @Override
                            public void handle(MouseEvent event) {
                                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                            }
                        });
                        s.setTranslateX(event.getSceneX() - 150);
                        s.setTranslateY(event.getSceneY() - 150);
                    } catch (UnsupportedOperationException e) {
                        System.out.println("elkapva");
                    }
//                    }

                }
            }
        });
    }
}
