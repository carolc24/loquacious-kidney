import java.io.*;
import java.util.*;

public class ClassNode {

   private String name;
   private int credits;
   private String type;
   private String offered;
   private ClassNode[] prereqs;
   
   public ClassNode(String name, String classInfo) throws FileNotFoundException{
      Scanner data = new Scanner(new File(classInfo));      
      while(data.hasNext()){
         data.nextLine(); //skips name in file
         
         this.name = name;
         credits = data.nextInt();
         type = data.nextLine();
         offered = data.nextLine();
         
         String[] strPrereqs = data.nextLine().split(",");
         for(int i = 0; i < strPrereqs.length; i++){
            prereqs[i] = new ClassNode(strPrereqs[i], "classes.txt");
         }
      }
   }

}
