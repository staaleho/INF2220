/**
 * Created by Stale on 12.09.2016.
 */
import java.io.*;
import java.util.*;

public class Main{

    public static void main(String[] args) throws Exception{
        /*
        Initial setup for tree and other variables.
        Creates input and output file.
         */
        File words = new File("words.txt");
        Scanner s = new Scanner(words);

        //Uses first word to create a node.
        Node root = new Node(s.nextLine());
        System.out.println("Root made");
        Tree tree = new Tree(root);
        ArrayList<String> treestatistics = new ArrayList<String>();
        ArrayList<String> results = new ArrayList<String>();

        File utskrift = new File("utskrift.txt");
        utskrift.createNewFile();
        FileWriter fw = new FileWriter(utskrift);

        //Reads the file, creates nodes, and sorts them.

        while(s.hasNextLine()){
            tree.insert(s.nextLine());
        }
        long total = tree.getTotal();
        treestatistics = tree.treeStatistics();

        //Writes statistics for the tree to the output file.
        for (String treestring: treestatistics) {
            fw.write(treestring);
            fw.write(System.getProperty("line.separator"));
            fw.flush();
        }
        fw.write(System.getProperty("line.separator"));

        //Initiates spellcheck.

        System.out.println(tree.search("busybody"));
        tree.remove("busybody");
        System.out.println(tree.search("busybody"));

        System.out.println("Welcome to spellcheck! Enter word: ('q' to exit)");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(!input.equals("q")) {
            if (!tree.search(input)) {
                results = tree.spellCheck(input);
                for (String rstring: results) {
                    fw.write(rstring);
                    fw.flush();
                    fw.write(System.getProperty("line.separator"));
                    fw.flush();
                }
                fw.write(System.getProperty("line.separator"));
                System.out.println("Enter word: ('q' to exit)" );
                input = in.nextLine();
            }else{
                System.out.println(input + " found!");
                System.out.println("Enter word: ('q' to exit)");
                input = in.nextLine();
            }

            results.clear();
        }

        for (String exitstring : treestatistics) {
            System.out.println(exitstring);
        }
    }
}
