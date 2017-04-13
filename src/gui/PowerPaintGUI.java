/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package gui;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import tools.EllipseTool;
import tools.LineTool;
import tools.PencilTool;
import tools.RectangleTool;
import tools.Tool;


/**
 * The GUI for the PowerPaint program. 
 * @author James Roberts
 * @version 23 November 2016
 */
public final class PowerPaintGUI {
    
    /** The program's DrawingPanel. */
    private final DrawingPanel myDrawingPanel;
    /** List of the ToolActions to be used by the program.*/
    private final List<ToolAction> myToolActions;
    
    /**
     * Constructor for the PowerPaint GUI.
     */
    public PowerPaintGUI() {
       
        myToolActions = new ArrayList<ToolAction>();
        createToolActions();
        //Create drawing panel with Tool of first ToolAction as it's starting tool.
        myDrawingPanel  = new DrawingPanel(myToolActions.get(0).getTool());
    }
    
    /**
     * Creates the ToolAction for each tool and adds it to the 
     * myToolActions list.
     */
    private void createToolActions() {
        final Tool lineTool = new LineTool();
        final Tool pencilTool = new PencilTool();
        final Tool rectTool = new RectangleTool();
        final Tool ellipTool = new EllipseTool();
        myToolActions.add(new ToolAction("Line", new ImageIcon("images/line.gif"), 
                          new ImageIcon("images/line_bw.gif"), lineTool));
        myToolActions.add(new ToolAction("Pencil", 
                          new ImageIcon("images/pencil.gif"),
                          new ImageIcon("images/pencil_bw.gif"), pencilTool));
        myToolActions.add(new ToolAction("Rectangle", 
                          new ImageIcon("images/rectangle.gif"), 
                          new ImageIcon("images/rectangle_bw.gif"), rectTool));
        myToolActions.add(new ToolAction("Ellipse", 
                          new ImageIcon("images/ellipse.gif"), 
                          new ImageIcon("images/ellipse_bw.gif"), ellipTool));
        myToolActions.get(0).selectIcon(); //First ToolAction will be selected 
                                           //when program starts.
    }
    
    /**
     * Creates the program's JFrame and sets it's state which includes a tool bar,
     * menu bar and a DrawingPanel which together make up the GUI functionality
     * of the program. 
     */
    public void start() {
        //Create frame
        final JFrame ppFrame = new JFrame("TCSS 305 - PowerPaint");
        ppFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        final ImageIcon img = new ImageIcon("images/programIcon.png");
        ppFrame.setIconImage(img.getImage());
        //Create and add menu bar.
        final PPMenu menuBar = new PPMenu(ppFrame, myDrawingPanel, img);
        //Allow menu bar to be aware of changes from DrawingPanel
        myDrawingPanel.addPropertyChangeListener(menuBar);
        //Create the menu bar's Tool buttons
        for (final ToolAction tool : myToolActions) {
            menuBar.createToolButton(tool);
        }
        ppFrame.setJMenuBar(menuBar);
        
        //Create and add the tool bar
        ppFrame.add(createToolBar(), BorderLayout.SOUTH);
        
        //add the DrawingPanel.
        ppFrame.add(myDrawingPanel);
        ppFrame.pack();
        ppFrame.setMinimumSize(ppFrame.getSize());
        ppFrame.setLocationRelativeTo(null);
        ppFrame.setVisible(true);
    }

    
    /**
     * Creates the PowerPaint program's ToolBar which includes a button for
     * each tool.
     * @return ToolBar for the program.
     */
    private JToolBar createToolBar() {
        final JToolBar toolBar = new JToolBar();
        final ButtonGroup btngrp = new ButtonGroup();
        for (final ToolAction tool : myToolActions) {
            final JToggleButton button = new JToggleButton(tool);
            btngrp.add(button);
            toolBar.add(button);
        }
        return toolBar;
    }

    
    /**
     * Creates an action for a Tool object using a passed name and Icon, sets
     * the DrawingPanel's tool to the action's tool object when the action is
     * performed. 
     * @author James Roberts
     * @version 23 November 2016
     *
     */
    private final class ToolAction extends AbstractAction {
        /** The serialization ID. */
        private static final long serialVersionUID = 8784271177695500761L;
        
        /** Tool object to be used on drawing panel. */
        private final Tool myTool;
        /** Icon to be used when ToolAction is selected. */
        private final Icon mySelectedIcon;
        /** Icon to be used when ToolAction is not selected. */
        private final Icon myDeselectedIcon;
 
        /**
         * Creates a new ToolAction.
         * @param theName Name of the tool.
         * @param theSelectedIcon The Icon to use when ToolAction is selected.
         * @param theDeselectedIcon The Icon to use when ToolAction is not selected.
         * @param theTool the Tool object.
         */
        ToolAction(final String theName, final Icon theSelectedIcon, 
                   final Icon theDeselectedIcon, final Tool theTool) {
           
            super(theName);
            mySelectedIcon = theSelectedIcon;
            myDeselectedIcon = theDeselectedIcon;
            myTool = theTool;
            putValue(Action.SMALL_ICON, theDeselectedIcon);
            putValue(Action.MNEMONIC_KEY,
                     KeyEvent.getExtendedKeyCodeForChar(theName.charAt(0)));
            putValue(Action.SELECTED_KEY, true);
        }
       
        /**
         * Return's the ToolAction's tool.
         * @return the Tool.
         */
        public Tool getTool() {
            return myTool;
        }
        
        /**
         * Changes the ToolAction's Icon to the selected version.
         */
        public void selectIcon() {
            putValue(Action.SMALL_ICON, mySelectedIcon);
        }
        
        /**
         * Changes the ToolAction's Icon to the deselected version.
         */
        public void deselectIcon() {
            putValue(Action.SMALL_ICON, myDeselectedIcon);
        }
        
        /**
         * Set's the DrawingPanel's Tool to myTool, Changes this ToolAction's
         * Icon to be selected and changes the other ToolAction's to be
         * deselected.  
         * @param theEvent the ActionEvent
         */
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myDrawingPanel.setTool(myTool);
            //Change this ToolActions Icon to the selected Icon and
            //all others to the Deselected Icons.
            for (final ToolAction t : myToolActions) {
                if (t == this) {
                    selectIcon();
                } else {
                    t.deselectIcon();
                }
            }

        }
    }
}
