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


    public void deleteNode(String s){
        if(this.left.getValue().equals(s)){
            System.out.println("Found it!");
            Node leftfromdeleted = this.left.getLeft();
            Node minimumright = this.left.findMinimumRight();
            this.left = minimumright;
            minimumright.setLeft(leftfromdeleted);
            System.out.println(this.left.getValue());
        }else if (this.right.getValue().equals(s)){
            System.out.println("Found it!");
            Node leftfromdeleted = this.right.getLeft();
            Node minimumright = this.right.findMinimumRight();
            this.right = minimumright;
            minimumright.setLeft(leftfromdeleted);
            System.out.println(this.right.getValue());
        }else if(this.value.compareTo(s) < 0){
            left.deleteNode(s);
        }else if(this.value.compareTo(s) > 0) {
            right.deleteNode(s);
        }

    }

    public Node findMinimumRight(){
        if(this.left == null){
            return this;
        }else{
            this.right.findMinimumRight();
        }
        return this;
    }

    public void findValues(){
        System.out.println(value);
        if(this.left != null){
            left.findValues();
        }
    }

    public Node findValue(String s){
        if(this.value.equals(s)){
            System.out.println("Found node with " + this.value);
            return this;
        }else if((this.value.compareTo(s)) < 0){
            this.left.findValue(s);
        }else if((this.value.compareTo(s)) > 0){
            this.right.findValue(s);
        }else{
            System.out.println("Not found.");
            return null;
        }
        return null;

    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }
}
