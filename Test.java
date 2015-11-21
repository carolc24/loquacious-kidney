import java.io.*;
import java.util.*;

public class Test{
   
   public static void main(String[] args) throws FileNotFoundException{
      MajorNode test = new MajorNode("IE");
      System.out.println(test.toString());
      //System.out.println(test);
      //test.remove("MATH 124");
      //System.out.println(test.toString());
      //test.remove("PHYS 123");
      //System.out.println(test.toString());
      System.out.println(test.getDepth("PHYS 123"));
      System.out.println(test.getDepth("CHEM 142"));
      test.makeSchedule(3);
   }
   
}
