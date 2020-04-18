package character;

public class CharacterCounter {

    public int count(String word) {
        int count = 0;
        for (char c : word.toCharArray()) {
            count++;
            int code = c;
            System.out.println(c + " unicode: " + code);
        }
        return count;
    }
}
