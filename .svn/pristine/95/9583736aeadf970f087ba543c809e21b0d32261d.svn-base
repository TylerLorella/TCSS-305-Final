/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package model;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import view.DrawingPanel;

/**
 * A class for handling actions when the tool buttons are clicked.
 * 
 * @author Tyler Lorella (tlorela@uw.edu)
 * @version 1.1
 */
public class ToolAction extends AbstractAction {
    
    
    /**
     * Auto-generated serial ID.
     */
    private static final long serialVersionUID = -6538414919197399404L;
    
    /**
     * The current tool being used by the program, when a tool is selected the object needs 
     * to be passed down to the DrawingPanel.
     */
    private final Tool myTool;
    
    /**
     * The drawing panel that myTool will be active for.
     */
    private final DrawingPanel myDrawingPanel;
    
    /**
     * Constructor for the ToolAction, will be used for connecting the two buttons with the 
     * same theTool.toString() so that when one is selected, the other is as well.
     * 
     * @param theTool The tool that will be selected.
     * @param theDrawingPanel the DrawningPanel that will have it's current tool modified.
     */
    public ToolAction(final Tool theTool, final DrawingPanel theDrawingPanel) {
        super(theTool.toString());
        myTool = theTool;
        myDrawingPanel = theDrawingPanel;
        putValue(SELECTED_KEY, true);
    }
    
    /**
     * Listeners for if the tool button assigned this action is clicked, is so it will
     * change the current tool in the myDrawningPanel.
     * 
     * @param theEvent The event that happened to this button.
     */
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myDrawingPanel.setTool(myTool);
    }
    

}
