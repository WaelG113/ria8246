/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.FileChooser;
import pendulum.model.Model;

/**
 * FXML Controller class
 *
 * @author jdev
 */
public class ExperimentLayoutController implements Initializable
{

    private View view;

    /**
     * This control will display the animated pendulum. (Canvas version.)
     */
    @FXML
    private Canvas pendulumDrawCanvas;
    
    @FXML
    private Circle pendulumBall;
    
    @FXML
    private Line pendulumString;

    private CanvasDrawThread canvasPendulum;
    private NodeDrawThread nodePendulum;

    @FXML
    private TextField stringLengthTextField;

    @FXML
    private TextField gravityAccelerationTextField;

    @FXML
    private TextField angleTextField;

    @FXML
    private TextField massTextField;

    @FXML
    private Slider stringLengthSlider;

    @FXML
    private Slider gravityAccelerationSlider;

    @FXML
    private Slider angleSlider;

    @FXML
    private Slider massSlider;

    @FXML
    private ToggleButton nodeToggleButton;

    @FXML
    private ToggleButton canvasToggleButton;

    /**
     * Initializes the controller class.
     */
    @Override
    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void initialize (URL url, ResourceBundle rb)
    {
	initTextFieldsFromSliders ();
	addListenersForSlidersChanging ();

	return;
    }

    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void setMain (View view)
    {
	this.view = view;

	return;
    }

    public TextField getStringLengthTextField ()
    {
	return stringLengthTextField;
    }

    public TextField getGravityAccelerationTextField ()
    {
	return gravityAccelerationTextField;
    }

    public TextField getAngleTextField ()
    {
	return angleTextField;
    }

    public TextField getMassTextField ()
    {
	return massTextField;
    }

    /**
     * Event handler of the "Save..." button.
     */
    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void handleSaveButton ()
    {
	FileChooser fileChooser = new FileChooser ();
	FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter ("Properties file (*.properties)", "*.properties");
	fileChooser.getExtensionFilters ().add (fileExtension);
	fileChooser.setTitle ("Save Properties to File");
	fileChooser.setInitialFileName ("Pendulum.properties");
	File chosenFile = fileChooser.showSaveDialog (view.getMainStage ());

	if (chosenFile == null)
	{
	    return;
	}

	try
	{
	    OutputStream outputStream = new FileOutputStream (chosenFile);
	    Properties pendulumProperties = backupProperties ();
	    if (outputStream != null)
	    {
		view.getController ().savePropertiesToFile (outputStream, pendulumProperties);
		outputStream.close ();
	    }
	}
	catch (IOException ioe)
	{
	    ioe.printStackTrace ();
	}

	return;
    }

    /**
     * Event handler for the "Load..." button.
     */
    public void handleLoadButton ()
    {
	FileChooser fileChooser = new FileChooser ();
	FileChooser.ExtensionFilter fileExtension = new FileChooser.ExtensionFilter ("Properties file (*.properties)", "*.properties");
	fileChooser.getExtensionFilters ().add (fileExtension);
	fileChooser.setTitle ("Load Properties from File");
	File chosenFile = fileChooser.showOpenDialog (view.getMainStage ());

	if (chosenFile == null)
	{
	    return;
	}

	Properties pendulumProperties = null;

	try
	{
	    InputStream inputStream = new FileInputStream (chosenFile);
	    if (inputStream != null)
	    {
		pendulumProperties = view.getController ().readPropertiesFromFile (inputStream);
		inputStream.close ();
	    }
	}
	catch (IOException ioe)
	{
	    ioe.printStackTrace ();
	}

	if (pendulumProperties != null)
	{
	    restoreProperties (pendulumProperties);
	}

	return;
    }

