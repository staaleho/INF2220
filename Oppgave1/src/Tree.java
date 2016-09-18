import java.util.ArrayList;

/**
 * Created by staleh on 18.09.2016.
 */
public class Tree {
    Node root = null;
    long total = 0;

    public Tree(Node root) {
        this.root = root;
    }

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

    public int nodesAtLevel(Node n, int level, int wantedlevel){
        if(n == null){
            return 0;
        }
        if(level == wantedlevel){
            return 1;
        }
        return nodesAtLevel(n.left, level+1, wantedlevel) + nodesAtLevel(n.right, level+1, wantedlevel);

    }

    public void nodesPerLevel(){

        for (int i = 0; i < getDepth(root); i++) {
            System.out.println("Level " + i + " " + nodesAtLevel(root, 0, i));
            total += nodesAtLevel(root, 0, i);
        }
    }


    public long getTotal() {
        return total;
    }

    public ArrayList<String> wordGenerator(String s){
        System.out.println("Making list");
        long starttime = System.currentTimeMillis();
        ArrayList<String> returnarraylist = new ArrayList<String>();
        int lettersinalphabet = 26;
        if(s.length() < 2){

            return null;
        }
        char[] chararray = s.toCharArray();

        for (int i = 0; i < chararray.length -1; i++) {
            char c1 = chararray[i];
            char c2 = chararray[i + 1];
            chararray[i] = c2;
            chararray[i + 1] = c1;
            String teststring = new String(chararray);

            returnarraylist.add(teststring);
            chararray = s.toCharArray();
        }


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

        for (int i = 0; i < s.length() + 1; i++) {
            StringBuilder stringBuilder = new StringBuilder(s);
            char c = 'a';
            for (int j = 0; j < lettersinalphabet; j++) {
                stringBuilder.insert(i,c);
                returnarraylist.add(s);
                stringBuilder.deleteCharAt(i);
                 c = (char) (c+1);
            }

        }
        long endTime = System.currentTimeMillis();
        System.out.println("It took " + (endTime - starttime) + " milliseconds to generate alternatives.");
        return returnarraylist;
    }


}
