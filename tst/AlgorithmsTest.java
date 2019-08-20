import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class AlgorithmsTest {

    @Test(timeout=1000)
    public void testSortQueueExample1() {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(3);
        queue.add(5);
        queue.add(4);
        queue.add(2);
        Algorithms.sortQueue(queue);
        assertEquals(1, (int)queue.remove());
        assertEquals(2, (int)queue.remove());
        assertEquals(3, (int)queue.remove());
        assertEquals(4, (int)queue.remove());
        assertEquals(5, (int)queue.remove());
        assertEquals(0, queue.size());
    }

    @Test(timeout=1000)
    public void testSortQueueExample2() {
        Queue<String> queue = new LinkedList<>();
        queue.add("a");
        queue.add("b");
        queue.add("c");
        queue.add("b");
        queue.add("a");
        Algorithms.sortQueue(queue);
        assertEquals("a", queue.remove());
        assertEquals("a", queue.remove());
        assertEquals("b", queue.remove());
        assertEquals("b", queue.remove());
        assertEquals("c", queue.remove());
    }

    @Test(timeout=1000)
    public void testFindMissingNumberExample1() {
        int[] arr = {2, 4, 6, 8, 10, 12, 14, 18, 20};
        assertEquals(16, Algorithms.findMissingNumber(arr));
    }

    @Test(timeout=1000)
    public void testFindMissingNumberExample2() {
        int[] arr = {4, 1, -5};
        assertEquals(-2, Algorithms.findMissingNumber(arr));
    }

    @Test
    public void testFindMissingNum() {
        int maxLen = 5;
        int[] deltas = {1, 2, -1, -3, -7};
        int[] sequence;
        for (int d : deltas) {
            sequence = new int[maxLen-1];
            for (int missing = 1; missing < maxLen-1; missing++) {
                int j = 0;
                for (int i = 0; i < maxLen; i++) {
                    if (i == missing) continue;
                    sequence[j] = -5 + i*d;
                    j++;
                }
                int result = Algorithms.findMissingNumber(sequence);
                assertEquals(-5 + missing*d, result);
            }
        }
    }

    @Test
    public void testConstantSequence() {
        assertEquals(5, Algorithms.findMissingNumber(new int[] {5, 5, 5}));
    }
}
