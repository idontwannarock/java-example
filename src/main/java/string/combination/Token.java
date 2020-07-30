package string.combination;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Token {

    private String word;
    private Integer startIndex;
}
