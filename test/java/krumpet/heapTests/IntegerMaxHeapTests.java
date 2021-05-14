package krumpet.heapTests;

import krumpet.heap.Heap;
import krumpet.heap.HeapFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerMaxHeapTests {
    Heap<Integer> integerMaxHeap;
    private final int defaultCapacity = 10;

    @Before
    public void initHeap() {
        integerMaxHeap = HeapFactory.makeMaxHeap(defaultCapacity);
    }

    @Test
    public void isEmpty() {
        Assert.assertThrows(NoSuchElementException.class, () -> integerMaxHeap.pop());
    }

    @Test
    public void isEmptyAfterAddAndPop() {
        integerMaxHeap.add(1);
        integerMaxHeap.pop();
        Assert.assertThrows(NoSuchElementException.class, () -> integerMaxHeap.pop());
    }

    @Test
    public void returnsOneAfterAddingOne() {
        integerMaxHeap.add(1);
        Assert.assertEquals(Integer.valueOf(1), integerMaxHeap.pop());
    }

    @Test
    public void returnsTwoAfterAddingTwoAndThenOne() {
        integerMaxHeap.add(2);
        integerMaxHeap.add(1);
        Assert.assertEquals(Integer.valueOf(2), integerMaxHeap.pop());
    }

    @Test
    public void returnsTwoAfterAddingOneAndThenTwo() {
        integerMaxHeap.add(1);
        integerMaxHeap.add(2);
        Assert.assertEquals(Integer.valueOf(2), integerMaxHeap.pop());
    }

    @Test
    public void createsEmptyHeapFromEmptyIterable() {
        List<Integer> intList = new ArrayList<>();
        Heap<Integer> intHeapFromList = HeapFactory.makeMaxHeap(intList);
        Assert.assertEquals(0, intHeapFromList.size());
    }

    @Test
    public void addToHeapBeyondCapacityShouldThrow() {
        for (int i = 0; i < 10; i++) {
            integerMaxHeap.add(i);
        }
        Assert.assertThrows(IllegalArgumentException.class, () -> integerMaxHeap.add(10));
    }

    @Test
    public void addToHeapFromIterableShouldThrow() {
        List<Integer> intList = new ArrayList<>();
        Heap<Integer> intHeapFromList = HeapFactory.makeMaxHeap(intList);
        Assert.assertThrows(IllegalArgumentException.class, () -> intHeapFromList.add(2));

        intList.add(3);
        intList.add(4);
        Heap<Integer> intHeapFromListTwo = HeapFactory.makeMaxHeap(intList);
        Assert.assertThrows(IllegalArgumentException.class, () -> intHeapFromListTwo.add(2));
    }

    @Test
    public void heapGivesSortedListFromUnsortedInputs() {
        List<Integer> unsortedNumbers = Arrays.asList(7, 3, 5, 4, 2, 9, 6);
        unsortedNumbers.forEach(integerMaxHeap::add);
        List<Integer> sortedNumbers = new ArrayList<>();
        while (integerMaxHeap.size() > 0) {
            sortedNumbers.add(integerMaxHeap.pop());
        }
        Assert.assertArrayEquals(sortedNumbers.toArray(), new Integer[]{9, 7, 6, 5, 4, 3, 2});
    }

    @Test
    public void variousSizesGiveSortedResults() {
        for (int i = 1; i < 23; i++) {
            // generate reverse list of numbers
            List<Integer> input = IntStream.rangeClosed(0, i).boxed().collect(Collectors.toList());
            List<Integer> expected = input.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
            List<Integer> actual = new ArrayList<>();
            Heap<Integer> heap = HeapFactory.makeMaxHeap(input);
            while (heap.size() > 0) {
                actual.add(heap.pop());
            }
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void newHeapWithNoCapacityHasZeroSize() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap();
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void newHeapWithSpecificCapacityHasZeroSize() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap(7);
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void newHeapFromIterableHasSameSizeAsIterable() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(7, heap.size());
    }

    @Test
    public void peekEmptyHeapShouldThrow() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap();
        Assert.assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    public void peekHeapAfterAddPopShouldThrow() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap();
        heap.add(1);
        heap.pop();
        Assert.assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    public void peekDoesNotModifyHeap() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap();
        heap.add(1);
        Assert.assertEquals(1, heap.size());
        Assert.assertEquals(Integer.valueOf(1), heap.peek());
        Assert.assertEquals(1, heap.size());
    }

    @Test
    public void peekChangesAfterRemovingMin() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap();
        heap.add(1);
        heap.add(3);
        heap.add(2);

        Assert.assertEquals(Integer.valueOf(3), heap.peek());
        heap.pop();
        Assert.assertEquals(Integer.valueOf(2), heap.peek());
        heap.pop();
        Assert.assertEquals(Integer.valueOf(1), heap.peek());
    }

    @Test
    public void clearEmptyHeap() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap();
        Assert.assertEquals(0, heap.size());

        heap.clear();
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void clearHeapAfterInsert() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap();
        Assert.assertEquals(0, heap.size());

        heap.add(3);
        Assert.assertEquals(1, heap.size());
        heap.clear();
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void insertAfterClearAfterInsert() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap();
        Assert.assertEquals(0, heap.size());

        heap.add(3);
        Assert.assertEquals(1, heap.size());
        heap.clear();
        Assert.assertEquals(0, heap.size());
        heap.add(5);
        Assert.assertEquals(1, heap.size());
    }

    @Test
    public void clearHeapFromCollection() {
        Heap<Integer> heap = HeapFactory.makeMaxHeap(Arrays.asList(1,2,3,4));
        Assert.assertEquals(4, heap.size());

        heap.clear();
        Assert.assertEquals(0, heap.size());
        heap.add(5);
        Assert.assertEquals(1, heap.size());
    }
}
