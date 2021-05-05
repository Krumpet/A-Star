package krumpet.heap;

import java.util.Comparator;

public class HeapFactory {
    // TODO: makeMaxHeap, same parameters, reverse the comparator
    public static <S extends Comparable<S>> Heap<S> makeMinHeap() {
        return HeapFactory.makeMinHeap(S::compareTo);
    }

    public static <T> Heap<T> makeMinHeap(Comparator<T> comparator) {
        return new Heap<>(comparator);
    }

    public static <S extends Comparable<S>> Heap<S> makeMinHeap(int capacity) {
        return HeapFactory.makeMinHeap(S::compareTo, capacity);
    }

    public static <T> Heap<T> makeMinHeap(Comparator<T> comparator, int capacity) {
        return new Heap<>(comparator, capacity);
    }

    public static <S extends Comparable<S>> Heap<S> makeMinHeap(Iterable<S> iterable) {
        return HeapFactory.makeMinHeap(S::compareTo, iterable);
    }

    public static <T> Heap<T> makeMinHeap(Comparator<T> comparator, Iterable<T> iterable) {
        return new Heap<>(comparator, iterable);
    }
}
