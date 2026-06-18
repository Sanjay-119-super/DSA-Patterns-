package Interval_Pattern_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class SummaryRangesBruteForce {

    List<Integer> list;

    public SummaryRangesBruteForce() {
        list = new ArrayList<>();
    }

    public void addNum(int value) {
        if (!list.contains(value)) {
            list.add(value);
        }
    }

    public int[][] getIntervals() {

        if (list.isEmpty()) {
            return new int[][] {};
        }

        Collections.sort(list);

        List<int[]> res = new ArrayList<>();

        int left = list.get(0);
        int right = list.get(0);

        for (int i = 1; i < list.size(); i++) {

            int curr = list.get(i);

            if (curr == right + 1) {
                right = curr;
            } else {
                res.add(new int[] { left, right });

                left = curr;
                right = curr;
            }
        }

        res.add(new int[] { left, right });

        return res.toArray(new int[res.size()][]);
    }

    public static void printIntervals(int[][] intervals) {
        System.out.print("[");
        for (int i = 0; i < intervals.length; i++) {
            System.out.print(Arrays.toString(intervals[i]));
            if (i < intervals.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        System.out.println("===== Test Case 1 =====");
        SummaryRangesOptimize sr1 = new SummaryRangesOptimize();

        sr1.addNum(1);
        printIntervals(sr1.getIntervals()); // [[1,1]]

        sr1.addNum(3);
        printIntervals(sr1.getIntervals()); // [[1,1],[3,3]]

        sr1.addNum(7);
        printIntervals(sr1.getIntervals()); // [[1,1],[3,3],[7,7]]

        sr1.addNum(2);
        printIntervals(sr1.getIntervals()); // [[1,3],[7,7]]

        sr1.addNum(6);
        printIntervals(sr1.getIntervals()); // [[1,3],[6,7]]


        System.out.println("\n===== Test Case 2 =====");
        SummaryRangesOptimize sr2 = new SummaryRangesOptimize();

        sr2.addNum(10);
        sr2.addNum(11);
        sr2.addNum(12);

        printIntervals(sr2.getIntervals());
        // [[10,12]]


        System.out.println("\n===== Test Case 3 =====");
        SummaryRangesOptimize sr3 = new SummaryRangesOptimize();

        sr3.addNum(5);
        sr3.addNum(1);
        sr3.addNum(3);

        printIntervals(sr3.getIntervals());
        // [[1,1],[3,3],[5,5]]


        System.out.println("\n===== Test Case 4 (Duplicates) =====");
        SummaryRangesOptimize sr4 = new SummaryRangesOptimize();

        sr4.addNum(1);
        sr4.addNum(1);
        sr4.addNum(1);
        sr4.addNum(2);
        sr4.addNum(3);

        printIntervals(sr4.getIntervals());
        // [[1,3]]


        System.out.println("\n===== Test Case 5 =====");
        SummaryRangesOptimize sr5 = new SummaryRangesOptimize();

        int[] nums = {100, 4, 200, 1, 3, 2};

        for (int num : nums) {
            sr5.addNum(num);
        }

        printIntervals(sr5.getIntervals());
        // [[1,4],[100,100],[200,200]]
    }

}
