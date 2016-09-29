import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by staleh on 28.09.2016.
 */
public class Project {
    Task[] tasks;
    int taskcounter = 0;

    public Project() {
    }

    public void newProjectFromFile(File filename) throws FileNotFoundException {
        Scanner fileReader = new Scanner(filename);
        tasks = new Task[Integer.parseInt(fileReader.nextLine())];
        fileReader.nextLine();
        while (fileReader.hasNextLine()){
            String str = fileReader.nextLine();
            String[] stringarray = str.split("\\t");
            Task t = new Task(stringarray, this);
            tasks[taskcounter] = t;
            System.out.println(taskcounter);
            taskcounter++;
        }

        System.out.println(tasks.length + " tasks added.");
    }

    public Task getTask(int i){
        if (tasks.length < 1 || tasks.length < i){
            return null;
        }
        return tasks[i];
    }
}
