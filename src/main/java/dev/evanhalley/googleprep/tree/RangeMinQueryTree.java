package dev.evanhalley.googleprep.tree;

public class RangeMinQueryTree {

    private final int[] segmentTree;

    public static void main(String[] args) {
        int[] data = { -1, 3, 6, 99, 8, 6, 5, 0 };
        RangeMinQueryTree rangeMinQueryTree = new RangeMinQueryTree(data);
        System.out.println(rangeMinQueryTree.rangeMinQuery(4, 7, 0, data.length - 1, 0));
    }

    public RangeMinQueryTree(int[] data) {
        segmentTree = new int[data.length * 2 - 1];
        constructTree(data, 0, data.length - 1, 0);
    }

    public int getParent(int childPos) {
        return (childPos - 1) / 2;
    }

    public int getLeftChild(int parentPos) {
        return 2 * parentPos + 1;
    }

    public int getRightChild(int parentPos) {
        return 2 * parentPos + 2;
    }

    public int rangeMinQuery(int qLeft, int qRight, int left, int right, int pos) {

        if (qLeft <= left && qRight >= right) { // total overlap
            return segmentTree[pos];
        }
        if (qLeft > right || qRight < left) { // no overlap
            return Integer.MAX_VALUE;
        }
        int mid = (left + right) / 2;
        return Math.min(rangeMinQuery(qLeft, qRight, left, mid, getLeftChild(pos)),
                rangeMinQuery(qLeft, qRight, mid + 1, right, getRightChild(pos)));
    }

    private void constructTree(int[] data, int left, int right, int pos) {

        if (left == right) {
            segmentTree[pos] = data[left];
            return;
        }
        int mid = (left + right) / 2;
        constructTree(data, left, mid, getLeftChild(pos));
        constructTree(data, mid + 1, right, getRightChild(pos));
        segmentTree[pos] = Math.min(segmentTree[getLeftChild(pos)], segmentTree[getRightChild(pos)]);
    }
}
