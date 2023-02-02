package integer;

import org.junit.Assert;
import org.junit.Test;

import java.util.Random;

public class MaxIntTest {

    @Test
    public void givenFiveRandomInt_whenFindMax_shouldNotReturnNull() {
        // arrange
        Integer[] intArray = new Integer[5];
        Random numRand = new Random();
        int min = 0;
        int max = 10000;
        for (int i = 0; i < 5; i++) {
            intArray[i]  = numRand.nextInt(max - min) + min;
            System.out.println(i + 1 + " - " + intArray[i] );
        }

        // act
        Integer maxInt = new MaxInt().max(intArray);

        // assert
        Assert.assertNotNull(maxInt);
    }

    @Test
    public void givenFiveInt_whenFindMax_shouldReturnMaxInt() {
        // arrange
        Integer[] intArray = {6109, 8792, 6191, 2645, 4789};

        // act
        int maxInt = new MaxInt().max(intArray);

        // assert
        Assert.assertEquals(8792, maxInt);
    }
}
