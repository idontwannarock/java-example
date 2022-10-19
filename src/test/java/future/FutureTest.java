package future;

import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class FutureTest {

    @Test
    public void givenListOfCompletableFutures_whenExecuteSuccessfully_returnCorrectNumberOfResults() {
        // arrange
        List<String> inputs = List.of("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

        // act
        List<String> results = inputs.stream().map(input -> CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(50);
                return input;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        })).map(CompletableFuture::join).collect(Collectors.toList());

        // assert
        assertThat(results.size(), Matchers.is(10));
    }
}
