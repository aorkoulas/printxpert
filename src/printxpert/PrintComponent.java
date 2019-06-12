//  2019 - MTS Engineering
/* NOTE: The following results can be achieved through constructing previews using the display tool in NetBeans.
Don't be afraid to do so when this print code is implemented into EpicoreXpert. */

package printxpert;

// Imports
import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import javax.swing.*;
import javax.swing.table.JTableHeader;

// @author A. Orkoulas
// PrintComponent creates a 2D graphic for displaying and printing.
public class PrintComponent extends JComponent implements Printable {
    private static final Dimension PREFERRED_SIZE = new Dimension(300,300);
    private Graphics2D g2D;
    
    private Object tableData[][];
    private String[] columns;
    private JTable table;
    private TablePanel getTable;
    private Image tableImage;

    
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        drawPage(g2);
    }
    
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException {
        if (page >= 1)
            return Printable.NO_SUCH_PAGE;
        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pf.getImageableX(), pf.getImageableY());
        g2.draw(new Rectangle2D.Double(0, 0, pf.getImageableWidth(), pf.getImageableHeight()));
        
        drawPage(g2);
        return Printable.PAGE_EXISTS;
    }
    
    /* This method draws the page on the screen and on the printer graphics context.
    @param g2 - the graphics context */
    public void drawPage(Graphics2D g2) {
        FontRenderContext context = g2.getFontRenderContext();
        Font font = new Font("Serif", Font.PLAIN, 72);
        GeneralPath clipShape = new GeneralPath();
        tableImage = createImage(table);
        
        
        TextLayout layout = new TextLayout(">:3", font, context);
        AffineTransform transform = AffineTransform.getTranslateInstance(200, 100);
        Shape outline = layout.getOutline(transform);
        clipShape.append(outline, false);
        
        g2.draw(clipShape);
        g2.clip(clipShape);
        
        g2.drawImage(tableImage, 0, 0, null);
        
        final int NLINES = 50;
        Point2D p = new Point2D.Double(0,0);
        
        for (int i = 0; i < NLINES; i++) {
            double x = (2 * getWidth() * i) / NLINES;
            double y = (2* getHeight() * (NLINES -1 - i)) / NLINES;
            Point2D q = new Point2D.Double(x, y);
            g2.draw(new Line2D.Double(p, q));
        }
    }
    
        // Draws image of a table from table data
    public Image createImage(JTable table) {
        tableData = getTable.getTableData(); // gets jTable1 data from EpicoreJFrame
        String[] columns = {"A", "B", "C", "D"};
        table = new JTable(tableData, columns);
        JTableHeader tableHeaderComp = table.getTableHeader();
        int totalWidth = tableHeaderComp.getWidth() + table.getWidth();
        int totalHeight = tableHeaderComp.getHeight() + table.getHeight();
        tableImage = new BufferedImage(totalWidth, totalHeight,
        BufferedImage.TYPE_INT_RGB);
        Graphics2D g2D = (Graphics2D) tableImage.getGraphics();
        tableHeaderComp.paint(g2D);
        g2D.translate(0, tableHeaderComp.getHeight());
        table.paint(g2D);
        return tableImage;
    }
    
    public Dimension getPreferredSize() {
        return PREFERRED_SIZE;
    }
    
    
}
