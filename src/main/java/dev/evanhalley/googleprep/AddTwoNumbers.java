package dev.evanhalley.googleprep;

import dev.evanhalley.googleprep.util.ListNode;

import static dev.evanhalley.googleprep.util.ListNodeUtil.listToString;
import static dev.evanhalley.googleprep.util.ListNodeUtil.stringToListReversed;

public class AddTwoNumbers {

    public static void main(String[] args) {
        System.out.println(listToString(addTwoNumbers(stringToListReversed("243"), stringToListReversed("564"))));
        System.out.println(listToString(addTwoNumbers(stringToListReversed("654321"), stringToListReversed("987"))));
        System.out.println(listToString(addTwoNumbers(stringToListReversed("9999999"), stringToListReversed("9999"))));
        System.out.println(listToString(addTwoNumbers(stringToListReversed("0"), stringToListReversed("0"))));
    }

    public static ListNode addTwoNumbersRecursively(ListNode listNode1, ListNode listNode2, int carry) {
        if ((listNode1 == null && listNode2 == null) && carry == 0) {
            return null;
        }

        int value1 = listNode1 != null ? listNode1.val : 0;
        int value2 = listNode2 != null ? listNode2.val : 0;
        int sum = value1 + value2 + carry;

        if (sum / 10 > 0) {
            carry = 1;
            sum -= 10;
        } else {
            carry = 0;
        }
        return new ListNode(sum, addTwoNumbersRecursively(listNode1 != null ? listNode1.next : null,
                listNode2 != null ? listNode2.next : null, carry));
    }

    public static ListNode addTwoNumbers(ListNode listNode1, ListNode listNode2) {
        return addTwoNumbersRecursively(listNode1, listNode2, 0);
    }
}
