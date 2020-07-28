package dataStructure.trie;

public class TrieTree {

    TrieNode root;

    public TrieTree(char firstLetter) {
        root = new TrieNode(firstLetter, false);
    }

    protected void put(String word) {
        if (word.length() == 0) return;  // 安全起见
        TrieNode branch = this.root;
        char[] chars = word.toCharArray();
        for (int i = 1; i < chars.length - 1; ++i) {
            // 除了最后一个字外，都是继续
            branch.addChild(new TrieNode(chars[i], false));
            branch = branch.getChild(chars[i]);
        }
        // 最后一个字加入时属性 isWord 为 true
        branch.addChild(new TrieNode(chars[chars.length - 1], true));
    }

    // Returns if the word is in the trie.
    protected boolean search(String word) {
        TrieNode node = searchPrefix(word);
        return node != null && node.isWord();
    }

    // search a prefix or whole key in trie and
    // returns the node where search ends
    private TrieNode searchPrefix(String word) {
        TrieNode node = root;
        for (int i = 1; i < word.length(); i++) {
            char curLetter = word.charAt(i);
            if (node != null && ArrayUtils.binarySearch(node.children, curLetter) >= 0) {
                node = node.getChild(curLetter);
            } else {
                return null;
            }
        }
        return node;
    }

    static class TrieNode implements Comparable<TrieNode> {

        char c;
        TrieNode[] children;
        boolean isWord;

        private TrieNode(char c, boolean isWord) {
            this.c = c;
            this.isWord = isWord;
            this.children = new TrieNode[0];
        }

        private boolean isWord() {
            return isWord;
        }

        private TrieNode getChild(char c) {
            if (children == null) {
                return null;
            }
            int index = ArrayUtils.binarySearch(this.children, c);
            if (index < 0) {
                return null;
            }
            return children[index];
        }

        @Override
        public int compareTo(TrieNode o) {
            return Character.compare(this.c, o.c);
        }

        int compareTo(char other) {
            return Character.compare(this.c, other);
        }

        void addChild(TrieNode newNode) {
            if (children == null) {
                children = new TrieNode[0];
            }
            int index = ArrayUtils.binarySearch(this.children, newNode.c);
            if (index >= 0) {
                TrieNode target = children[index];
                if (newNode.isWord && !target.isWord) {
                    target.isWord = true;
                }
            } else {
                TrieNode[] newChildren = new TrieNode[children.length + 1];
                int insert = -(index + 1);
                System.arraycopy(children, 0, newChildren, 0, insert);
                System.arraycopy(children, insert, newChildren, insert + 1, children.length - insert);
                newChildren[insert] = newNode;
                children = newChildren;
            }
        }
    }
}
