package future;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureTest {

    @Test
    public void applyAsync() {
        AtomicInteger counter = new AtomicInteger();

        ForkJoinPool.ForkJoinWorkerThreadFactory factory = pool -> {
            ForkJoinWorkerThread thread =
                    ForkJoinPool.defaultForkJoinWorkerThreadFactory.newThread(pool);

            thread.setName("test-" + counter.incrementAndGet());

            return thread;
        };

        List<String> indexes = Stream.of("1", "2", "3").parallel().map(index -> CompletableFuture.supplyAsync(() -> {
                    System.out.println("index " + index + " starts to work");
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("index " + index + " finished working.");
                    return index;
                }, new ForkJoinPool(Runtime.getRuntime().availableProcessors(), factory, null, false))).map(CompletableFuture::join)
                .collect(Collectors.toList());
        Assertions.assertThat(indexes).hasSize(3);
    }
}
