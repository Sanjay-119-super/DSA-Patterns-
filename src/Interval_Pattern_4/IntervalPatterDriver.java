package Interval_Pattern_4;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
}

public  class  IntervalPatterDriver{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] intervals = {
                {1,4},
                {2,6},
                {8,10},
                {15,18},
        };
        int[][] merged = solution.mergeBruteForce(intervals);
        for (int[] num : merged)
            System.out.println(Arrays.toString(num) + " ");
    }


}