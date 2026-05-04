package sliding_window_pattern3;

import java.util.*;

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
    public int minSubArrayLenBruteForce(int[] nums, int target){
        int min = Integer.MAX_VALUE;
        for (int i=0; i<nums.length; i++){
            int sum=0;
            for (int j=i; j<nums.length; j++){
                sum+=nums[j];
                if (sum>=target){
                    min=Math.min(min,j-i+1);
                    break;
                }
            }
        }
        return min==Integer.MAX_VALUE ? 0 : min;
    }
    public int minSubArrayLenOptimizeDynamicSlidingWindow(int[] nums, int target){
        int min = Integer.MAX_VALUE;
        int i=0,
                j=0;
        int sum=0;

        while (j<nums.length){
            sum+=nums[j];
            while (sum>=target){
                min = Math.min(min,j-i+1);
                sum-=nums[i];
                i++;
            }
            j++;
        }
        return min==Integer.MAX_VALUE ? 0 : min;
    }
    public  boolean containsNearbyDuplicateBruteForce(int[] nums, int k){
        /*
        * [1,2,3,1], k = 3
        *    i
        *        j
        * j==3
        * <2,3,1>
        *
        * */
        for (int i = 0; i < nums.length; i++) {
            Set<Integer> checkDups = new HashSet<>();
            for (int j = i; j <=Math.min(i+k, nums.length-1) ; j++) {
                if (checkDups.contains(nums[j]))
                    return true;
                checkDups.add(nums[j]);
            }
        }
        return false;
    }
    public boolean containsNearbyDuplicateOptimizeFixedSlidingWindow(int[] nums , int k){
        Set<Integer> checkDup = new HashSet<>();
        //initial window check withing range j+k, n
        for (int i=0; i<Math.min(k,nums.length); i++){
            if (checkDup.contains(nums[i]))
                return true;
            checkDup.add(nums[i]);
        }

        //slid window
        for (int i=k; i<nums.length; i++){
            if (checkDup.contains(nums[i]))
                return true;
            checkDup.add(nums[i]);
            checkDup.remove(nums[i-k]);
        }
        return false;
    }
    public  int lengthOfLongestSubstringBruteForce(String s){
        int max = 0;
        for (int i =0; i<s.length(); i++){
            Set<Character> set = new HashSet<>();
            for (int j =i; j<s.length(); j++){
                char c = s.charAt(j);
                if (set.contains(c))
                    break;
                set.add(c);
                max = Math.max(max,j-i+1);
            }
        }
        return max;
    }
    public int lengthOfLongestSubstringOptimizeSlidingDynamicWindow(String s){
        int max=0;
        Set<Character> set = new HashSet<>();
        int i=0,
                j=0;
        while (j<s.length()){
            char c = s.charAt(j);
            while (set.contains(c)){
                set.remove(s.charAt(i));
                i++;
            }
            set.add(c);
            max = Math.max(max, j-i+1);
            j++;
        }
        return max;
    }

    public double findMaxAverageBruteForce(int[] nums , int k){
        double max =0;
        for (int i=0; i<=nums.length-k; i++){
            int sum = 0;
            for (int j=i; j<i+k; j++){
                sum+=nums[j];

            }
            max = Math.max(max, sum);

        }

        return (double) max/k;
    }

    public double findMaxAverageOptimizeFixedSlidingWindow(int[] nums, int k) {
        double max = Double.MIN_VALUE;
        int sum=0;

        for(int j=0; j<k; j++){
            sum+=nums[j];
        }
        max = sum;
        for(int i=k; i<nums.length; i++){
            sum+=nums[i];
            sum-=nums[i-k];
            max = Math.max(max,sum);
        }
        return (double) max/k;
    }
    public List<String> findRepeatedDnaSequencesBruteForce(String s){
        Set<String> seen = new HashSet<>();
        Set<String> res = new HashSet<>();

        /**
         * ============================================================
         *  BRUTE FORCE APPROACH
         * ============================================================
         *
         * 💡 Intuition:
         * - Slide a window of size 10 over the string.
         * - Store every 10‑letter substring in a HashSet 'seen'.
         * - If a substring is already in 'seen', add it to the result set.
         *
         * ⏱️ Time Complexity:  O(n)   (n = s.length())
         *     - Loop runs (n-9) times, each substring takes O(10) = O(1)
         *     - Set operations are O(1) average
         *
         * 💾 Space Complexity: O(n)
         *     - In worst case, all substrings distinct → 'seen' stores ~n strings
         *     - Each string is 10 chars (constant overhead)
         *
         * 🧪 Dry Run Example:
         *     s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT" (length 31)
         *
         *     Window start i = 0 : "AAAAACCCCC" → not seen, add to seen
         *     ...
         *     i = 10: "AAAAACCCCC" → already seen! → add to res
         *     i = 20: "CCCCCAAAAA" → repeats → add to res
         *     Result: {"AAAAACCCCC","CCCCCAAAAA"}
         */
        for (int i=0; i<=s.length()-10; i++){
            String dna = s.substring(i, i+10);
            if (seen.contains(dna))
                res.add(dna);
            seen.add(dna);
        }
        return new ArrayList<>(res);
    }

    public List<String> findRepeatedDnaSequencesRobinKarpAlgoWithFixedSlidingWindow(String s){
        int k=10;
        int sumRep =0;
        Set<Integer> seen = new HashSet<>();
        Set<String> res = new HashSet<>();
        Map<Character,Integer> map = new HashMap<>();
        map.put('A',0);
        map.put('C',1);
        map.put('G',2);
        map.put('T',3);

        /**
         * ============================================================
         *  OPTIMIZED (ROLLING HASH / RABIN‑KARP) APPROACH
         * ============================================================
         *
         * 💡 Intuition:
         * - Encode the 4 letters (A, C, G, T) as digits 0‑3, i.e., a base‑4 number.
         * - A 10‑letter substring becomes a 10‑digit base‑4 integer.
         *   Value range: 0 to 4^10 - 1 ≈ 1 million → fits in an int.
         *
         * - Instead of recomputing the hash from scratch, we use a **rolling hash**:
         *   newHash = (oldHash - outgoingDigit * 4^9) * 4 + incomingDigit
         *   This O(1) update allows O(n) total time with minimal constant overhead.
         *
         * 🔢 Encoding Map:
         *   'A' → 0, 'C' → 1, 'G' → 2, 'T' → 3
         *
         * 📐 Detail Steps:
         *   1. Compute 4^9 = 262144 once.
         *   2. Initial window hash: for i=0..9, sum of (digit * 4^(9-i)).
         *   3. Store hash in 'seen' integer set.
         *   4. For i = 10 to n-1:
         *        - Subtract leftmost char's contribution: oldHash -= leftDigit * power
         *        - Multiply remaining by 4 (shift left in base‑4)
         *        - Add new char's digit
         *        - If hash already seen → substring repeated → extract via substring and add to result
         *        - Add hash to seen
         *
         * ⏱️ Time Complexity:  O(n)
         *     - Initial window hash O(10)=O(1)
         *     - Sliding loop O(n) with O(1) operations per iteration
         *
         * 💾 Space Complexity: O(n)
         *     - 'seen' stores at most n-9 distinct integer hashes (4 bytes each)
         *     - Result set stores worst‑case n/2 substrings (10 chars each)
         *     - Both together still O(n)
         *
         * 🧪 Dry Run (First few steps) on s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT" (n=31, k=10)
         *     Power = 4^9 = 262144
         *
         *     Initial window (i=0..9): "AAAAACCCCC"
         *       Positions: A A A A A C C C C C
         *       Digits:    0 0 0 0 0 1 1 1 1 1
         *       Hash = 0*4^9 + 0*4^8 + ... + 0*4^5 + 1*4^4 + 1*4^3 + 1*4^2 + 1*4^1 + 1*4^0
         *            = 256 + 64 + 16 + 4 + 1 = 341
         *       seen = {341}
         *
         *     i=10: remove s[0]='A'(0), add s[10]='A'(0)
         *       newHash = (341 - 0*262144) * 4 + 0 = 341*4 = 1364   (window "AAAACCCCCA")
         *       1364 not in seen → add, seen={341,1364}
         *
         *     i=11: remove s[1]='A'(0), add s[11]='A'(0)
         *       newHash = 1364*4 = 5456   (window "AAACCCCCAA")
         *       add → seen={341,1364,5456}
         *
         *     ... (continues) ...
         *
         *     When the window starting at index 10 ("AAAAACCCCC") appears again,
         *     the hash will be 341, which is already in seen → repeated → extract substring
         *     "AAAAACCCCC" and add to result. Similarly for "CCCCCAAAAA".
         *
         *     Final result: ["AAAAACCCCC","CCCCCAAAAA"]
         */

        for (int i=0; i<k; i++){
            int pow = k-i-1;
            sumRep+=(int)Math.pow(4,pow) * map.get(s.charAt(i));
        }
        seen.add(sumRep);

        //Game On Remove / ADD in content time
        for (int i=k; i<s.length(); i++){
            sumRep-=(int) Math.pow(4,k-1)*map.get(s.charAt(i-k));
            sumRep*=4;
            sumRep+=map.get(s.charAt(i));
            if (seen.contains(sumRep))
                res.add(s.substring(i-k+1,i+1));
            seen.add(sumRep);
        }
        return new ArrayList<String>(res);
    }
    public int lengthOfLongestSubstringDynamicSlidingWindowWithMap(String s){
        int max =0;
        Map<Character,Integer> map = new HashMap<>();
        int i=0,
                j=0;
        while (j<s.length()){
            char c = s.charAt(j);
            if (map.containsKey(c)){
                if (map.get(c)>=i){
                    i=map.get(c)+1;
                }
            }
            map.put(c,j);
            max = Math.max(max,j-i+1);
            j++;

        }
        return max;
    }
    public  int totalFruitBruteForce(int[] fruits){
        int max =0;
        for (int i=0; i<fruits.length; i++){
            Set<Integer> basket = new HashSet<>();
            for (int j=i; j<fruits.length; j++){
                basket.add(fruits[j]);
                if (basket.size()>2)
                    break;
                max=Math.max(max,j-i+1);
            }

        }
        return max;
    }
    public int totalFruitDynamicWindowWithMapOptimize(int[] fruits){
        int max =0;
        int i=0,
                j=0;
        Map<Integer,Integer> map = new HashMap<>();

        while (j<fruits.length){
            map.put(fruits[j],map.getOrDefault(fruits[j],0)+1);
            while (map.size()>2){
                map.put(fruits[i],map.get(fruits[i])-1);

                if (map.get(fruits[i])==0)
                    map.remove(fruits[i]);
                i++;
            }
            max=Math.max(max,j-i+1);
            j++;
        }
        return max;

    }
    public int characterReplacementBruteForce(String s, int k){
        int max =0;
        /*
           0123
          "ABAB", k=2 i=0, j=1, max=1, maxC=2, freqC={A-2,B-2} OPS=2,K=2
        *  i
        *     j
        *
        *
        * */
        for (int i=0; i<s.length(); i++){
            int maxC =0;
            int[] freqC = new int[26];
            for (int j=i; j<s.length(); j++){
                char c = s.charAt(j);
                freqC[c-'A']=freqC[c-'A']+1;
                maxC=Math.max(maxC,freqC[c-'A']);
                int ops =(j-i+1)-maxC;
                if (ops>k) break;

                max=Math.max(max,j-i+1);
            }
        }
        return max;
    }
    public  int characterReplacementOptimizeSlidingWindow(String s, int k){
        int max =0, maxC=0, i=0, j=0;
        int[] freqC = new int[26];

        while (j<s.length()){
            char c = s.charAt(j);
            freqC[c-'A']=freqC[c-'A']+1;
            maxC=Math.max(maxC,freqC[c-'A']);

            while (j-i+1-maxC>k){
                char d = s.charAt(i);
                freqC[d-'A']=freqC[d-'A']-1;
                maxC=Math.max(maxC,freqC[c-'A']);
                i++;
                for (int l=0; l<26; l++){
                    max=Math.max(max,j-i+1);
                }
            }
            max=Math.max(max,j-i+1);
            j++;
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

/*        int[] nums={1,5,4,2,9,9,9};
        int k=3;
//        System.out.println(solution.maximumSubarraySumBruteForce(nums,k));
        System.out.println(solution.maximumSubarraySumOptimizeSlidingFixedWindow(nums,k));*/

        /*int[] nums = {2,3,1,2,4,3};
        int target=7;
        System.out.println(solution.minSubArrayLenBruteForce(nums,target));
        System.out.println(solution.minSubArrayLenOptimizeDynamicSlidingWindow(nums,target));*/
/*
        int[] nums = {1,2,3,1};
        int k =3;
        System.out.println(solution.containsNearbyDuplicateBruteForce(nums,k));
        System.out.println(solution.containsNearbyDuplicateOptimizeFixedSlidingWindow(nums,k));*/

        /*int[] fruits = {0,1,2,1,2};
        System.out.println(solution.totalFruitBruteForce(fruits));
        System.out.println(solution.totalFruitDynamicWindowWithMapOptimize(fruits));*/

        String s = "AABABBA";
        int k=1;
        System.out.println(solution.characterReplacementBruteForce(s,k));
        System.out.println(solution.characterReplacementOptimizeSlidingWindow(s,k));
    }
}