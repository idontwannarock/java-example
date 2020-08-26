package string;

public class SameCharacterCounter {

    /**
     * 比較兩個字串有幾個 character 相同，不重複計算
     */
    public int count(String first, String second) {
        long start = System.currentTimeMillis();
        int characterMatched = 0;
        char[] firstCharacters = first.toLowerCase().toCharArray();
        char[] secondCharacters = second.toLowerCase().toCharArray();
        for (int indexOfAliasCharacters = 0; indexOfAliasCharacters < firstCharacters.length; indexOfAliasCharacters++) {
            for (int indexOfWordCharacters = 0; indexOfWordCharacters < secondCharacters.length; indexOfWordCharacters++) {
                if (firstCharacters[indexOfAliasCharacters] != Character.MIN_VALUE &&
                        secondCharacters[indexOfWordCharacters] != Character.MIN_VALUE &&
                        firstCharacters[indexOfAliasCharacters] == secondCharacters[indexOfWordCharacters]) {
                    characterMatched++;
                    firstCharacters[indexOfAliasCharacters] = Character.MIN_VALUE;
                    secondCharacters[indexOfWordCharacters] = Character.MIN_VALUE;
                }
            }
        }
        long cost = System.currentTimeMillis() - start;
        System.out.println("cost: " + cost);
        return characterMatched;
    }
}
