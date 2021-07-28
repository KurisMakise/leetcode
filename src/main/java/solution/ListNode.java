package solution;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author violet
 * @version 1.0
 * @since 2020/1/9 0:10
 */
public class ListNode {
    int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
        next = null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        ListNode tmp = next;

        while (tmp != null) {
            sb.append(tmp.val);
            tmp = tmp.next;
        }
        return sb.toString();
    }
}
