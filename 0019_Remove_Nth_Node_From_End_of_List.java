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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode MthLastNode; // will point to the node before the Nth last node
        ListNode current = head;
        
        for(int i = 0; i < n; i++) {
            if(current == null) {
                // length < n (shouldn't happen) 
                return null;
            }
            current = current.next;
        }
        
        if(current == null) { // n == length
            return head.next;    
        } else {
            // advance once more
            current = current.next;    
        }
        
        MthLastNode = head;
        
        while(current != null) {
            current = current.next;
            MthLastNode = MthLastNode.next;
        }
        
        // remove the node after MthLastNode
        MthLastNode.next = MthLastNode.next.next;
        return head;
    }
}
