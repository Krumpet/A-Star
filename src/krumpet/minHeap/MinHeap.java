package krumpet.minHeap;

import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

public class MinHeap<T extends Comparable<T>> {
    private final T[] elements;
    private int size = 0;

    public MinHeap() {
        this(100);
    }

    public MinHeap(int capacity) {
        elements = (T[]) new Comparable[capacity];
    }

    public MinHeap(Iterable<T> collection) {
        // TODO: optimize building the elements
        elements = (T[]) StreamSupport
                .stream(collection.spliterator(), false)
                .sorted()
                .toArray(Comparable[]::new);
        size = elements.length;
    }

    public void add(T item) throws IllegalArgumentException {
        if (size >= this.elements.length) {
            throw new IllegalArgumentException("MinHeap over initial capacity of " + this.elements.length);
        }
        elements[size++] = item;
        heapifyUp();
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public T removeMin() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Empty heap");
        T root = elements[0];
        swap(size - 1, 0);
        elements[size - 1] = null;
        size--;
        heapifyDown();
        return root;
    }

    public T peek() throws NoSuchElementException {
        if (isEmpty()) throw new NoSuchElementException("Empty heap");
        return elements[0];
    }

    private void heapifyDown(int i) {
        // get left and right child of node at index `i`
        int left = LEFT(i);
        int right = RIGHT(i);

        int smallest = i;

        // TODO: extract to pickIndexOfSmallestChild
        // compare `A[i]` with its left and right child
        // and find the smallest value
        if (left < size() && elements[left].compareTo(elements[i]) <= 0) {
            smallest = left;
        }

        if (right < size() && elements[right].compareTo(elements[smallest]) <= 0) {
            smallest = right;
        }

        if (smallest != i) {
            // swap with a child having lesser value
            swap(i, smallest);

            // call heapifyDown on the child
            heapifyDown(smallest);
        }
    }

    // TODO: rename LEFT and RIGHT and PARENT
    // return left child of `A[i]`
    private int LEFT(int i) {
        return (2 * i + 1);
    }

    // return right child of `A[i]`
    private int RIGHT(int i) {
        return (2 * i + 2);
    }

    // return parent of `A[i]`
    private int PARENT(int i) {
        // if `i` is already a root node
        if (i == 0) {
            return 0;
        }

        return (i - 1) / 2;
    }

    private void heapifyDown() {
        heapifyDown(0);
    }

    private void heapifyUp() {
        int childIndex = size - 1;
        int parentIndex = PARENT(childIndex);

        while (parentIndex >= 0 && elements[childIndex].compareTo(elements[parentIndex]) < 0) {
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = PARENT(childIndex);
        }
    }

    private void swap(int childIndex, int parentIndex) {
        T t = elements[childIndex];
        elements[childIndex] = elements[parentIndex];
        elements[parentIndex] = t;
    }
}
