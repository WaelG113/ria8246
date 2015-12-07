/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls.controller;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Circle;

/**
 *
 * @author n0g3
 */
public class ballAnimation extends AnimationTimer{

    long lastUpdate;
    double changeY;
    double changeX;
    double elasticity;
    double gravity;
    Circle ball2;
    double height;
    double width;
    
    public ballAnimation(double changeY, double changeX, double elasticity,
            double gravity, Circle ball2, double height, double width )
    {
        this.ball2=ball2;
        this.changeX=changeX;
        this.changeY=changeY;
        this.elasticity=elasticity;
        this.gravity=gravity;
        this.height=height;
        this.width=width;
    };        
    
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
                
                if (ball2.getCenterY() + changeY + ball2.getRadius() >= height) {
                    changeY = - changeY * elasticity;
                } else {
                    changeY = changeY + gravity * elapsedSeconds ;
                }
               
               
               
                
                ball2.setCenterY(Math.min(ball2.getCenterY() + changeY, height - ball2.getRadius()));
    }
    
}
