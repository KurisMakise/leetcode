package algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author kurisu makise
 * @version 1.0
 * @date 2021/4/12 16:56
 */
public class MapReduce extends RecursiveTask<Map<String, Long>> {


    public static void main(String[] args) {
        String[] words = {

        };
        MapReduce mapReduce = new MapReduce(words, 0, words.length);

        ForkJoinPool forkJoinPool = new ForkJoinPool(4);
        try {
            System.out.println(forkJoinPool.submit(mapReduce).get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public MapReduce(String[] fc, int start, int end) {
        this.start = start;
        this.end = end;
        this.fc = fc;
    }

    private final int start;
    private final int end;
    private final String[] fc;

    @Override
    protected Map<String, Long> compute() {
        if (end - start == 1) {
            return calculate(fc[start]);
        }
        int middle = (end + start) / 2;
        MapReduce mr1 = new MapReduce(fc, start, middle);
        MapReduce mr2 = new MapReduce(fc, middle, end);
        mr1.fork();

        return merge(mr1.join(), mr2.compute());
    }

    private Map<String, Long> merge(Map<String, Long> r1, Map<String, Long> r2) {
        r1.forEach((key, value)
                -> r2.compute(key, (k, v) -> v == null ? value : v + value));
        return r2;
    }

    private Map<String, Long> calculate(String line) {
        Map<String, Long> result = new HashMap<>(30);

        String[] split = line.split("\\s+");
        for (String s : split) {
            result.compute(s, (key, value) -> value == null ? 1 : ++value);
        }

        return result;
    }


}
