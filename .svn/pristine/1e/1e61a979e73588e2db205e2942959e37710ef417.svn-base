/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.util.Objects;

/**
 * A Figure object is the representation of a Shape, the Color it is, and the Stroke or 
 * thickness of the Shape. Thus this object is designed to hold three values for a specific 
 * figure.
 * 
 * @author Tyler Lorella(tlorella@uw.edu)
 * @version 1
 */
public class Figure {
    
    /**
     * The Shape that this Figure is made of.
     */
    private final Shape myShape;

    /**
     * The Color that the Shape is.
     */
    private final Color myColor;
    
    /**
     * The BasicStroke or the thickness of the Shape.
     */
    private final BasicStroke myStroke;
    
    /**
     * Constructor for building a Figure, must be composed of non-null values. Will throw
     * NullPointer exception if a parameter is null.
     * 
     * @param theShape The Shape that this Figure will represent.
     * @param theColor The Color that this Figure will be.
     * @param theStroke The BasicStroke or thickness that this figure will be.
     */
    public Figure(final Shape theShape, final Color theColor, final BasicStroke theStroke) {
        myShape = Objects.requireNonNull(theShape);
        myColor = Objects.requireNonNull(theColor);
        myStroke = Objects.requireNonNull(theStroke);
    }
    
    /**
     * Getter for the Shape of this figure.
     * @return A non-null Shape.
     */
    public Shape getShape() {
        return myShape;
    }

    /**
     * Getter for the Color of this figure.
     * @return A non-null Color.
     */
    public Color getColor() {
        return myColor;
    }

    /**
     * Getter for the BasicStroke of this figure.
     * @return a non-null BasicStroke.
     */
    public BasicStroke getStroke() {
        return myStroke;
    }
    
    
}
