
package zetrix.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BgPane extends JPanel {
    private Image finalIm;
    private Image bgIm;
    
    public BgPane() {
        setOpaque(true);
        try {
            bgIm = ImageIO.read(this.getClass().getResource("/zetrix/res/bg.png")).getScaledInstance(400, 400, 1);
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    @Override
    public void update (Graphics g) {
         paint(g);
    }
    
    @Override
    public void paintComponent (Graphics g2) {
        int w = getWidth();
        int h = getHeight();
        
        if ((finalIm == null)  || (finalIm.getWidth(null) != w) || (finalIm.getHeight(null) != h)) {
            finalIm = createImage(w, h);
            Graphics g = finalIm.getGraphics();
            for (int x = 0; x <= w/400; x++) {
            for (int y = 0; y <= h/400; y++)
                g.drawImage(bgIm, 0, 0, null);
        }
            g.dispose();
        }
        g2.drawImage(finalIm, 0, 0, w, h, null);
    }
            
}
