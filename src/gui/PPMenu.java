/*
* TCSS 305 – Autumn 2016
* Assignment 5 – PowerPaint
*/
package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


/**
 * Menu Bar for the PowerPaint program, Includes File, Options, Tools and Help
 * sub menu's. 
 * @author James Roberts
 * @version 23 November 2016
 */
public final class PPMenu extends JMenuBar implements PropertyChangeListener {
    
    /** The serialization ID.*/
    private static final long serialVersionUID = -6406182444105301987L;
    /** The Menu's Tools sub-menu. */
    private final JMenu myToolsMenu;
    /** The Menu's clear button. */
    private final JMenuItem myClearBtn;
    /** A button group for the tool buttons. */
    private final ButtonGroup myBtnGrp;
    
    /**
     * Creates the PowerPaint menu bar.
     * @param theFrame The program's JFrame.
     * @param thePanel The program's drawing panel.
     * @param theImg The program's Icon.
     */
    public PPMenu(final JFrame theFrame, final DrawingPanel thePanel, final ImageIcon theImg) {
        super();
        myToolsMenu = new JMenu("Tools");
        myClearBtn = new JMenuItem("Clear", KeyEvent.VK_C);
        myBtnGrp = new ButtonGroup();
        setup(theFrame, thePanel, theImg);
    }
    
    /**
     * Creates and adds each sub-menu of the PowerPaint menu. Includes a File menu,
     * an Options menu, a Tools menu and a Help menu.
     * @param theFrame The program's JFrame.
     * @param thePanel The program's DrawingPanel.
     * @param theImage The program's Icon.
     */
    private void setup(final JFrame theFrame, final DrawingPanel thePanel, 
                       final ImageIcon theImage) {
        //Create and add the file menu
        add(createFileMenu(theFrame, thePanel));
        //Create and add the Options menu
        add(createOptionsMenu(thePanel));
        //Set mnemonic for tools menu and add
        myToolsMenu.setMnemonic(KeyEvent.VK_T);
        add(myToolsMenu);
        //Create and add's the help menu
        add(createHelpMenu(theImage));
    }
    
