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
    private ListNode reverseLinkedList(ListNode head) {
        ListNode newHead = null;
        ListNode temp;
        
        while(head != null) {
            temp = head;
            head = head.next;
            temp.next = newHead;
            newHead = temp;
        }
        
        return newHead;
    }
    
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode current = head;    
        for(int i = 0; i < k - 1; i++) { // move current to head[k - 1]
            if(current == null) return head; // length < k
            current = current.next;
        }
        
        if(current == null) return head;
        ListNode rest = current.next; // next node after reversed portion
        current.next = null; // truncate reversed portion
        current = reverseLinkedList(head); // current = new head, head = end of reversed list
        head.next = reverseKGroup(rest, k);
        
        return current;
    }
}
