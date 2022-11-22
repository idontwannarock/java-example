package datetime;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;

public class TimestampTest {

    @Test
    public void test_timestamp_digit() {
        long currentTimeMillis = System.currentTimeMillis();
        Assertions.assertThat(String.valueOf(currentTimeMillis)).hasSize(13);
    }

    @Test
    public void test_timestamp_in_seconds_digit() {
        long epochSecond = Instant.now().getEpochSecond();
        Assertions.assertThat(String.valueOf(epochSecond)).hasSize(10);
    }
}