    /**
     * Private method responsible for Property saving.
     *
     * @return the saved Property containing fields related to the pendulum.
     */
    private Properties backupProperties ()
    {
	Properties pendulumProperties = new Properties ();
	NumberFormat numberFormat = NumberFormat.getInstance (Locale.forLanguageTag ("hu-HU"));

	Number stringLengthNumber = null;
	Number gravityAccelerationNumber = null;
	Number angleNumber = null;
	Number massNumber = null;

	double stringLengthValue = 0;
	double gravityAccelerationValue = 0;
	double angleValue = 0;
	double massValue = 0;

	try
	{
	    stringLengthNumber = numberFormat.parse (getStringLengthTextField ().getText ());
	    stringLengthValue = stringLengthNumber.doubleValue ();
	    gravityAccelerationNumber = numberFormat.parse (getGravityAccelerationTextField ().getText ());
	    gravityAccelerationValue = gravityAccelerationNumber.doubleValue ();
	    angleNumber = numberFormat.parse (getAngleTextField ().getText ());
	    angleValue = angleNumber.doubleValue ();
	    massNumber = numberFormat.parse (getMassTextField ().getText ());
	    massValue = massNumber.doubleValue ();
	}
	catch (ParseException pe)
	{
	    String stringLengthTransformed = stringLengthTextField.getText ().replaceAll (",", ".");
	    String gravityAccelerationTransformed = gravityAccelerationTextField.getText ().replaceAll (",", ".");
	    String angleTransformed = angleTextField.getText ().replaceAll (",", ".");
	    String massTransformed = massTextField.getText ().replaceAll (",", ".");

	    stringLengthValue = Double.parseDouble (stringLengthTransformed);
	    gravityAccelerationValue = Double.parseDouble (gravityAccelerationTransformed);
	    angleValue = Double.parseDouble (angleTransformed);
	    massValue = Double.parseDouble (massTransformed);
	}

	pendulumProperties.setProperty ("length", Double.toString (stringLengthValue));
	pendulumProperties.setProperty ("acceleration", Double.toString (gravityAccelerationValue));
	pendulumProperties.setProperty ("angle", Double.toString (angleValue));
	pendulumProperties.setProperty ("mass", Double.toString (massValue));

	return pendulumProperties;
    }

    /**
     * Private method responsible for loading data and updating fields related
     * to pendulum.
     *
     * @param properties the object that will be read
     */
    @SuppressWarnings ("UnnecessaryReturnStatement")
    private void restoreProperties (Properties properties)
    {
	double stringLengthValue = Double.parseDouble (properties.getProperty ("length"));
	double gravityAccelerationValue = Double.parseDouble (properties.getProperty ("acceleration"));
	double angleValue = Double.parseDouble (properties.getProperty ("angle"));
	double massValue = Double.parseDouble (properties.getProperty ("mass"));

	// Update TextFields
	// In fact, no need to update the textfields since sliders are bound to them they will update automatically.
	stringLengthTextField.setText (String.format ("%.2f", stringLengthValue));
	gravityAccelerationTextField.setText (String.format ("%.2f", gravityAccelerationValue));
	angleTextField.setText (String.format ("%.2f", angleValue));
	massTextField.setText (String.format ("%.2f", massValue));

	// Update Sliders
	stringLengthSlider.valueProperty ().set (stringLengthValue);
	gravityAccelerationSlider.valueProperty ().set (gravityAccelerationValue);
	angleSlider.valueProperty ().set (angleValue);
	massSlider.valueProperty ().set (massValue);

	// Update Model
	view.getController ().getModel ().setStringLength (stringLengthValue);
	view.getController ().getModel ().setGravityAcceleration (gravityAccelerationValue);
	view.getController ().getModel ().setAngle (angleValue);
	view.getController ().getModel ().setMass (massValue);

	return;
    }

    /**
     * Custom initialiser for the class which is different from the other one
     * provided by the framework.
     */
    public void initialise ()
    {
	initSlidersFromModell ();
	initializeNode ();
	initializeCanvas ();

	return;
    }

    private void initSlidersFromModell ()
    {
	double stringLengthValue = view.getController ().getModel ().getStringLength ();
	double gravityAccelerationValue = view.getController ().getModel ().getGravityAcceleration ();
	double angleValue = view.getController ().getModel ().getAngle ();
	double massValue = view.getController ().getModel ().getMass ();

	stringLengthSlider.valueProperty ().set (stringLengthValue);
	gravityAccelerationSlider.valueProperty ().set (gravityAccelerationValue);
	angleSlider.valueProperty ().set (angleValue);
	massSlider.valueProperty ().set (massValue);

	return;
    }

    private void initTextFieldsFromSliders ()
    {
	stringLengthTextField.setText (String.format ("%.2f", stringLengthSlider.valueProperty ().get ()));
	gravityAccelerationTextField.setText (String.format ("%.2f", gravityAccelerationSlider.valueProperty ().get ()));
	angleTextField.setText (String.format ("%.2f", angleSlider.valueProperty ().get ()));
	massTextField.setText (String.format ("%.2f", massSlider.valueProperty ().get ()));

	return;
    }

