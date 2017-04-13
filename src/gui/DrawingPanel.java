/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import tools.Tool;


/**
 * The DrawingPanel of the PowerPaint program. Allows different shapes to be
 * drawn via a tool, stores and displays a collection of the previously drawn shapes.
 * allows fill status, line color and fill color to be set and allows previously drawn
 * shapes to be cleared. 
 * @author James Roberts
 * @version 23 November 2016
 */
public final class DrawingPanel extends JPanel {
    
    /** The serialization ID. */
    private static final long serialVersionUID = 2302812257149047261L;
    /** Width of the Drawing Panel. */
    private static final int PANEL_WIDTH = 600;
    /** Height of the Drawing Panel. */
    private static final int PANEL_HEIGHT = 400;
    /** Default outline color of drawn shapes. */
    private static final Color DEFAULT_LINE_COLOR = new Color(51, 0, 111); //UW purple
    /** Default fill color of drawn shapes. */
    private static final Color DEFAULT_FILL_COLOR = new Color(232, 211, 162); //UW Gold
    /** Name of the property change event. */
    private static final String PROPERITY_NAME = "Existing Shapes";
    /** Line color of drawn shapes. */
    private Color myLineColor;
    /** Fill color of drawn shapes. */
    private Color myFillColor;
    /** If drawn shapes are filled. */ 
    private boolean myFillStatus;
    /** Thickness of drawn shape's outline. */
    private int myThickness;
    /** Tool used on drawing panel. */
    private Tool myTool;
    /** Collection of shapes that have been drawn. */
    private final List<DetailedShape> myShapes;

    /**
     * Constructor for the DrawingPanel.
     * @param theTool Tool for the DrawingPanel to use when created.
     */
    public DrawingPanel(final Tool theTool) {
        super();
        myLineColor = DEFAULT_LINE_COLOR;
        myFillColor = DEFAULT_FILL_COLOR;
        myFillStatus = false;
        myThickness = 1;
        myTool = theTool;
        myShapes = new ArrayList<DetailedShape>();
        setup();
    }
    
    /**
     * Sets up the initial state of the drawing panel.
     */
    private void setup() {
        setBackground(Color.WHITE);
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        final MyMouseInputAdapter adapter = new MyMouseInputAdapter();
        addMouseListener(adapter);
        addMouseMotionListener(adapter);
    }
    
    /**
     * Paints the shape being currently drawn and all previously drawn
     * shapes if they exist.
     * {@inheritDoc}
     */
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);      
        final Graphics2D g2d = (Graphics2D) theGraphics; 
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Draw previously drawn shapes
        for (final DetailedShape s : myShapes) {
            if (s.getThickness() > 0) { //Only draw shapes created with thickness > 0
                if (s.isFilled()) {
                    g2d.setPaint(s.getFillColor());
                    g2d.fill(s.getShape());
                }
            
                g2d.setPaint(s.getLineColor());
                g2d.setStroke(new BasicStroke(s.getThickness()));
                g2d.draw(s.getShape());
            }
        }
        
        //Only draw shapes with thickness > 0
        if (myThickness > 0) {
            //If fill is selected and shape being produced can be filled draw filled shape
            if (myFillStatus && myTool.canFill()) {
                g2d.setPaint(myFillColor);
                g2d.fill(myTool.getShape());
            }
        
            //Draw shape outline
            g2d.setPaint(myLineColor);
            g2d.setStroke(new BasicStroke(myThickness));
            g2d.draw(myTool.getShape());
        }
    }

    
    /**
     * Sets the tool of the drawing Panel.
     * @param theTool Drawing panel's tool.
     */
    public void setTool(final Tool theTool) {
        myTool = theTool;
    }
    
    /**
     * Sets the outline thickness that shapes are drawn with.
     * @param theThickness Thickness of shape's outlines.
     */
    public void setThickness(final int theThickness) {
        myThickness = theThickness;
    }
    
    /**
     * Sets the fill color of drawn shapes.
     * @param theFillColor Fill color of drawn shapes.
     */
    public void setFillColor(final Color theFillColor) {
        myFillColor = theFillColor;
    }
    
    /**
     * Sets the line color of drawn shapes.
     * @param theLineColor Line color of drawn shapes.
     */
    public void setLineColor(final Color theLineColor) {
        myLineColor = theLineColor;
        
    }
    
    /**
     * Sets if shapes are drawn as outlines or with a filled center.
     * @param theChoice If shapes should be filled.
     */
    public void setFillStatus(final boolean theChoice) {
        myFillStatus = theChoice;
    }
    
    /**
     * Clears all shapes currently on the DrawingPanel.
     */
    public void clear() {
        myShapes.clear();
        firePropertyChange(PROPERITY_NAME, null, "false");
        repaint();
    }
    
    /**
     * Mouse listener for the DrawingPanel. Starts a new shape using the current
     * tool when the mouse is pressed, updates the shape as the mouse is dragged
     * and puts the completed shape into a collection when the mouse is released.
     * @author James Roberts
     * @version 23 November 2016
     */
    private final class MyMouseInputAdapter extends MouseInputAdapter {
        
        /** 
         * Constructor for MyMouseInputAdapter.
         */
        MyMouseInputAdapter() {
            super();
        }
        
        /**
         * Prompts the current tool to create a new shape at given coordinate.
         * One corner of the shape will remain anchored at this point and the 
         * other will follow the mouse.
         */
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myTool.createShape(theEvent.getX(), theEvent.getY());
            repaint();
        }
        
        /**
         * Gets the completed shape from the tool object, creates a 
         * DetailedShape object using the current line and fill color,
         * fill status and thickness and adds it to the collection
         * of shapes.
         */
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            final DetailedShape finalShape = new DetailedShape(myLineColor, myFillColor, 
                    myFillStatus && myTool.canFill(), myThickness, myTool.getShape());
            myShapes.add(finalShape);
            firePropertyChange(PROPERITY_NAME, null, "true");
            //Set's tool's shape to one not visible on the drawing panel. 
            myTool.createShape(-100, -100);
        }
        
        /**
         * Passes the mouse's new coordinates to the current Tool so that
         * the shape being produced can be updated.
         */
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            myTool.updateShape(theEvent.getX(), theEvent.getY());
            repaint();

        }
    }


}
