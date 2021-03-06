/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package model;

import java.awt.Color;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import view.DrawingPanel;

/**
 * A class for handling the actions of the choose color buttons.
 * 
 * @author Tyler Lorella (tlorella@uw.edu)
 * @version 1
 */
public class ColorAction extends AbstractAction {

    /**
     * Generated serial.
     */
    private static final long serialVersionUID = -8862552305106169585L;
    
    /**
     * The DrawingPanel object to modify through te actionPerformed.
     */
    private final DrawingPanel myDrawingPanel;

    /**
     * Builds a ColorAction that will modify theDrawingPanel's color when activated.
     * @param theDrawingPanel the Panel to modify when activated.
     */
    public ColorAction(final DrawingPanel theDrawingPanel) {
        super("Color...");
        myDrawingPanel = theDrawingPanel;
    }

    /**
     * When an actionPerformed event happens, the JColorChoose menu appears and if a valid 
     * Color is chosen then the color of the DrawingPanel will be set to that.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        final Color color = JColorChooser.showDialog(myDrawingPanel, "CHOOSE YOUR COLOR",
                                                myDrawingPanel.getColor());
        if (color != null) {
            myDrawingPanel.setColor(color);
        }
    }



}
