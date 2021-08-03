package algorithm;

import lombok.Data;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/6/15 15:43
 */
public class TestList {

    @Data
    private static class List {
        private int val;
        private List next;

        public List(int val) {
            this.val = val;
        }
    }

    public static void main(String[] args) {
        List head;
        List list = new List(1);
        head = list;
        head.next = new List(2);
        head = head.next;
        head.next = new List(3);
        head = head.next;
        head.next = new List(4);
        head = head.next;
        head.next = new List(5);
        List revert = revert(null, list);
        System.out.println(revert);

    }

    public static List revert(List front, List head) {
        if (head == null) {
            return front;
        }
        List temp = head.next;
        head.next = front;
        return revert(head, temp);
    }

}
