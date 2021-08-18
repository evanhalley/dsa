package dev.evanhalley.googleprep;

import dev.evanhalley.googleprep.util.ListNode;
import dev.evanhalley.googleprep.util.ListNodeUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {

    public static void main(String[] args) {
        ListNode l1 = ListNodeUtil.stringToList("541");
        ListNode l2 = ListNodeUtil.stringToList("431");
        ListNode l3 = ListNodeUtil.stringToList("62");
        new Solution().mergeKLists(new ListNode[]{ l1, l2, l3 });
    }

    public static class Solution {

        public ListNode mergeKLists(ListNode[] lists) {
            Comparator<ListNode> comparator = Comparator.comparingInt(o -> o.val);
            PriorityQueue<ListNode> minHeap = new PriorityQueue<>(comparator);

            for (ListNode list : lists) {
                addListToHeap(list, minHeap);
            }

            ListNode merged = new ListNode();
            ListNode tail = merged;

            while (minHeap.size() > 0) {
                ListNode node = minHeap.remove();
                tail.next = node;
                tail = node;
            }
            return merged.next;
        }

        public void addListToHeap(ListNode list, PriorityQueue<ListNode> heap) {

            while (list != null) {
                ListNode temp = list;
                list = list.next;
                temp.next = null;
                heap.add(temp);
            }
        }
    }
}
