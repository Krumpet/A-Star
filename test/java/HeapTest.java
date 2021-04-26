import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
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
        Assert.assertThrows(IllegalArgumentException.class, () -> intHeapFromList.add(2));
    }
}