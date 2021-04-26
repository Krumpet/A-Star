import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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
    public void heapGivesSortedListFromUnsortedIterable() {
        List<Integer> unsortedNumbers = Arrays.asList(7, 3, 5, 4, 2, 9, 6);
        unsortedNumbers.forEach(integerMinHeap::add);
        List<Integer> sortedNumbers = new ArrayList<>();
        while (integerMinHeap.size() > 0) {
            sortedNumbers.add(integerMinHeap.removeMin());
        }
        Assert.assertArrayEquals(sortedNumbers.toArray(), new Integer[]{2, 3, 4, 5, 6, 7, 9});
    }
}