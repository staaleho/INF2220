/**
 * Created by Stale on 12.09.2016.
 */
import java.io.*;
import java.util.*;

public class Main{
    public static void main(String[] args) throws Exception{
        File words = new File("words.txt");
        Scanner s = new Scanner(words);
        Node root = new Node(s.nextLine());
        System.out.println("Root made");
        Tree tree = new Tree(root);
        ArrayList<String> inputarraylist = new ArrayList<String>();

        while(s.hasNextLine()){

            Node newnode = new Node(s.nextLine());
            root.sortNode(newnode);
        }
        long total = 0;






        System.out.println("Welcome to spellcheck! Enter word:");
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        while(!input.equals("q")){
            if(!root.findValue(input, root)){
                System.out.println("Not found! Trying spellcheck:");
                inputarraylist = tree.wordGenerator(input);
                System.out.println("There are " + inputarraylist.size() + " variants of the word to try.");
                long startTime = System.currentTimeMillis();
                for (String str: inputarraylist) {
                    if(root.findValue(str, root)){
                        System.out.println("Found " + str + " instead of " + input);
                    }
                }
                long endTime = System.currentTimeMillis();
                System.out.println("It took " + (endTime - startTime) + " ms to search for alternatives.");
            }else{
                System.out.println("Word found!");
            }
            input = in.nextLine();
        }

        System.out.println("Some statistics about the current tree:");
        System.out.println("1: The tree has a depth of " + tree.getDepth(root));
        System.out.println("2: Per level, there are the follow number of nodes:");
        tree.nodesPerLevel();
        System.out.println("3: The average depth is " + Math.log(tree.getTotal()) / Math.log(2) + ".");
        System.out.print("4: The first word is " + root.findMaximumValue(root).getValue());
        System.out.println(", and the last is " + root.findMinimumValue(root).getValue() + ".");








    }


    


}
