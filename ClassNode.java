import java.io.*;
import java.util.*;

public class ClassNode {

   private String name;
   private int credits;
   private String[] type;
   private String[] offered;
   private ArrayList<ClassNode> prereqs;
   
   public ClassNode(String name) throws FileNotFoundException{
       if (name.contains("/")) {
         String[] strOptions = name.split("/");
         //System.out.println("Options: " + Arrays.toString(strOptions));
         this.name = name;
         type = "OR".split(", ");
         prereqs = new ArrayList<ClassNode>();
         for(int i = 0; i < strOptions.length; i++) {
            prereqs.add(new ClassNode(new String(strOptions[i])));
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
      prereqs = new ArrayList<ClassNode>();
      if (!strPrereqs[0].equals("None")) {
         for(int i = 0; i < strPrereqs.length; i++){
            prereqs.add(new ClassNode(strPrereqs[i]));
	 }
      }
       }
   }

    public String toString() {
	String info = new String();
        //info += "Credits: " + credits + "\n";
        //info += "Type: " + Arrays.toString(type) + "\n";
        //info += "Offered: " + Arrays.toString(offered) + "\n";
        //info += "Prereqs: ";
        for (int i = 0; i < prereqs.size(); i++)
	    info += prereqs.get(i).toString() + " ";
        if (prereqs.size() > 0) info += " --> ";
        info += name;
        return info;
    }

    public String getName() {
	return this.name;
    }
    
    public Set<String> getPrereqs() {
	Set<String> reqs = new HashSet<String>();
        for (int i = 0; i < prereqs.size(); i++) {
            reqs.add(prereqs.get(i).getName());
            for (String req : prereqs.get(i).getPrereqs())
		reqs.add(req);
	}
        return reqs;
    }
    
    public void remove(String taken){
	for(int i = 0; i < prereqs.size(); i++){
	  if (prereqs.get(i).getName().contains(taken)) {
	      System.out.println("Prereq found, deleting");
	      prereqs.remove(i);
	  } else {
            prereqs.get(i).remove(taken);
          }
      }
   }

    public int getDepth(String req) {
        int depth;
        int depth_max = 0;
	for (int i = 0; i < prereqs.size(); i++) {
	    if (prereqs.get(i).getName().contains(req)) return 1;
            depth = prereqs.get(i).getDepth(req);
	    if (depth > 0) depth++;
            if (depth > depth_max) depth_max = depth;
	}
	return depth_max;
    }
}
