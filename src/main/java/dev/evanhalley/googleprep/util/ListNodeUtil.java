package dev.evanhalley.googleprep.util;


public class ListNodeUtil {

    public static String listToString(ListNode listNode) {

        if (listNode == null) {
            return String.valueOf("");
        }

        if (listNode.next != null) {
            return listToString(listNode.next) + listNode.val;
        } else {
            return String.valueOf(listNode.val);
        }
    }

    public static ListNode stringToListReversed(String string) {
        ListNode listNode = null;

        for (int i = string.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(string.charAt(i));

            if (listNode == null) {
                listNode = new ListNode(digit);
            } else {
                listNode = new ListNode(digit, listNode);
            }
        }
        return listNode;
    }

    public static ListNode stringToList(String string) {
        ListNode listNode = null;

        for (int i = 0; i < string.length(); i++) {
            int digit = Character.getNumericValue(string.charAt(i));

            if (listNode == null) {
                listNode = new ListNode(digit);
            } else {
                listNode = new ListNode(digit, listNode);
            }
        }
        return listNode;
    }

}
