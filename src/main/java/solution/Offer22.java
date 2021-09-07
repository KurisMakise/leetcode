package solution;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/9/2 9:18
 */
public class Offer22 {

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode first = head, second = head;

        while (--k > 0) {
            first = first.next;
            if (first == null) {
                first = head;
            }
        }
        while (first.next != null) {
            first = first.next;
            second = second.next;
        }

        return second;
    }



    public static void main(String[] args) {
        Offer22 offer22 = new Offer22();
        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);
        System.out.println(offer22.getKthFromEnd(listNode, 8));
        System.out.println(offer22.getKthFromEnd(listNode, 3));
    }
}
