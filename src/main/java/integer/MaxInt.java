package integer;

public class MaxInt {

    public <E extends Comparable<E>> E max(E[] list) {
        E max = list[0] ;
        for (E element : list) {
            if (max.compareTo(element) < 0) max = element;
        }
        return max;
    }
}
