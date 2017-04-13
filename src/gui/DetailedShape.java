/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package gui;

import java.awt.Color;
import java.awt.Shape;

/**
 * Representation of a shape object that includes a fill color, line color,
 * if the shape is to be drawn filled and a stroke thickness. 
 * @author James Roberts
 * @version 23 November 2016
 */
public final class DetailedShape {
    /** The line color of the Shape. */
    private final Color myLineColor;
    /** The fill color of the Shape. */
    private final Color myFillColor;
    /** If the Shape is filled. */
    private final boolean myFillStatus;
    /** The thickness of the shape's outline. */
    private final int myThickness;
    /** The type of shape. */
    private final Shape myShape;
    
    /** Creates a new DetailedShape object.
     * 
     * @param theLineColor The line color.
     * @param theFillColor The fill color.
     * @param theFillStatus If the shape is filled.
     * @param theThickness The thickness of the shape's outline.
     * @param theShape The type of shape.
     */
    public DetailedShape(final Color theLineColor, final Color theFillColor, 
                         final boolean theFillStatus, final int theThickness, 
                         final Shape theShape) {
        myShape = theShape;
        myLineColor = theLineColor;
        myFillColor = theFillColor;
        myFillStatus = theFillStatus;
        myThickness = theThickness;
                
    }
    
    /**
     * Returns the line color of the shape.
     * @return the line color (Color Object).
     */
    public Color getLineColor() {
        return myLineColor;
    }
    
    /**
     * Returns the fill color of the shape.
     * @return the fill color (Color Object).
     */
    public Color getFillColor() {
        return myFillColor;
    }
    
    /**
     * Returns if the shape is filled T/F.
     * @return T/F if shape is filled.
     */
    public boolean isFilled() {
        return myFillStatus;
    }
    
    /** 
     * Returns the thickness of the shape's outline.
     * @return The outline's thickness.
     */
    public int getThickness() {
        return myThickness;
    }
    
    /**
     * Returns the shape object.
     * @return The shape (Shape Object)
     */
    public Shape getShape() {
        return myShape;
    }

}
