import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Write your solution to this assignment in this class
 */
public class Algorithms {

    /**
     * Write your implementation of the sortQueue algorithm here
     *
     * @param queue the queue to sort
     */
    public static <T extends Comparable<T>> void sortQueue(Queue<T> queue) {
        if (queue.size() <= 1)
            return; // nothing to do
        int size = queue.size();
        T a, b, prev;
        boolean repeat = true;
        int iterations = 0;
        while (repeat) {
            iterations++;
            repeat = false;
            //System.out.println("\niterating");
            System.out.println("" + iterations + ": " + queue);

            a = queue.remove();
            b = null;
            prev = null; // previously pushed element

            for (int i = 0; i < size - 1; i++) {
                if (a == null) {
                    a = queue.remove();
                } else {
                    b = queue.remove();
                }
                // toPush is the smaller of 'a' and 'b'.
                T toPush = a.compareTo(b) < 0 ? a : b;
                T other = toPush == a ? b : a;
                queue.add(toPush); // push smaller to end of queue.
                //System.out.println("pushing "+toPush+", keeping "+other);
                if (a == toPush) {
                    a = null;
                } else {
                    b = null;
                }
                if (!repeat && prev != null && prev.compareTo(toPush) > 0) {
                    //System.out.println("REPEAT FLAG SET");
                    repeat = true;
                }
                prev = toPush;
            }
            // add back the last element.
            queue.add(a != null ? a : b);
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
