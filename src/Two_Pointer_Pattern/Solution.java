package Two_Pointer_Pattern;

import java.util.*;

//DAY-2
public class Solution {
    public boolean isPalindrome(String s) {
        // Use two Pointer i =0, j=n-1
        int i = 0,
                j = s.length() - 1;

        /*
         *  "A man, a plan, a canal: Panama"
         *   i                            j
         * remove non-alphanumeric chars
         *
         *  "amanaplanacanalpanama"
         *            ij
         *
         *
         * */
        while (i < j) {
            char left = s.charAt(i),
                    right = s.charAt(j);
            // remove non-alphanumeric characters
            if (!Character.isLetterOrDigit(left)) {
                i = i + 1;
                continue;
            }
            if (!Character.isLetterOrDigit(right)) {
                j--;
                continue;
            }
            //  uppercase -> lowercase
            if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                return false;
            }
            i++;
            j--;

        }
        return true;

    }

    public void reverseString(char[] s) {
        int i = 0,
                j = s.length - 1;
        /*DRY - RUN
         * {'o','l','l','e','h'}
         *           ij
         *
         * */
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }

    public int[] sortedSquares(int[] nums) {
        int i = 0,
                j = nums.length - 1;
        int k = nums.length - 1;

        int[] res = new int[nums.length];
        /*
         * [-4,-1,0,3,10]
         *      i j
         *  k
         *  0 1 9  16  100
         *
         * */
        while (i < j) {
            if (Math.abs(nums[i]) > Math.abs(nums[j])) {
                res[k] = nums[i] * nums[i];
                i++;

            } else {
                res[k] = nums[j] * nums[j];
                j--;

            }
            k--;

        }
        return res;
    }

    public boolean validPalindrome(String s) {
        int i = 0,
                j = s.length() - 1;
        /*
         * "aba"
         *   j
         *   i no delete
         *
         * "abca"
         *   ij -> mismatch need to delete delete c = aba
         * "aba"
         *   j
         *   i -> valid palindrome
         *
         * */
        while (i <= j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            } else {
                return isPalindrome(s, i + 1, j) || isPalindrome(s, i, j - 1);
            }
        }
        return true;
    }

    public static boolean isPalindrome(String s, int i, int j) {
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }

        return true;
    }

    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0,
                j = 0;

        /*
        * word = "implementation"
        *                       i
        * abbr = "i12n"
        *            j
        *
        * index =0 match i++, j++
        * index =1 have number 12 create number
        * word: i m p l e m e n t a t i o n
                0 1 2 3 4 5 6 7 8 9 10 11 12 13

          Step 1: i=0, match 'i' -> i=1, j=1
          Step 2: abbr[j]='1', parse '12' -> curr=12, j=3, i=1+12=13
         (Skip karo 12 characters: m p l e m e n t a t i o)
         Step 3: abbr[j]='n', word[13]='n' match -> i=14, j=4
         *
         *
* while (j < abbr.length() && Character.isDigit(abbr.charAt(j)))
Yeh loop tab tak chalta hai jab tak digit milti rahe.
Example "12":
Pehle '1' dikha → curr = 0*10 + (1) = 1
Agla character '2' bhi digit hai → curr = 1*10 + (2) = 12
Agla character 'n' digit nahi hai → loop ruk gaya, curr = 12 final.
Isi ko kehte hain parse integer from string.
        * */
        while (i < word.length() && j < abbr.length()) {
            char w_c = word.charAt(i);
            char a_c = abbr.charAt(j);

            if (Character.isDigit(a_c)) {
                //case.1 0
                if (a_c == '0') {
                    return false;
                }
                //case.3 handle number
                int curr = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    curr = curr * 10 + (abbr.charAt(j) - '0');
                    j++;
                }
                //add skip number in word
                i += curr;

            } else {
                //case.2 mismatch
                if (a_c != w_c) {
                    return false;
                }
                i++;
                j++;
            }
        }
        //return same length of both string after checking
        return i == word.length() && j == abbr.length();
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;

        /*
          [1,2,2,3,5,6] = m : 3 , i = 3-1 = 2
        i
        k     3>6 = J , 3>5 J, 2>3 i, 2>2 i, 1>2 i
          [2,5,6] = n : 3 , j = 3-1 = 2
           j

          k=m+n-1 , k = 3+3-1 , 5

        */

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        //remaining element in nums2
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
    public int countPairs(List<Integer> nums, int target){
        int count=0;
        int i =0,
                j = nums.size()-1;

        /*   0 1 2 3 4
           [-1,1,2,3,1]
               i j   --> j-i = 4-1 = 3

        */
        while (i<j){
            if (nums.get(i) + nums.get(j) ==target){
                count+=(j-i);
                i++;
            }
            j--;
        }
        return count;
    }

    public int[] twoSum(int[] nums, int target){
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0; i<nums.length; i++){
            int x = target-nums[i];
            if(map.containsKey(x)){
                return new int[] {map.get(x), i};
            }
            map.put(nums[i],i);
        }
        return new int[]{-1,-1};
    }
    public int[] twoSum2(int[] nums, int target){
        int i =0,
                j = nums.length-1;

        while(i<j){
            int sum = nums[i] + nums[j];
            if(sum<target)
                i++;
            else if(sum>target)
                j--;
            else
                return new int[] {i+1,j+1};

        }
        return new int[] {-1,-1};
    }
    public List<List<Integer>> threeSum(int[] nums){
        //create list of lists for res
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        //rest i & skip duplicate
        for (int i=0; i<nums.length; i++){
            if (i >0 && nums[i] == nums[i-1]){
                continue;
            }
            // setup two pinters
            int j = i+1;
            int k = nums.length-1;

            while (j<k){
                int sum = nums[i] + nums[j] + nums[k];

                if (sum<0)
                    j++;
                else if (sum>0) {
                    k--;
                }
                else {
                    // add triple in res asList
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    // skip dupilcate triplet
                    while (nums[j] == nums[j-1] && j<k){
                        j++;
                    }

                }
            }

        }



        // return res
        return res;
    }
    public int[] sortTwoColors(int[] nums){
        /*
        * [0,0,0,0,0,1,1,1,1]
        *            i
        *            j
        * */
        int i =0,
                j=nums.length-1;
        while (i<j){
            if (nums[i] == 0){
                i++;
            }else {
                int temp = nums[i];
                nums[i]=nums[j];
                nums[j]=temp;
                j--;
            }
        }
        return nums;
    }
    public void  sortColors(int[] nums){
        int i=0,
                k=0,
                j=nums.length-1;

        /*
        * [2,0,2,1,1,0] | k==1 k++ | k==2 swap to j j-- | k==0 swap to i i++ ,k++
        *  i
        *  k
        *            j
        *
        * */
        while (k<=j){
            if (nums[k]==1){
                k++;
            }else if (nums[k]==2){
                int temp = nums[k];
                nums[k]=nums[j];
                nums[j]=temp;
                j--;
            }else {
                int temp = nums[k];
                nums[k]=nums[i];
                nums[i]=temp;
                i++;
                k++;
            }
        }
    }

    public static class ListNode{
        int val;
        ListNode next;
       public ListNode(){

        }
       public ListNode(int val){
            this.val=val;
        }
        public ListNode(int val, ListNode next){
           this.val=val;
           this.next=next;
        }
    }
    public ListNode removeNthFromEnd(ListNode head,int n){
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode slow = dummy, fast=dummy;

        for (int i=0; i<=n; i++){
            fast=fast.next;
        }
        while (fast != null){
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return dummy.next;
    }
    public static boolean isStrobogrammatic(Map<Character,Character> map , String s){
        int i=0,
                j=s.length()-1;

        /*
        * s= "181"
        *      i
        *      j
        *
        *  left =1
        *  right=1
        *
        *  map= {1:1}
        *       {6:9}
        *       {9:6}
        *       {8:8}
         * */
        while (i<j){
            char left = s.charAt(i),
                    right = s.charAt(j);
            if (map.containsKey(left)){ // key = 1
                if (map.get(left) != right) // 1!=1
                    return false;
                i++;
                j--;
            }else {
                return false; // key hi nhi hai
            }

        }
        return true; // match
    }
    public boolean strobogrammatic(String s){
        Map<Character , Character> map = new HashMap<>();

        map.put('0','0');
        map.put('1','1');
        map.put('6','9');
        map.put('9','6');
        map.put('8','8');

        return isStrobogrammatic(map, s);
    }
    public int appendCharacters(String s, String t){
        int i=0,
                j=0;
        /*
             0 1 2 3 4 5 6 7
        * s="c o a c h i n g"
        *                    i
             0 1 2 3 4 5
        * t="c o d i n g"
        *      j
        *   append minimum character = ding (4) t ka length = 6 , j = 2 so t.length()-j = 4
        * */
        while (i<s.length() && j<t.length()){
            if (s.charAt(i) == t.charAt(j)){
                i++;
                j++;
            }else {
                i++;
            }
        }
        return t.length()-j;
    }
    public static class Node{
        Node next;
        int val;
        Node parent;
    }
    public Node LCA(Node p,Node q){

        Node p1 = p,
                q1 = q;
        while (p1 != q1){
            p1 = (p1==null) ? q : p1.parent;
            q1 = (q1==null) ? p : q1.parent;
        }
        return p1;

    }
}
class CheckSolution{
    public static void main(String[] args) {
        Solution solution = new Solution();
        /*String s = "A man, a plan, a canal: Panama";
        System.out.println(solution.isPalindrome(s));*/

        /*char[] s = {'h','e','l','l','o'};
        solution.reverseString(s);
        for (char c : s){
            System.out.print(c +" ");
        }*/
        /*int[] nums={-4,-1,0,3,10};
        int[] ints = solution.sortedSquares(nums);
        for (int n : ints){
            System.out.print(n + " ");
        }*/

       /* String s = "abahf";
        System.out.println(solution.validPalindrome(s));*/

       /* String word = "implementationjg";
        String abbr = "i12n";
        System.out.println(solution.validWordAbbreviation(word,abbr));*/

/*        int[] nums1 = {1,2,3,0,0,0};
        int m =3;
        int[] nums2 = {2,5,6};
        int n =3;
        solution.merge(nums1,m,nums2,n);

        for(int x : nums1){
            System.out.print(x + " ");
        }*/

     /*   List<Integer> nums = new ArrayList<>();
        nums.add(-1);
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(1);

        int target = 2;

        System.out.println(solution.countPairs(nums,target));*/

     /*   int[] nums = {2,7,9,11,15};
        int target = 9;
        int[] twoSum = solution.twoSum(nums, target);
        for (int n : twoSum){
            System.out.println(n + " ");
        }*/

      /*     int[] nums = {2,7,9,11,15};
        int target = 9;
        int[] twoSum = solution.twoSum2(nums, target);
        for (int n : twoSum){
            System.out.println(n + " ");
        }*/
        /*int[] nums = {-1,0,1,2,-1,-4};
        List<List<Integer>> threeSum = solution.threeSum(nums);
        for (List<Integer> triplet : threeSum){
            System.out.print(triplet + " ");
        }*/

       /* int[] nums = {0,0,0,0,0,1,1,1,1};
        int[] sortTwoColors = solution.sortTwoColors(nums);
        System.out.println(Arrays.toString(sortTwoColors));*/

        /*int[] colors = {2,0,2,1,1,2};
        solution.sortColors(colors);
        for (int n : colors){
            System.out.print(n + " ");
        }*/

        // Test: [1,2,3,4,5], n=2
        /*Solution.ListNode head = new Solution.ListNode(1,new Solution.ListNode(2, new Solution.ListNode(3, new Solution.ListNode(4, new Solution.ListNode(5)))));

        System.out.print("Original: ");
        print(head);

        Solution.ListNode result = solution.removeNthFromEnd(head, 2);

        System.out.print("After remove: ");
        print(result);
    }

    static void print(Solution.ListNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.next;
        }
        System.out.println();*/

       /* String s = "181";

        System.out.println(solution.strobogrammatic(s));*/

        String s = "coaching";
        String t = "coding";

        System.out.println(solution.appendCharacters(s,t));


    }
}