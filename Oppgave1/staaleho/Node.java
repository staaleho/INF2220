/**
 * Created by Stale on 12.09.2016.
 */
public class Node {
    String value = "";
    int frequency = 0;
    Node left = null;
    Node right = null;

    public Node(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void sortNode(Node node) {
        if (this.value.compareTo(node.getValue()) == 0) {
            frequency++;
        } else if (this.value.compareTo(node.getValue()) < 0) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.sortNode(node);
            }
        } else if (this.value.compareTo(node.getValue()) > 0) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.sortNode(node);
            }
        }
    }


    public boolean findValue(String s, Node n){
        if(n == null){
            return false;
        }
        int compare = s.compareTo(n.getValue());

        if(compare > 0){
            return findValue(s, n.left);
        }else if(compare < 0){
            return findValue(s, n.right);
        }else{
            return true;
        }
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node findMinimumValue(Node n){
        if(n == null){
            return null;
        }else if(n.left == null){
            return n;
        }
        return findMinimumValue(n.left);
    }

    public Node findMaximumValue(Node n){
        if(n == null){
            return null;
        }else if(n.right == null){
            return n;
        }
        return findMaximumValue(n.right);
    }


    public Node findNode(String s, Node n){
        if(n == null){
            return n;
        }
        int compare = s.compareTo(n.getValue());

        if(compare > 0){
            return findNode(s, n.left);
        }else if(compare < 0){
            return findNode(s, n.right);
        }else{
            return n;
        }
    }




    public void setValue(String value) {
        this.value = value;
    }

    public Node remove(String s, Node n){
        if(n == null){
            return n;
        }
        int compareresult = s.compareTo(n.value);

        if(compareresult > 0){
            n.left = remove(s, n.left);
        }else if(compareresult < 0){
            n.right = remove(s, n.left);
        }else if (n.left != null && n.right != null){
            Node minimumvalue = findMinimumValue(n.right);
            n.setValue(minimumvalue.getValue());
            remove(minimumvalue.getValue(), n.right);
        }
        else if(n.left != null){
            Node delete = n;
            n = n.left;
            delete = null;
        }
        else if(n.right != null){
            Node delete = n;
            n = n.right;
            delete = null;
        }
        return n;
    }

    public boolean switchLetters(String s, Node n){
        if(s.length() < 2){
            return false;
        }

        char[] chararray = s.toCharArray();
        for (int i = 0; i < chararray.length -1; i++) {
            char c1 = chararray[i];
            char c2 = chararray[i+1];
            chararray[i] = c2;
            chararray[i+1] = c1;
            String teststring = new String(chararray);
            System.out.println("Trying " + teststring);
            if(n.findValue(teststring,n)){
                System.out.println("Found word " + teststring);
                return true;
            }
            chararray = s.toCharArray();

        }
        return false;
    }

}

