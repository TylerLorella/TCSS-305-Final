/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package model;

import java.awt.Point;
import java.awt.Shape;

/**
 * Tool interface that makes sure each tool implemented contains some basic methods to be 
 * functional.
 * 
 * @author Tyler Lorella (tlorella@uw.edu)
 * @version 1
 *
 */
public interface Tool {
    
    
    
    /**
     *  Builds a shape from the given the start and end points then returns it.
     *  @return A non-null shape.
     */
    Shape buildShape();
    
    /**
     * Sets the starting point of the Tool.
     * @param thePoint The point at which the starting point will be.
     */
    void setStartPoint(Point thePoint);
    
    /**
     * Sets the ending point of the Tool.
     * @param thePoint Sets the end point to this point.
     */
    void setEndPoint(Point thePoint);
    
    /**
     * Moves the points off screen and ensures the tool will not redraw anything onto the 
     * screen.
     */
    void clearShape();
    
    /**
     * The String representation of the tool, used for the name of the buttons the tool is
     * associated with.
     * @return String represention of the name of the tool.
     */
    String toString();

}
