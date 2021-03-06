/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.ColorAction;
import model.EllipseTool;
import model.LineTool;
import model.PencilTool;
import model.RectangleTool;
import model.ToolAction;
import model.ToolKit;

/**
 * The GUI display for the paint program. Will fully generate a UI with menus, tools and 
 * actions. In order to begin the GUI display the start() method must be called after creating
 * an object of this type. If new tools are desired a tool class, imageIcon must be created,
 * then added to the myToolKitMap map in the constructor.
 * 
 * @author Tyler Lorella (tlorella@uw.edu)
 * @version 1
 *
 */
public class PaintGUI implements PropertyChangeListener {

    /**
     * Constant value representing that the initial GUI size will be 1/3rd the screen size.
     */
    private static final int SIZE_MODIFIER = 3;

    /**
     * Constant value for finding the center of the screen.
     */
    private static final int CENTER_MODIFIER = 2;

    /**
     * The maximum value that the thickness slider may have.
     */
    private static final int SLIDER_MAX = 15;

    /**
     * The Default value for the thickness slider.
     */
    private static final int SLIDER_DEFAULT = 2;

    /**
     * The minimum value that the thickness slider may have.
     */
    private static final int SLIDER_MIN = 0;

    /**
     * The thickness slider major tick value.
     */
    private static final int SLIDER_TICK_MAJOR = 5;

    /**
     * The thickness slider minor tick value.
     */
    private static final int SLIDER_TICK_MINOR = 1;

    /**
     * The icon for the GUI display, replaces the JFrame icon and the about menu icon.
     */
    private static final ImageIcon FRAME_ICON = new ImageIcon("./resources/Tyler.jpg");

    /**
     * the string representation of the title of the program.
     */
    private static final String TITLE_STRING = "TCSS 305 - Paint Program";

    /**
     * The JFrame for the PaintGUI object.
     */
    private final JFrame myJFrame;

    /**
     * The current DrawningPanel object for this JFrame, will contain all of the shapes.
     */
    private final DrawingPanel myDrawingPanel;

    /**
     * Object for drawing Lines on the DrawningPanel through the PaintGUI.
     */

    /**
     * A map that goes from the toString representation of a tool object, to a ToolKit object
     * that contains the Tool, ImageIcon, and Action for that tool.
     */
    private final Map<String, ToolKit> myToolKitMap = new LinkedHashMap<String, ToolKit>();

    /**
     * The clear button that will be used by this program, An attached PropertyChangeListener 
     * must be capable of changing if the clear button is enabled or not based off what the 
     * DrawingPanel reports.
     */
    private final JMenuItem myClearItem = new JMenuItem("Clear");
    
    
    /**
     * Builds the GUI display, uses the LineTool as the starting tool. If more tools are 
     * desired a tool class and image icon must be created and then added to the build 
     * ToolKitMap method. Follow the pattern for the four default tools in this method, no 
     * other modification is needed.
     */
    public PaintGUI() {
        myJFrame = new JFrame(TITLE_STRING);

        final LineTool line = new LineTool();
        final RectangleTool rectangle = new RectangleTool();
        final EllipseTool ellipse = new EllipseTool();
        final PencilTool pencil = new PencilTool();
        myDrawingPanel = new DrawingPanel(line);

        myToolKitMap.put(line.toString(), 
                         new ToolKit(line, 
                                     new ToolAction(line, myDrawingPanel), 
                                     new ImageIcon("./resources/line.gif")));

        myToolKitMap.put(pencil.toString(),
                         new ToolKit(pencil, 
                                     new ToolAction(pencil, myDrawingPanel), 
                                     new ImageIcon("./resources/pencil.gif")));

        myToolKitMap.put(rectangle.toString(),
                         new ToolKit(rectangle, 
                                     new ToolAction(rectangle, myDrawingPanel), 
                                     new ImageIcon("./resources/rectangle.gif")));

        myToolKitMap.put(ellipse.toString(),
                         new ToolKit(ellipse, 
                                     new ToolAction(ellipse, myDrawingPanel), 
                                     new ImageIcon("./resources/ellipse.gif")));

    }

    /**
     * Adds the various elements to the GUI display, and calls several methods to help with 
     * the construction of the GUI display. 
     */
    public void start() {
        buildMyJFrame();
        myJFrame.add(myDrawingPanel);
        
        final ColorAction colorAction = new ColorAction(myDrawingPanel);

        final JToolBar toolBar = buildToolBar(colorAction);
        myJFrame.add(toolBar, BorderLayout.SOUTH);

        myJFrame.setJMenuBar(buildMenuBar(colorAction));
        myDrawingPanel.addPropertyChangeListener(this);
    }

    /**
     * Method for building the JFrame, will be sized to 1/3rd of the screen size and will 
     * be centered.
     */
    private void buildMyJFrame() {  
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        final int width = (int) (screenSize.getWidth() / SIZE_MODIFIER);
        final int height = (int) (screenSize.getHeight() / SIZE_MODIFIER);

        final int startX = (int) ((screenSize.getWidth() / CENTER_MODIFIER) 
                        - (width / CENTER_MODIFIER));
        final int startY = (int) ((screenSize.getHeight() / CENTER_MODIFIER) 
                        - (height / CENTER_MODIFIER));

        myJFrame.setBounds(startX, startY, width, height);
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJFrame.setVisible(true);
        myJFrame.setIconImage(FRAME_ICON.getImage());
    }

