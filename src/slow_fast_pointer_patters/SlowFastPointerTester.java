package slow_fast_pointer_patters;

 class Solution {
     public boolean isHappy(int n){
         int fast = n,
                 slow=n;
         while (fast != 1){
             slow = helper(slow);
             fast = helper(helper(fast));

             if (fast ==1)
                 return true;
             if (fast == slow)
                 return false;
         }
         return true;
     }

     private int helper(int n) {
         int sum =0;
         while (n>0){
             int digit = n%10;
             sum+=(digit*digit);
             n/=10;
         }
         return sum;
     }

      static class ListNode{
         ListNode next;
         int val;

         public ListNode() {
         }

         public ListNode(int val) {
             this.val = val;
         }

         public ListNode(int val,ListNode next) {
             this.next = next;
             this.val = val;
         }

          @Override
          public String toString() {
              return "ListNode{" +
                      "next=" + next +
                      ", val=" + val +
                      '}';
          }
      }
     public ListNode middleNode(ListNode head){
         ListNode fast = head,
                 slow = head;
         while (fast != null && fast.next != null){
             slow = slow.next;
             fast = fast.next.next;
         }
         return slow;
     }

     public boolean hasCycle(ListNode head){
         ListNode fast = head,
                 slow =head;
         while (fast != null && fast.next != null){
             slow = slow.next;
             fast =fast.next.next;

             if (slow == fast){
                 return true;
             }
         }
         return false;
     }

     public ListNode detectCycle(ListNode head){
         ListNode fast = head,
                 slow=head;
         while (fast != null && fast.next != null){
             slow=slow.next;
             fast=fast.next.next;

             if (slow == fast){
                 ListNode n1 = head;
                 ListNode n2 = slow;

                 while (n1 != n2){
                     n1 = n1.next;
                     n2 = n2.next;
                 }
                 return n1;
             }
         }
         return null;
     }
     public int lengthOfLoop(ListNode head) {
         // code here
         ListNode fast = head,
                 slow = head;

         while(fast != null && fast.next != null){
             slow=slow.next;
             fast=fast.next.next;


             if(slow==fast){
                 int len=1;
                 ListNode temp = slow.next;
                 while(temp !=slow){
                     len++;
                     temp=temp.next;
                 }
                 return len;

             }
         }
         return 0;
     }
 }
public class SlowFastPointerTester{
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.isHappy(2));

        // 1 → 2 → 3 → 4 → 5
        Solution.ListNode  head = new Solution.ListNode(1,
                new Solution.ListNode(2,
                        new Solution.ListNode(3,
                                new Solution.ListNode(4,
                                        new Solution.ListNode(5)))));
       Solution.ListNode middleNode=solution.middleNode(head);
        System.out.println("Middle node: " + middleNode);
    }
}