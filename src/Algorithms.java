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
        while (repeat) {
            iterations++;
            repeat = false;
            //System.out.println("\niterating");

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
    }

    /**
     * Computes the missing number in a given arithmetic sequence. The number
     * should be missing between numbers[start] and numbers[end].
     * @param numbers Arithmetic sequence with one missing term.
     * @param start Lowest bound of missing term's index.
     * @param end Upper bound of missing term's index.
     * @param delta Expected step between adjacent values in the sequence.
     * @return Value of missing term.
     */
    private static int findNumInRange(int[] numbers, int start, int end,
                                      int delta) {
        int len = end - start + 1; // length of subarray we are considering
        // System.out.println(Arrays.toString(numbers) + " " + start + " " +end);
        if (len == 2) { // base case.
            return (numbers[start] + numbers[end]) / 2; // average of 2 points.
        }

        int middle = start + len/2; // index of middle split
        int leftDifference = numbers[middle] - numbers[start];

        // if the left difference is exactly the number of steps on the left
        // side + 1, then we know that the number is missing from the left.
        if (leftDifference == delta * (middle - start+ 1)) {
            // missing number is in the left half.
            return findNumInRange(numbers, start, middle, delta);
        } else {
            // missing number is in the right half.
            return findNumInRange(numbers, middle, end, delta);
        }
    }

    /**
     * Write your implementation of the findMissingNumber algorithm here
     *
     * @param numbers the arithmetic sequence
     * @return the missing number in the sequence
     */
    public static int findMissingNumber(int[] numbers) {
        int len = numbers.length-1;
        // we expect len+1 'steps' in the sequence between the start and
        // end numbers, because one item is missing.
        int delta = (numbers[len] - numbers[0]) / (len + 1);

        return findNumInRange(numbers, 0, len, delta);
    }

    public static void main(String[] args) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList("a", "b", "a", "z", "c", "a", "b"));
        sortQueue(queue);

        Queue<Integer> q = new LinkedList<>(Arrays.asList(
                76, 85, 18, 31, 19, 86, 42, 46, 72, 66,
                64, 16, 68, 5, 73, 93, 84, 88, 92, 99, 3, 4, 5));

        System.out.println(findMissingNumber(new int[] {0, 1, 3}));
    }
}
