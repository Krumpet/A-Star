package krumpet.heap;

import java.util.Comparator;

public class HeapFactory {
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

    public static <S extends Comparable<S>> Heap<S> makeMaxHeap() {
        return HeapFactory.makeMaxHeap(S::compareTo);
    }

    public static <T> Heap<T> makeMaxHeap(Comparator<T> comparator) {
        return new Heap<>(comparator.reversed());
    }

    public static <S extends Comparable<S>> Heap<S> makeMaxHeap(int capacity) {
        return HeapFactory.makeMaxHeap(S::compareTo, capacity);
    }

    public static <T> Heap<T> makeMaxHeap(Comparator<T> comparator, int capacity) {
        return new Heap<>(comparator.reversed(), capacity);
    }

    public static <S extends Comparable<S>> Heap<S> makeMaxHeap(Iterable<S> iterable) {
        return HeapFactory.makeMaxHeap(S::compareTo, iterable);
    }

    public static <T> Heap<T> makeMaxHeap(Comparator<T> comparator, Iterable<T> iterable) {
        return new Heap<>(comparator.reversed(), iterable);
    }
}
