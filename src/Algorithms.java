import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Write your solution to this assignment in this class
 */
public class Algorithms {

    /**
     * Returns the smaller of two values.
     * @param a Value 1.
     * @param b Value 2.
     * @return min(a, b)
     */
    static private int min(int a, int b) {
        return a < b ? a : b;
    }

    /**
     * Write your implementation of the sortQueue algorithm here
     *
     * @param queue the queue to sort
     */
    public static <T extends Comparable<T>> void sortQueue(Queue<T> queue) {
        int size = queue.size();
        if (size <= 1)
            return; // nothing to do
        
        /* 
        This is a bottom-up merge sort implemented using a queue. Starts with
        groups of 2 elements, doubling in size each iteration.
        */
        int subSize = 1;
        Queue<T> left = new LinkedList<>();
        Queue<T> right = new LinkedList<>();
        int iterations = 0;
        while (subSize < size) {
            iterations++;
            System.out.println("" + iterations + " " + queue);
            // if queue size is a multiple of sublist size, stop at end of
            // queue. otherwise, we need to overshoot to get the last sublist.

            // for each pair of two sublists
            int i = 0;
            do {
                System.out.println("i " + i);

                // pop left sublist into left queue, storing minimum value.
                int limit = min(i+subSize, size);
                for (int j = i; j < limit; j++) {
                    left.add(queue.remove());
                }

                // pop right sublist into queue, storing smallest value.
                limit = min(i+2*subSize, size);
                for (int j = i+subSize; j < limit; j++) {
                    right.add(queue.remove());
                }
                System.out.println("" + left + " / " + right);

                // assuming the sublists are sorted individually, we check
                // which one should be ordered first.
                // merge sublists in order
                while (!(left.isEmpty() || right.isEmpty())) {
                    if (left.element().compareTo(right.element()) < 0) {
                        queue.add(left.remove());
                    } else {
                        queue.add(right.remove());
                    }
                }
                // one of left/right is empty. add all of the other queue
                // back to the main queue.
                while (!right.isEmpty())
                    queue.add(right.remove());
                while (!left.isEmpty())
                    queue.add(left.remove());

                System.out.println(queue);
                System.out.println();
                assert size == queue.size();

                i += 2*subSize; // because iterating over pairs of sublists.
            } while (i < size);
            subSize *= 2;
        }

        System.out.println(queue);
        System.out.println("took " + iterations + " iterations, size " + size);
        System.out.println();
    }

    /**
     * Write your implementation of the findMissingNumber algorithm here
     *
     * @param numbers the arithmetic sequence
     * @return the missing number in the sequence
     */
    public static int findMissingNumber(int[] numbers) {
        return 0;
    }

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList("a", "b", "a", "z", "c", "a", "b"));
        sortQueue(queue);

        Queue<Integer> q = new LinkedList<>(Arrays.asList(
                76, 85, 18, 31, 19, 86, 42, 46, 72, 66,
                64, 16, 68, 5, 73, 93, 84, 88, 92, 99, 3, 4, 5));
        sortQueue(q);
        sortQueue(q);

        sortQueue(new LinkedList<>(Arrays.asList(
                10, 9, 8, 7, 6, 5, 4, 3, 1, 2, 2
        )));
    }
}
