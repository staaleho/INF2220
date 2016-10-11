import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by staleh on 28.09.2016.
 */
public class Project {
    private Task[] tasks;
    private int taskcounter = 0;
    private Boolean[] taskCompleted;
    private int runningtime = 0;

    public Project() {
    }

    public void newProjectFromFile(File filename) throws FileNotFoundException {
        Scanner fileReader = new Scanner(filename);
        tasks = new Task[Integer.parseInt(fileReader.nextLine())]; //First line is number of tasks
        fileReader.nextLine(); //Skip empty line

        while (fileReader.hasNextLine()){

            String str = fileReader.nextLine();//Read next line
            if(str.equals("")){//Extra check for empty lines
                continue;
            }

            String[] stringarray = str.split("\\s+");//Use regex to split by variable whitespace

            for (String replacestring : stringarray) {//Replace
                replacestring.replaceAll(" +","");
            }

            Task t = new Task(stringarray, this);

            tasks[taskcounter] = t;
            taskcounter++;
        }
        taskCompleted = new Boolean[taskcounter];

        for (Boolean b : taskCompleted){
            b = false;
        }

        setEarlierTasks();

    }

    public Task getTask(int i){
        if (tasks.length < 1){
            return null;
        }
        return tasks[i];
    }

    public void setEarlierTasks(){
        ArrayList < Task > earliertaskslist = new ArrayList<Task>();
        for (Task t : tasks) {
            t.setEdges();
        }

        return;
    }

    public void listTasks(){
        System.out.println("This project consists of these tasks:");
        for (Task t : tasks) {
            System.out.println(t.getName());
            System.out.println(t.getEarliertasks().length);
        }
    }

    public Task[] getTasks() {
        return tasks;
    }

    public Task findFirstTask(){
        Task starttask = tasks[0];

        for (Task t3 : tasks){
            if (t3.getCntPredecessors() < 1)
                starttask = t3;
        }
        return starttask;
    }

    public void findCycle(Task t){

        switch (t.taskstate){
            case RUNNING:
                System.out.println("Loop!");
                findLoopingTasks();
                break;
            case UNSEEN:
                t.taskstate = State.RUNNING;
                for (Task t2 : t.getOutEdges())
                    findCycle(t2);
                t.taskstate = State.SEEN;
        }

    }

    public void findLoopingTasks(){
        System.out.println("Loop consists of");
        for (Task t :tasks)
            if(t.taskstate.equals(State.RUNNING)){
                if(!(t.getCntPredecessors() < 1)){ //Not pretty, but it disregards the initial task
                    System.out.println(t.getName());
                }

            }
    }

    public void findQuickestCompletion(){
        for (Task t: tasks) {
            t.taskstate = State.UNSEEN; //Resets all tasks after searching for a cycle
        }

        Task t = findFirstTask();
        t.completeTask();

    }

    public void completeGraph(){
        for (Task t : tasks);
    }

    public void fullListPred(){
        for (Task t:  tasks) {
            System.out.println(t.getName() + " is necessary for these tasks: ");
            for(Task t2 : t.getOutEdges()){
                System.out.println(t2.getName());
            }
            System.out.println("- - -");
        }
    }


}
