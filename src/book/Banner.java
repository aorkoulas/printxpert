// 2019 - MTS Engineering
package book;

// Imports
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.print.*;

// @author A. Orkoulas
// This creates a banner that prints a string on multiple pages - a header or a logo or something of that nature :)
public class Banner implements Printable {
    // Declarations
    private String message;
    private double scale;
    
    // Constructor
    public Banner(String m) {
        message = m;
    }
    
    /* Gets page count of current section.
    @param g2 - Graphics context
    @param pf - Page format
    @return - The number of pages needed */
    public int getPageCount(Graphics2D g2, PageFormat pf) {
        if (message.equals(""))
            return 0;
        
        FontRenderContext context = g2.getFontRenderContext();
        Font f = new Font("Serif", Font.PLAIN, 72);
        
        Rectangle2D bounds = f.getStringBounds(message, context);
        scale = pf.getImageableHeight()  / bounds.getHeight();
        
        double width = scale * bounds.getWidth();
        int pages = (int) Math.ceil(width / pf.getImageableWidth());
        return pages;
    }
    
    // @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        Graphics2D g2 = (Graphics2D) g;
        
        if (page > getPageCount(g2, pf))
            return Printable.NO_SUCH_PAGE;
        
        g2.translate(pf.getImageableX(), pf.getImageableY());
        drawPage(g2, pf, page);
        return Printable.PAGE_EXISTS;
    }
    
    public void drawPage(Graphics2D g2, PageFormat pf, int page) {
        if (message.equals(""))
            return;
        page--; // This accounts for the cover page.
        
        drawCropMarks(g2, pf);
        
    }
    
    /* Draws half-inch crop marks in the corners of the page.
    @param g2 - The graphics context
    @param pf - The page format */
    public void drawCropMarks(Graphics2D g2, PageFormat pf) {
        // Declarations
        final double C = 36;
        double w = pf.getImageableWidth();
        double h = pf.getImageableHeight();
        
        g2.draw(new Line2D.Double(0, 0, 0, C));
        g2.draw(new Line2D.Double(0,0,C,0));
        g2.draw(new Line2D.Double(w, 0, w, C));
        g2.draw(new Line2D.Double(w, 0, w - C, 0));
        g2.draw(new Line2D.Double(0, h, 0, h - C));
        g2.draw(new Line2D.Double(0, h, C, h));
        g2.draw(new Line2D.Double(w, h, w, h - C));
        g2.draw(new Line2D.Double(w, h, w - C, h));
    }
}

class CoverPage implements Printable {
    // Declarations
    private String title;
    
    /* Constructor
    @param t - Title */
    public CoverPage(String t) {
        title = t;
    }
    
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page >= 1)
            return Printable.NO_SUCH_PAGE;
        
        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(Color.black);
        g2.translate(pf.getImageableX(), pf.getImageableY());
        
        FontRenderContext context = g2.getFontRenderContext();
        Font f = g2.getFont();
        TextLayout layout = new TextLayout(title, f, context);
        float ascent = layout.getAscent();
        g2.drawString(title, 0, ascent);
        return Printable.PAGE_EXISTS;
    }
}