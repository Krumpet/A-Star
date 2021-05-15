package krumpet.heapTests;

import krumpet.heap.IHeapFactory;
import krumpet.heap.IHeap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerMaxHeapTests {
    IHeap<Integer> integerMaxHeap;
    private final int defaultCapacity = 10;

    @Before
    public void initHeap() {
        integerMaxHeap = IHeapFactory.makeMaxHeap(defaultCapacity);
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
        IHeap<Integer> intIHeapFromList = IHeapFactory.makeMaxHeap(intList);
        Assert.assertEquals(0, intIHeapFromList.size());
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
        IHeap<Integer> intHeapFromList = IHeapFactory.makeMaxHeap(intList);
        Assert.assertThrows(IllegalArgumentException.class, () -> intHeapFromList.add(2));

        intList.add(3);
        intList.add(4);
        IHeap<Integer> intHeapFromListTwo = IHeapFactory.makeMaxHeap(intList);
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
            IHeap<Integer> IHeap = IHeapFactory.makeMaxHeap(input);
            while (IHeap.size() > 0) {
                actual.add(IHeap.pop());
            }
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void newHeapWithNoCapacityHasZeroSize() {
        IHeap<Integer> IHeap = IHeapFactory.makeMaxHeap();
        Assert.assertEquals(0, IHeap.size());
    }

    @Test
    public void newHeapWithSpecificCapacityHasZeroSize() {
        IHeap<Integer> IHeap = IHeapFactory.makeMaxHeap(7);
        Assert.assertEquals(0, IHeap.size());
    }

    @Test
    public void newHeapFromIterableHasSameSizeAsIterable() {
        IHeap<Integer> IHeap = IHeapFactory.makeMaxHeap(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
        Assert.assertEquals(7, IHeap.size());
    }

    @Test
    public void peekEmptyHeapShouldThrow() {
        IHeap<Integer> IHeap = IHeapFactory.makeMaxHeap();
        Assert.assertThrows(NoSuchElementException.class, IHeap::peek);
    }

    @Test
    public void peekHeapAfterAddPopShouldThrow() {
        IHeap<Integer> heap = IHeapFactory.makeMaxHeap();
        heap.add(1);
        heap.pop();
        Assert.assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    public void peekDoesNotModifyHeap() {
        IHeap<Integer> heap = IHeapFactory.makeMaxHeap();
        heap.add(1);
        Assert.assertEquals(1, heap.size());
        Assert.assertEquals(Integer.valueOf(1), heap.peek());
        Assert.assertEquals(1, heap.size());
    }

    @Test
    public void peekChangesAfterRemovingMin() {
        IHeap<Integer> heap = IHeapFactory.makeMaxHeap();
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
        IHeap<Integer> IHeap = IHeapFactory.makeMaxHeap();
        Assert.assertEquals(0, IHeap.size());

        IHeap.clear();
        Assert.assertEquals(0, IHeap.size());
    }

    @Test
    public void clearHeapAfterInsert() {
        IHeap<Integer> heap = IHeapFactory.makeMaxHeap();
        Assert.assertEquals(0, heap.size());

        heap.add(3);
        Assert.assertEquals(1, heap.size());
        heap.clear();
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void insertAfterClearAfterInsert() {
        IHeap<Integer> heap = IHeapFactory.makeMaxHeap();
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
        IHeap<Integer> heap = IHeapFactory.makeMaxHeap(Arrays.asList(1,2,3,4));
        Assert.assertEquals(4, heap.size());

        heap.clear();
        Assert.assertEquals(0, heap.size());
        heap.add(5);
        Assert.assertEquals(1, heap.size());
    }
}
