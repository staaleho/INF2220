/**
 * Created by Stale on 12.09.2016.
 */
import java.io.*;
import java.util.*;

public class Oblig1{
    public static void main(String[] args) throws Exception{
        File words = new File("words.txt");
        Scanner s = new Scanner(words);
        Node root = new Node(s.nextLine());
        System.out.println("Root made");

        while(s.hasNextLine()){

            Node newnode = new Node(s.nextLine());

            root.sortNode(newnode);

        }
        System.out.println("Done making initial tree");
        root.findValue("busybody");
        root.deleteNode("busybody");
        root.findValue("busybody");

    }
}
