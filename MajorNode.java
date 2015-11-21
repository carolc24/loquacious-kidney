import java.io.*;
import java.util.*;
import java.lang.*;

public class MajorNode {
   
   private String name;
   private ArrayList<ClassNode> requiredClasses;
   private ArrayList<String> classNames;
   
   public MajorNode(String majorName) throws FileNotFoundException{
      Scanner data = new Scanner(new File("majors.txt"));
      name = "";
      while(data.hasNext() && !name.equals(majorName)){
         name = data.nextLine();
      }
      classNames = new ArrayList<String>();
      String[] strClasses = data.nextLine().split(", ");
      requiredClasses = new ArrayList<ClassNode>();
      for(int i = 0; i < strClasses.length; i++){
         requiredClasses.add(new ClassNode(strClasses[i]));
         classNames.add(strClasses[i]);
         for(String req : requiredClasses.get(i).getPrereqs())
	     classNames.add(req);
      }
   }
   
   //print
   public String toString(){
      String result = name + ": \n";
      for(int i = 0; i < requiredClasses.size(); i++){
         result += requiredClasses.get(i) + "\n";
      }
      return result;
   }
   
   //classes taken
   public void remove(String taken){
       for(int i = 0; i < requiredClasses.size(); i++){
	   if(requiredClasses.get(i).getName().contains(taken)){
            requiredClasses.remove(i);
         } else {
            requiredClasses.get(i).remove(taken);
         }
      }
   }

   public int getDepth(String req) {
       int depth;
       int depth_max = 0;
       for (int i = 0; i < requiredClasses.size(); i++) {
           if (requiredClasses.get(i).getName().contains(req)) return 0;
	   depth = requiredClasses.get(i).getDepth(req);
           if (depth > depth_max) depth_max = depth;
       }
       return depth_max;
   }

    public void makeSchedule() {
        int i = 1;
        ArrayList<Integer> depths = new ArrayList<Integer>();
        for (int j = 0; j < classNames.size(); j++)
	    depths.add(getDepth(classNames.get(i)));
	while (requiredClasses.size() > 0) {
            System.out.println(depths.toString());
            System.out.println("Quarter " + i + ":");
            List<String> classes = new ArrayList<String>();
            for (int k = 0; k < 3; k++) {
                int depth_max = 0;
                int max_index = 0;
                for (int j = 0; j < classNames.size(); j++) {
		    if (depths.get(j) > depth_max) {
			depth_max = depths.get(j);
			max_index = j;
		    }
		}
                classes.add(classNames.get(max_index));
	        System.out.println(classNames.get(max_index));
                classNames.remove(max_index);
                depths.remove(max_index);
            }
            for (String taken : classes) remove(taken);
	    i++;
	}
    }

}
