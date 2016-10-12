import java.util.ArrayList;

/**
 * Created by staleh on 28.09.2016.
 */
class Task {
    int id , time , staff ;
    String name ;
    int earliestStart , latestStart;
    ArrayList < Task > outEdges = new ArrayList<Task>();
    int cntPredecessors = 0;
    int[] earliertasks;
    Project thisproject;
    State taskstate = State.UNSEEN;


    //The constructor accepts a stringarray as parameter, and uses it to fill variables.
    public Task(String[] taskelements, Project p) {
        //Check if the array is big enough
        if (taskelements.length < 5 ){
            return;
        }
        //Set values
        this.id = Integer.parseInt(taskelements[0]);
        this.name = taskelements[1];
        this.time = Integer.parseInt(taskelements[2]);
        this.staff = Integer.parseInt(taskelements[3]);

        //Temporary array to keep track of preceeding tasks(they dont necessarily exist yet)
        earliertasks = new int[taskelements.length - 5];

        for (int i = 4; i < taskelements.length - 1; i++) {
            earliertasks[i-4] = Integer.parseInt(taskelements[i]);
            cntPredecessors++;
        }

        thisproject = p;
    }

    public void setEdges(){
        for (int i : earliertasks) {
            thisproject.getTask(i-1).addSingleOutEdge(this);
        }
    }

    public void addSingleOutEdge(Task t){
        outEdges.add(t);
    }

    public ArrayList<Task> getOutEdges() {
        return outEdges;
    }

    public int[] getEarliertasks(){
        return earliertasks;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void listNecsTasks(){
        for (Task t : outEdges) {
            System.out.println("Task " + name + " needs these to start:");
            System.out.println(t.getName());
        }
    }

    public void addPredecessor(){
        cntPredecessors++;
    }

    public int getCntPredecessors() {
        return cntPredecessors;
    }

    public void removePredecessor(){
        cntPredecessors--;

    }

    public void setUnseen(){
        taskstate = State.UNSEEN;
    }

    public void setRunning(){
        taskstate = State.RUNNING;
    }

    public int getEarliestStart() {
        return earliestStart;
    }

    public void setFinished(){
        taskstate = State.SEEN;
    }

    public int getTime() {
        return time;
    }

    public void setEarliestStart(int earliestStart) {
        if(this.earliestStart < earliestStart){
            System.out.println(name + " earliest set to " + earliestStart);
            this.earliestStart = earliestStart;
        }

    }

    public void completeTask(int i) {

        this.earliestStart = i;

        if (cntPredecessors == 0) {
            System.out.println("Starting " + this.name);
            this.taskstate = State.SEEN;
            thisproject.addStaffAtTime(earliestStart, staff, time);

            System.out.println("Completed in " + (earliestStart + this.time) + " time.");
            System.out.println("- - -");
        for (Task t : outEdges) {
            t.removePredecessor();
            if (t.getCntPredecessors() == 0 && t.taskstate.equals(State.UNSEEN)) {
                t.completeTask(earliestStart + time);
            }
        }
    }else{
            return;
        }
    }

    public void runningTimeForTask(int i){
        System.out.println(this.name + " complete in " + (i + time) + " time.");
        for (Task t: outEdges) {
            if(t.taskstate.equals(State.SEEN)){
                t.runningTimeForTask(i);
            }
        }
    }


}
