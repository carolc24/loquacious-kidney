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
      String result = name + ": \n";
      for(int i = 0; i < requiredClasses.length; i++){
         result += requiredClasses[i] + "\n";
      }
      return result;
   }
   
   //classes taken
   public void remove(ClassNode taken){
      for(int i = 0; i < requiredClasses.length; i++){
         if(requiredClasses[i].contains(taken.getName())){
            for(int j = i; j < requiredClasses.length - 1; j++){
               requiredClasses[j] = requiredClasses[j + 1];
            }
         } else {
            requiredClasses[i].remove(taken);
         }
      }
   }
}
