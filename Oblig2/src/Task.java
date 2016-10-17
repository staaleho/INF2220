import java.util.ArrayList;

/**
 * Created by staleh on 28.09.2016.
 */
class Task implements Comparable<Task>{
    int id , time , staff ;
    String name ;
    int earliestStart , latestStart = 0; //Needed for slack calculation
    ArrayList < Task > outEdges = new ArrayList<Task>();
    ArrayList < Task > inEdges = new ArrayList<Task>();
    int cntPredecessors = 0;
    int[] earliertasks;
    Project thisproject;
    State taskstate = State.UNSEEN;
    int slack;
    int endsAt = 0;
    boolean isCritical = false;



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

    public void setInEdge(Task t){
        inEdges.add(t);
    }

    public ArrayList<Task> getOutEdges() {
        return outEdges;
    }

    public void addInEdges(){
        for(Task t : outEdges){
            t.setInEdge(this);
        }
    }

    public int getLatestStart() {
        return latestStart;
    }

    public String getName() {
        return name;
    }

    public int getCntPredecessors() {
        return cntPredecessors;
    }

    public void removePredecessor(){
        cntPredecessors--;

    }

    public void addStartTime(int earliestStart) {
        if(this.earliestStart == 0){
            this.earliestStart = earliestStart;
        }else if(this.latestStart == 0){
            this.latestStart = Math.max(this.earliestStart, earliestStart);
            this.earliestStart = Math.min(this.earliestStart, earliestStart);
        }else{
            this.latestStart = Math.max(this.latestStart, earliestStart);
            this.earliestStart = Math.min(this.earliestStart, earliestStart);
        }
    }

    public void completeTask(int i) {
        if(i > latestStart){
            latestStart = i;
        }

        if (cntPredecessors == 0) {
            this.taskstate = State.SEEN;
            thisproject.addStaffAtTime(latestStart, staff, time);
            endsAt = latestStart + time;

        for (Task t : outEdges) {
            t.removePredecessor();
            t.addStartTime(i + time);
            if (t.getCntPredecessors() == 0 && t.taskstate.equals(State.UNSEEN)) {
                t.completeTask(latestStart + time);
            }
        }
    }else{
            return;
        }
    }

    public void findSlack(){
        int subsequentstart = 0;
        for(Task t : outEdges){
            if (subsequentstart == 0){
                subsequentstart = t.getLatestStart();
            }
                subsequentstart = Math.min(subsequentstart, t.getLatestStart());
        }

        slack = subsequentstart - (latestStart + time);

        if(slack < 1 ){
            isCritical = true;
        }
    }

    @Override
    public int compareTo(Task o) {
        return this.latestStart - o.getLatestStart();

    }

    public void printAllInfo(){
        System.out.println("Task ID " + id);
        System.out.println("Name: " + name);
        System.out.println("Time to complete " + time);
        System.out.println("Staff: " + staff);
        System.out.println("Earliest start " + earliestStart);
        System.out.println("Latest start " + latestStart);
        if(isCritical){
            System.out.println("Task is critical!");
        }else{
            System.out.println("Task has slack " + slack + ".");
        }

        if(outEdges.size() > 0){
            System.out.println("The project is necessary for these tasks:");
            for (Task t: outEdges){
                System.out.println(t.id + " " + t.getName());
            }
        }


    }
}
