import java.util.List;

/**
 * Created by staleh on 28.09.2016.
 */
class Task {
    int id , time , staff ;
    String name ;
    int earliestStart , latestStart ;
    List < Task > outEdges ;
    int cntPredecessors ;
    int[] earliertasks;
    Project thisproject;

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
        earliertasks = new int[taskelements.length - 4];

        for (int i = 4; i < taskelements.length; i++) {
            earliertasks[i-4] = Integer.parseInt(taskelements[i]);
        }

        thisproject = p;
    }

    public void setEdges(){
        for (int i : earliertasks) {

        }
    }


}
