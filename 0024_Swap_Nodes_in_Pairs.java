/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) { // cannot swap
            return head;
        }    
        
        ListNode rest = head.next.next; // a list with all nodes after head and head.next
        ListNode newHead = head.next;
        newHead.next = head;
        head.next = swapPairs(rest);
        
        return newHead;
    }
}
