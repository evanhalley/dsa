package dev.evanhalley.googleprep;

import dev.evanhalley.googleprep.util.ListNode;

import static dev.evanhalley.googleprep.util.ListNodeUtil.stringToListReversed;

/**
 n = 2
 head = 1 - 2
 head = 2

 * }
 */
public class RemoveNthNodeFromList {

    public static void main(String[] args) {
        System.out.println(removeNthFromEnd(stringToListReversed("1"), 1));
        System.out.println(removeNthFromEnd(stringToListReversed("12"), 1));
        System.out.println(removeNthFromEnd(stringToListReversed("12"), 2));
        System.out.println(removeNthFromEnd(stringToListReversed("1234"), 2));
        System.out.println(removeNthFromEnd(stringToListReversed("1234"), 4));
        System.out.println(removeNthFromEnd(stringToListReversed("1234"), 5));
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {

        if (n < 3) {
            if (head.next == null && n == 1) {
                return null;
            } else if (head.next != null && head.next.next == null) {

                if (n == 1) {
                    head.next = null;
                    return head;
                } else if (n == 2) {
                    head = removeHead(head);
                    return head;
                }
            }
        }
        int i = 0;
        ListNode scout = head;
        ListNode iterator = head;

        while (scout.next != null) {
            scout = scout.next;
            i++;

            if (i > n) {
                iterator = iterator.next;
            }
        }
        int listSize = i + 1;
        if (n > listSize) {
            return head;
        } else if (iterator == head && n == listSize) {
            head = removeHead(head);
        } else {
            removeChildNode(iterator);
        }
        return head;
    }

    public static ListNode removeHead(ListNode head) {
        ListNode temp = head;
        head = head.next;
        temp.next = null;
        return head;
    }

    public static void removeChildNode(ListNode nodeParent) {
        ListNode temp = nodeParent.next.next;
        nodeParent.next.next = null;
        nodeParent.next = temp;
    }
}
