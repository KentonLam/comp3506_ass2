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

    /** Returns the absolute value of a. */
    private static int abs(int a) {
        return a < 0 ? -a : a;
    }

    private static int findNumInRange(int[] numbers, int start, int end) {
        int len = end - start + 1; // length of subarray we are considering
        // System.out.println(Arrays.toString(numbers) + " " + start + " " +end);
        if (len == 2) { // base case.
            return (numbers[start] + numbers[end]) / 2; // average of 2 points.
        }

        int middle = start + len/2; // index of middle split
        // total differences between start and end of left/right halves.
        int leftDiff = numbers[middle] - numbers[start];
        int rightDiff = numbers[end] - numbers[middle];

        // to compute the difference between adjacent elements, we test
        //      | leftDiff / (middle-start) |  > | rightDiff / (len - middle) |
        // which occurs if and only if
        //      |leftDiff| * (len - middle) > |rightDiff| * (middle - start)
        // to avoid floating-point rounding problems
        int leftCompare = leftDiff * (end - middle);
        int rightCompare = rightDiff * (middle - start);

        // side with larger absolute delta has a missing number.
        if (abs(leftCompare) > abs(rightCompare)) {
            // missing number is in the left half.
            return findNumInRange(numbers, start, middle);
        } else {
            // missing number is in the right half.
            return findNumInRange(numbers, middle, end);
        }
    }

    /**
     * Write your implementation of the findMissingNumber algorithm here
     *
     * @param numbers the arithmetic sequence
     * @return the missing number in the sequence
     */
    public static int findMissingNumber(int[] numbers) {
        return findNumInRange(numbers, 0, numbers.length-1);
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
