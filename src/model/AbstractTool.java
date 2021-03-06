/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package model;

import java.awt.Point;
import java.awt.Shape;

/**
 * Abstract representation of the a painting tool. Contains a few methods used by all tools
 * that is required by the interface.
 * 
 * @author Tyer Lorella (tlorella@uw.edu)
 * @version 1
 */
public abstract class AbstractTool implements Tool {
    
    /**
     * A point that is off screen, for ensuring that when the panel is cleared, nothing is 
     * drawn back onto the panel and is the point that the constructor initializes to.
     */
    private static final Point NO_POINT = new Point(-50, -50);

    /**
     * The starting point for this tool's shape.
     */
    private Point myStartPoint;
    
    /**
     * The ending point for this tool's shape.
     */
    private Point myEndPoint;
    
    /**
     * Constructs the tool object with off screen points rather than a null value.
     */
    public AbstractTool() {
        myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }
    
    /**
     *  Builds a shape from the given the start and end points then returns it.
     *  @return A non-null shape.
     */
    public abstract Shape buildShape();
    
    /**
     * The name of the tool that will be displayed on the GUI buttons.
     * 
     * @return Name of the tool.
     */
    @Override
    public abstract String toString();
    
    /**
     * CLears the current tool by setting its start and end points to the NO_POINT. Some tools
     * may need to override this method to implement their proper clearing method.
     */
    public void clearShape() {
        myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }
    
    /**
     * Sets the current start point to the point passed as a parameter.
     * @param theOtherPoint The Point that will be assigned as the current start point.
     */
    public void setStartPoint(final Point theOtherPoint) {
        myStartPoint = (Point) theOtherPoint.clone();
    }
    
    /**
     * Gets a copy of the Current start point, will not modify this objects start point.
     * @return A copy of the current start point.
     */
    public Point getStartPoint() {
        return (Point) myStartPoint.clone();
    }
    
    /**
     * Sets the current end point to the point passed as a parameter.
     * @param theOtherPoint The point that will be assigned as the current end point.
     */
    public void setEndPoint(final Point theOtherPoint) {
        myEndPoint = (Point) theOtherPoint.clone();
    }
    
    /**
     * Gets a copy of the Current end point, will not modify this objects end point.
     * @return A copy of the current end point.
     */
    public Point getEndPoint() {
        return (Point) myEndPoint.clone();
    }
    
    
    

    
    
}
