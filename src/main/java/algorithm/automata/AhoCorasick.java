package algorithm.automata;

import algorithm.AcNode;
import algorithm.MatchResult;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author violet
 * @version 1.0
 * @since 2020/3/19 21:42
 */
public class AhoCorasick {

    private Tire trie = new Tire();

    /**
     * 插入模式船
     *
     * @param patterns
     */
    public void insert(Iterator<String> patterns) {
        while (patterns.hasNext()) {
            String pattern = patterns.next();
            if (StringUtils.isEmpty(pattern)) {
                continue;
            }
            trie.insert(pattern);
        }
        buildFailurePointer();
    }

    private void buildFailurePointer() {
        algorithm.AcNode p = trie.getRoot();

        Queue<algorithm.AcNode> queue = new LinkedList<>();
        while (p != null) {
            for (int i = 0; i < p.children.length; i++) {
                algorithm.AcNode pc = p.children[i];
                if (pc == null) {
                    continue;
                }
                queue.add(pc);

                algorithm.AcNode q = p.failure;
                while (q != null) {
                    algorithm.AcNode qc = q.children[pc.val - '/'];
                    if (qc == null) {
                        q = q.failure;
                    } else {
                        pc.failure = qc;
                        break;
                    }
                }
                if (pc.failure == null) {
                    pc.failure = trie.getRoot();
                }
            }
            p = queue.poll();
        }
    }

    public MatchResult match(String text) {
        return match(text, null, null);
    }

    public MatchResult match(String text, Integer start) {
        return match(text, start, null);
    }

    public MatchResult match(String text, Integer start, String pattern) {

        char[] chars = text.toCharArray();

        algorithm.AcNode root = trie.getRoot();
        algorithm.AcNode node = trie.getRoot();

        int i = start == null ? 0 : start;

        for (; i < chars.length; i++) {
            int index = chars[i] - '/';
            if (index < 0 || index > 75) {
                node = root;
                continue;
            }
            while (node.children[index] == null && node != root) {
                node = node.failure;
            }

            if (node.children[index] != null) {
                node = node.children[index];
            }

            AcNode tmp = node;
            while (tmp != root) {
                if (tmp.isEndChar) {
                    int matchIndex = i - tmp.length + 1;
                    String match = text.substring(matchIndex, matchIndex + tmp.length);
                    //当pattern不为空时需要匹配
                    if (pattern != null && !pattern.equals(match)) {
                        tmp = tmp.failure;
                        continue;
                    }
                    return new MatchResult(matchIndex, match);
                }
                tmp = tmp.failure;
            }
        }
        return null;
    }
}
