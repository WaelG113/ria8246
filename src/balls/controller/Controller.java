/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls.controller;

import balls.model.Model;
import balls.view.View;

/**
 *
 * @author n0g3
 */
public class Controller {

    private Model model;

    public Controller(Model model) {
        this.model = model;
    }
    
    

    public float computeAcceleration() {
        return (model.getDistance() / (getModel().getTime() * getModel().getTime()));
    }
    
    public float computeFreeFallVelocity(){
        return ((model.getGravity() / 2) * (getModel().getTime() * getModel().getTime()));
    }
    
    private float computeDeceleration(){
        return (model.getAcceleration() / 2);
    }
    
    private float computeVelocity(){
        return (model.getAcceleration() * model.getTime());
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
    
}
