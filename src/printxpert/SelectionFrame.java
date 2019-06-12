// 2019 - MTS Engineering
package printxpert;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

// @author A. Orkoulas
class SelectionFrame extends JFrame {
    // Declarations
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;
    private JCheckBox shape;
    private JCheckBox weight;
    private JCheckBox height;
    private JCheckBox color;
    private PrintComponent canvas;
    private JButton nextButton;
    private JPanel checkPanel;
    private JPanel buttonPanel;
    private JFrame selectionFrame;
    
    // Default constructor
    public SelectionFrame() {
        this.setVisible(true);
        canvas = new PrintComponent();
        add(canvas, BorderLayout.CENTER);
        
        // Create declared swing elements
        JFrame selectionFrame = new JFrame("Data Selection");
        selectionFrame.setPreferredSize(new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT));
        
        JCheckBox shape = new JCheckBox("Shape");
        JCheckBox weight = new JCheckBox("Weight");
        JCheckBox height = new JCheckBox("Height");
        JCheckBox color = new JCheckBox("Color");
        JButton nextButton = new JButton("Next");
    
        // Create check box and button panels
        JPanel checkPanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        // Assemble visual elements
        selectionFrame.add(checkPanel);
        selectionFrame.add(buttonPanel);
        buttonPanel.add(nextButton);
        checkPanel.add(shape);
        checkPanel.add(weight);
        checkPanel.add(height);
        checkPanel.add(color);
        
        /* TODO: Add handlers that transfers data to a modular, interactive preview
        based upon the user's check box input. This should reflect the same process
        going into EpicoreXpert's print module, except without all the LaTeX mathematical
        calculations. Instead, this test code will simply display data associated with each check
        box through further selection handling in PrintComponent. */
        
         nextButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 PrintTestFrame preview = new PrintTestFrame();
             }
          });
    
        add(checkPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
}