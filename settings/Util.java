
package zetrix.settings;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.*;
import javax.imageio.ImageIO;


public class Util {
    
    public static File workDir = null;
    public static File getWorkDir() {
      if (workDir == null) {
          workDir = getPath("test123");
      }  
      return workDir;
    }
    
      private static File getPath(String workDir) {
          String userHome = System.getProperty("user.home", ".");
          File wrkDir;
          switch (getPlatform().ordinal()) {
              case 0:
                  String appdata = System.getenv("APPDATA");
                  if (appdata != null) { wrkDir = new File(appdata, "." + workDir + "/"); }
                  else {
                      wrkDir = new File(userHome, "." + workDir + "/");
                  }
              break;
              case 1: 
              case 2:
                  wrkDir = new File(userHome, "." + workDir + "/");
                  break;
              case 3:
                  wrkDir = new File(userHome, "Library/Application Support" + workDir);
                  break;
              default:
                  wrkDir = new File(userHome, workDir + "/");
                                
          }
          if ((!wrkDir.exists()) && (!wrkDir.mkdirs())) {
              throw new RuntimeException("Рабочая директория не найдена (" + wrkDir + ")");
              
          }
          return wrkDir;
      }
      
          public static OS getPlatform() {
              String Name = System.getProperty("os.name").toLowerCase();
              if (Name.contains("win")) {
                  return OS.windows;
              } else if ((Name.contains("linux")) || (Name.contains("unix"))) {
                  return OS.linux;
              } else if (Name.contains("mac")) {
                  return OS.macos;
              } else if (Name.contains("sunos")) {
                  return OS.solaris;
              } else {
                  return OS.unknown;
              }
          }

          public enum OS {
              windows,
              linux,
              solaris,
              macos,
              unknown
          }
          
          public static BufferedImage getRes (String name) {
        try {
            BufferedImage img = ImageIO.read(zetrix.Main.class.getResource("/zetrix/res/" + name ));
            return img;
        } catch (IOException ex) {
            System.out.println("Error img: " + ex.toString());
        } return new BufferedImage(1, 1, 2);
          }
    
    public static void unpack(String path, String dir_to) throws IOException {
    ZipFile zip = new ZipFile(path);
    Enumeration entries = zip.entries();
    LinkedList<ZipEntry> zfiles = new LinkedList<ZipEntry>();
    while (entries.hasMoreElements()) {
      ZipEntry entry = (ZipEntry) entries.nextElement();
      if (entry.isDirectory()) {
        new File(dir_to+"/"+entry.getName()).mkdir();
      } else {
        zfiles.add(entry);
      }
    }
    for (ZipEntry entry : zfiles) {
      InputStream in = zip.getInputStream(entry);
      OutputStream out = new FileOutputStream(dir_to+"/"+entry.getName());
      byte[] buffer = new byte[1024];
      int len;
      while ((len = in.read(buffer)) >= 0)
        out.write(buffer, 0, len);
      in.close();
      out.close();
      }
    zip.close();
    new File(path).delete();
  }
    
    public static String Connect(String sURL, String Params) {
        HttpURLConnection connection = null;
        try {
            URL url = new URL(sURL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("User-Agent", "test_v0");
            
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length", Integer.toHexString(Params.getBytes().length));
            connection.setRequestProperty("Content-Language", "en-US");
            
            connection.setUseCaches(false);
            connection.setDoInput(true);
            connection.setDoOutput(true);
            
            connection.connect();
            try (DataOutputStream dos = new DataOutputStream(connection.getOutputStream())) {
                dos.writeBytes(Params);
                dos.flush();
            }
            
            InputStream is = connection.getInputStream();
            StringBuilder resp;
            try (BufferedReader brd = new BufferedReader(new InputStreamReader(is))) {
                resp = new StringBuilder();
                String line;
                while ((line = brd.readLine()) != null) {
                    resp.append(line);
                    resp.append('\r');
                }
            }
            
            String strn = resp.toString().trim();
            return strn;
        } catch (Exception ex) {
            return null;
        } finally {
            if (connection != null){
                connection.disconnect();
            }
        }
    }


}

  
