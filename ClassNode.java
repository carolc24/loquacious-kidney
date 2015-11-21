import java.io.*;
import java.util.*;

public class ClassNode {

   private String name;
   private int credits;
   private String[] type;
   private String[] offered;
   private ClassNode[] prereqs;
   
   public ClassNode(String name) throws FileNotFoundException{
       if (name.contains("/")) {
         String[] strOptions = name.split("/");
         //System.out.println("Options: " + Arrays.toString(strOptions));
         this.name = name;
         type = "OR".split(", ");
         prereqs = new ClassNode[strOptions.length];
         for(int i = 0; i < strOptions.length; i++) {
            prereqs[i] = new ClassNode(new String(strOptions[i]));
	 }
       } else {

      Scanner data = new Scanner(new File("classes.txt"));      
      while(data.hasNext() && !name.equals(this.name)){
         this.name = data.nextLine(); //skips name in file
      }
      if (!data.hasNext())
          throw new IllegalArgumentException("Class name '" + name + "' not found.");
      
      //      System.out.println("Name: " + this.name);
      credits = data.nextInt();
      //System.out.println("Credits: " + credits);
      data.nextLine();
      type = data.nextLine().split(", ");
      //System.out.println("Type: " + Arrays.toString(type));
      offered = data.nextLine().split(", ");
      //System.out.println("Offered: " + Arrays.toString(offered));

      String[] strPrereqs = data.nextLine().split(", ");
      //System.out.println("Prereqs: " + Arrays.toString(strPrereqs));
      if (strPrereqs[0].equals("None")) {
         prereqs = new ClassNode[0];
      } else {
	 prereqs = new ClassNode[strPrereqs.length];
         for(int i = 0; i < strPrereqs.length; i++){
            prereqs[i] = new ClassNode(strPrereqs[i]);
	 }
      }
       }
   }

    public String toString() {
	String info = new String();
        info += name + "\n";
        //info += "Credits: " + credits + "\n";
        //info += "Type: " + Arrays.toString(type) + "\n";
        //info += "Offered: " + Arrays.toString(offered) + "\n";
        //info += "Prereqs: ";
        for (int i = 0; i < prereqs.length; i++)
	    info += prereqs[i].toString();
        return info;
    }

    public String getName() {
	return this.name;
    }
}
