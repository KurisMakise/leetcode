package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/18 10:15
 */
public class MapReduce extends RecursiveTask<Map<String, Long>> {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        String[] words = new String[]{
                "this is a test",
                "test is good a thing",
        };
        MapReduce mapReduce = new MapReduce(words, 0, words.length);
        Map<String, Long> invoke = forkJoinPool.invoke(mapReduce);

        System.out.println(invoke);
    }

    public MapReduce(String[] words, int start, int end) {
        this.words = words;
        this.start = start;
        this.end = end;
    }

    private final String[] words;
    private final int start;
    private final int end;

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return cal();
        }
        int mid = (start + end) / 2;
        MapReduce mr1 = new MapReduce(words, start, mid);
        MapReduce mr2 = new MapReduce(words, mid , end );

        Map<String, Long> m1 = mr1.compute();
        mr2.fork();
        Map<String, Long> m2 = mr2.join();

        for (Map.Entry<String, Long> entry : m1.entrySet()) {
            m2.compute(entry.getKey(), (key, value) -> value == null ? entry.getValue() : entry.getValue() + value);
        }

        return m2;
    }

    private Map<String, Long> cal() {
        String[] word = words[start].split("\\s");
        Map<String, Long> map = new HashMap<>(word.length);

        for (String w : word) {
            map.compute(w, (k, v) -> v == null ? 1 : ++v);
        }
        return map;
    }
}
