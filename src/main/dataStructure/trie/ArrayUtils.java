package dataStructure.trie;

public class ArrayUtils {

    static int binarySearch(TrieTree.TrieNode[] children, char key) {
        int high = children.length - 1;
        if (children.length < 1) {
            return high;
        }
        int low = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1; // divide by 2
            int cmp = children[mid].compareTo(key);

            if (cmp < 0) low = mid + 1;
            else if (cmp > 0) high = mid - 1;
            else return mid;
        }
        return -(low + 1);
    }

    private ArrayUtils() {}
}
