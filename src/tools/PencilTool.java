/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package tools;

import java.awt.Shape;
import java.awt.geom.GeneralPath;
import java.awt.geom.Path2D;
/**
 * Tool object representing a pencil. Starts with a single point where the
 * shape is created and line's to any point where updateShape is called.
 * @author James Roberts
 * @version 23 November 2016
 */
public class PencilTool extends AbstractTool {
    /** The path that makes up the pencil line. */
    private Path2D myShape;
    
    /** Constructor for the PencilTool object. 
     * Initially a single point at (-100, -100).
     */
    public PencilTool() {
        super();
        myShape = new GeneralPath();
    }
    
    /**
     * Shape is a single pixel at passed coordinates initially.
     * {@inheritDoc}
     */
    @Override
    public void createShape(final double theX, final double theY) {
        myShape = new GeneralPath();
        myShape.moveTo(theX, theY);
    }
    
    /**
     * Has the Pencil's path line to the passed coordinates.
     * {@inheritDoc}
     */
    @Override
    public void updateShape(final double theEndX, final double theEndY) {
        myShape.lineTo(theEndX, theEndY);
    }
    
    /**
     * {@inheritDoc}
     * @return Path object representing the pencil's path. 
     */
    @Override
    public Shape getShape() {
        final Path2D copy = new GeneralPath();
        copy.append(myShape, false);
        return copy;
    }
    
    /**
     * {@inheritDoc}
     * Pencil lines cannot be filled.
     */
    @Override
    public boolean canFill() {
        return false;
    }

}