    /**
     * Creates the File menu aspect of the PowerPaint menu.
     * Includes a clear button and a quit button.
     * @param theFrame The program's JFrame.
     * @param thePanel The program's drawing panel.
     * @return The completed file menu.
     */
    private JMenu createFileMenu(final JFrame theFrame, final DrawingPanel thePanel) {
        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        
        //Add action listener to the already created clear button
        myClearBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                thePanel.clear();
            }
        });
        
        myClearBtn.setEnabled(false);
        fileMenu.add(myClearBtn); //Add clear button to File sub-menu
        fileMenu.addSeparator();
        
        //Create quit button
        final JMenuItem quitBtn = new JMenuItem("Quit", KeyEvent.VK_Q);
        quitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                theFrame.dispatchEvent(new WindowEvent(theFrame,
                                                       WindowEvent.WINDOW_CLOSING));
            }
        });
        fileMenu.add(quitBtn);
        
        return fileMenu;
    }
    
    /**
     * Creates the Options Menu which includes a slider that set's the DrawingPanel's
     * thickness, Draw and Fill buttons that set the DrawingPanel's line color and fill color 
     * and a fill check box which determines if the DrawingPanel draws filled shapes.
     * @param thePanel The program's DrawingPanel.
     * @return The Options Menu.
     */
    private JMenu createOptionsMenu(final DrawingPanel thePanel) {
        final JMenu optionsMenu = new JMenu("Options");
        optionsMenu.setMnemonic(KeyEvent.VK_O);
        //Create and add the thickness slider
        optionsMenu.add(createSlider(thePanel));
        optionsMenu.addSeparator();
        //Create and add the draw and fill buttons
        optionsMenu.add(createDrawBtn(thePanel));
        optionsMenu.add(createFillBtn(thePanel));
        optionsMenu.addSeparator();
        //Create and add the fill check box
        optionsMenu.add(createFillCheckBox(thePanel));
        
        return optionsMenu;
    }
    
    /**
     * Creates the options menu's thickness slider which controls
     * the thickness set on the DrawingPanel.
     * @param thePanel The program's DrawingPanel.
     * @return JMenu item that opens a JSlider.
     */
    private JMenu createSlider(final DrawingPanel thePanel) {
        final JMenu thicknessMenu = new JMenu("Thickness");
        thicknessMenu.setMnemonic(KeyEvent.VK_T);
        
        final JSlider thickSlide = new JSlider(SwingConstants.HORIZONTAL, 0, 20, 1);
        thickSlide.setMajorTickSpacing(5);
        thickSlide.setMinorTickSpacing(1);
        thickSlide.setPaintLabels(true);
        thickSlide.setPaintTicks(true);
        thickSlide.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(final ChangeEvent theEvent) {
                final int value = thickSlide.getValue();
                if (value >= 0) {
                    thePanel.setThickness(value);
                }
            }
        });
        thicknessMenu.add(thickSlide);
        return thicknessMenu;
    }
    
    /**
     * Creates a JMenuItem called "Draw Color..." which opens a JColorChooser
     * which allows the user to select the line color of shapes drawn on the
     * DrawingPanel. This JMenuItem has an Icon that changes color to show
     * what draw color is selected. 
     * @param thePanel The program's DrawingPanel.
     * @return The "Draw Color..." button.
     */
    private JMenuItem createDrawBtn(final DrawingPanel thePanel) {
        //Icon w/ default line color, UW purple.
        final ColorIcon icon = new ColorIcon(new Color(51, 0, 111));
        /** 
         * Local class to implement the Draw button's Action and color
         * changing Icon. 
         * @author James Roberts
         * @version 23 November 2016
         */
        final class DrawColorAction extends AbstractAction {
            /** The serialization ID. */
            private static final long serialVersionUID = 5541417100736283851L;

            /** Constructor for DrawColorAction object 
             * @param theName the button's label.  
             */
            DrawColorAction(final String theName) {
                super(theName);
                putValue(Action.SMALL_ICON, icon);
            }
            
            /**
             * Opens JColorChooser, if a valid color is selected sets the
             * DrawingPanel's line color and the Draw button's icon to the
             * chosen color.
             * @param theEvent the Action Event.
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, null, null);
                if (result != null) {
                    thePanel.setLineColor(result);
                    icon.setColor(result);
                }
            }
        }
        final JMenuItem drawBtn = new JMenuItem(new DrawColorAction("Draw Color..."));
        drawBtn.setMnemonic(KeyEvent.VK_D);
        return drawBtn;
    } 

    
    /**
     * Creates a JMenuItem called "Fill Color..." which opens a JColorChooser
     * which allows the user to select the fill color of shapes drawn on the
     * DrawingPanel. This JMenuItem has an Icon that changes color to show
     * what draw color is selected.
     * @param thePanel The program's DrawingPanel.
     * @return The "Fill Color..." button.
     */
    private JMenuItem createFillBtn(final DrawingPanel thePanel) {
        //Default Fill color, UW Gold
        final ColorIcon icon = new ColorIcon(new Color(232, 211, 162));
        /** 
         * Local class to implement the Fill button's Action and color
         * changing Icon. 
         * @author James Roberts
         * @version 23 November 2016
         */
        final class FillColorAction extends AbstractAction {
            /** The serialization ID. */
            private static final long serialVersionUID = 2691166934141780143L;

            /** Constructor for FillColorAction object 
             * @param theName the button's label.  
             */
            FillColorAction(final String theName) {
                super(theName);
                putValue(Action.SMALL_ICON, icon);
            }
            
            /**
             * Opens JColorChooser, if a valid color is selected sets the
             * DrawingPanel's fill color and the Fill button's icon to the
             * chosen color.
             * @param theEvent the Action Event.
             */
            @Override
            public void actionPerformed(final ActionEvent theEvent) {
                final Color result = JColorChooser.showDialog(null, null, null);
                if (result != null) {
                    thePanel.setFillColor(result);
                    icon.setColor(result);
                }
            }
        }
        final JMenuItem fillBtn = new JMenuItem(new FillColorAction("Fill Color..."));
        fillBtn.setMnemonic(KeyEvent.VK_F);
        return fillBtn;
    }
    
    /**
     * Creates the fill check box which will set the DrawingPanel
     * to draw filled shapes if selected and outlines only if not
     * selected.
     * @param thePanel The program's DrawingPanel.
     * @return The fill check box.
     */
    private JCheckBoxMenuItem createFillCheckBox(final DrawingPanel thePanel) {
        final JCheckBoxMenuItem fillCheckBox = new JCheckBoxMenuItem("Fill");
        fillCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                if (fillCheckBox.isSelected()) {
                    thePanel.setFillStatus(true);
                } else {
                    thePanel.setFillStatus(false);
                }
                           
            }
        });
        return fillCheckBox;
    }
    
    /**
     * Creates the Help menu which has an about section that
     * opens a JOptionPane displaying the program's information.
     * @param theImage The program's Icon.
     * @return The Help menu.
     */
    private JMenu createHelpMenu(final ImageIcon theImage) {
        final JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        final JMenuItem aboutBtn = new JMenuItem("About...", KeyEvent.VK_A);
        aboutBtn.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent theEvent) {
                JOptionPane.showMessageDialog(null, 
                                  "TCSS 305 PowerPaint\nAutumn 2016\nJames Roberts",
                                  "About", JOptionPane.PLAIN_MESSAGE, theImage);
            }
        });
        helpMenu.add(aboutBtn);
        return helpMenu;
    }
    
    /**
     * Creates a Tool button on the Tools Menu based on the passed action, adds
     * it to a button group so only one can be selected at a time.
     * @param theAction Passed ToolAction.
     */
    public void createToolButton(final Action theAction) {
        final JRadioButtonMenuItem button = new JRadioButtonMenuItem(theAction);
        myBtnGrp.add(button);
        myToolsMenu.add(button);
    }
    
    /**
     * Enables the Clear button if there are shapes on the DrawingPanel that
     * can be removed, disables it otherwise.
     */
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
       
        if ("Existing Shapes".equals(theEvent.getPropertyName())) {
            if ("true".equals(theEvent.getNewValue())) {
                myClearBtn.setEnabled(true);
            } else {
                myClearBtn.setEnabled(false);
            }

        }
        
    }
    
}

