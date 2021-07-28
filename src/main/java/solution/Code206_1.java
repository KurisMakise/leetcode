package solution;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author violet
 * @version 1.0
 * @since 2020/1/2 21:15
 */
public class Code206_1 {

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode p = reverseList(head.next);

        head.next.next = head;
        head.next = null;
        return p;
    }

    public static void main(String[] args) {
        solution.ListNode listNode = new solution.ListNode(1);
        solution.ListNode tmp = listNode;
        listNode.next = new solution.ListNode(2);
        listNode = listNode.next;
        listNode.next = new solution.ListNode(3);
        listNode = listNode.next;
        listNode.next = new solution.ListNode(4);
        listNode = listNode.next;
        listNode.next = new solution.ListNode(5);

        Code206_1 code206_2 = new Code206_1();
        System.out.println(code206_2.reverseList(tmp));
    }

}
