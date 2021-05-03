package krumpet.heap;

import java.util.Comparator;
import java.util.stream.StreamSupport;

public class Heap<T> {
    private final T[] elements;
    private int size = 0;
    private final Comparator<T> comparator;

    public Heap(Comparator<T> comparator) {
        this(comparator, 100);
    }

    public Heap(Comparator<T> comparator, int capacity) {
        elements = (T[]) new Object[capacity];
        this.comparator = comparator;
    }

    public Heap(Comparator<T> comparator, Iterable<T> collection) {
        // TODO: optimize building the elements
        elements = (T[]) StreamSupport
                .stream(collection.spliterator(), false)
                .sorted(comparator)
                .toArray(Object[]::new);
        size = elements.length;
        this.comparator = comparator;
    }
}
