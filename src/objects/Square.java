/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javafx.scene.shape.Rectangle;

/**
 *
 * @author Czakó Tamás
 */
public class Square extends Rectangle{
    private double weight;
    
    public Square(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    /**
     * @return the weight
     */
    public double getWeight() {
        return weight;
    }

    /**
     * @param weight the weight to set
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }
}
