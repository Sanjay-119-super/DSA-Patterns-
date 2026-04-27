package sliding_window_pattern3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    /**
     * Computes the maximum sum of any contiguous subarray of size k using brute force.
     * Time Complexity: O(n*k)
     * Space Complexity: O(1)
     *
     * @param arr input array
     * @param k   window size
     * @return maximum sum of a subarray of length k
     */
    public int maxSubArrayBruteForce(int[] arr, int k) {
        int max = 0;

        for (int i = 0; i <= arr.length - k; i++) {
            int sum = 0;
            for (int j = i; j < i + k; j++) {
                sum += arr[j];
            }
            max = Math.max(max, sum);
        }
        return max;
    }

    /**
     * Finds the maximum sum of a fixed-size subarray using the sliding window technique.
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param arr input array
     * @param k   window size
     * @return maximum sum of a subarray of length k
     */
    public int maxSubArraySlidingWindowFixed(int[] arr, int k) {
        int max = 0;
        int sum = 0;

        // Compute initial window sum
        for (int j = 0; j < k; j++) {
            sum += arr[j];
        }
        max = Math.max(sum, max);

        // Slide the window
        for (int i = k; i < arr.length; i++) {
            sum += arr[i];         // expand
            sum -= arr[i - k];     // shrink
            max = Math.max(sum, max);
        }
        return max;
    }

    /**
     * Returns the maximum sum of a subarray of size k with all distinct elements.
     * Uses brute force with a hash set to check duplicates.
     * Time Complexity: O(n*k)
     * Space Complexity: O(k)
     *
     * @param nums input array
     * @param k    window size
     * @return maximum sum of a distinct-element subarray of length k, 0 if none
     */
    public long maximumSubarraySumBruteForce(int[] nums, int k) {
        long max = 0;

        for (int i = 0; i <= nums.length - k; i++) {
            Set<Integer> dups = new HashSet<>();
            boolean isDups = false;
            long sum = 0;
            for (int j = i; j < i + k; j++) {
                if (dups.contains(nums[j])) {
                    isDups = true;
                    break;
                }
                dups.add(nums[j]);
                sum += nums[j];
            }
            if (!isDups) {
                max = Math.max(max, sum);
            }
        }
        return max;
    }

    /**
     * Computes the maximum sum of a size-k subarray with all distinct elements.
     * Uses a sliding window and a frequency map to track duplicates in O(1) per step.
     * Time Complexity: O(n)
     * Space Complexity: O(k)
     *
     * @param nums input array
     * @param k    window size
     * @return maximum sum of a distinct-element subarray of length k, 0 if none
     */
    public long maximumSubarraySumOptimizeSlidingFixedWindow(int[] nums, int k) {
        long sum = 0, max = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        int dupCount = 0;

        // Initialize first window
        for (int i = 0; i < k; i++) {
            freq.putIfAbsent(nums[i], 0);
            freq.put(nums[i], freq.get(nums[i]) + 1);
            sum += nums[i];
            if (freq.get(nums[i]) > 1) {
                dupCount++;
            }
        }
        if (dupCount == 0) {
            max = Math.max(max, sum);
        }

        // Slide window, maintain sum and duplicate count
        for (int i = k; i < nums.length; i++) {
            int numToAdd = nums[i];
            int numToRemove = nums[i - k];

            freq.putIfAbsent(numToAdd, 0);
            freq.put(numToAdd, freq.get(numToAdd) + 1);
            if (freq.get(numToAdd) > 1) {
                dupCount++;
            }
            sum += numToAdd;

            if (freq.get(numToRemove) > 1) {
                dupCount--;
            }
            freq.put(numToRemove, freq.get(numToRemove) - 1);
            sum -= numToRemove;

            if (dupCount == 0) {
                max = Math.max(max, sum);
            }
        }
        return max;
    }
}

public class SlidingWindow{
    public static void main(String[] args) {
        Solution solution = new Solution();
        /*int[] arr = {100, 200, 300, 400};
        int k=2;
        System.out.println(solution.maxSubArrayBruteForce(arr,k));
        System.out.println(solution.maxSubArraySlidingWindowFixed(arr,k));*/

        int[] nums={1,5,4,2,9,9,9};
        int k=3;
//        System.out.println(solution.maximumSubarraySumBruteForce(nums,k));
        System.out.println(solution.maximumSubarraySumOptimizeSlidingFixedWindow(nums,k));
    }
}