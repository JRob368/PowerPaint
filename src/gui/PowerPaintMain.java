/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package gui;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * Driver for the PowerPaint program. 
 * @author James Roberts
 * @version 23 November 2016
 */
public final class PowerPaintMain {
    
    /**
     * Private constructor to prevent instantiation. 
     */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }
    
    /**
     * Invokes the PowerPaint GUI. Does not use
     * the command line arguments.
     * @param theArgs Command line arguments.
     */
    public static void main(final String[] theArgs) {
        setLookAndFeel();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PowerPaintGUI().start();
            }
        });
    }
    
    /**
     * Sets the look and feel of the GUI.
     */
    private static void setLookAndFeel() {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            
        } catch (final ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}
