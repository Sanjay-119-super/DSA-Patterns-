package Interval_Pattern_4;


import java.util.*;

class Solution {
    public int[][] mergeBruteForce(int[][] intervals) {
        // COPY -- list of Array
        // pair wise compare if merge run again algo
        // if merge not happen return res

        List<int[]> list = new ArrayList<>();

        for (int[] interval : intervals) {
            list.add(interval);
        }
        boolean isMergedHappen = true;

        while (isMergedHappen){
            isMergedHappen=false;
            for (int i = 0; i < list.size(); i++) {

                int j = i + 1;
                while (j < list.size()) {
                    int[] int1 = list.get(i);
                    int[] int2 = list.get(j);

                    if (isVerlap(int1, int2)) {
                        int1[0] = Math.min(int1[0], int2[0]);
                        int1[1] = Math.max(int1[1], int2[1]);

                        list.remove(j);

                        isMergedHappen=true;

                    }else {
                        j++;
                    }
                }
            }
        }

        int[][] res = new int[list.size()][2];

        for (int i=0; i<list.size(); i++){
            res[i]=list.get(i);
        }
        return res;
    }

    private boolean isVerlap(int[] int1, int[] int2) {
        return int1[1]>=int2[0] && int2[1]>=int1[0];
    }
     public int[][] mergeOptimizeIntervalPattern(int[][] intervals) {
         Arrays.sort(intervals, (a,b)-> Integer.compare(a[0], b[0]));

         List<int[]> list = new ArrayList<>();

         list.add(intervals[0]);

         for(int i =0; i<intervals.length; i++){
             int[] prev = list.get(list.size()-1);
             int[] curr = intervals[i];

             if(curr[0]<=prev[1]){
                 prev[0] = Math.min(prev[0], curr[0]);
                 prev[1] = Math.max(prev[1], curr[1]);
             }else{
                 list.add(intervals[i]);
             }
         }
         int[][] res = new int[list.size()][2];

         for(int i=0; i<list.size(); i++){
             res[i] = list.get(i);
         }
         return res;
     }
     public int[][] intervalIntersection(int[][] firstList, int[][] secondList){
        int i =0,
                j=0;
        List<int[]> list = new ArrayList<>();

        while (i<firstList.length && j<secondList.length){
            int e1 = firstList[i][1],
                e2 = secondList[j][1],
                s1 = firstList[i][0],
                s2 = secondList[j][0];

            if (e1>=s2 && e2>=s1){
                list.add(new int[]{
                        Math.max(s1,s2),
                        Math.min(e1,e2)
                });
            }

            if (e1<e2)
                i++;
            else
                j++;
        }
        int[][] res = new int[list.size()][2];
        for (int k=0; k<list.size(); k++){
            res[k]=list.get(k);
        }
        return res;
     }
     public int countDays(int days, int[][] meetings) {
         // Handle empty input
         if (meetings == null || meetings.length == 0) {
             return days;
         }

         // Sort meetings by start day (fixed Comparator syntax)
         Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));

         // Create list to store merged intervals
         List<int[]> res = new ArrayList<>();
         res.add(meetings[0]);

         // Merge overlapping meetings
         for (int i = 1; i < meetings.length; i++) {
             int[] last = res.get(res.size() - 1);
             if (meetings[i][0] <= last[1]) { // overlap
                 // Merge: update end (start does not change due to sorting)
                 last[1] = Math.max(last[1], meetings[i][1]);
             } else {
                 // No overlap: add the meeting as a new interval
                 res.add(meetings[i]);
             }
         }

         // Calculate total free days (gaps)
         int gap = 0;
         // Days before the first meeting
         gap += res.get(0)[0] - 1;
         // Gaps between merged meetings
         for (int i = 1; i < res.size(); i++) {
             gap += res.get(i)[0] - res.get(i - 1)[1] - 1;
         }
         // Days after the last meeting
         gap += days - res.get(res.size() - 1)[1];

         return gap;
     }

     /*Neat Brute force Algo : days - meetings*/
     public int countDaysNeatBruteForce(int days, int[][] mettings){
         Arrays.sort(mettings, Comparator.comparingInt(a->a[0]));
         List<int[]> res = new ArrayList<>();
         int meetingDays = 0;
                 res.add(mettings[0]);
         /*[2,3],[5,7],[6,7] // meeting ka start time > res ke end time se
         *        i
         *
         * res = [2,3]
         *
         * */
         for (int i=1; i<mettings.length; i++){
             if (mettings[i][0]>res.get(res.size()-1)[1]){
                 res.get(res.size()-1)[0] = Math.min(
                         res.get(res.size()-1)[0],
                         mettings[i][0]
                 );
                 res.get(res.size()-1)[1] = Math.max(
                         res.get(res.size()-1)[1],
                         mettings[i][1]
                 );
             }
         }

         for (int i=0; i<res.size(); i++){
             meetingDays+=res.get(i)[1]-res.get(i)[0]+1;
         }
         return days-meetingDays;
     }

     public int countDaysOptimize(int days, int[][] meetings){
         Arrays.sort(meetings, Comparator.comparingInt(a->a[0]));

         int maxEnd = meetings[0][1];
         int gap=0;

         for (int i=1; i<meetings.length; i++){
             if (meetings[i][0]>maxEnd){
                 gap+=meetings[i][0]-maxEnd-1;
             }
             maxEnd= Math.max(maxEnd,meetings[i][1]);
         }
         gap+=meetings[0][0]-1;
         gap+=days-maxEnd;

         return gap;
     }
     public int[][] insert(int[][] intervals, int[] newInterval) {
         List<int[]> res = new ArrayList<>();
         int i=0;

         while(i<intervals.length && newInterval[0] > intervals[i][1]){
             res.add(intervals[i]);
             i++;
         }

         while(i<intervals.length && intervals[i][0] <= newInterval[1]){
             newInterval[0] = Math.min(newInterval[0],intervals[i][0]);
             newInterval[1] = Math.max(newInterval[1],intervals[i][1]);
             i++;
         }
         res.add(newInterval);

         while(i<intervals.length){
             res.add(intervals[i]);
             i++;
         }
         int[][] ans = new int[res.size()][2];

         for(int j=0; j<res.size(); j++){
             ans[j]=res.get(j);
         }
         return ans;

     }
     public int removeCoveredIntervalsBruteForce(int[][] intervals){
         int count=0;
         /*
         TC"n^2 SC:1
         {{1,4}, {3,6}, {2,8}}
                          i
                              j
          a=2, b=8  2.........8
                    2.........8
          c=2, d=8
          count=2
         */
         for (int i=0; i<intervals.length; i++){
             int a = intervals[i][0],
                     b=intervals[i][1];
             boolean isCovers = false;
             for (int j=0; j<intervals.length; j++){
                 int c = intervals[j][0],
                         d=intervals[j][1];
                 if ((i !=j) && a<=c && b>=d){
                     isCovers=true;
                     break;
                 }
             }
             if (!isCovers)
                 count++;
         }
         return count;
     }
     public  int removeCoveredIntervalsOptimize(int[][] intervals){
         /*
         TC : n.log - SC : Sorting space
         * */
         int count=1;
         Arrays.sort(intervals, (a,b)->{
             int val = Integer.compare(a[0],b[0]);
             return val==0 ? Integer.compare(b[1],a[1]) : val;
         });
         int maxEnd = intervals[0][1];

         for (int i=1; i<intervals.length; i++){
             if (intervals[i][1]>maxEnd){
                 count++;
                 maxEnd=intervals[i][1];
             }
         }
         return count;
     }
     public boolean caAttendBruteForce(int[][] meetings){
          /*
         Time - O(n^2)
         Space - O(1)
         */
         for (int i=0; i<meetings.length; i++){
             int s1 = meetings[i][0],
                     e1 = meetings[i][1];
             for (int j=i+1; j<meetings.length; j++){
                 int s2 = meetings[j][0],
                         e2 = meetings[j][1];
                 if (s1<e2 && s2<e1)
                     return false;
             }
         }
         return  true;
     }
     public boolean canAttendOptimize(int[][] meetings){

         /*
         Time - O(n.log.n)
         Space - O(1)
         */
         Arrays.sort(meetings, Comparator.comparingInt(a->a[0]));

         for(int i=0; i<meetings.length-1; i++){
             int currentMeetingEnd= meetings[i][1];
             int nextMeetingStart = meetings[i+1][0];

             if (currentMeetingEnd>nextMeetingStart)
                 return false;
         }

         return true;
     }
     public boolean carPollingLineSweepAlgo(int[][] trips, int capacity){
         Map<Integer , Integer> map = new TreeMap<>();

         //Time: O(n.log.n)
         //space: O(n)
         //put all trips in map
         for (int[] trip : trips){
             int passenger=trip[0],
                     start=trip[1],
                     end=trip[2];

             map.put(start, map.getOrDefault(start,0) + passenger);
             map.put(end, map.getOrDefault(end,0) - passenger);
         }
         //apply line sweep algo
         int passengers =0;
         for (var entry: map.entrySet()){
             passengers = passengers+entry.getValue();

             if (passengers>capacity)
                 return false;

         }
         return true;
     }

     public boolean carPoolingBucketSortAlgo(int[][] trips, int capacity){
         int[] buckets = new int[1001];

         //time = O(1001 + n)
         //space = O(1001)

         //fill all trips in buckets & track start & end event or destination
         for (int[] trip : trips){
             int pass=trip[0],
                     start = trip[1],
                     end= trip[2];
             buckets[start] +=pass;
             buckets[end] -=pass;
         }
         //work same as line sweep
         int passengers=0;
         for (int bucket : buckets){
             passengers+=bucket;

             if (passengers>capacity)
                 return false;

         }
         return true;
     }

}

