package in_place_pattern_05;

class ListNode{
    int val;
    ListNode next;
    public ListNode(){};
    public ListNode(int val){
        this.val=val;
    }
    public ListNode(int val, ListNode next){
        this.val=val;
        this.next=next;
    }
}

public class Solution {
    public ListNode reverseBruteForce(ListNode head){
        if (head==null||head.next==head){
            return head;
        }
        //find length of list
        int len =0;
        ListNode l = head;
        //[1->2->3->4->5]->x
        //                 l
        while (l != null){
            l=l.next;
            len++;
        }
        //fill list into a list array DS
        //Now len = 5
        l = head;
        ListNode[] arr = new ListNode[len];
        int i=0;
        while (l != null){
            arr[i]=l;
            i=i+1;
            l=l.next;
        }
        //arr: [(1), (2), (3), (4), (5)]->x
        //reverse the arr
        int star=0,
                end = len-1;
        while (star<end){
            ListNode temp = arr[star];
            arr[star]=arr[end];
            arr[end]=temp;

            star = star+1;
            end= end-1;
        }
        //arr: [(5), (4), (3), (2), (1)]->x
        //connect wire
        for (int k =0; k<len-1; k=k+1){
            arr[k].next = arr[k+1];
        }
        arr[len-1].next=null;
        return arr[0];

    }
    public ListNode inPlaceReverse(ListNode head){
        ListNode prev=null,
                curr=head,
        next = curr;

       //  x    [1->2->3->4->5]->x
        //     prev    curr
        //         next

        //prev=null, curr=head, next=curr
        while (curr != null){
            next = curr.next;

            //modify curr.next
            curr.next=prev;
            prev=curr;
            curr=next;
        }
        return prev;
    }

    public  ListNode InPlaceReverse(ListNode head){
        ListNode prev = null,
                curr= head;

        //  1->2->3->x
        //  h
        //     c
        //p
        while (curr != null){
            ListNode next = curr.next;
            curr.next=prev;
            prev=curr;
            curr=next;

        }

        return prev;
    }

    public ListNode bruteForceRemoveElements(ListNode head , int val){
        while (head !=null && head.val == val){
            head = head.next;
        }

        if (head==null)return null;

        ListNode prev = head,
                curr= head.next;

        while (curr != null){
            if (curr.val == val){
                prev.next = curr.next;
            }else {
                prev = curr;
            }
            curr = curr.next;
        }
        return head;
    }

    public ListNode inPlaceRemoveElements(ListNode head, int val){
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode curr = head,
                prev=dummy;

        while (curr != null){
            if (curr.val==val){
                prev.next=curr.next;
                curr=curr.next;
            }else {
                prev=curr;
                curr=curr.next;
            }
        }

        return dummy.next;
    }

    public ListNode deleteDuplicates(ListNode head){
        ListNode nodeA = head;
        //1->1->2->2->x
        //h
        //      A
        //      B
        while (nodeA != null){
            ListNode nodeB=nodeA.next;
            int val = nodeA.val;

            while (nodeB != null && val==nodeB.val){
                nodeB = nodeB.next;
            }
            nodeA.next=nodeB;
            nodeA=nodeB;
        }
        return head;
    }

}
