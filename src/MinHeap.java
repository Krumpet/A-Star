import java.lang.reflect.Array;
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
        elements = (T[]) StreamSupport.stream(collection.spliterator(), false).toArray(Comparable[]::new);
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

    private void heapifyDown() {
        int parent = 0;
        int leftChild = 1;
        int rightChild = 2;

        int choice = compareAndPick(leftChild, rightChild);

        while (choice != -1) {
            swap(choice, parent);
            parent = choice;
            choice = compareAndPick(2 * choice + 1, 2 * choice + 2);
        }
    }

    private int compareAndPick(int leftChild, int rightChild) {
        if (leftChild >= elements.length || elements[leftChild] == null) return -1;
        if (rightChild >= elements.length || elements[rightChild] == null || elements[leftChild].compareTo(elements[rightChild]) <= 0)
            return leftChild;
        return rightChild;
    }

    private void heapifyUp() {
        int childIndex = size - 1;
        int parentIndex = (childIndex - 1) / 2;

        while (parentIndex >= 0 && elements[childIndex].compareTo(elements[parentIndex]) < 0) {
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
    }

    private void swap(int childIndex, int parentIndex) {
        T t = elements[childIndex];
        elements[childIndex] = elements[parentIndex];
        elements[parentIndex] = t;
    }
}
