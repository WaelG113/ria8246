/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balls.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author n0g3
 */
public class LayoutController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    private View view;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
     public void setMain (View view)
    {
	this.view = view;

	return;
    }
     public void initialise ()
    {
	//initSlidersFromModell ();
	//initializeCanvas ();

	return;
    }
    
}
