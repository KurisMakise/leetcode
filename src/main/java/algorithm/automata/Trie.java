package algorithm.automata;

/**
 * @author VIOLET
 * @version 1.0
 * @date 2020/6/22 11:33
 */
public class Trie {

    private TireNode root = new TireNode('/');

    public void insert(String text) {
        char[] dataArr = text.toCharArray();
        TireNode node = root;
        for (char data : dataArr) {
            node = node.add(data);
        }
        node.isEndingChar = true;
    }

    public int find(String text) {
        char[] dataArr = text.toCharArray();

        for (char data : dataArr) {

        }
        return 0;
    }


    public class TireNode {
        private char data;
        private TireNode[] children = new TireNode[26];
        private boolean isEndingChar = false;

        public TireNode add(char data) {
            int index = data - 'a';
            if (children[index] == null) {
                children[index] = new TireNode(data);
            }
            return children[index];
        }

        public TireNode(char data) {
            this.data = data;
        }
    }
}
