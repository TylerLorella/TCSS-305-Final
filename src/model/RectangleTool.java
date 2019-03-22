/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * Tool that can build rectangles from the given starting and ending point.
 * 
 * @author Tyler Lorella (tlorella@uw.edu)
 * @version 1
 *
 */
public class RectangleTool extends AbstractTool implements Tool {
    
    /**
     * Builds a rectangle from the given starting and ending coordinates, can draw it both 
     * forwards and backwards.
     * 
     * @return A rectangle with going from StartPoint to EndPoint.
     */
    public Shape buildShape() {

        double startX = getStartPoint().getX();

        double startY = getStartPoint().getY();

        double width = getEndPoint().getX() - getStartPoint().getX();

        double height = getEndPoint().getY() - getStartPoint().getY();

        if (getEndPoint().getX() < getStartPoint().getX()) {
            startX = getEndPoint().getX();
            width = getStartPoint().getX() - getEndPoint().getX();
        }

        if (getEndPoint().getY() < getStartPoint().getY()) {
            startY = getEndPoint().getY();
            height = getStartPoint().getY() - getEndPoint().getY();
        }

        return new Rectangle2D.Double(startX, startY, width, height);
    }
    
    /**
     * Name of the Tool.
     * @return The name of the tool as displayed on buttons.
     */
    @Override
    public String toString() {
        return "Rectangle Tool";
    }
}
