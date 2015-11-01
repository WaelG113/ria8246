/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package physicssimulator;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 *
 * @author jdev
 */
public class RootLayoutController implements Initializable
{
   private PhysicsSimulator mainApplication;
   
   public void setMainApplication (PhysicsSimulator mainApplication)
   {
       this.mainApplication = mainApplication;
       
       return;
   }
    
    @Override
    public void initialize (URL url, ResourceBundle rb)
    {
    }
}
