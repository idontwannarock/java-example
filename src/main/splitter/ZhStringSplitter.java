package splitter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

import static java.lang.Character.isDigit;

public class ZhStringSplitter {

    /**
     * Used by TreeSet to maintain the order, its equality,
     * and to avoid adding the same item more than once
     */
    public static final Comparator<String[]> STRING_ARRAY_COMPARATOR = (o1, o2) -> {
        if (o1 == null) return +1;
        if (o2 == null) return -1;
        if (o1.length < o2.length) return +1;
        if (o1.length > o2.length) return -1;
        for (int i = 0; i < o1.length; i++) {
            if (o1[i].length() != o2[i].length()) {
                if (o1[i].length() < o2[i].length()) return -1;
                else return +1;
            }
        }
        return 0;
    };

    /**
     * Main Split method to be used by other programs.
     *
     * It first splits a question by a space,
     * and put all substring into the recursive method,
     * which creates all combinations of tokens of a String.
     *
     * @param question is a string to be split
     * @param addSpaceCombinedTokens is a flag
     *                               to determine whether space-split tokens
     *                               should be combined as a combination in the result.
     *                               (e.g. "unit cost" -> {["unit","cost"],["unit cost"]})
     * @return all combinations of tokens
     */
    public TreeSet<String[]> split(String question, boolean addSpaceCombinedTokens) {
        if (StringUtils.isBlank(question)) return new TreeSet<>(STRING_ARRAY_COMPARATOR);

        // Split question by space
        String[] tokens = question.trim().split(" ");

        // Generate the result of the first substring
        TreeSet<String[]> firstTreeSet = splitNoSpace(tokens[0],0, new HashMap<>());

        // If question could not be split to more than one token by space,
        // return firstTreeSet directly
        if (tokens.length < 2) return firstTreeSet;

        List<TreeSet<String[]>> allTreeSets = new ArrayList<>();
        allTreeSets.add(firstTreeSet);

        // Generate all other results of the rest of substrings
        for (int i = 1; i < tokens.length; i++) {
            allTreeSets.add(splitNoSpace(tokens[i],0, new HashMap<>()));
        }

        return join(allTreeSets, addSpaceCombinedTokens);
    }

    /**
     * Join tree sets into a set of combinations with orders
     *
     * @param treeSets to be joined
     * @param addCombinedTokens is a flag
     *                          to determine whether to add combined tokens,
     *                          both with space and without space,
     *                          from tree sets to form new combinations
     * @return all combinations of tree sets
     */
    public TreeSet<String[]> join(List<TreeSet<String[]>> treeSets, boolean addCombinedTokens) {
        if (isEmpty(treeSets)) return new TreeSet<>(STRING_ARRAY_COMPARATOR);

        TreeSet<String[]> finalTreeSet = new TreeSet<>(STRING_ARRAY_COMPARATOR);

        // Join all tree sets together
        for (TreeSet<String[]> strings : treeSets) {
            TreeSet<String[]> result = new TreeSet<>(STRING_ARRAY_COMPARATOR);

            if (strings.isEmpty()) {
                result = finalTreeSet;
            } else if (finalTreeSet.isEmpty()) {
                result = strings;
            } else {
                for (String[] first : finalTreeSet) {
                    for (String[] other : strings) {
                        result.add(ArrayUtils.addAll(first, other));

                        if (addCombinedTokens) {
                            // Concatenate the last string of first
                            // with space and the first string of the other
                            // to form a new combination of tokens
                            String[] newOther = Arrays.copyOf(other, other.length);
                            newOther[0] = first[first.length - 1] + " " + newOther[0];
                            result.add(ArrayUtils.addAll(Arrays.copyOfRange(first, 0, first.length - 1), newOther));

                            // Do the same thing above without space
                            newOther = Arrays.copyOf(other, other.length);
                            newOther[0] = first[first.length - 1] + newOther[0];
                            result.add(ArrayUtils.addAll(Arrays.copyOfRange(first, 0, first.length - 1), newOther));
                        }
                    }
                }
            }
            finalTreeSet = result;
        }

        return finalTreeSet;
    }

    /**
     * @param question the target question (since it's recursive, it can be substring)
     * @param depth for debugging purpose only to print and see how deep the recursion has gone
     * @param cacheMap for performance boosting that if a substring has been completed once, it will not attempt to perform recursive again
     * @return all possible combination of a question split (a.k.a combinations of tokens)
     */
    private TreeSet<String[]> splitNoSpace(String question, int depth, HashMap<String, TreeSet<String[]>> cacheMap) {
        // If this question was done already, return directly
        if (cacheMap.containsKey(question)) {
            return cacheMap.get(question);
        }

        // Use TreeSet to avoid recording the same combination of tokens
        TreeSet<String[]> result = new TreeSet<>(STRING_ARRAY_COMPARATOR);

        if (StringUtils.isEmpty(question)) return result;

        if (question.length() == 1) {
            result.add(new String[] {question});
            return result;
        }

        // Main logic here.
        // It split a question from index 0 for all possible splits
        // and recursively create all combinations of tokens.
        for (int i = 1; i <= question.length(); ) {
            // Find the furthest index split point
            int splitIndex = findSplitIndex(question,i-1);

            // Cut out the first part
            String firstPart = question.substring(0, splitIndex);

            // Recursively find all the possible combinations
            // of tokens for the second part
            TreeSet<String[]> secondPart = splitNoSpace(question.substring(splitIndex),depth+1, cacheMap);

            if (secondPart.isEmpty()) {
                result.add(new String[] {firstPart});
            } else {
                for (String[] second : secondPart) {
                    // Add two token arrays together
                    result.add(ArrayUtils.addAll(new String[] {firstPart}, second));

                    // Concatenate the string of first part and the first String of the second
                    // to form a new combination of tokens
                    String[] newSecond = Arrays.copyOf(second, second.length);
                    newSecond[0] = firstPart + newSecond[0];
                    result.add(newSecond);
                }
            }
            i = Math.max(i+1, splitIndex+1);
        }

        cacheMap.put(question, result);
        return result;
    }

    /**
     * Try to find the first split index.
     *
     * A 'split index' could be the next index of
     * either the first Chinese character,
     * or the first consecutive numeric string,
     * or the first consecutive alphabets.
     *
     * @param segment is a given string (substring of a question in this case)
     * @param index to indicate where this method should use to start looking for a 'split index'
     * @return the split index
     */
    private int findSplitIndex(String segment, int index) {
        if (segment.length() <= index) return segment.length() + 1;

        if (!isAlphabet(segment.charAt(index)) && !isDigit(segment.charAt(index))) {
            // For the case that the first character is neither an alphabet nor a digit,
            // it should return index + 1
            return index + 1;
        } else if (isAlphabet(segment.charAt(index))) {
            // For alphabet, return the next index of the consecutive alphabets
            for (int i = index; i < segment.length(); i++) {
                if (!isAlphabet(segment.charAt(i))) return i;
            }
        } else if ( isDigit(segment.charAt(index)) ) {
            // For digit, return the next index of the consecutive digits
            for ( int i = index ; i < segment.length() ; i++ ) {
                if (!isDigit(segment.charAt(i))) return i;
            }
        }

        return segment.length();
    }

    private boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    private boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
    }
}
