package krumpet.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

public class Heap<T> {
    public static final int DEFAULT_CAPACITY = 100;

    private final T[] elements;
    private int size = 0;
    private final Comparator<? super T> comparator;

    Heap(Comparator<? super T> comparator) {
        this(comparator, DEFAULT_CAPACITY);
    }

    Heap(Comparator<? super T> comparator, int capacity) {
        elements = (T[]) new Object[capacity];
        this.comparator = comparator;
    }

    Heap(Comparator<? super T> comparator, Iterable<? extends T> collection) {
        // TODO: optimize sorting the elements
        elements = (T[]) StreamSupport
                .stream(collection.spliterator(), false)
                .sorted(comparator)
                .toArray(Object[]::new);
        size = elements.length;
        this.comparator = comparator;
    }

    public void add(T item) throws IllegalArgumentException {
        if (size() >= this.elements.length) {
            throw new IllegalArgumentException("Heap over initial capacity of " + this.elements.length);
        }
        elements[size++] = item;
        heapifyUp();
    }

    public void clear() {
        Arrays.fill(elements, null);
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public T pop() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Empty heap");
        T root = elements[0];
        swap(size() - 1, 0);
        elements[size() - 1] = null;
        size--;
        heapifyDown();
        return root;
    }

    public T peek() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Empty heap");
        return elements[0];
    }

    private void heapifyDown() {
        heapifyDown(0);
    }

    private void heapifyDown(int i) {
        // get left and right child of node at index `i`
        int left = indexOfLeftChild(i);
        int right = indexOfRightChild(i);

        int smallest = i;

        // TODO: extract to pickIndexOfSmallestChild
        // compare `A[i]` with its left and right child
        // and find the smallest value
        if (left < size() && this.comparator.compare(elements[left], elements[i]) <= 0) {
            smallest = left;
        }

        if (right < size() && this.comparator.compare(elements[right], (elements[smallest])) <= 0) {
            smallest = right;
        }

        if (smallest != i) {
            // swap with a child having lesser value
            swap(i, smallest);

            // call heapifyDown on the child
            heapifyDown(smallest);
        }
    }

    private int indexOfLeftChild(int i) {
        return (2 * i + 1);
    }

    private int indexOfRightChild(int i) {
        return (2 * i + 2);
    }

    private int indexOfParent(int i) {
        // if `i` is already a root node
        if (i == 0) {
            return 0;
        }

        return (i - 1) / 2;
    }

    private void heapifyUp() {
        int childIndex = size() - 1;
        int parentIndex = indexOfParent(childIndex);

        while (parentIndex >= 0 && comparator.compare(elements[childIndex], elements[parentIndex]) < 0) {
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = indexOfParent(childIndex);
        }
    }

    private void swap(int childIndex, int parentIndex) {
        T t = elements[childIndex];
        elements[childIndex] = elements[parentIndex];
        elements[parentIndex] = t;
    }
}
