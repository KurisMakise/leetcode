import org.junit.Test;
import solution.Code206;
import solution.Code206_1;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author violet
 * @version 1.0
 * @since 2020/1/2 19:26
 */
public class Test206 {

    @Test
    public void test() {
        Code206.ListNode head = new Code206.ListNode(1);
        head.next = new Code206.ListNode(2);
        head.next.next = new Code206.ListNode(3);
        head.next.next.next = new Code206.ListNode(4);

        Code206 code206 = new Code206();
        code206.reverseList(head);

    }

    @Test
    public void test1() {

    }
}
