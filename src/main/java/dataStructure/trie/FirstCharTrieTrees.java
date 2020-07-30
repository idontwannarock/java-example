package dataStructure.trie;

public class FirstCharTrieTrees {

    TrieTree[] trees;

    public FirstCharTrieTrees() {
        trees = new TrieTree[(int) Character.MAX_VALUE];
    }

    public void insert(String word) {
        char firstLetter = word.charAt(0);
        if (trees[firstLetter] == null) {
            trees[firstLetter] = new TrieTree(firstLetter);
        }
        TrieTree tree = trees[firstLetter];
        tree.put(word);
    }

    public boolean exist(String word) {
        char firstLetter = word.charAt(0);
        if (trees[firstLetter] == null) {
            System.out.println("trie tree for " + firstLetter + " does not exist");
            return false;
        } else {
            System.out.println("trie tree for " + firstLetter + " does exist");
            TrieTree tree = trees[firstLetter];
            return tree.search(word);
        }
    }
}
