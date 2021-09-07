package solution.offer;

import solution.ListNode;

import java.util.Stack;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/9/2 18:36
 */
public class Offer025 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode l3 = new ListNode(0);
        ListNode ans = l3;
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }
        int carry = 0;
        while (!stack1.isEmpty() && !stack2.isEmpty()) {
            int num = stack1.pop() + stack2.pop();
            l3.val = num % 10 + carry;
            l3.next = new ListNode(0);
            l3 = l3.next;
            carry = num / 10;
        }

        if (stack1.isEmpty()) {
            stack1 = stack2;
        }
        while (!stack1.isEmpty()) {
          
        }

        return ans;
    }
}
