package solution;

/**
 * @author makise
 * @version 1.0
 * @date 2021/7/13 22:46
 */
public class Code208 {
    public static void main(String[] args) {
        Code208 code208 = new Code208();
        Trie trie = code208.new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));// 返回 True
        System.out.println(trie.search("app"));    // 返回 False
        System.out.println(trie.startsWith("app")); // 返回 True
        trie.insert("app");
        System.out.println(trie.search("app"));     // 返回 True

    }

    public class Trie {
        private final TireNode root = new TireNode();

        /**
         * Initialize your data structure here.
         */
        public Trie() {

        }

        /**
         * Inserts a word into the trie.
         */
        public void insert(String word) {
            TireNode tmpNode = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (tmpNode.children[index] == null) {
                    tmpNode.children[index] = new TireNode();
                }
                tmpNode = tmpNode.children[index];
            }
            tmpNode.end = true;
        }

        /**
         * Returns if the word is in the trie.
         */
        public boolean search(String word) {
            return searchPrefix(word).end;
        }

        /**
         * Returns if there is any word in the trie that starts with the given prefix.
         */
        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != root;
        }

        public TireNode searchPrefix(String prefix) {
            TireNode tmpNode = root;
            for (int i = 0; i < prefix.length(); i++) {
                char c = prefix.charAt(i);
                int index = c - 'a';
                if (tmpNode.children[index] == null) {
                    return root;
                }
                tmpNode = tmpNode.children[index];
            }
            return tmpNode;
        }
    }

    class TireNode {
        public TireNode[] children = new TireNode[26];
        public boolean end;
    }


}
