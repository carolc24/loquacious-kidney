import java.io.*;
import java.util.*;
import java.lang.*;

public class MajorNode {
   
   private String name;
   private ClassNode[] requiredClasses;
   
   public MajorNode(String majorName) throws FileNotFoundException{
      Scanner data = new Scanner(new File("majors.txt"));
      name = "";
      while(data.hasNext() && !name.equals(majorName)){
         name = data.nextLine();
      }

      String[] strClasses = data.nextLine().split(", ");
      requiredClasses = new ClassNode[strClasses.length];
      for(int i = 0; i < strClasses.length; i++){
         requiredClasses[i] = new ClassNode(strClasses[i]);
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
