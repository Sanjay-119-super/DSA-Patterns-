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
/*     public Pair<Node, Node> splitList(Node head) {

         if (head == null) {
             return new Pair<>(null, null);
         }

         if (head.next == head) {
             return new Pair<>(head, null);
         }

         Node slow = head;
         Node fast = head;

         // find middle
         while (fast.next != head && fast.next.next != head) {
             slow = slow.next;
             fast = fast.next.next;
         }

         // even nodes case
         if (fast.next.next == head) {
             fast = fast.next;
         }

         Node head1 = head;
         Node head2 = slow.next;

         // make first half circular
         slow.next = head1;

         // make second half circular
         fast.next = head2;

         return new Pair<>(head1, head2);
     }*/
     public int duplicateNumber(int[] nums){
         int slow=0,
                 fast=0;
         do {
             slow=nums[slow];
             fast=nums[nums[fast]];
         }while (fast != slow);

         //cycle
         int n1 = 0, n2 = slow;
         while (n1 != n2){
             n1 = nums[n1];
             n2 = nums[n2];
         }
         return n1;
     }
     public boolean isPalindrome(ListNode head){
         // handle empty or single node
         if (head==null || head.next==null){
             return true;
         }
         //prepare fast & slow
         ListNode fast =head,
                 slow=head;
         while (fast.next !=null && fast.next.next != null){
             slow=slow.next;
             fast=fast.next.next;
         }

         ListNode secondHalfHead=reverseList(slow.next);

         ListNode i = head, j=secondHalfHead;
         boolean isPal=true;
         while (i != j){
             if (i.val != j.val){
                 isPal = false;
                 break;
             }
             i=i.next;
             j=j.next;
         }
         return isPal;
     }

     private ListNode reverseList(ListNode head) {
         ListNode prev = null;
         ListNode curr = head;

         while (curr != null){
             ListNode next = curr.next;
             curr.next=prev;
             prev=curr;
             curr=next;
         }
         return prev;
     }
 }
public class SlowFastPointerTester{
    public static void main(String[] args) {
        Solution solution = new Solution();
//        System.out.println(solution.isHappy(2));

        // 1 → 2 → 3 → 4 → 5
        /*Solution.ListNode  head = new Solution.ListNode(1,
                new Solution.ListNode(2,
                        new Solution.ListNode(3,
                                new Solution.ListNode(4,
                                        new Solution.ListNode(5)))));
       Solution.ListNode middleNode=solution.middleNode(head);
        System.out.println("Middle node: " + middleNode);*/

        /*int[] nums = {1,3,4,2,2,5};
        System.out.println(solution.duplicateNumber(nums));*/


        int[] nums = {1,3,4,2,2,5};
        System.out.println(solution.duplicateNumber(nums));
    }
}