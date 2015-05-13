
package zetrix.gui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LgPane extends JPanel {
    private Image lgIm;
       private Image finalIm;
 // public final static Color white = new Color(255, 255, 255, 0);
       private Image imgfn; 
    public LgPane () {
        setOpaque(true);
         
        try {
        BufferedImage im = ImageIO.read(this.getClass().getResource("/zetrix/res/logo.png"));
//        imgfn = getTransparentImage((Image)ImageIO.read(new File("/zetrix/res1/logo.png")), Color.white);
//        lgIm = imgfn;
        int w = im.getWidth();
        int h = im.getHeight();
        lgIm = im.getScaledInstance(370, 75, 16);
        setPreferredSize(new Dimension(370, 75));
        } catch (Exception ex) {}
        
        setCursor(Cursor.getPredefinedCursor(12));
        addMouseListener(new MouseAdapter() {
       
            public void mousePressed(MouseEvent arg0) {
                try {
                    openUrl(new URL("http://test.ru/").toURI());
                } catch (MalformedURLException | URISyntaxException e) {
                    System.out.println(e.toString());
                }
            }
        });
    }
        
     @Override
    public void update (Graphics g) {
         paint(g);
    }
    
    @Override
    public void paintComponent (Graphics g2) {
        int w = getWidth();
        int h = getHeight();
        
//        if ((finalIm == null)  || (finalIm.getWidth(null) != w) || (finalIm.getHeight(null) != h)) {
            finalIm = createImage(w, h);
            Graphics g = finalIm.getGraphics();
             g.drawImage(lgIm, 0, 0, null);
//            for (int x = 0; x <= w/800; x++) {
//            for (int y = 0; y <= h/800; y++)
//                
//                g.drawImage(lgIm, 0, 0, null);
//        }
            g.dispose();
//        }
        g2.drawImage(lgIm, 0, 0, w, h, null);
    }            
    
// public class ColorFilter extends RGBImageFilter
//{
//    private Color color;
// 
//    public ColorFilter(Color color)
//    {
//        super();
//        this.color = color;
//    }
// 
//    public int filterRGB(int x, int y, int rgb)
//    {
//        if ((rgb | 0xFF000000) == (color.getRGB() | 0xFF000000))
//        {
//            return 0x00FFFFFF & rgb;
//        }
//        else
//        {
//            return rgb;
//        }
//    }
//}
// 
////вспомогательный метод
//private Image getTransparentImage(Image image, Color color)
//    {
//        return Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(image.getSource(), new ColorFilter(color)));}
//// imgfn = getTransparentImage((Image)ImageIO.read(new File("E:\\image.png")), Color.white);
 
    public static void openUrl (URI Link) {
        try {
            Object obj = Class.forName("java.awt.Desktop").getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            obj.getClass().getMethod("browse", new Class[] {URI.class}).invoke(obj, new Object[] { Link });
        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
           System.out.println(ex.toString());
        }
        
    }
    

}
