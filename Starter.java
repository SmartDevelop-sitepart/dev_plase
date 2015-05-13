/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zetrix;

import java.io.File;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import static zetrix.Main.AuthData;
import zetrix.settings.Util;

/**
 *
 * @author 123
 */
public class Starter {

     List<URL> url = new ArrayList<URL>();
    URLClassLoader cl;
    String Class = null;
    
public  Starter() {
      initComponents();
  }
//    private JFrame frame;
//  private TextField username;
  
  public void initComponents() {
         try {
             //      frame = new JFrame("Launcher");
//      frame.setSize(156, 100);
//      Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
//      int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
//      int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
//      frame.setLocation(x, y);
//      frame.setResizable(false);
//      frame.setVisible(true);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      username = new TextField();
//      username.setLocation(17, 10);
//      username.setSize(120, 25);
//      JButton button = new JButton("Играть");
//      button.setVisible(true);
//      button.setLocation(17, 40);
//      button.setSize(120, 25);
//      frame.getContentPane().add(username);
//      frame.getContentPane().add(button);
//      frame.getContentPane().add(new JLabel());
//      button.addActionListener(new ActionListener() {
//        public void actionPerformed(ActionEvent e) {
             String jarpath = Starter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
         } catch (URISyntaxException ex) {
         }
		//int memory = 1024;
                        List<String> params = new ArrayList<String>();
                     // params.add(System.getProperty("C:\Program Files\Java\jdk1.7.0_67\bin\java.exe"));
                      //  params.add(System.getProperty("java.home")+"/bin/java");
			//params.add("-Xincgc");
                            params.add("-Xdock:name=Minecraft");
                         params.add("-Xmx1024m");
			//params.add("-XX:MaxPermSize=128m");
			//params.add("-Dfile.encoding=UTF-8");
                
                    File Dir = new File(Util.getWorkDir().getAbsolutePath() + File.separator);
                   // System.out.println("Путь до папки с клиентом -> "+Dir.getAbsolutePath());
                    //if (!new File(Dir.getAbsolutePath()+"/versions/1.6.4/natives").exists()) new File(Dir.getAbsolutePath()+"/versions/1.6.4/natives").mkdirs(); //Папка с нативами
                    try {
                        url.addAll(getLibs(new File(Dir.getAbsolutePath()+"/versions"))); //Папка с клиентом
                        url.addAll(getLibs(new File(Dir.getAbsolutePath()+"/libraries"))); //Папка с либами
                    } catch (Exception x) {
//                        JOptionPane.showMessageDialog(frame, "Неправильный путь к клиенту!", "", javax.swing.JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    cl = new URLClassLoader(url.toArray(new URL[url.size()]));
                   // System.out.println("Список загруженных jar ->"+url);
                    System.setProperty("fml.ignoreInvalidMinecraftCertificates", "true");
                    System.setProperty("fml.ignorePatchDiscrepancies", "true");
                    System.setProperty("org.lwjgl.librarypath", Dir.getAbsolutePath()+"/versions/1.6.4/natives");
                    System.setProperty("net.java.games.input.librarypath", Dir.getAbsolutePath()+"/versions/1.6.4/natives");
                    System.setProperty("java.library.path", Dir.getAbsolutePath()+"/versions/1.6.4/natives");
                    try {
                        cl.loadClass("com.mojang.authlib.Agent");
//                        params.add("--accessToken");
//                        params.add("1");
//                        params.add("--uuid");
//                        params.add("12345");
//                        params.add("--userProperties");
//                        params.add("{}");
//                        params.add("--assetIndex");
//                        params.add("1.6.4");
                    } catch (ClassNotFoundException x) {
                        params.add("--session " + AuthData[2]);
                        //params.add("12");
                    }
                    
                    params.add("--username");
                    params.add(AuthData[1]);
                    params.add("--version");
                    params.add("1.6.4");
                    params.add("--gameDir");
                    params.add(Dir.getAbsolutePath()+"/");
                    params.add("--assetsDir");
                    params.add(Dir.getAbsolutePath()+"/assets");
                    
                boolean tweakClass = false;
//                try {
//                    cl.loadClass("com.mumfrey.liteloader.launch.LiteLoaderTweaker");
//                    params.add("--tweakClass");
//                    params.add("com.mumfrey.liteloader.launch.LiteLoaderTweaker");
//                    tweakClass = true;
//                } catch (ClassNotFoundException x) {}
                try {
                    cl.loadClass("cpw.mods.fml.common.launcher.FMLTweaker");
                    params.add("--tweakClass");
                    params.add("cpw.mods.fml.common.launcher.FMLTweaker");
                    tweakClass = true;
                } catch (ClassNotFoundException x) {}
                if(tweakClass)
                {
                    Class = "net.minecraft.launchwrapper.Launch";
                } else {
                    Class = "net.minecraft.client.main.Main";
                }
               // frame.setVisible(false);
                try
                {
                    Class<?> start = cl.loadClass(Class);
                    System.out.println("Stanting...");
                    Method main = start.getMethod("main", new Class[] { String[].class });
                    main.invoke(null, new Object[] { params.toArray(new String[0]) });
                } catch (Exception x)
                {
//                    JOptionPane.showMessageDialog(frame, "Ошибка запуска!", "", javax.swing.JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
//          }
//      });
 }
    public static void main(String[] args) {
           SwingUtilities.invokeLater(new Runnable() {
        public void run() {
            System.out.println("Launcher Start");
            new Starter();
        }
      });
    }
      private  List<URL> getLibs(File libsfolder) throws MalformedURLException {
      List<URL> libs = new ArrayList<URL>();
      if (!libsfolder.exists()) libsfolder.mkdirs();
      for (File file : libsfolder.listFiles()) {
        if (file.isDirectory()) {
        libs.addAll(getLibs(file));
        } else {
          if (file.getName().endsWith(".jar")) {
                libs.add(file.toURI().toURL());
          }
        }
      }
      return libs;
    }
    
}
