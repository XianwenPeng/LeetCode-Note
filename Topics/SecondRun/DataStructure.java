package LeetCode.Topics.SecondRun;
import java.util.*;

public class DataStructure {

    /* 642. Moving Average from Data Stream */
    public class MovingAverage {
        Queue<Integer> queue;
        double sum;
        int size;
        /*
        * @param size: An integer
        */public MovingAverage(int size) {
            // do intialization if necessary
            this.queue = new LinkedList<>();
            this.size = size;
            this.sum = 0;
        }

        /*
         * @param val: An integer
         * @return:
         */
        public double next(int val) {
            // write your code here
            if (queue.size() == this.size)   sum -= queue.poll();
            queue.offer(val);
            sum += val;
            return sum / queue.size();
        }
    }
}
