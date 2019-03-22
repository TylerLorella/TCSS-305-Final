/*
 * TCSS 305 - Winter 2019
 * Assignment 5 - Paint Program
 */
package control;

import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.PaintGUI;

/**
 * This class provides the starting point for the Paint program that is capable of allowing
 * the user to draw several different shapes onto a JPanel.
 * 
 * @author Tyler Lorella
 * @version 1
 *
 */
public final class PaintMain {
    
    /**
     * Private constructor to prevent instantiation.
     */
    private PaintMain() {
        throw new IllegalStateException();
    }

    /**
     * The main method for launching the paint program with the metal look and feel UI Manger.
     * 
     * @param theArgs command line arguments which are ignored by this program.
     */
    public static void main(final String[] theArgs) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final ClassNotFoundException e) {
            e.printStackTrace();
        } catch (final InstantiationException e) {
            e.printStackTrace();
        } catch (final IllegalAccessException e) {
            e.printStackTrace();
        } catch (final UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new PaintGUI().start();
                

            }  
        });
    }
}
