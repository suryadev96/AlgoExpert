import java.util.PriorityQueue;

// Do not edit the class below except for
// the insert method. Feel free to add new
// properties and methods to the class.
class Program {

    static class ContinuousMedianHandler {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
        ;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        ;
        double median = 0;

        //maxHeap holds all values less than or equal to median
        //maximum value of the maxHeap is median itself
        //minHeap holds all values greater than median
        //12345678  => 1234  | 5678
        //123456789 => 12345 | 6789

        //the two heaps sizes will always be offset by 1
        public void insert(int number) {
            //if both heaps size is same
            //then if number is greater than minHeap peek =>
            if (maxHeap.size() == minHeap.size()) {
                if (minHeap.peek() != null && number > minHeap.peek()) {
                    maxHeap.offer(minHeap.poll());
                    minHeap.offer(number);
                } else {
                    maxHeap.offer(number);
                }
            } else {
                if (maxHeap.peek() > number) {
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(number);
                } else {
                    minHeap.offer(number);
                }
            }
        }

        public double getMedian() {
            if (minHeap.isEmpty()) {
                return maxHeap.peek();
            }
            if (maxHeap.size() == minHeap.size()) {
                return ((double) minHeap.peek() + (double) maxHeap.peek()) / 2;
            } else {
                return maxHeap.peek();
            }
        }
    }

}
