/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package model;

import java.util.Objects;
import javax.swing.ImageIcon;

/**
 * An object that represents a specific tool with the action and the icon tied to that tool.
 * All tools must have a corresponding icon and action.
 * 
 * @author Tyler Lorella (tlorella@uw.edu)
 * @version 1
 */
public class ToolKit {

    /**
     * The tool that this ToolKit object has.
     */
    private final Tool myTool;
    
    /**
     * The action that the tool uses.
     */
    private final ToolAction myAction;
    
    /**
     * The icon for the tool.
     */
    private final ImageIcon myIcon;
    
    /**
     * Constructs a toolkit object that represents a tool with it's action and icon. Each 
     * parameter must be non-null, will throw NullPointerException.
     * 
     * @param theTool The tool this kit will represent.
     * @param theAction The action the tool will take.
     * @param theIcon The Icon the tool will use.
     */
    public ToolKit(final Tool theTool, final ToolAction theAction, final ImageIcon theIcon) {
        myTool = Objects.requireNonNull(theTool);
        myAction = Objects.requireNonNull(theAction);
        myIcon = Objects.requireNonNull(theIcon);
    }

    /**
     * Gets the tool that this kit represents.
     * @return Tool for this kit.
     */
    public Tool getTool() {
        return myTool;
    }

    /**
     * Gets the action the tool takes.
     * @return ToolAction that the tool takes.
     */
    public ToolAction getAction() {
        return myAction;
    }

    /**
     * Gets the icon the tool uses.
     * @return ImageIcon the tool uses.
     */
    public ImageIcon getIcon() {
        return myIcon;
    }
    
    
}
