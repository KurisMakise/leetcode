package algorithm;

import algorithm.automata.AhoCorasick;
import org.junit.Test;

import java.util.Arrays;

/**
 * <p>
 * </p>
 *
 * @author violet
 * @version 1.0
 * @since 2020/3/19 23:35
 */
public class AhoCorasickTest {

    @Test
    public void test() {
        AhoCorasick ahoCorasick = new AhoCorasick();
//        String[] patterns = {"abcd", "bcd", "c", "bce"};
        String[] patterns = {"good", "happy", "script", "html", "go", "g", "cri"};
        ahoCorasick.insert(Arrays.asList(patterns).iterator());

//        ahoCorasick.match("this is a good day, i am very happy, script  not script html5 is fine");
        System.out.println('A' - '/');
        System.out.println('A' - '>');
        System.out.println('z' - '/');
        System.out.println((int) 'A' + "A");
        System.out.println((int) 'a' + "a");
        System.out.println((int) 'Z' + "Z");
        System.out.println((int) 'z' + "z");
        System.out.println((int) '/' + "/");
        System.out.println((int) '<' + "<");
        System.out.println((int) '>' + ">");
    }
}
