package main.segment;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
public class Token {

    private String word;
    private SymbolType type;
}
