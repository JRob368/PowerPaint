/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Shape;

/**
 * An object representing a tool that can create and update a shape
 * based on passed x and y coordinates. 
 * @author James Roberts
 * @version 23 November 2016
 */
public abstract class AbstractTool implements Tool {
    /** Starting coordinate for shape, not visible on a DrawingPanel. */
    private static final int STARTING_COORD = -100;
    /** Starting X coordinate of the shape. */
    private double myStartingX;
    /** Starting Y coordinate of the shape. */
    private double myStartingY; 
    
    /**
     * Constructor for AbstractTool object. Sets x and y
     * coordinates to location where shape won't be visable 
     * on a DrawingPanel
     */
    protected AbstractTool() {
        myStartingX = STARTING_COORD;
        myStartingY = STARTING_COORD;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void createShape(final double theX, final double theY) {
        myStartingX = theX;
        myStartingY = theY;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract void updateShape(final double theEndX, final double theEndY);
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract Shape getShape();
    
    /**
     * {@inheritDoc}
     */
    @Override
    public abstract boolean canFill();
    
    /**
     * Returns the starting x coordinate of the shape produced by the tool.
     * @return initial x coordinate of the shape.
     */
    protected double getStartingX() {
        return myStartingX;
    }
    
    /**
     * Returns the starting y coordinate of the shape produced by the tool.
     * @return initial y coordinate of the shape.
     */
    protected double getStartingY() {
        return myStartingY;
    }
    


}
