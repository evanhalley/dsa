package dev.evanhalley.googleprep.tree;

public class BinaryTreeNode<T extends Comparable<T>> {

    private final T val;
    private BinaryTreeNode<T> leftNode;
    private BinaryTreeNode<T> rightNode;

    public static void main(String[] args) {
        BinaryTreeNode<Integer> bst = new BinaryTreeNode<>(10);
        bst.add(1);
        bst.add(5);
        bst.add(20);
        bst.add(15);
        bst.add(4);
        bst.print();
        System.out.println("Contains 20: " + bst.contains(20));
        System.out.println("Contains 66: " + bst.contains(66));
        System.out.println("Contains 3: " + bst.contains(3));
    }

    public BinaryTreeNode(T val) {
        this.val = val;
    }

    public boolean contains(T val) {
        int result = this.val.compareTo(val);

        switch (result) {
            case -1:
                return rightNode != null && rightNode.contains(val);
            case 0:
                return true;
            case 1:
                return leftNode != null && leftNode.contains(val);
            default:
                return false;
        }
    }

    public void add(T val) {
        int result = this.val.compareTo(val);

        if (result > 0) {
            if (leftNode != null) {
                leftNode.add(val);
            } else {
                leftNode = new BinaryTreeNode<>(val);
            }
        } else if (result < 0) {
            if (rightNode != null) {
                rightNode.add(val);
            } else {
                rightNode = new BinaryTreeNode<>(val);
            }
        }
    }

    public void print() {

        if (leftNode != null) {
            leftNode.print();
        }
        System.out.println(val);
        if (rightNode != null) {
            rightNode.print();;
        }
    }

    @Override
    public String toString() {
        return "BinaryTreeNode{" +
                "val=" + val +
                '}';
    }
}
