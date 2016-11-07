import java.util.Arrays;
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
            }else{
                if(badcharshift.containsKey(needlearray[i])){ //In case key exists, and would have skip 0
                    return;
                }else{ //Key does not exists, gets skip of whole needle length
                    badcharshift.put(needlearray[i], needlearray.length);
                }
            }
        }
    }

    public void findNeedle(){
        int shiftindex = needlearray.length - 1;
        int needlecounter;
        int haystackcounter;

        while(shiftindex < haystackarray.length){
            if (needlearray[needlearray.length - 1] == haystackarray[shiftindex]){
                needlecounter = needlearray.length - 1;
                haystackcounter = shiftindex;
                while (needlearray[needlecounter] == haystackarray[haystackcounter] || needlearray[needlecounter] == '_'){
                    if (needlecounter == 0){
                        System.out.println("Pattern found at " + haystackcounter);
                        char[] foundpattern = Arrays.copyOfRange(haystackarray, haystackcounter, ((haystackcounter + needlearray.length)));
                        String stringfoundpattern = new String(foundpattern);
                        System.out.println(stringfoundpattern);
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
