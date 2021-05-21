package krumpet.heapTests;

import krumpet.heap.IHeapFactory;
import krumpet.heap.IHeap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IntegerMinHeapTests {

    IHeap<Integer> integerMinHeap;

    @Before
    public void initHeap() {
        int defaultCapacity = 10;
        integerMinHeap = IHeapFactory.makeMinHeap(defaultCapacity);
    }

    @Test
    public void isEmpty() {
        Assert.assertThrows(NoSuchElementException.class, () -> integerMinHeap.pop());
    }

    @Test
    public void isEmptyAfterAddAndPop() {
        integerMinHeap.add(1);
        integerMinHeap.pop();
        Assert.assertThrows(NoSuchElementException.class, () -> integerMinHeap.pop());
    }

    @Test
    public void returnsOneAfterAddingOne() {
        integerMinHeap.add(1);
        Assert.assertEquals(Integer.valueOf(1), integerMinHeap.pop());
    }

    @Test
    public void returnsOneAfterAddingTwoAndThenOne() {
        integerMinHeap.add(2);
        integerMinHeap.add(1);
        Assert.assertEquals(Integer.valueOf(1), integerMinHeap.pop());
    }

    @Test
    public void returnsOneAfterAddingOneAndThenTwo() {
        integerMinHeap.add(1);
        integerMinHeap.add(2);
        Assert.assertEquals(Integer.valueOf(1), integerMinHeap.pop());
    }

    @Test
    public void createsEmptyHeapFromEmptyIterable() {
        List<Integer> intList = new ArrayList<>();
        IHeap<Integer> intIHeapFromList = IHeapFactory.makeMinHeap(intList);
        Assert.assertEquals(0, intIHeapFromList.size());
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
        IHeap<Integer> intHeapFromList = IHeapFactory.makeMinHeap(intList);
        Assert.assertThrows(IllegalArgumentException.class, () -> intHeapFromList.add(2));

        intList.add(3);
        intList.add(4);
        IHeap<Integer> intHeapFromListTwo = IHeapFactory.makeMinHeap(intList);
        Assert.assertThrows(IllegalArgumentException.class, () -> intHeapFromListTwo.add(2));
    }

    @Test
    public void heapGivesSortedListFromUnsortedInputs() {
        List<Integer> unsortedNumbers = Arrays.asList(7, 3, 5, 4, 2, 9, 6);
        unsortedNumbers.forEach(integerMinHeap::add);
        List<Integer> sortedNumbers = new ArrayList<>();
        while (integerMinHeap.size() > 0) {
            sortedNumbers.add(integerMinHeap.pop());
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
            IHeap<Integer> IHeap = IHeapFactory.makeMinHeap(input);
            while (IHeap.size() > 0) {
                actual.add(IHeap.pop());
            }
            Assert.assertEquals(expected, actual);
        }
    }

    @Test
    public void newHeapWithNoCapacityHasZeroSize() {
        IHeap<Integer> IHeap = IHeapFactory.makeMinHeap();
        Assert.assertEquals(0, IHeap.size());
    }

    @Test
    public void newHeapWithSpecificCapacityHasZeroSize() {
        IHeap<Integer> IHeap = IHeapFactory.makeMinHeap(7);
        Assert.assertEquals(0, IHeap.size());
    }

    @Test
    public void newHeapFromIterableHasSameSizeAsIterable() {
        IHeap<Integer> IHeap = IHeapFactory.makeMinHeap(Arrays.asList(1,2,3,4,5,6,7));
        Assert.assertEquals(7, IHeap.size());
    }

    @Test
    public void peekEmptyHeapShouldThrow() {
        IHeap<Integer> IHeap = IHeapFactory.makeMinHeap();
        Assert.assertThrows(NoSuchElementException.class, IHeap::peek);
    }

    @Test
    public void peekHeapAfterAddPopShouldThrow() {
        IHeap<Integer> heap = IHeapFactory.makeMinHeap();
        heap.add(1);
        heap.pop();
        Assert.assertThrows(NoSuchElementException.class, heap::peek);
    }

    @Test
    public void peekDoesNotModifyHeap() {
        IHeap<Integer> heap = IHeapFactory.makeMinHeap();
        heap.add(1);
        Assert.assertEquals(1, heap.size());
        Assert.assertEquals(Integer.valueOf(1), heap.peek());
        Assert.assertEquals(1, heap.size());
    }

    @Test
    public void peekChangesAfterRemovingMin() {
        IHeap<Integer> heap = IHeapFactory.makeMinHeap();
        heap.add(1);
        heap.add(3);
        heap.add(2);

        Assert.assertEquals(Integer.valueOf(1), heap.peek());
        heap.pop();
        Assert.assertEquals(Integer.valueOf(2), heap.peek());
        heap.pop();
        Assert.assertEquals(Integer.valueOf(3), heap.peek());
    }

    @Test
    public void clearEmptyHeap() {
        IHeap<Integer> IHeap = IHeapFactory.makeMinHeap();
        Assert.assertEquals(0, IHeap.size());

        IHeap.clear();
        Assert.assertEquals(0, IHeap.size());
    }

    @Test
    public void clearHeapAfterInsert() {
        IHeap<Integer> heap = IHeapFactory.makeMinHeap();
        Assert.assertEquals(0, heap.size());

        heap.add(3);
        Assert.assertEquals(1, heap.size());
        heap.clear();
        Assert.assertEquals(0, heap.size());
    }

    @Test
    public void insertAfterClearAfterInsert() {
        IHeap<Integer> heap = IHeapFactory.makeMinHeap();
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
        IHeap<Integer> heap = IHeapFactory.makeMinHeap(Arrays.asList(1,2,3,4));
        Assert.assertEquals(4, heap.size());

        heap.clear();
        Assert.assertEquals(0, heap.size());
        heap.add(5);
        Assert.assertEquals(1, heap.size());
    }
}