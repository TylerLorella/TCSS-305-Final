/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import model.Figure;
import model.Tool;


/**
 * Drawing Panel extends JPanel and has additional functionality for drawing objects onto 
 * the panel. When a DrawingPanel object is created a Tool object must be assigned before 
 * anything is drawn. The default color is husky purple and the default line thickness is 2.
 * 
 * @author Tyler Lorella (tlorella@uw.edu)
 * @version 1
 */
public class DrawingPanel extends JPanel {

    /**
     * Generated serial ID.
     */
    private static final long serialVersionUID = 661748911042328747L;

    /**
     * The default color for this program, UW Purple.
     */
    private static final Color DEFAULT_COLOR = new Color(51, 0, 111);

    /**
     * The default stroke for this program, 2.
     */
    private static final BasicStroke DEFAULT_STROKE = new BasicStroke(2);

    /**
     * The current color that shapes will be drawn with.
     */
    private Color myColor;

    /**
     * The current BasicStroke or thickness that shapes will be drawn with.
     */
    private BasicStroke myStroke;

    /**
     * The current Shape Tool being used for creating Shapes.
     */
    private Tool myTool;

    /**
     * A list of the Figures that are currently drawn on this panel.
     */
    private final List<Figure> myFigureList;

    /**
     * The current status of the clear button if present in the object using this 
     * DrawningPanel object.
     */
    private boolean myClearIsEnabled;

    /**
     * Constructs a DrawingPanel with the default settings, a white background, and a 
     * MouseAdapter for drawing objects onto this panel.
     * 
     * @param theInitialTool 
     */
    public DrawingPanel(final Tool theInitialTool) {
        super();
        setBackground(Color.WHITE);
        myColor = DEFAULT_COLOR;
        myStroke = DEFAULT_STROKE;
        myTool = theInitialTool;
        myFigureList = new ArrayList<Figure>();
        
        
        

        final MouseInputAdapter mouseHandler = new MouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }

    /**
     * Setter method for the current Color being used to draw a Shape.
     * @param theColor The Color that the Shape will be.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }

    /**
     * Setter method for the current Stroke being used to draw a Shape.
     * @param theStroke The thickness of the Shape.
     */
    public void setStroke(final BasicStroke theStroke) {
        myStroke = theStroke;
    }

    /**
     * Setter method for the current Shape Tool being used.
     * @param theTool The Tool that will draw it's respective Shape.
     */
    public void setTool(final Tool theTool) {
        myTool = theTool;
    }

    /**
     * Getter method for myColor, will return a non-null value.
     * @return a Color object representing the current color this drawing panel draws in.
     */
    public Color getColor() {
        return myColor;
    }

    /**
     * Getter method for myStroke, will return a non-null value.
     * @return a BasicStroke object representing the current thickness this drawing panel 
     * draws with.
     */
    public BasicStroke getStroke() {
        return myStroke;
    }

    /**
     * Clears the current drawings on the Panel, fires an event that it's been cleared, and
     * clears the current shape for the current myTool then it repaints the empty panel.
     */
    public void clear() {
        myFigureList.clear();
        myTool.clearShape();
        setClearButton(false);
        repaint();
    }
    
    /**
     * Paints the current figures stored in myFigureList and the recent shape added onto the 
     * DrawingPanel.
     * @param theGraphics The graphics that will be painting onto the panel.
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D graphics2D = (Graphics2D) theGraphics;
        
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                                    RenderingHints.VALUE_ANTIALIAS_ON);

        for (final Figure aFigure: myFigureList) {
            graphics2D.setStroke(aFigure.getStroke());
            graphics2D.setColor(aFigure.getColor());
            graphics2D.draw(aFigure.getShape());   
        }
        graphics2D.setStroke(myStroke);
        graphics2D.setColor(myColor);
        graphics2D.draw(myTool.buildShape());


    }
    
    /**
     * To ensure unneeded events are fired every time an object is drawn, this method will
     * check if the boolean that is passed is different then the current status, if so then 
     * any listener's attached to this DrawingPanel object will be notified.
     * 
     * @param theButtonStatus If the clear button should be enabled or disabled.
     */
    private void setClearButton(final boolean theButtonStatus) {
        if (myClearIsEnabled != theButtonStatus) {
            firePropertyChange("clearChange", myClearIsEnabled, theButtonStatus);
            myClearIsEnabled = theButtonStatus;
            
        }
    }

    /**
     * Class for using the mouse to draw the objects onto the Panel, and change the cursor
     * into the crosshair when it's in the panel and the default cursor when it exits.
     * 
     * @author Tyler Lorella (tlorella@uw.edu)
     *
     */
    private class MouseHandler extends MouseInputAdapter {

        /**
         * When a mouse pressed activity happens a shape is drawn onto the panel that can 
         * still be modified.
         * @param theEvent The event that occurred to the listener, is ignored.
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            if (myStroke.getLineWidth() != 0) {
                myTool.setStartPoint(theEvent.getPoint());
                myTool.setEndPoint(theEvent.getPoint());
                repaint();
            }
        }

        /**
         * When the mouse is dragged the shape created previously will have it's end 
         * Coordinates moved to the mouse.
         * @param theEvent The event that occurred to the listener, is ignored.
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            if (myStroke.getLineWidth() != 0) {
                myTool.setEndPoint(theEvent.getPoint());
                repaint();
            }
        }

        /**
         * Once the mouse is released, the recently drawn shape will be added to made into a 
         * figure and added to a list of previously drawn figures, the tool will then clear
         * it's current shape to prevent unintended access.
         * @param theEvent The event that occurred to the listener, is ignored.
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            if (myStroke.getLineWidth() != 0) {
                myTool.setEndPoint(theEvent.getPoint());
                myFigureList.add(new Figure(myTool.buildShape(), myColor, myStroke));
                setClearButton(true);
                repaint();
                myTool.clearShape();
            }
        }
        
        /**
         * When the mouse enters the DrawningPanel the cursor is changed to a cross hair.
         * @param theEvent The event that occurred to the listener, is ignored.
         */
        @Override
        public void mouseEntered(final MouseEvent theEvent) {
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }
        
        /**
         * When the mouse exits the DrawingPanel the cursor is changed back to the default
         * cursor.
         * @param theEvent The event that occurred to the listener, is ignored.
         */
        @Override
        public void mouseExited(final MouseEvent theEvent) {
            setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        }
        
    }


}
