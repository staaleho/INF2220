import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by staleh on 28.09.2016.
 */
public class Project {
    private Task[] tasks;
    private int taskcounter = 0;
    private Boolean[] taskCompleted;
    private int runningtime = 0;
    private ArrayList<Integer> staffAtTime = new ArrayList<Integer>();

    public Project() {
        for (int i = 0; i < 100; i++) {
            staffAtTime.add(0); //Populating the arraylist with zero to avoid indexoutofbounds
        }
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
        setInEdges();

    }

    public Task getTask(int i){
        if (tasks.length < 1){
            return null;
        }
        return tasks[i];
    }

    public void setEarlierTasks(){
        for (Task t : tasks) {
            t.setEdges();
        }

        return;
    }

    public void setInEdges(){
        for(Task t : tasks){
            t.addInEdges();
        }
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
        t.completeTask(0);

    }


    public void addStaffAtTime(int index, int staff, int duration){
        for (int i = 0; i < duration; i++) {
            staffAtTime.add((i+index), (staffAtTime.get(i + index) + staff));

        }
    }


    public void findSlackForProject(){
        for (Task t : tasks){
            t.findSlack();
        }
    }


    public void finalPrintOut(){
        completionPrintOut();
        taskInfoPrintOut();
    }

    public void completionPrintOut(){

        Arrays.sort(tasks);

        for(Task t : tasks){
            System.out.println("Time: " + t.latestStart);
            System.out.println("Starting " + t.getName());
            System.out.println("Current staff is " + staffAtTime.get(t.getLatestStart()));
            System.out.println("Ends at " + t.endsAt);
            System.out.println("- - -");
        }
    }

    public void taskInfoPrintOut(){
        for(Task t : tasks){
            t.printAllInfo();
            System.out.println("- - -");
        }
    }






}
