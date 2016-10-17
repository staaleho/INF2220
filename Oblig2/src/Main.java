import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by staleh on 28.09.2016.
 */
public class Main {
    public static void main(String[] args){


        Project newproject = new Project(); //Create empty project

        try {
            File file = new File("buildhouse1.txt"); //Create file
            newproject.newProjectFromFile(file); //Populate project with tasks from file
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Task t = newproject.findFirstTask();

        newproject.findCycle(newproject.findFirstTask());
        newproject.findQuickestCompletion();
        newproject.findSlackForProject();
        newproject.finalPrintOut();


    }
}
