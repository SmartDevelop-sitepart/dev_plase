
package zetrix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import zetrix.gui.BgPane;
import zetrix.gui.LgPane;

import zetrix.settings.GuardUtil;
import zetrix.settings.Util;


public class Main extends JFrame {
  
    public static Main mgui;
    public BgPane Bg = new BgPane();
    public LgPane Logo = new LgPane();
   
    public static JPanel empt; 
    public static JPanel empt1;
    public static JPanel BGPane; 
    public static JPanel MainPane; 
    public static JPanel UPDPane; 
    public static Box MBox; 
    public static String[] AuthData = null;
    public static boolean Version = false;
    public static boolean AU = false;
    public static boolean PWD = false;
    public static boolean CH = false;
    public static JLabel UpdText;
    public static JProgressBar UpdBar;
   // public static String GameDirectory;
     public final static Color white = new Color(255, 255, 255, 0);
    public static JLabel LgLabel = new JLabel("Логин: ");
    public JTextField LgFld = new JTextField(20);
    public static JLabel PassLabel = new JLabel("Пароль: ");
    public static JLabel emptL = new JLabel("123: ");
    public JPasswordField PassFld = new JPasswordField(20);
    public JButton lgbut = new JButton("Войти", (new ImageIcon (Util.getRes("go.png")))); 
//    public JButton Options = new JButton("Опции", (Icon) Util.getRes("fav.png")); 
    public JButton Options = new JButton("Опции", (new ImageIcon (Util.getRes("opt.png"))));         
    
    
    
