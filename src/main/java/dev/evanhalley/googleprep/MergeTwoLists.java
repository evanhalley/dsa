package dev.evanhalley.googleprep;

import dev.evanhalley.googleprep.util.ListNode;
import dev.evanhalley.googleprep.util.ListNodeUtil;

public class MergeTwoLists {

    public static void main(String[] args) {
        ListNode l1 = ListNodeUtil.stringToList("");
        ListNode l2 = ListNodeUtil.stringToList("");
        new Solution().mergeTwoLists(l1, l2);
    }

    public static class Solution {

        public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            ListNode merged = new ListNode();
            ListNode lastNode = null;

            while (l1 != null && l2 != null) {
                lastNode = getLastNode(lastNode == null ? merged : lastNode);

                if (l1.val <= l2.val) {
                    l1 = moveNodeToList(l1, lastNode);
                } else {
                    l2 = moveNodeToList(l2, lastNode);
                }
            }

            while (l1 != null) {
                lastNode = getLastNode(lastNode == null ? merged : lastNode);
                l1 = moveNodeToList(l1, lastNode);
            }

            while (l2 != null) {
                lastNode = getLastNode(lastNode == null ? merged : lastNode);
                l2 = moveNodeToList(l2, lastNode);
            }
            return merged.next;
        }

        public ListNode moveNodeToList(ListNode source, ListNode destination) {
            ListNode temp = source;
            source = source.next;
            temp.next = null;
            destination.next = temp;
            return source;
        }

        public ListNode getLastNode(ListNode node) {

            if (node.next != null) {
                node = node.next;
                return getLastNode(node);
            }
            return node;
        }
    }
}