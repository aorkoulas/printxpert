//  2019 - MTS Engineering
package book;

// Imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.awt.print.Book;
import java.awt.print.PrinterJob;
import javax.print.attribute.*;
import javax.swing.*;

// @author A. Orkoulas
// This frame has a text field for the banner text (see Banner.java) and buttons for printing, page setup, and print preview.
public class BookTestFrame extends JFrame {
    private JTextField text;
    private PageFormat pageFormat;
    private PrintRequestAttributeSet attributes;
    private Book book;
    
    // Constructor
    public BookTestFrame() {
         JTextField text = new JTextField();
         add(text, BorderLayout.NORTH);
         attributes = new HashPrintRequestAttributeSet();
        
         // Sets up PRINT button and functionality.
         JPanel buttonPanel = new JPanel();
         JButton printButton = new JButton("Print");
        
         buttonPanel.add(printButton);
        
         printButton.addActionListener(new ActionListener() { 
             public void actionPerformed(ActionEvent e) { 
                  try {
                      PrinterJob job = PrinterJob.getPrinterJob();
                      job.setPageable(makeBook());
                      
                      if (job.printDialog(attributes)) {
                          job.print(attributes);
                      }
                  } catch (PrinterException ex) {
                      JOptionPane.showMessageDialog(BookTestFrame.this, e);
                  }
             }
         });
         
         // Sets up SETUP PAGE button and functionality.
         JButton pageSetupButton = new JButton("Setup Page");
         buttonPanel.add(pageSetupButton);
         
         pageSetupButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
             PrinterJob job = PrinterJob.getPrinterJob();
             pageFormat = job.pageDialog(attributes);
             }
         });
         
         // Sets up PRINT PREVIEW button and functionality.
         JButton printPreviewButton = new JButton("Print Preview");
         buttonPanel.add(printPreviewButton);
       
         pageSetupButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 PrintPreviewDialog dialog = new PrintPreviewDialog(makeBook());
                 dialog.setVisible(true);
             }
         });
         
     // Makes a book.
    }
    
public Book makeBook() {
            // Declarations
            String textData;
            String message = "MTS Engineering 2019";
   
             if (pageFormat == null) {
                 PrinterJob job = PrinterJob.getPrinterJob();
                 pageFormat = job.defaultPage();
             }
             
              // Dump data into book for printing.
              JComponent ListComps[] = {};
              pageFormat.setOrientation(PageFormat.LANDSCAPE);
              
              int tpages = 0;
              int pageRender[]  = {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
              int pageRenderNum[] ={ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};

              // Insert switch statement that performs operations based upon whichever check boxes are selected in the print preview.

             Book book = new Book();
            Banner banner = new Banner(message);
             
             int pageCount = banner.getPageCount((Graphics2D) getGraphics(), pageFormat);
             book.append(new CoverPage(message + " (" + pageCount + " pages)"), pageFormat);
             book.append(banner, pageFormat, pageCount);
             
             return book;
         }
}
