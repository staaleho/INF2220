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

        System.out.println("Before we begin, lets look at the tree!");

        for (String exitstring : treestatistics) {
            System.out.println(exitstring);
        }

        System.out.println("Looking for busybody...");
        if (tree.search("busybody")){
            System.out.println("Busybody found!");
        }else{
            System.out.println("Busybody not found!");
        }

        System.out.println("Deleting busybody");

        tree.remove("busybody");
        System.out.println("Looking for busybody...");
        if (tree.search("busybody")){
            System.out.println("Busybody found!");
        }else{
            System.out.println("Busybody not found!");
        }

        System.out.println("Lets delete a leaf node, like abactinally");

        tree.remove("abactinally");

        System.out.println("Looking for abactinally...");
        if (tree.search("abactinally")){
            System.out.println("Abactinally found!");
        }else{
            System.out.println("Abactinally not found!");
        }


        System.out.println("Lets hope the tree is still working!");
        treestatistics = tree.treeStatistics();

        for (String exitstring : treestatistics) {
            System.out.println(exitstring);
        }

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

    }
}
