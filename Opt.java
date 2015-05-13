
package zetrix;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JComboBox;
import javax.swing.*;
import zetrix.settings.Util;

public class Opt extends JFrame {
    
    public static JLabel MemT = new JLabel("Выделено памяти: ");
    String[] memset = new String[] {"512", "768", "1024", "2048", "4096"};
    private final JComboBox mem = new JComboBox(memset);
    
    public JCheckBox fscr = new JCheckBox("Full screen mode: ");
    
    public Opt () {
        super("Опции");
        setIconImage(Util.getRes("opt.png"));
//        this.setForeground(Color.RED);
        
        this.setSize(new Dimension(300, 100));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
        JPanel Main = new JPanel();
        
        JPanel mpane = new JPanel();
        mpane.add(MemT);
        mpane.add(mem);
        JPanel mpane2 = new JPanel();
        mpane2.add(fscr);
        
        Main.add(mpane, BorderLayout.NORTH);
        Main.add(mpane2, BorderLayout.SOUTH);
        
        this.getContentPane().add(Main, "Center");
    }
    
}
