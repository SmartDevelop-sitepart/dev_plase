
package zetrix;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.util.logging.Level;
import java.util.logging.Logger;
 import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
/**
 *
 * @author Wall
 */
public class LoginWindow extends JFrame implements Runnable {

 int log = 0;
private  JFrame frame;
private  JTextArea textArea;
public  Boolean debug = false;
private static String t;
private static String x;
  public  LoginWindow(){
        
        final JFrame frame = new JFrame ("Debug window");
        frame.setSize(200,300);
        frame.setResizable(false);
//

//TEXT AREA
      final JTextArea textArea = new JTextArea();
      textArea.setSize(400,400);    

    textArea.setLineWrap(true);
    textArea.setEditable(false);
    textArea.setVisible(true);

    JScrollPane scroll = new JScrollPane (textArea);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
          scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

    frame.add(scroll);
    frame.setVisible(true);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     textArea.append("{"+log+"} "+"start"+"\n \n");
        frame.repaint();
        log +=1;
       
    debug=true;
    
    textArea.append("{"+log+"} "+"start123"+"\n \n");
        frame.repaint();
        log +=1;
        
            
        textArea.append("{"+log+"} "+"double start"+"\n \n");
        frame.repaint();
        log +=1;
        t="123";
        x="124";
        while (true){
            
        if (x.hashCode()!=t.hashCode()) {
            textArea.append("{"+log+"} "+t+"\n \n");
            frame.repaint();
            log +=1;
            t=x;
        }
        
        }
           
       
       
       
}
    public static void main(String[] args) {
             // Thread thread2 = new Thread(new LoginWindow());
            //  thread2.start();
        LoginWindow loginWindow = new LoginWindow();
    }

    @Override
    public void run() {
        
    }
   
    public static void log(){
        t="1233";
    }
}
