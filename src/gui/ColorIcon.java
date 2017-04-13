/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * A square icon with a black outline and a fill color which can be
 * changed.
 * @author James Roberts
 * @version 23 November 2016
 *
 */
public final class ColorIcon implements Icon {
    
    /** The width of the Icon. */
    private static final int WIDTH = 15;
    /** The height of the Icon. */
    private static final int HEIGHT = 15;
    /** The Icon's fill color. */
    private Color myColor;
    
    /**
     * Constructor for a Color Icon, If the passed
     * Color is null, Icon is created with a white fill.
     * @param theColor the Icon's fill color. 
     */
    public ColorIcon(final Color theColor) {
        if (theColor == null) {
            myColor = Color.WHITE;
        } else {
            myColor = theColor;
        }
    }
    
    /**
     * Sets the Icon's fill color to the passed color. 
     * If passed color is null, fill color is set to white. 
     * @param theColor a new fill color for the Icon. 
     */
    public void setColor(final Color theColor) {
        if (theColor == null) {
            myColor = Color.WHITE;
        } else {
            myColor = theColor;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getIconHeight() {
        return HEIGHT;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public int getIconWidth() {
        return WIDTH;
    }
    
    /**
     * Creates a square Icon with a filled center and a black outline.
     * {@inheritDoc}
     */
    @Override
    public void paintIcon(final Component theComponent, final Graphics theGraphics, 
                          final int theX, final int theY) {
        final Graphics2D g2d = (Graphics2D) theGraphics.create();

        g2d.setColor(myColor);
        g2d.fillRect(theX - 2, theY, WIDTH, HEIGHT);
        g2d.setColor(Color.BLACK);
        g2d.drawRect(theX - 2, theY, WIDTH, HEIGHT);

        g2d.dispose();
        
    }

}
