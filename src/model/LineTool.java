/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package model;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * Builds a Line2D shape from the given starting and ending points, and has a unique toString.
 * 
 * @author Tyler Lorella (tlorella@uw.edu)
 * @version 1
 */
public class LineTool extends AbstractTool implements Tool {

    /**
     * Builds a line from the start point to the end point.
     * 
     * @return a Line shape from the start point to the end point.
     */
    @Override
    public Shape buildShape() {
        return new Line2D.Double(getStartPoint(), getEndPoint());
    }
    
    /**
     * String representation of the name of the tool.
     * @return The name of the tool.
     */
    @Override
    public String toString() {
        return "Line Tool";
    }
}
