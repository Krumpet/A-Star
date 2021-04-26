import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

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
    public void returnsOneAfterAddingTwo() {
        integerMinHeap.add(2);
        Assert.assertEquals(Integer.valueOf(1), integerMinHeap.removeMin());
    }
}