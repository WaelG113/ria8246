/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls.controller;

import balls.model.Model;
import balls.view.View;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author n0g3
 */
public class Controller {

    //ball states
    private final int START = 0;
    private final int FREE_FALL = 1;
    private final int ON_GROUND = 2;
    private final int REBOUND = 3;

    private Model model;
    private View view;

    private Slider gravitySlider;
    private Slider elaSlider;
    private Slider xSlider;
    private Button startButton;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
        initItems();
        initEventHandlers();
    }

    private void initEventHandlers() {
        //gravitySlider
        gravitySlider.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                setModelGravity();
                view.getGravityValueLabel().setText(String.valueOf((int) gravitySlider.getValue()));
            }
        });
        //startButton
        startButton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                view.startAnim((double)model.getxChange(),0,(double)model.getGravity(),(double)model.getElasticity());
                
//                Thread t = new Thread() {
//                    @Override
//                    public void run() {
//                        //view.startAnim(0,0.2,2,1);
//                        while (true) {
//                            view.drawBall(model.getX(), model.getY() + model.getVelocity(), model.getRadius());
//                            switch (model.getState()) {
//                                case START:
//                                    model.setState(FREE_FALL);
//                                    break;
//                            }
//                            System.out.println("vel: " + model.getVelocity());
//                            model.setTime(model.getTime() + 0.1f);
//                            computeVelocity();
//                           
//                        }
//                         
//
//                    }
//                };
//                t.setDaemon(true);
//                t.start();
                
            }

        });

    }

    private void initItems() {
        //sliders
        gravitySlider = view.getGravitySlider();
        model.setGravity((float) gravitySlider.getValue());
        elaSlider = view.getElaSlider();
        model.setElasticity((float)elaSlider.getValue());
        xSlider = view.getxSlider();
        model.setxChange((float)xSlider.getValue());

        //button
        startButton = view.getButton();
    }

    public Controller() {
    }

    public void setModelGravity() {
        model.setGravity((float) gravitySlider.getValue());
    }

    public float computeAcceleration() {
        return (model.getDistance() / (getModel().getTime() * getModel().getTime()));
    }

    public float computeDistance() {
        return ((float) (model.getGravity() / 2) * (getModel().getTime() * getModel().getTime()));
    }

    public float computeDeceleration(float acceleration) {
        return ((float) acceleration / 2);
    }

    public void computeVelocity() {
        float tempAcc = 0, tempVel = 0;
        switch (model.getState()) {
            case START:
                model.setVelocity(0);
                model.setAcceleration(0);
                break;
            case FREE_FALL:
                model.setAcceleration(model.getGravity());
                model.setVelocity(model.getVelocity() + model.getAcceleration() * model.getTime());
                break;
            case ON_GROUND:
                tempAcc = model.getAcceleration();
                tempVel = model.getVelocity();
                model.setAcceleration(0);
                model.setVelocity(0);
                break;
            case REBOUND:
                model.setAcceleration(computeDeceleration(tempAcc));
                model.setVelocity(tempVel * model.getAcceleration() - model.getGravity());
        }
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
     * @return the view
     */
    public View getView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(View view) {
        this.view = view;
    }

}
