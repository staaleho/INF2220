import java.util.HashMap;

/**
 * Created by staleh on 28.10.2016.
 */
public class BMH {
    HashMap<Character, Integer> badcharshift  = new HashMap<Character, Integer>();
    char[] needlearray;
    char[] haystackarray;
    int charnotpresent;

    public BMH(String needle, String haystack) {
        needlearray = needle.toCharArray();
        haystackarray = haystack.toCharArray();
        charnotpresent = needlearray.length;
        populateHashMap();
        findNeedle();
    }

    public void populateHashMap(){
        for (int i = 0; i < needlearray.length; i++) {
            if(!(charnotpresent - i - 1 == 0)){ //Prevents final character from getting value 0
                badcharshift.put(needlearray[i], (charnotpresent - i - 1));
            }
        }
        for (char c : badcharshift.keySet())
            System.out.println(c + " key, " + badcharshift.get(c) + " value");
         {

        }
    }

    public void findNeedle(){
        int shiftindex = needlearray.length - 1;
        int needlecounter;
        int haystackcounter;

        while(shiftindex < haystackarray.length){
            System.out.println("Shiftindex" + shiftindex);
            if (needlearray[needlearray.length - 1] == haystackarray[shiftindex]){
                needlecounter = needlearray.length - 1;
                haystackcounter = shiftindex;
                while (needlearray[needlecounter] == haystackarray[haystackcounter] || needlearray[needlecounter] == '_'){
                    if (needlecounter == 0){
                        System.out.println("Pattern found at " + haystackcounter);
                        break;
                    }else{
                        needlecounter--;
                        haystackcounter--;
                    }
                }
            }
            if(badcharshift.containsKey(haystackarray[shiftindex])){
                shiftindex += badcharshift.get(haystackarray[shiftindex]);
            }else{
                shiftindex += charnotpresent;
            }
        }



    }
}
