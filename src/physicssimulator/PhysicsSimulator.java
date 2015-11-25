/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicssimulator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;
import objects.Ball;
import objects.MyPane;
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
    int i = 0;
    private RootLayoutController rootLayoutController;

    @Override
    @SuppressWarnings("UnnecessaryReturnStatement")
    public void start(Stage stage) throws Exception {
        initRootLayout();
        // Here will be initialized other components... (Later...)

//        stage.setResizable(false);
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
//            FXMLLoader loader = new FXMLLoader();
//            loader.setLocation(this.getClass().getResource("RootLayout.fxml"));
//            root = (BorderPane) loader.load();
//            RootLayoutController controller = loader.getController();
//            controller.setMainApplication(this);

        setPanes();
        root = new BorderPane(main, menu, properties, results, tools);
        Scene scene = new Scene(root, 1000, 1000);
        this.scene = scene;

        return;
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
        tools = new MyPane(50, 600);
        main = new MyPane();
        properties = new MyPane(50, 600);
        Button btn = new Button("GOMB");
        properties.getChildren().add(btn);
        results = new MyPane(600, 50);
        menu = new MyPane(600, 50);
//        root.setLeft(tools);
//        root.setCenter(main);
//        root.setRight(properties);
//        root.setBottom(results);
//        root.setTop(menu);
    }

    private void setListeners() {
        //toolsban a golyora kattintas
        ball.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("mouse click detected! " + mouseEvent.getSource());
                main.getChildren().add(new Ball(0, 0, 20));
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
    }

    public void setDrag(Node s) {
        s.addEventFilter(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                Ball lBall = (Ball) s;

//                if (lBall.getCenterX() >= main.getLayoutBounds().getWidth()) {
                    s.relocate(event.getSceneX() - 50, event.getSceneY() - 50);
//                }
                if (main.contains(lBall.getLayoutX(), lBall.getLayoutY())) {
                    System.out.println("asd" + i++);
                }

            }
        }
        );
    }
}
