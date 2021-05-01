import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HeapTest {

    MinHeap<Integer> integerMinHeap;

    @Before
    public void initHeap() {
        integerMinHeap = new MinHeap<>(10);
    }

    @Test
    public void isEmpty() {
        Assert.assertThrows(NoSuchElementException.class, () -> integerMinHeap.removeMin());
    }

    @Test
    public void isEmptyAfterAddAndPop() {
        integerMinHeap.add(1);
        integerMinHeap.removeMin();
        Assert.assertThrows(NoSuchElementException.class, () -> integerMinHeap.removeMin());
    }

    @Test
    public void returnsOneAfterAddingOne() {
        integerMinHeap.add(1);
        Assert.assertEquals(Integer.valueOf(1), integerMinHeap.removeMin());
    }

    @Test
    public void returnsOneAfterAddingTwoAndThenOne() {
        integerMinHeap.add(2);
        integerMinHeap.add(1);
        Assert.assertEquals(Integer.valueOf(1), integerMinHeap.removeMin());
    }

    @Test
    public void returnsOneAfterAddingOneAndThenTwo() {
        integerMinHeap.add(1);
        integerMinHeap.add(2);
        Assert.assertEquals(Integer.valueOf(1), integerMinHeap.removeMin());
    }

    @Test
    public void createsEmptyHeapFromEmptyIterable() {
        List<Integer> intList = new ArrayList<>();
        MinHeap<Integer> intHeapFromList = new MinHeap<>(intList);
        Assert.assertEquals(0, intHeapFromList.size());
    }

    @Test
    public void addToHeapBeyondCapacityShouldThrow() {
        for (int i = 0; i < 10; i++) {
            integerMinHeap.add(i);
        }
        Assert.assertThrows(IllegalArgumentException.class, () -> integerMinHeap.add(10));
    }

    @Test
    public void addToHeapFromIterableShouldThrow() {
        List<Integer> intList = new ArrayList<>();
        MinHeap<Integer> intHeapFromList = new MinHeap<>(intList);
        Assert.assertThrows(IllegalArgumentException.class, () -> intHeapFromList.add(2));

        intList.add(3);
        intList.add(4);
        MinHeap<Integer> intHeapFromListTwo = new MinHeap<>(intList);
        Assert.assertThrows(IllegalArgumentException.class, () -> intHeapFromListTwo.add(2));
    }

    @Test
    public void heapGivesSortedListFromUnsortedInputs() {
        List<Integer> unsortedNumbers = Arrays.asList(7, 3, 5, 4, 2, 9, 6);
        unsortedNumbers.forEach(integerMinHeap::add);
        List<Integer> sortedNumbers = new ArrayList<>();
        while (integerMinHeap.size() > 0) {
            sortedNumbers.add(integerMinHeap.removeMin());
        }
        Assert.assertArrayEquals(sortedNumbers.toArray(), new Integer[]{2, 3, 4, 5, 6, 7, 9});
    }

    @Test
    public void variousSizesGiveSortedResults() {
        for (int i = 1; i < 23; i++) {
            int finalI = i;
            // generate reverse list of numbers
            List<Integer> input = IntStream.rangeClosed(0, i).mapToObj(x -> finalI - x).collect(Collectors.toList());
            List<Integer> expected = input.stream().sorted().collect(Collectors.toList());
            List<Integer> actual = new ArrayList<>();
            MinHeap<Integer> heap = new MinHeap<>(input);
            while (heap.size() > 0) {
                actual.add(heap.removeMin());
            }
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void newHeapWithNoCapacityHasZeroSize() {
        MinHeap<Integer> heap = new MinHeap<>();
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void newHeapWithSpecificCapacityHasZeroSize() {
        MinHeap<Integer> heap = new MinHeap<>(7);
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void newHeapFromIterableHasSameSizeAsIterable() {
        MinHeap<Integer> heap = new MinHeap<>(Arrays.asList(1,2,3,4,5,6,7));
        Assert.assertEquals(7, heap.size());
    }

    @Test
    public void peekEmptyHeapShouldThrow() {
        MinHeap<Integer> heap = new MinHeap<>();
        Assert.assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    public void peekHeapAfterAddPopShouldThrow() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1);
        heap.removeMin();
        Assert.assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    public void peekDoesNotModifyHeap() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1);
        Assert.assertEquals(1, heap.size());
        Assert.assertEquals(Integer.valueOf(1), heap.peek());
        Assert.assertEquals(1, heap.size());
    }

    @Test
    public void peekChangesAfterRemovingMin() {
        MinHeap<Integer> heap = new MinHeap<>();
        heap.add(1);
        heap.add(3);
        heap.add(2);

        Assert.assertEquals(Integer.valueOf(1), heap.peek());
        heap.removeMin();
        Assert.assertEquals(Integer.valueOf(2), heap.peek());
        heap.removeMin();
        Assert.assertEquals(Integer.valueOf(3), heap.peek());
    }
}