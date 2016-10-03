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

    public Node deleteNode(Node root, String s) {
        if (root == null){
            return null;
        }
        int compareint = s.compareTo(root.value);

        if (compareint > 0) {
            root.left = deleteNode(root.left, s);
        } else if (compareint < 0) {
            root.right = deleteNode(root.right, s);
        } else {

            if (root.left != null && root.right != null) {
                Node temp = root;

                Node minNodeForRight = root.findMinimumValue(root.right);

                root.value = minNodeForRight.getValue();

                deleteNode(root.right, minNodeForRight.value);
            }

            else if (root.left != null) {
                root = root.left;
            }

            else if (root.right != null) {
                root = root.right;
            }

            else
                root = null;
        }
        return root;
    }



}

