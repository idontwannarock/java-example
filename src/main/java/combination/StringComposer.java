package combination;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class StringComposer {

    public List<String[]> compose(String[] combination, List<String>[] mappings) {
        List<String[]> sentences = new ArrayList<>();
        return appendToExpendCombination(sentences, mappings, 0, combination.length);
    }

    private List<String[]> appendToExpendCombination(List<String[]> sentences, List<String>[] mappings, int index, int length) {
        if (index == length) {
            return sentences;
        }
        List<String> currentMappings = mappings[index];
        if (sentences.isEmpty()) {
            for (String mapping : currentMappings) {
                String[] sentence = new String[]{mapping};
                sentences.add(sentence);
            }
        } else {
            List<String[]> tempSentences = new ArrayList<>();
            for (String[] sentence : sentences) {
                for (String mapping : currentMappings) {
                    String[] appendedSentence = ArrayUtils.add(sentence, mapping);
                    tempSentences.add(appendedSentence);
                }
            }
            sentences = tempSentences;
        }
        return appendToExpendCombination(sentences, mappings, index + 1, length);
    }
}
