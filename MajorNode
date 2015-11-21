import java.io.*;
import java.util.*;

public class MajorNode {
   
   private String name;
   private ClassNode[] requiredClasses;
   
   public MajorNode(String majorClasses) throws FileNotFoundException{
      Scanner data = new Scanner(new File(majorClasses));
      while(data.hasNext()){
         name = data.nextLine();
         
         String[] strClasses = data.nextLine().split(",");
         for(int i = 0; i < strClasses.length; i++){
            requiredClasses[i] = new ClassNode(strClasses[i], "classes.txt");
         }
      }
   }
   
   //print
   public String toString(){
      String result = name + ": ";
      for(int i = 0; i < requiredClasses.length; i++){
         result += requiredClasses[i];
      }
      return result;
   }
   
   //classes taken
   
}
