
package zetrix.file;

import java.io.*;
import java.util.*;
public class WorkInFile {
    

public class DirControl 
{
  public void main(String args[])
  {
    int i;
    
    System.out.println(
      "* Directory control demonstration\n");

    try
    {  
      Properties p = System.getProperties();
    
      System.out.println("Work dir: " +
	p.getProperty("user.dir"));
	
      System.out.println("Home dir: " +
	p.getProperty("user.home"));
	
      System.out.println("Separator: " +
	p.getProperty("file.separator"));
	
      System.out.println("Path separator: " +
	p.getProperty("path.separator"));
	
      File f = new File(
        "temp.dir" + File.separator);	
      
      f.mkdir();
      
      File f1 = new File("newtemp.dir" + 
	File.separator);
      f.renameTo(f1);
      
      File f2 = new File("1.dir" + 
	File.separator + "2.dir" + 
        File.separator);
      f2.mkdirs();
      
      f1.delete();
      
      f2.delete();
      
      File f3 = new File("1.dir" + 
	File.separator);
	
      f3.delete();	
    }
    catch(Exception ex)
    {
      System.out.println(ex.toString());
      System.exit(0);
    }
  }
}
}
