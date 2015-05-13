
package zetrix.settings;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import static zetrix.Main.AuthData;


public class GuardUtil {
    public static String map = Util.getWorkDir().getAbsolutePath() + File.separator;
    public static String Mjar = map + "versions\\1.6.4\\1.6.4.jar";
//    public static String map = Util.getWorkDir().getAbsolutePath() + File.separator;
    public static File[] client = new File[] {
      
        new File(map + "versions\\1.6.4\\1.6.4.jar"),
//        new File(map + "lwjgl.jar"),
//        new File(map + "jinput.jar"),
//        new File(map + "lwjgl_util.jar"),
        new File(map + "versions\\1.6.4\\natives")

    };
    public static String ShaHash(String pass) {
        String hash = "";
        try {
            
            MessageDigest crypt = MessageDigest.getInstance("SHA1");
            crypt.reset();
            crypt.update(pass.getBytes("UTF-8"));
            hash = byteToHex(crypt.digest());
      
            
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            
        }
        
        return hash;
        
    } 

    private static String byteToHex(byte[] digest) {
      Formatter formtr = new Formatter();
      for (byte b : digest) {
          formtr.format("%02x", b);
          
      }
      return formtr.toString();
    }
    
    public static boolean CheckUpd () {
    if ((!client[0].exists())) {
        return false;
    } else {
        if ((AuthData[3].equals(FileHash(Mjar))) && (Integer.parseInt(zetrix.Main.AuthData[4])) == FileSize(Mjar)) {
            return true;
        } else {
            return false;
        }
    }
}
    
    public static String FileHash(String Path) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            
            FileInputStream fis = new FileInputStream(Path);
            DigestInputStream dis = new DigestInputStream(fis, md);
            BufferedInputStream bis = new BufferedInputStream(dis);
            
            while(true) {
                int b = bis.read();
                if (b == -1) break;
                
            }
            
            BigInteger bi = new BigInteger(md.digest());
            return(bi.toString(16));
        } catch (NoSuchAlgorithmException | IOException e) {
            System.out.println("Error: " + e);
            return(e.getMessage());
        }
    }
    
    public static long FileSize(String Path) {
        File chfile = new File(Path);
        if (chfile.exists()) {
            long chllgh = chfile.length();
            return(chllgh);
        }
        return 0;
    }
    
}