    @SuppressWarnings ("UnnecessaryReturnStatement")
    private void addListenersForSlidersChanging ()
    {
	ChangeListener<Number> stringLengthChanged = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
	{
	    stringLengthTextField.setText (String.format ("%.2f", newValue.doubleValue ()));
	    view.getController ().getModel ().setStringLength (newValue.doubleValue ());
	};

	ChangeListener<Number> gravityAccelerationchanged = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
	{
	    gravityAccelerationTextField.setText (String.format ("%.2f", newValue.doubleValue ()));
	    view.getController ().getModel ().setGravityAcceleration (newValue.doubleValue ());
	};

	ChangeListener<Number> angleChanged = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
	{
	    angleTextField.setText (String.format ("%.2f", newValue.doubleValue ()));
	    view.getController ().getModel ().setAngle (newValue.doubleValue ());
	};

	ChangeListener<Number> massChanged = (ObservableValue<? extends Number> observable, Number oldValue, Number newValue) ->
	{
	    massTextField.setText (String.format ("%.2f", newValue.doubleValue ()));
	    view.getController ().getModel ().setMass (newValue.doubleValue ());
	};

	stringLengthSlider.valueProperty ().addListener (stringLengthChanged);
	gravityAccelerationSlider.valueProperty ().addListener (gravityAccelerationchanged);
	angleSlider.valueProperty ().addListener (angleChanged);
	massSlider.valueProperty ().addListener (massChanged);

	return;
    }
    
        private void initializeNode ()
    {
	double length = view.getController ().getModel ().getStringLength ();
	double acceleration = view.getController ().getModel ().getGravityAcceleration ();
	// double angle = Model.convertDegreeToRadian (view.getController ().getModel ().getAngle ());
	double angle = Math.PI / 2;
	double mass = view.getController ().getModel ().getMass ();
	nodePendulum = new NodeDrawThread (length, acceleration, angle, mass, pendulumBall, pendulumString);
	
	return;
    }

    private void initializeCanvas ()
    {
	double length = view.getController ().getModel ().getStringLength ();
	double acceleration = view.getController ().getModel ().getGravityAcceleration ();
	// double angle = Model.convertDegreeToRadian (view.getController ().getModel ().getAngle ());
	double angle = Math.PI / 2;
	double mass = view.getController ().getModel ().getMass ();
	canvasPendulum = new CanvasDrawThread (pendulumDrawCanvas, length, acceleration, angle, mass);

	return;
    }

    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void handleAnimateNode ()
    {
	boolean isNodeSelected = nodeToggleButton.isSelected ();
	
	double length = view.getController ().getModel ().getStringLength ();
	double acceleration = view.getController ().getModel ().getGravityAcceleration ();
	// double angle = Model.convertDegreeToRadian (view.getController ().getModel ().getAngle ());
	double angle = Math.PI / 2;
	double mass = view.getController ().getModel ().getMass ();
	
	if (isNodeSelected)
	{
	    // Start animation
	    nodePendulum = new NodeDrawThread (length, acceleration, angle, mass, pendulumBall, pendulumString);
	    nodePendulum.start ();
	}
	else
	{
	    // Stop animation
	    nodePendulum.interrupt ();
	}

	return;
    }

    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void handleAnimateCanvas ()
    {
	boolean isCanvasSelected = canvasToggleButton.isSelected ();

	System.out.println ("Canvas selected: " + isCanvasSelected);

	double length = view.getController ().getModel ().getStringLength ();
	double acceleration = view.getController ().getModel ().getGravityAcceleration ();
	// double angle = Model.convertDegreeToRadian (view.getController ().getModel ().getAngle ());
	double angle = Math.PI / 2;
	double mass = view.getController ().getModel ().getMass ();

	if (isCanvasSelected)
	{
	    // Start animation
	    canvasPendulum = new CanvasDrawThread (pendulumDrawCanvas, length, acceleration, angle, mass);
	    canvasPendulum.start ();
	}
	else
	{
	    // Stop animation
	    canvasPendulum.interrupt ();
	}

	return;
    }
}
