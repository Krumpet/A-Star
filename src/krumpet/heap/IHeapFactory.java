package krumpet.heap;

import java.util.Comparator;

public interface IHeapFactory {
    static <S extends Comparable<S>> IHeap<S> makeMinHeap() {
        return IHeapFactory.makeMinHeap(S::compareTo);
    }

    static <T> IHeap<T> makeMinHeap(Comparator<? super T> comparator) {
        return new Heap<>(comparator);
    }

    static <S extends Comparable<S>> IHeap<S> makeMinHeap(int capacity) {
        return IHeapFactory.makeMinHeap(S::compareTo, capacity);
    }

    static <T> IHeap<T> makeMinHeap(Comparator<? super T> comparator, int capacity) {
        return new Heap<>(comparator, capacity);
    }

    static <S extends Comparable<S>> IHeap<S> makeMinHeap(Iterable<? extends S> iterable) {
        return IHeapFactory.makeMinHeap(S::compareTo, iterable);
    }

    static <T> IHeap<T> makeMinHeap(Comparator<? super T> comparator, Iterable<? extends T> iterable) {
        return new Heap<>(comparator, iterable);
    }

    static <S extends Comparable<S>> IHeap<S> makeMaxHeap() {
        return IHeapFactory.makeMaxHeap(S::compareTo);
    }

    static <T> IHeap<T> makeMaxHeap(Comparator<? super T> comparator) {
        return new Heap<>(comparator.reversed());
    }

    static <S extends Comparable<S>> IHeap<S> makeMaxHeap(int capacity) {
        return IHeapFactory.makeMaxHeap(S::compareTo, capacity);
    }

    static <T> IHeap<T> makeMaxHeap(Comparator<? super T> comparator, int capacity) {
        return new Heap<>(comparator.reversed(), capacity);
    }

    static <S extends Comparable<S>> IHeap<S> makeMaxHeap(Iterable<? extends S> iterable) {
        return IHeapFactory.makeMaxHeap(S::compareTo, iterable);
    }

    static <T> IHeap<T> makeMaxHeap(Comparator<? super T> comparator, Iterable<? extends T> iterable) {
        return new Heap<>(comparator.reversed(), iterable);
    }
}