public  class  IntervalPatterDriver{
    public static void main(String[] args) {
        Solution solution = new Solution();
       /* int[][] intervals = {
                {1,4},
                {2,6},
                {8,10},
                {15,18},
        };
        int[][] merged = solution.mergeBruteForce(intervals);
        for (int[] num : merged)
            System.out.println(Arrays.toString(num) + " ");

    */

/*        int[][] firstList = {
                {0,2},{5,10},{13,23},{24,25}
        };
        int[][] secondList = {
                {1,5},{8,12},{15,24},{25,26}
        };
        System.out.println(Arrays.deepToString(solution.intervalIntersection(firstList, secondList)));*/

/*        int[][] meetings = {
                {5,7},{1,3},{2,4}
        };
        int days = 10;

        System.out.println(solution.countDaysNeatBruteForce(days,meetings));
        System.out.println(solution.countDaysOptimize(days,meetings));*/

        /*int[][] intervals1 = {{1,4}, {3,6}, {2,8}};
        System.out.println(solution.removeCoveredIntervalsBruteForce(intervals1));
        System.out.println(solution.removeCoveredIntervalsOptimize(intervals1));*/

/*        int[][] meetings = {
                {2,4},
                {9,12},
                {6,10}

        };
        System.out.println(solution.caAttendBruteForce(meetings));
        System.out.println(solution.canAttendOptimize(meetings));*/


        // Example 1
        int[][] trips1 = {{2, 1, 5}, {3, 3, 7}};
        int capacity1 = 4;
        boolean result1 = solution.carPollingLineSweepAlgo(trips1, capacity1);
        System.out.println("Example 1: " + result1);  // Expected: false

        // Example 2
        int[][] trips2 = {{2, 1, 5}, {3, 3, 7}};
        int capacity2 = 5;
        boolean result2 = solution.carPoolingBucketSortAlgo(trips2, capacity2);
        System.out.println("Example 2: " + result2);  // Expected: true

    }

}