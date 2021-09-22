package dev.evanhalley.googleprep.tree;

public class RangeSumQueryTree {

    private final int[] segmentTree;
    private final int size;

    public static void main(String[] args) {
        int[] data = { 2,3,-1,5,-2,4 };
        RangeSumQueryTree segmentTree = new RangeSumQueryTree(data);
        System.out.println(segmentTree.sum(0, 7));
    }

    public RangeSumQueryTree(int[] data) {
        this.size = data.length;
        int height = (int) (Math.log(size) / Math.log(2)) + 1;
        segmentTree = new int[(int) Math.pow(2, height + 1)];
        constructTree(data, 0, size - 1, 0);
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

    public int rangeMinQuery(int left, int right) {
        return rangeMinQuery(left, right, 0, size - 1, 0);
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

    public int sum(int left, int right) {
        return sum(left, right, 0, size - 1, 0);
    }

    public int sum(int left, int right, int i, int j, int pos) {

        if (left <= i && right >= j) { // total overlap
            return segmentTree[pos];
        }
        if (left > j || right < i) { // no overlap
            return 0;
        }
        int mid = (i + j) / 2;
        return sum(left, right, i, mid, getLeftChild(pos)) +
                sum(left, right, mid + 1, j, getRightChild(pos));
    }

    public void update(int treeIndex, int left, int right, int index, int val) {

        if (left == right) {
            segmentTree[treeIndex] = val;
            return;
        }
        int mid = left + (right - left) / 2;

        if (index > mid) {
            update(2 * treeIndex + 2, mid + 1, right, index, val);
        } else {
            update(2 * treeIndex + 1, left, mid, index, val);
        }
        segmentTree[treeIndex] = segmentTree[2 * treeIndex + 1] + segmentTree[2 * treeIndex + 2];
    }

    private void constructTree(int[] data, int left, int right, int pos) {

        if (left == right) {
            segmentTree[pos] = data[left];
            return;
        }
        int mid = left + (right - left) / 2;
        constructTree(data, left, mid, getLeftChild(pos));
        constructTree(data, mid + 1, right, getRightChild(pos));
        //segmentTree[pos] = Math.min(segmentTree[getLeftChild(pos)], segmentTree[getRightChild(pos)]);
        segmentTree[pos] = segmentTree[getLeftChild(pos)] + segmentTree[getRightChild(pos)];
    }

}
