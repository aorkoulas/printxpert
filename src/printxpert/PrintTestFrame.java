// 2019 - MTS Engineering
package printxpert;

// Imports
import book.PrintPreview;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import javax.print.attribute.*;
import javax.swing.*;
import javax.swing.table.TableModel;

 // @author A. Orkoulas
// This frame displays a panel with graphics constructed from data drawn from elsewhere. Buttons are set to print the graphics and set up the formatting of the image.
public class PrintTestFrame extends JFrame {
    private PrintComponent canvas;
    private PrintRequestAttributeSet attributes;
    private PrintPreview preview;
    private TableModel tableInfo;
    
    public PrintTestFrame() {
        this.setVisible(true);
        this.setPreferredSize(new Dimension(600, 750));
        canvas = new PrintComponent();
        add(canvas, BorderLayout.CENTER);
        
        attributes = new HashPrintRequestAttributeSet();
        
        JPanel buttonPanel = new JPanel();
        JPanel infoPanel = new JPanel();
        
        JButton printButton = new JButton("Print");
        
        buttonPanel.add(printButton);
        
        // Add the actual visual preview here.
        
        
        // Open the print GUI.
        printButton.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                try {
                PrinterJob job = PrinterJob.getPrinterJob();
                job.setPrintable(canvas);
                
                if (job.printDialog(attributes))
                    job.print(attributes); 
                } catch (PrinterException ex) {
                    JOptionPane.showMessageDialog(PrintTestFrame.this, ex);
                }
            }
});
        
        // Open the page format GUI.
         JButton pageSetupButton = new JButton("Page Setup");
         buttonPanel.add(pageSetupButton); 
         
         pageSetupButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 PrinterJob job = PrinterJob.getPrinterJob();
                 job.pageDialog(attributes);
             }
          });
         
         
         add(buttonPanel, BorderLayout.SOUTH);
         add(infoPanel, BorderLayout.NORTH);
         pack();
         
      }
}