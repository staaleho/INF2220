import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by staleh on 18.09.2016.
 */
public class Tree {
    Node root = null;
    long total = 0;
    List<String> inputarraylist = new ArrayList<String>();
    ArrayList<String> results = new ArrayList<String>();


    public Tree(Node root) {
        this.root = root;
    }
    //Gets depth of tree by returning depth of largest subtree in a node.
    public int getDepth(Node n){
        if(n == null){
            return 0;
        }else if(n.left == null && n.right == null) {
            return 1;
        }else{
                int ldepth = getDepth(n.left);
                int rdepth = getDepth(n.right);
                if(ldepth > rdepth){
                    return ldepth+1;
                }else{
                    return rdepth+1;
                }
            }
        }
    //Counts nodes at a given level.
    public int nodesAtLevel(Node n, int level, int wantedlevel){
        if(n == null){
            return 0;
        }
        if(level == wantedlevel){
            return 1;
        }
        return nodesAtLevel(n.left, level+1, wantedlevel) + nodesAtLevel(n.right, level+1, wantedlevel);

    }

    public ArrayList<String> nodesPerLevel(){
        ArrayList<String> nodesperlevellist = new ArrayList<String>();
        for (int i = 0; i < getDepth(root); i++) {

            nodesperlevellist.add("Level " + i + " " + nodesAtLevel(root, 0, i));
            total += nodesAtLevel(root, 0, i);
        }
        return nodesperlevellist;
    }

    public Double getAverageDepth(Node n){
        ArrayList<Double> averagedepth = new ArrayList<Double>();
        Double averagedepthdouble = 0.0;
        for (int i = 0; i < getDepth(root); i++) {
            averagedepth.add(i, (double ) nodesAtLevel(root, 0, i));
        }

        for (int i = 0; i < averagedepth.size(); i++) {
            averagedepthdouble += (i * averagedepth.get(i));
        }

        return averagedepthdouble/getTotal();
    }


    public long getTotal() {
        return total;
    }

    //Generates an arraylist of strings containing all possible permutations of a word.
    public ArrayList<String> wordGenerator(String s){
        System.out.println("Making list");
        long starttime = System.currentTimeMillis();
        ArrayList<String> returnarraylist = new ArrayList<String>();
        int lettersinalphabet = 26;

        if(s.length() < 2){
            return null;
        }

        char[] chararray = s.toCharArray();

        returnarraylist.addAll(generateSwitchedLetters(chararray, s));
        returnarraylist.addAll(generateReplacedLetters(chararray, s, lettersinalphabet));
        returnarraylist.addAll(generateRemovedLetters(chararray, s));
        returnarraylist.addAll(generateAddedLetters(chararray, s));

        long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - starttime) + " milliseconds to generate alternatives.");
        return returnarraylist;
    }
    //Generates words with switched letters.
    public ArrayList<String> generateSwitchedLetters(char[] chararray, String s){
        ArrayList<String> returnarraylist = new ArrayList<String>();
        for (int i = 0; i < chararray.length -1; i++) {
            char c1 = chararray[i];
            char c2 = chararray[i + 1];
            chararray[i] = c2;
            chararray[i + 1] = c1;
            String teststring = new String(chararray);

            returnarraylist.add(teststring);
            chararray = s.toCharArray();
        }

        return returnarraylist;
    }
    //Generates words with replaced letters.
    public ArrayList<String> generateReplacedLetters(char[] chararray, String s, int lettersinalphabet){
        ArrayList<String> returnarraylist = new ArrayList<String>();

        for (int i = 0; i < chararray.length; i++) {
            chararray[i] = 'a';
            String teststring = new String(chararray);
            returnarraylist.add(teststring);
            for (int j = 0; j < lettersinalphabet-1; j++) {
                chararray[i] = (char) (chararray[i]+1);
                teststring = new String(chararray);

                returnarraylist.add(teststring);
            }
            chararray = s.toCharArray();

        }

        return returnarraylist;
    }
    //Generates words with removed letters.
    public ArrayList<String> generateRemovedLetters(char[] chararray, String s){
        ArrayList<String> returnarraylist = new ArrayList<String>();
        for (int i = 0; i < chararray.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            chararray[i] = '.';
            for (char c: chararray) {
                if(c != '.'){
                    stringBuilder.append(c);
                }
            }
            String teststring = new String(stringBuilder);
            returnarraylist.add(teststring);
            chararray = s.toCharArray();
        }

        return returnarraylist;

    }
    //Generate words with added letters.
    public ArrayList<String> generateAddedLetters(char[] chararray, String s){
        ArrayList<String> returnarraylist = new ArrayList<String>();

        for (int i = 0; i < s.length() + 1; i++) {
            StringBuilder stringBuilder = new StringBuilder(s);
            int lettersinalphabet = 26;
            char c = 'a';
            for (int j = 0; j < lettersinalphabet; j++) {
                stringBuilder.insert(i,c);
                String str = new String(stringBuilder);
                returnarraylist.add(str);
                stringBuilder.deleteCharAt(i);
                c = (char) (c+1);
            }

        }
        return returnarraylist;
    }
    //Checks spelling according to a generated list of words.
    public ArrayList<String> spellCheck(String s){
        if(!root.findValue(s, root)){
            if(!root.findValue(s, root)){
                System.out.println(s + " not found! Trying spellcheck:");
                results.add(s + " not found! Trying spellcheck:");
                inputarraylist = wordGenerator(s);
                Set<String> noduplicates = new LinkedHashSet<String>();
                noduplicates.addAll(inputarraylist);
                inputarraylist.clear();
                inputarraylist.addAll(noduplicates);
                System.out.println("There are " + inputarraylist.size() + " variants of the word to try.");
                results.add("There are " + inputarraylist.size() + " variants of the word to try.");
                long startTime = System.currentTimeMillis();
                for (String str: inputarraylist) {
                    if(root.findValue(str, root)){
                        System.out.println("Found " + str + " instead of " + s);
                        results.add("Found " + str + " instead of " + s);
                    }
                }
                long endTime = System.currentTimeMillis();
                System.out.println("It took " + (endTime - startTime) + " ms to search for alternatives.");
                results.add("It took " + (endTime - startTime) + " ms to search for alternatives.");
            }else{
                System.out.println(s + " found!");
                results.add(s + " found!");
            }
        }
        return results;
    }
    //Generates statistics for the tree.
    public ArrayList<String> treeStatistics (){
        ArrayList<String> treestats = new ArrayList<String>();

        treestats.add("Statistics for the current tree:");
        treestats.add("1: The tree has a depth of " + getDepth(root));
        treestats.add("2: Per level, there are the follow number of nodes:");
        treestats.addAll(nodesPerLevel());
        treestats.add("3: The average depth of a BST is O(log(n)), in this tree that is " + Math.log(getTotal()) / Math.log(2) + ".");
        treestats.add("This BST in particular has an average node depth of " + getAverageDepth(root) + ".");
        treestats.add("4: The first word is " + root.findMaximumValue(root).getValue() + ",");
        treestats.add(" and the last is " + root.findMinimumValue(root).getValue() + ".");

        return treestats;
    }


}
