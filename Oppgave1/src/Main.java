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

        while(s.hasNextLine()){

            Node newnode = new Node(s.nextLine());
            root.sortNode(newnode);
        }
        System.out.println("Done making initial tree");
        System.out.println(root.findValue("busybody", root));
        root.remove("busybody", root);
        System.out.println(root.findValue("busybody", root));
        Node busybodynode = new Node("busybody");
        root.sortNode(busybodynode);
        System.out.println(root.findValue("busybody", root));

    }

}
