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
        } else if (this.value.compareTo(node.getValue()) > 0) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.sortNode(node);
            }
        } else if (this.value.compareTo(node.getValue()) < 0) {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.sortNode(node);
            }
        }
    }

    public boolean findValue(String s, Node n) {
        if (n == null) {
            return false;
        }
        int compare = s.compareTo(n.getValue());

        if (compare < 0) {
            return findValue(s, n.left);
        } else if (compare > 0) {
            return findValue(s, n.right);
        } else {
            return true;
        }
    }

    public Node findMinimumValue(Node n) {
        if (n == null) {
            return null;
        } else if (n.left == null) {
            return n;
        }
        return findMinimumValue(n.left);
    }

    public Node findMaximumValue(Node n) {
        if (n == null) {
            return null;
        } else if (n.right == null) {
            return n;
        }
        return findMaximumValue(n.right);
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node remove(String s, Node n) {
        if (n == null) {
            //Nothing happens
            return null;
        }
        if (n.getValue().compareTo(s) < 0) {
            n.setRight(remove(s, n.getRight()));
        } else if (n.getValue().compareTo(s) > 0) {
            n.setLeft(remove(s, n.getLeft()));
        } else if (n.getLeft() != null && n.getRight() != null) {
            n.setValue(findMinimumValue(n.getRight()).getValue());
            n.setRight(remove(n.getValue(), n.getRight()));
        } else {
            if (n.getLeft() != null)
                n = n.getLeft();
            else if (n.getRight() != null){
                n = n.getRight();
            }else{
                n = null;
            }
        }
        return n;
    }


    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

