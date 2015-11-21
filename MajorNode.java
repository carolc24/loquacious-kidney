import java.io.*;
import java.util.*;
import java.lang.*;

public class MajorNode {
   
   private String name;
   private ArrayList<ClassNode> requiredClasses;
   
   public MajorNode(String majorName) throws FileNotFoundException{
      Scanner data = new Scanner(new File("majors.txt"));
      name = "";
      while(data.hasNext() && !name.equals(majorName)){
         name = data.nextLine();
      }

      String[] strClasses = data.nextLine().split(", ");
      requiredClasses = new ArrayList<ClassNode>();
      for(int i = 0; i < strClasses.length; i++){
         requiredClasses.set(i, new ClassNode(strClasses[i]));
      }
   }
   
   //print
   public String toString(){
      String result = name + "Prerequisite Classes: \n";
      for(int i = 0; i < requiredClasses.length; i++){
         result += requiredClasses.get(i) + "\n";
      }
      return result;
   }
   
   //classes taken
   public void remove(String taken){
      for(int i = 0; i < requiredClasses.length; i++){
         if(requiredClasses.get(i).contains(taken.getName())){
            requiredClasses.remove(i);
         } else {
            requiredClasses.get(i).remove(taken);
         }
      }
   }
}
