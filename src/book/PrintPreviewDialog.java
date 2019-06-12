// 2019 - MTS Engineering
package book;

// Imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import javax.swing.*;

// @author A. Orkoulas
// PrintPreviewDialog implements a generic print preview dialog.
class PrintPreviewDialog extends JDialog {
    // Field delcarations
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;
    PrintPreviewCanvas canvas;
    
    // Parameter Constructor - Add parameter definitions later.
    public PrintPreviewDialog(Printable p, PageFormat pf, int pages) {
        Book book = new Book();
        book.append(p, pf, pages);
        layoutUI(book);
    }
    
    /* Constructs a print preview dialog.
    @param b - A book. */
    public PrintPreviewDialog(Book b) {
        layoutUI(b);
    }
    
    /* TODO: Implement modular page print preview. */
    
    /* Lays out the dialog interface.
    @param book - The book(s?) to be previewed. */
    public void layoutUI(Book book) {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        
        canvas = new PrintPreviewCanvas(book);
        add(canvas, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        
        // Button to view next page
        JButton nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.flipPage(1);
            }
        });
        
        // Button to view previous page
        JButton previousButton = new JButton("Previous");
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                canvas.flipPage(-1);
            }
        });
        
        // Button to close
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
