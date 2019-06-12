// 2019 - MTS Engineering
package book;

// Imports
import java.awt.*;
import java.awt.geom.*;
import java.awt.print.*;
import javax.swing.*;

// @author A. Orkoulas
// This class serves as the canvas for displaying the print preview.
public class PrintPreviewCanvas extends JComponent {
    // Declarations
    private Book book;
    private int currentPage;
    
    /*Constructor
    @param b - The book being previewed. */
    public PrintPreviewCanvas(Book b) {
        book  = b;
       currentPage = 0;
    }
    
    public void paintComponent(Graphics g) {
        // Declarations
        Graphics2D g2 = (Graphics2D) g;
        PageFormat pageFormat = book.getPageFormat(currentPage);
        
        double xoff; // X OFFSET of page start in window.
        double yoff; // Y OFFSET of page start in window.
        double scale; // Scale factor for fitting the page in the window.
        double px = pageFormat.getWidth();
        double py = pageFormat.getHeight();
        double sx = getWidth() - 1;
        double sy = getHeight() - 1;
        if (px / py < sx / sy) {  // Center horizontally
            scale = sy / py;
            xoff = 0.5 * (sx - scale * px);
            yoff = 0;
        } else { // Center vertically
            scale = sx / px;
            xoff = 0;
            yoff = 0.5 * (sy - scale * py);
        }
        
        g2.translate((float) xoff, (float) yoff);
        g2.scale((float) scale, (float) scale);
        
        // Draws page outline. Ignores margins.
        Rectangle2D page = new Rectangle2D.Double(0, 0, px, py);
        g2.setPaint(Color.white);
        g2.fill(page);
        g2.setPaint(Color.black);
        g2.draw(page);
        
        Printable printable = book.getPrintable(currentPage);
        
        try {
            printable.print(g2, pageFormat, currentPage);
        } catch (PrinterException e) {
            g2.draw(new Line2D.Double(0, 0, px, py));
            g2.draw(new Line2D.Double(px, 0, 0, py));
        }
    }
    
    /* Flips book by given amount of pages.
    @param by - The number of pages to flip. Negative values flip backward. */
    public void flipPage(int by) {
        int newPage = currentPage + by;
        if (0 <= newPage && newPage < book.getNumberOfPages()) {
            currentPage = newPage;
            repaint();
        }
    }
}