/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pendulum.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import pendulum.model.Model;
import pendulum.view.View;

/**
 *
 * @author jdev
 */
public class Controller
{

    private final View view;
    private final Model model;

    /**
     * The expected controller constructor for a MVC architecture.
     * @param view the MVC View
     * @param model  the MVC Model
     */
    public Controller (View view, Model model)
    {
	this.view = view;
	this.model = model;
    }

    public View getView ()
    {
	return view;
    }

    public Model getModel ()
    {
	return model;
    }
    
    /**
     * Save certain Properties for later use.
     * @param outputStream the stream to save to (destination)
     * @param properties the object to save
     * @throws IOException 
     */
    @SuppressWarnings ("UnnecessaryReturnStatement")
    public void savePropertiesToFile (OutputStream outputStream, Properties properties) throws IOException
    {
	properties.store (outputStream, "Modifiable Pendulum Properties");
	
	return;
    }
    
    /**
     * Reads from given location a Properties object.
     * @param inputStream the source for reading the input
     * @return the loaded Properties object
     * @throws IOException 
     */
    public Properties readPropertiesFromFile (InputStream inputStream) throws IOException
    {
	Properties pendulumProperties = new Properties ();
	pendulumProperties.load (inputStream);
	
	return pendulumProperties;
    }
}
