package solution;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/8/28 0:12
 */
public class Code295 {
    private PriorityQueue<Integer> minQueue = new PriorityQueue<>(Comparator.comparingInt(value -> value));
    private PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);

    public void addNum(int num) {
        if (!maxQueue.isEmpty() && num < maxQueue.peek()) {
            maxQueue.offer(num);
            if (maxQueue.size() > minQueue.size()) {
                minQueue.offer(maxQueue.poll());
            }
        } else {
            minQueue.offer(num);
            if (maxQueue.size() + 1 < minQueue.size()) {
                maxQueue.offer(minQueue.poll());
            }
        }
    }

    public double findMedian() {
        if (maxQueue.size() < minQueue.size()) {
            return minQueue.peek();
        }
        return (maxQueue.peek() + minQueue.peek()) / 2.0;
    }

    public static void main(String[] args) {
        Code295 code295 = new Code295();
        code295.addNum(-1);
        System.out.println(code295.findMedian());
        code295.addNum(-2);
        System.out.println(code295.findMedian());
        code295.addNum(-3);
        System.out.println(code295.findMedian());
        code295.addNum(-4);
        System.out.println(code295.findMedian());
        code295.addNum(-5);
        System.out.println(code295.findMedian());
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */