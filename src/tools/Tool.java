/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Shape;
/**
 * An interface for objects that can create a shape and update it based on
 * a passed coordinate. 
 * @author James Roberts
 * @version 23 November 2016
 */
public interface Tool {
    /**
     * Creates a new shape with it's starting point at the passed
     * x,y coordinate.
     * @param theX X coordinate.
     * @param theY Y coordinate
     */
    void createShape(double theX, double theY);
    
    /**
     * Updates the state of the Shape based on the passed coordinate.
     * @param theX X coordinate.
     * @param theY Y coordinate.
     */
    void updateShape(double theX, double theY);
    /** 
     * Returns the shape produced by the tool.
     * @return shape produced by the tool.
     */
    Shape getShape();
    /**
     * Returns if the shape produced by the tool can be filled.
     * @return if produced shaped can be filled.
     */
    boolean canFill();
}