    public Main(){
        setTitle("Test");
        setIconImage(Util.getRes("fav.png"));
        this.setSize(new Dimension(400, 400));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
         

        empt = new JPanel();
        empt1 = new JPanel();
        MainPane = new JPanel();
        BGPane = new JPanel();
        
        Box emptb = Box.createHorizontalBox();
        Box emptb1 = Box.createHorizontalBox();
        Box loginb = Box.createHorizontalBox();
        loginb.add(LgLabel);
        loginb.add(Box.createVerticalStrut(8));
        loginb.add(LgFld);
        Box passb = Box.createHorizontalBox();
        passb.add(PassLabel);
        passb.add(Box.createVerticalStrut(8));
        passb.add(PassFld);
        Box butb = Box.createHorizontalBox();
         butb.add(Box.createVerticalStrut(8));
        butb.add(Options);
        butb.add(Box.createVerticalStrut(8));
        butb.add(lgbut);
         butb.add(Box.createVerticalStrut(8));
        LgLabel.setPreferredSize(PassLabel.getPreferredSize());
        MBox = Box.createVerticalBox();
        MBox.setBorder(new EmptyBorder(10, 10, 10, 10));
        MBox.add(loginb);
        MBox.add(Box.createVerticalStrut(8));
        MBox.add(passb);
        MBox.add(Box.createVerticalStrut(8));
        MBox.add(butb);
//        empt.setBorder(new EmptyBorder(10, 10, 10, 10));
//        empt.add(emptL);
        empt.add(emptb);
        empt.add(Box.createVerticalStrut(20));
        empt.add(Box.createHorizontalStrut(80));
        empt.setBackground(white);
        empt1.add(emptb1);
        empt1.add(Box.createVerticalStrut(80));
        empt1.add(Box.createHorizontalStrut(80));
        empt1.setBackground(white);
//        MBox.add(Options);
//        MBox.add(Box.createVerticalStrut(8));
//        MBox.add(lgbut);
        MainPane.add(MBox);
//        MBox.setBackground(white);
//        MBox.setForeground(Color.red);
//        Bg.setBackground(white);
//        Logo.setBackground(white);
//        BGPane.setBackground(white);
//        Bg.add(empt1, BorderLayout.CENTER);
        Bg.add(Logo, BorderLayout.NORTH);
        BGPane.add(MainPane);
        Bg.add(empt, BorderLayout.CENTER);
        Bg.add(BGPane, BorderLayout.SOUTH);
       
        this.getContentPane().add(Bg);
        
        lgbut.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                if ((!LgFld.getText().equals("")) && (!new String(PassFld.getPassword()).equals(""))) {
                    if (Authorize(LgFld.getText(), new String(PassFld.getPassword()))) {
                        if(CH) {
                            JOptionPane.showMessageDialog ((Component)
                        null, 
                       "Вы авторизированны!\n\nЛогин: " + AuthData[1],
                                    "Ура!",
                        JOptionPane.INFORMATION_MESSAGE);
//                       JOptionPane.showMessageDialog ((Component)
//                        null, 
//                       "Мы вошли!\n\nЛогин: " + AuthData[1] +
//                                "\nПароль: " + AuthData[0] +
//                                "\nСессия: " + AuthData[2] +
//                               "\nhash: " + AuthData[3] + 
//                               "\nsize: " + AuthData[4],
//                                    "Ура!",
//                        JOptionPane.INFORMATION_MESSAGE);
//                        BGPane.removeAll();
//                        BGPane.add(UPDPane());
//                        BGPane.validate();
//                        BGPane.repaint();
                       if (GuardUtil.CheckUpd()) {
                   JOptionPane.showMessageDialog((Component)
                        null,
                        "ОК.",
                        "Статус",
                        JOptionPane.INFORMATION_MESSAGE);
                             mgui = new Main();
                             mgui.setVisible(false);
                             
//                            mgui.hide();
//                           Starter.main(AuthData);
                       } else {
//                        BGPane.removeAll();
//                        BGPane.add(UPDPane());
//                        BGPane.validate();
//                        BGPane.repaint();
//                        Starter.main(AuthData);
                         JOptionPane.showMessageDialog((Component)
                        null,
                        "неОК.\n\nБуду обновлятся...",
                        "Статус",
                        JOptionPane.INFORMATION_MESSAGE);
                        BGPane.removeAll();
                        BGPane.add(UPDPane());
                        BGPane.validate();
                        BGPane.repaint();
//                        mgui.setVisible(false);
                        Starter.main(AuthData);
                       }
                        }
                    }
                     else if (CH) {
                    JOptionPane.showMessageDialog((Component)
                        null,
                        "Нет соединения с сервером.",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
                    }  
                   else if (PWD) {
                    JOptionPane.showMessageDialog((Component)
                        null,
                        "Неправильный пароль.",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
                    }  
                   else if (Version) {
                    JOptionPane.showMessageDialog((Component)
                        null,
                        "Лаунчер устарел.",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
                    }
                   else if (AU) {
                    JOptionPane.showMessageDialog((Component)
                        null,
                        "Вы ввели неправильный логин.",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
                    } 
                    else {
                        JOptionPane.showMessageDialog((Component)
                        null,
                        "Неведомая ошибка.",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
                   }
                } else {
                        JOptionPane.showMessageDialog((Component)
                        null,
                        "Вы не ввлели логин или пароль.",
                        "Ошибка",
                        JOptionPane.WARNING_MESSAGE);
                    
                    }
                
            }
        });
        Options.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LoginWindow.log();
                new Opt().setVisible(true);
            }
        });
    } 

    public JPanel UPDPane() {
         JPanel UPane = new JPanel();
         
         UpdText = new JLabel();
         UpdText.setText("");
         
         UpdBar = new JProgressBar();
         UpdBar.setStringPainted(true);
         UpdBar.setMinimum(0);
         UpdBar.setMaximum(100);
         UpdBar.setValue(0);
         UpdBar.setPreferredSize(new Dimension(300, 50));
         
         Box ubox1 = Box.createHorizontalBox();
         ubox1.add(UpdText);
         ubox1.add(Box.createHorizontalStrut(10));
         Box ubox2 = Box.createHorizontalBox();
         ubox2.add(UpdBar);
         ubox1.add(Box.createHorizontalStrut(10));
         
         ubox1.add(Box.createHorizontalStrut(10));
         Box Ubox = Box.createVerticalBox();
         Ubox.setBorder(new EmptyBorder (10, 10, 10, 10));
         Ubox.add(ubox1);
         Ubox.add(Box.createVerticalStrut(15));
         Ubox.add(ubox2);
         Ubox.add(Box.createVerticalStrut(5));
         
         
         UPane.add(Ubox);
         
         
         GetUpdate();
        
        return UPane;      
    }
    
    public static boolean Authorize(final String user, final String pass) {
        String AuthResult = Util.Connect("http://test.ru/web/new.php", "a=auth" + "&user=" + user + "&password=" + GuardUtil.ShaHash(pass));
       if (AuthResult == null) {
           CH = true;
           return false;
       }
        AuthData = AuthResult.split("<>");
        switch (AuthResult.trim()) {
            case "":
                return false; 
            case "bad login":
                AU = true;
                PWD = false;
                return false;
            case "bad pwd":
                 AU = false;
                PWD = true;
                return false;
            case "error":
                
                return false;
            case "WV":
                Version = true;
                return false;
            default:
                try {
                    CH = true;
                   AuthData = AuthResult.split("<>");
                    return true;
                } catch (Exception ex) {
                    return false;
                }
        }
    }
    
    public static void GetUpdate() {
        new Thread() {
            public void run() {
                try {
                    UpdText.setText("Подключение к серверу...");
                    URL url = new URL("http://test.ru/web/upd/client.zip");
                    HttpURLConnection updcon = (HttpURLConnection) url.openConnection();
                    
                    File client = new File(Util.getWorkDir().getAbsolutePath() + File.separator, "client.zip");
                    long cli_web = updcon.getContentLength();
                    UpdBar.setMaximum((int) cli_web);
                    FileOutputStream fw = null;
                  
                    BufferedInputStream bis = new BufferedInputStream(updcon.getInputStream());
                    UpdText.setText("Проверяем пути...");
                    fw = new FileOutputStream(client);
                    UpdText.setText("Загрузка клиента...");
                    byte[] by = new byte[1024];
                    int count = 0;
                    while ((count = bis.read(by)) != -1) {
                        fw.write(by, 0, count);
                        UpdBar.setValue((int) client.length());
                        UpdBar.setString("Загруженно: " + ((int) client.length() /1024) + " Кбайт из " + (cli_web / 1024) + " Кбайт");
                    }
                  
                        fw.close();
                        UpdText.setText("Распаковываем клиент...");
                        Util.unpack(Util.getWorkDir().getAbsolutePath() + File.separator + "client.zip", Util.getWorkDir().getAbsolutePath() + File.separator);
                        UpdText.setText("Распаковка завершена успешно...");
                        UpdBar.setString("Готово");
                      // GameDirectory = Util.getWorkDir().getAbsolutePath() + File.separator;
                        //UpdText.setText(gameDirectory);
                        
                } catch (IOException ex) {
                }
            }
        }
                .start(); 
    }
    
    public static void main(String[] args) {
        try {
           for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
               if ("Nimbus".equals(info.getName())) {
                     UIManager.setLookAndFeel(info.getClassName());
               }
            }
          
       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
    }
       Main frame = new Main();
       frame.show();
        Thread thread1 = new Thread(new LoginWindow());
             thread1.start();
    }   
}


