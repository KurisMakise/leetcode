package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/24 12:27
 */
public class Code206_2 {

    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode tmp = head.next;
            head.next = pre;
            pre = head;
            head = tmp;
        }
        return pre;
    }

    ListNode t;

    public ListNode recursion(ListNode head) {
        if (head.next == null) {
            t = head;
            return head;
        }
        ListNode reverse = recursion(head.next);
        head.next.next = head;
        head.next = null;
        return reverse;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode tmp = listNode;
        listNode.next = new ListNode(2);
        listNode = listNode.next;
        listNode.next = new ListNode(3);
        listNode = listNode.next;
        listNode.next = new ListNode(4);
        listNode = listNode.next;
        listNode.next = new ListNode(5);

        Code206_2 code206_2 = new Code206_2();
        System.out.println(code206_2.recursion(tmp));
    }


}
