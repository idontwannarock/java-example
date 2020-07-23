package splitter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ZhStringSplitter {

    /**
     * Used by TreeSet to maintain the order and it's equality and to avoid adding the same item more than once
     */
    private static Comparator<String[]> stringArrayComparator = new Comparator<String[]>() {
        @Override
        public int compare(String[] o1, String[] o2) {
            if ( o1 == null ) return +1;
            if ( o2 == null ) return -1;
            if ( o1.length < o2.length ) {
                return +1;
            }
            if ( o1.length > o2.length ) {
                return -1;
            } else {
                for ( int i = 0 ; i < o1.length ; i++ ) {
                    if ( o1[i].length() == o2[i].length() ) {
                        continue;
                    } else if ( o1[i].length() < o2[i].length() ) {
                        return -1;
                    } else {
                        return +1;
                    }
                }
            }
            return 0;
        }
    };

    /**
     *
     * @param item a given string (substring of a question in this case)
     * @param index an index where this method should use to start looking for a 'split index'
     * @return It will attempt to find the longest split index while grouping all the integer together or all the English letter together.
     */
    public static int findSplitIndex(String item,int index) {
        if ( item.length() <= index ) return item.length()+1;

        //for the case where index is 0, it should return index+1;
        if ( ! ((item.charAt(index) >= 'a' && item.charAt(index) <= 'z') || (item.charAt(index) >= 'A' && item.charAt(index) <= 'Z') || Character.isDigit(item.charAt(index)) ) ) {
            return index+1;

            //for English letter, once it finds the first one, it attempts to find all trailing English letter till it can't
        } else if ( (item.charAt(index) >= 'a' && item.charAt(index) <= 'z') || (item.charAt(index) >= 'A' && item.charAt(index) <= 'Z') ) {
            for ( int i = index ; i < item.length() ; i++ ) {
                if ( ! ((item.charAt(i) >= 'a' && item.charAt(i) <= 'z') || (item.charAt(i) >= 'A' && item.charAt(i) <= 'Z') ) ) {
                    return i;
                }
            }

            //for digit, once it finds the first one, it attempts to find all trailing digit till it can't
        } else if ( Character.isDigit(item.charAt(index)) ) {
            for ( int i = index ; i < item.length() ; i++ ) {
                if ( ! Character.isDigit(item.charAt(i)) ) {
                    return i;
                }
            }
        }

        return item.length();
    }

    /**
     * @param question the target question (since it's recursive, it can be substring)
     * @param depth for debugging purpose only to print and see how deep the recursion has gone
     * @param cacheMap for performance boosting that if a substring has been completed once, it will not attempt to perform recursive again
     * @return all possible combination of a question split (a.k.a combinations of tokens)
     */
    public static TreeSet<String[]> splitNoSpace(String question, int depth, HashMap<String, TreeSet<String[]>> cacheMap) {
        //if this question was done already, return
        if ( cacheMap.containsKey(question) ) {
            return cacheMap.get(question);
        }

        //Use TreeSet to avoid recording the same combination of tokens
        TreeSet<String[]> result = new TreeSet<String[]>(stringArrayComparator);

        if ( question == null || question.length() == 0 ) {
            return result;
        }

        if ( question.length() == 1 ) {
            result.add(new String[]{question});
            return result;
        }

        //main logic here. It split a question from index 0 for all possible splits
        //and recursively create all combinations of tokens.
        for ( int i = 1 ; i <= question.length() ; ) {
            //find the furthest index split point
            int findSplitIndex = findSplitIndex(question,i-1);

            //cut out the first part
            String firstPart = question.substring(0,findSplitIndex);

            //recursively find all the possible combinations of tokens for the second half
            TreeSet<String[]> secondPart = splitNoSpace(question.substring(findSplitIndex,question.length()),depth+1,cacheMap);
            if ( secondPart.size() == 0 ) {
                result.add(ArrayUtils.addAll(new String[] {firstPart}));
            } else {
                for ( String[] second : secondPart ) {
                    //Add two token arrays together
                    result.add(ArrayUtils.addAll(new String[] {firstPart}, second));

                    //concatenate the last String of first and the first String of the second
                    //And form a new combination of tokens
                    String[] newSecond = Arrays.copyOf(second, second.length);
                    newSecond[0] = firstPart + newSecond[0];
                    result.add(newSecond);
                }
            }
            i = Math.max(i+1, findSplitIndex+1);
        }

        cacheMap.put(question, result);
        return result;
    }

    /**
     * Main Split method to be used by other programs. It first splits a question by a space, and put all substring into
     * the recursive method, which creates all combinations of tokens of a String.
     *
     * @param question A question String
     * @return all combinations of tokens
     */
    public static TreeSet<String[]> split(String question) {
        if ( StringUtils.isBlank(question) ) return new TreeSet<String[]>();

        String[] tokens = question.trim().split(" ");

        //Generate the result of the first substring
        TreeSet<String[]> firstTreeSet = splitNoSpace(tokens[0],0,new HashMap<String,TreeSet<String[]>>());

        if ( tokens.length < 2 ) return firstTreeSet;

        //Generate all other results of the rest of substrings
        List<TreeSet<String[]>> otherTreeSetList = new ArrayList<TreeSet<String[]>>();
        for ( int i = 1 ; i < tokens.length ; i++ ) {
            otherTreeSetList.add(splitNoSpace(tokens[i],0,new HashMap<String,TreeSet<String[]>>()));
        }

        //Join the first and the rest of results together
        Iterator<TreeSet<String[]>> iter = otherTreeSetList.iterator();
        while (iter.hasNext()) {
            TreeSet<String[]> result = new TreeSet<String[]>(stringArrayComparator);
            TreeSet<String[]> nextTreeSet = iter.next();

            if ( nextTreeSet.size() == 0 ) {
                result = firstTreeSet;
            } else if ( firstTreeSet.size() == 0 ) {
                result = nextTreeSet;
            } else {
                for ( String[] first : firstTreeSet ) {
                    for ( String[] other : nextTreeSet ) {
                        result.add(ArrayUtils.addAll(first, other));
                    }
                }
            }
            firstTreeSet = result;
        }

        return firstTreeSet;
    }

    /**
     * When the string length is greater than 20 character long, it will take 1 second to fully split.
     * When there is space, the token between the space will be treated as "one unit" to be split.
     */
    public static void main(String[] args) {
        System.out.println("Test starts");

        String[] testStringArray = new String[] {"今天我們二十八年過後的水果是不是好的","1998","abc","1998abc","1998 1994 abc 1997 cde 1003"
                ,"一二三四五","一二三四五六七八九十","一二三四五六七八九十一二三四五","一二三四五六七八九十一二三四五六七八九十"
                ,"一二三四五六七八九十 一二三四五六七八九十","一二三四五 六七八九十 一二三四五 六七八九十", "2019年SlQG-35256012的概況","1998一二三 mno 1997 三 abc1994xyz四五"};

        for ( String testString : testStringArray ) {
            System.out.println("\n\n\nTest for '" + testString + "'; length = " + testString.length());
            long currentTime = System.currentTimeMillis();

            TreeSet<String[]> resultList = split(testString);

            long finalTime = System.currentTimeMillis() - currentTime;

            for ( String[] result : resultList ) {
                System.out.println(Arrays.toString(result));
            }

            System.out.println("Total splits: " + resultList.size());
            System.out.println("Total time taken: " + finalTime);
        }

        System.out.println("Test ends");
    }
}
