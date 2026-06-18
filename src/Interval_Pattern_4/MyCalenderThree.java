package Interval_Pattern_4;

import java.util.Map;
import java.util.TreeMap;

public class MyCalenderThree {
    Map<Integer, Integer> map;

    public MyCalenderThree(){
        map=new TreeMap<>();
    }

    public int book(int startTime, int endTime){
        map.put(startTime, map.getOrDefault(startTime,0)+1);
        map.put(endTime, map.getOrDefault(endTime,0)-1);

        int bookings=0,
                max=0;

        for (var entry : map.entrySet()){
            bookings+=entry.getValue();

            max = Math.max(max, bookings);
        }
        return max;
    }
}

class DriveThree{
    // 🌟 TEST SUITE LOGIC HERE 🌟
    public static void main(String[] args) {
        System.out.println("🎬 Starting MyCalendarThree Test Case...");
        System.out.println("---------------------------------------");

        // Step 1: Initialize the calendar
        MyCalenderThree myCalendarThree = new MyCalenderThree();
        System.out.println("Initialized: MyCalendarThree -> Output: null");

        // Step 2: Define your test inputs
        int[][] inputs = {
                {10, 20},
                {50, 60},
                {10, 40},
                {5, 15},
                {5, 10},
                {25, 55}
        };

        // Step 3: Run the bookings and match with expected outputs
        int[] expectedOutputs = {1, 1, 2, 3, 3, 3};

        for (int i = 0; i < inputs.length; i++) {
            int start = inputs[i][0];
            int end = inputs[i][1];

            // Call the booking function
            int result = myCalendarThree.book(start, end);

            // Print results with an emotional status check!
            if (result == expectedOutputs[i]) {
                System.out.printf("✅ book(%d, %d) -> Output: %d | MATCH! 🎉\n", start, end, result);
            } else {
                System.out.printf("❌ book(%d, %d) -> Output: %d | EXPECTED: %d 😰\n", start, end, result, expectedOutputs[i]);
            }
        }

        System.out.println("---------------------------------------");
        System.out.println("🏁 Test Finished!");
    }
}