    /**
     * Builds the menu bar and menus for this program, will contain a Options, Tools, and About
     * menus with implemented actions. 
     * 
     * @param theColorAction The color action that the color chooser button will take, perform
     * the same action as the tool bar color chooser.
     * 
     * @return JMenuBar with attached specified menus.
     */
    private JMenuBar buildMenuBar(final ColorAction theColorAction) {
        final JMenuBar menuBar = new JMenuBar();

        menuBar.add(buildOptionMenu(theColorAction));
        menuBar.add(buildToolMenu());
        menuBar.add(buildHelpMenu());

        return menuBar;
    }

    /**
     * Builds the option menu for this program with a thickness slider, color choosing button,
     * and a clear button.
     * 
     * @param theColorAction The action that the color button will take, will be the same
     * action as the color chooser in the tool bar.
     * 
     * @return JMenu that contains the specified elements and actions.
     */
    private JMenu buildOptionMenu(final ColorAction theColorAction) {
        final JMenu menu = new JMenu("Option");
        final JMenu thickMenu = buildThickMenu();

        menu.add(thickMenu);
        menu.addSeparator();

        //need to implement a color chooser action not a listener.
        final JMenuItem colorItem = new JMenuItem("Color");
        colorItem.setAction(theColorAction);
        menu.add(colorItem);
        menu.addSeparator();

        myClearItem.setEnabled(false);
        myClearItem.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                myDrawingPanel.clear();
                for (final String key: myToolKitMap.keySet()) {
                    myToolKitMap.get(key).getTool().clearShape();
                }
                //myClearItem.setEnabled(false);
            }
        });
        menu.add(myClearItem);

        return menu;
    }

    /**
     * Builds the thickness sub-menu that contains the thickness slider.
     * @return JMenu that is fully built with a functional thickness slider.
     */
    private JMenu buildThickMenu() {
        final JMenu menu = new JMenu("Thickness");
        final JSlider slider = new JSlider(SwingConstants.HORIZONTAL, SLIDER_MIN, 
                                           SLIDER_MAX, SLIDER_DEFAULT);
        slider.setMajorTickSpacing(SLIDER_TICK_MAJOR);
        slider.setMinorTickSpacing(SLIDER_TICK_MINOR);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                //if the value is 0 we shouldn't draw anything.
                myDrawingPanel.setStroke(new BasicStroke(slider.getValue()));
            }

        });
        menu.add(slider);
        return menu;
    }

    /**
     * Builds the tool menu with JRadioButtons that contains all tools in this program and
     * their icons, and actions.
     * 
     * @return JMenu with the name "Tools" and a button for every tool.
     */
    private JMenu buildToolMenu() {
        final JMenu menu = new JMenu("Tools");
        final ButtonGroup buttonGroup = new ButtonGroup();

        for (final String key: myToolKitMap.keySet()) {
            final JRadioButtonMenuItem button = buildRadioButtonItem(key);
            menu.add(button);
            buttonGroup.add(button);
        }

        return menu;
    }

    /**
     * Helper method for building the Help menu in the GUI display. 
     * @return A completed Help Menu.
     */
    private JMenu buildHelpMenu() {
        final JMenu menu = new JMenu("Help");
        final JMenuItem item = new JMenuItem("About...");
        item.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(myJFrame, "Winter 2019 \n Tyler Lorella",
                                              TITLE_STRING, 
                                              JOptionPane.PLAIN_MESSAGE, FRAME_ICON);
            }
        });
        menu.add(item);
        return menu;
    }

    /**
     * Builds the tool bar for this program, which consists of a color choosing button, and 
     * a button for all the tools for this program.
     * 
     * @param theColorAction The action that the color button will be mapped to.
     * 
     * @return JToolBar with color and tool buttons with all actions assigned.
     */
    private JToolBar buildToolBar(final ColorAction theColorAction) {
        final JToolBar toolBar = new JToolBar();
        final ButtonGroup buttonGroup = new ButtonGroup();

        final JButton colorButton = new JButton();
        colorButton.setAction(theColorAction);
        toolBar.add(colorButton);

        for (final String key: myToolKitMap.keySet()) {
            final JToggleButton button = buildToggleButton(key);
            toolBar.add(button);
            buttonGroup.add(button);
        }

        return toolBar;
    }

    /**
     * Helper method for building the different RadioButtons required by the specifications.
     * name of button will be determined through the toString of the specified tool.
     * 
     * @param theMapKey The key to the ToolKitMap that will be used for generating the button.
     * 
     * @return Returns a functional Button mapped to the ToolAction given from the key.
     */
    private JRadioButtonMenuItem buildRadioButtonItem(final String theMapKey) {
        final JRadioButtonMenuItem button = new JRadioButtonMenuItem();
        button.setAction(myToolKitMap.get(theMapKey).getAction());
        button.setIcon(myToolKitMap.get(theMapKey).getIcon());
        return button;
    }

    /**
     * Helper method for building toggle buttons for each tool, these will be connected to the
     * RadioButtons in the menu of the GUI display.
     * @param theMapKey The key to the ToolKitMap that will be used for generating the button.
     * 
     * @return JToggleButton with the toString name of the Tool, The action for that tool, and
     * the Icon for that tool.
     */
    private JToggleButton buildToggleButton(final String theMapKey) {
        final JToggleButton button = new JToggleButton();
        button.setAction(myToolKitMap.get(theMapKey).getAction());
        button.setIcon(myToolKitMap.get(theMapKey).getIcon());
        return button;
    }

    /**
     * listener to check if the DrawingPanel object has signaled for the clear button to be 
     * disabled or enabled.
     * 
     * @param theEvent The event fired from the DrawingPanel.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("clearChange".equals(theEvent.getPropertyName())) {
            myClearItem.setEnabled((boolean) theEvent.getNewValue());
        }
        
    }



}
