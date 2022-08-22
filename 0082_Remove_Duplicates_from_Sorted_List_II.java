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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode oneBeforeHead = new ListNode(0, head); // dummy node (in case head is removed)
        ListNode current = oneBeforeHead;
        
        while(current.next != null && current.next.next != null) {
            if(current.next.val == current.next.next.val) { // current.next has duplicates
                // remove all duplicates
                do {
                    current.next.next = current.next.next.next;
                } while(current.next.next != null && current.next.val == current.next.next.val);
                
                // remove current.next
                current.next = current.next.next;
            } else { // current.next does not have duplicates
                current = current.next;
            }
        }
        
        return oneBeforeHead.next;
    }
}
