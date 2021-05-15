package krumpet.heap;

import java.util.NoSuchElementException;

public interface IHeap<T> {
    int DEFAULT_CAPACITY = 100;

    void add(T item) throws IllegalArgumentException;

    void clear();

    int size();

    boolean isEmpty();

    T pop() throws NoSuchElementException;

    T peek() throws NoSuchElementException;
}
