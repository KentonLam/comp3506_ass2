import java.util.Queue;

/**
 * Solutions to COMP3506 assignment 2. Semester 2, 2019.
 */
public class Algorithms {

    /**
     * Given a queue of elements implementing the Comparable 
     * interface, sorts the elements of the queue using O(1)
     * extra space.
     *
     * Dequeing the elements of the sorted queue will yield each
     * element in ascending order.
     *
     * @param <T> Type of objects inside the queue.
     * @param queue Queue to sort in-place.
     */
    public static <T extends Comparable<T>> void sortQueue(Queue<T> queue) {
        int size = queue.size();
        if (size <= 1)
            return; // nothing to do

        T a, b, prev;
        boolean repeat = true;
        while (repeat) {
            repeat = false;

            a = queue.remove(); // pop element from queue
            b = null;
            prev = null; // current tail of the queue.

            for (int i = 0; i < size - 1; i++) {
                // pop an element to fill either a or b, depending on which is
                // null.
                if (a == null) {
                    a = queue.remove();
                } else {
                    b = queue.remove();
                }

                // toPush is the smaller of 'a' and 'b'.
                T toPush = a.compareTo(b) < 0 ? a : b;
                // push smaller element to end of queue.
                queue.add(toPush);

                // clear reference to the element we pushed.
                if (a == toPush) {
                    a = null;
                } else {
                    b = null;
                }

                // if the previous tail of the queue is greater than the element
                // we just pushed, something is out of order and we should repeat.
                if (!repeat && prev != null && prev.compareTo(toPush) > 0) {
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
     *
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
            return numbers[start] + delta;
        }

        int middle = start + len/2; // index of middle split
        int leftDifference = numbers[middle] - numbers[start];

        // if the left difference is exactly the number of steps on the left
        // side + 1, then we know that the number is missing from the left.
        // WARNING: this will break in weird ways if the assumptions aren't
        // met.
        if (leftDifference == delta * (middle - start + 1)) {
            // missing number is in the left half.
            return findNumInRange(numbers, start, middle, delta);
        } else {
            // missing number is in the right half.
            return findNumInRange(numbers, middle, end, delta);
        }
    }

    /**
     * Given an array of integers representing an arithmetic sequence,
     * finds the missing element.
     *
     * <br><b>Assumptions:</b>
     * the missing element is not at the start or end of the sequence;
     * the input array will contain at least two elements;
     * exactly one element will be missing from the sequence.
     *
     * @param numbers the arithmetic sequence
     * @return the missing number in the sequence
     */
    public static int findMissingNumber(int[] numbers) {
        int end = numbers.length-1;
        // we expect end+1 steps in the sequence between the start and
        // end numbers, because one item is missing.
        int delta = (numbers[end] - numbers[0]) / (end + 1);

        return findNumInRange(numbers, 0, end, delta);
    }

}
