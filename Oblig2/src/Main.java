import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by staleh on 28.09.2016.
 */
public class Main {
    public static void main(String[] args){
        boolean cyclefound = false;


        Project newproject = new Project(); //Create empty project

        try {
            File file = new File(args[0]); //Create file
            newproject.newProjectFromFile(file); //Populate project with tasks from file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try{
            for (Task t : newproject.findZeroEdgeTasks()) {
                newproject.findCycle(t);
            }
        }catch (Error e){
            System.out.println("Cycle found! Terminating program.");
            cyclefound = true;
        }


        if(!cyclefound){
            newproject.findQuickestCompletion();
            newproject.findSlackForProject();
            newproject.finalPrintOut();
        }
    }
}
