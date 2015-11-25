/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objects;

import javafx.scene.layout.Pane;

/**
 *
 * @author Czakó Tamás
 */
public class MyPane extends Pane{
    
    public MyPane(){
        setWidth(300);
        setHeight(300);
        setStyle("-fx-border-width: 1; -fx-border-color: 000000; -fx-border-style: solid;");
    }
    
    public MyPane(double w, double h){
        setWidth(w);
        setHeight(h);
        
        setMinWidth(w);
        setMinHeight(h);
        setStyle("-fx-border-width: 1; -fx-border-color: 000000; -fx-border-style: solid;");
    }
    
}
