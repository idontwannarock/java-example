package main.segment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import static main.segment.SymbolType.Unknown;

public class MappingSegment {

    private HashMap<String, List<SymbolType>> mappings = new HashMap<>();
    private int maxLength = 0;

    public void addTokenList(List<String> words, SymbolType type) {
        for (String word : words) {
            if (mappings.containsKey(word)) {
                mappings.get(word).add(type);
            } else {
                mappings.put(word, Collections.singletonList(type));
                maxLength = Math.max(maxLength, word.length());
            }
        }
    }

    public List<Token> segment(String sentence) {
        List<Token> dtoEntities = new ArrayList<>();
        int unknownIndex = 0;
        int startIndex = 0;
        int offset = 1;
        while (startIndex != sentence.length()) {
            while (offset <= this.maxLength && (startIndex + offset) <= sentence.length() ) {
                String substring = sentence.substring(startIndex, startIndex + offset);
                if (mappings.containsKey(substring)) {
                    if (unknownIndex < startIndex) {
                        String unknown = sentence.substring(unknownIndex, startIndex);
                        Token dtoEntity = new Token();
                        dtoEntity.setWord(unknown);
                        dtoEntity.setType(Unknown);
                        dtoEntities.add(dtoEntity);
                    }
                    for (SymbolType tokenType : mappings.get(substring)) {
                        Token dtoEntity = new Token();
                        dtoEntity.setWord(substring);
                        dtoEntity.setType(tokenType);
                        dtoEntities.add(dtoEntity);
                    }
                    unknownIndex = startIndex + offset;
                }
                offset += 1;
            }
            startIndex += 1;
            offset = 1;
        }
        if (unknownIndex + offset < sentence.length()) {
            String unknown = sentence.substring(unknownIndex);
            Token dtoEntity = new Token();
            dtoEntity.setWord(unknown);
            dtoEntity.setType(Unknown);
            dtoEntities.add(dtoEntity);
        }
        return dtoEntities;
    }
}
