package main.segment;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Token {

    private String word;
    private Integer startIndex;
    private SymbolType type;

    public Token(String word, Integer startIndex) {
        this.word = word;
        this.startIndex = startIndex;
    }
}
