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
    public ListNode partition(ListNode head, int x) {
        // dummy nodes that point to the respective node lists
        ListNode greaterHead = new ListNode(), lessHead = new ListNode();
        // iterators for the respective lists
        ListNode greater = greaterHead, less = lessHead;
        
        // place each node into the list based on its value
        while(head != null) {
            if(head.val < x) {
                less.next = head;
                less = less.next;
                head = head.next;
            } else {
                greater.next = head;
                greater = greater.next;
                head = head.next;
            }
        }
        
        // truncate, connect, and return
        greater.next = null;
        less.next = greaterHead.next;
        return lessHead.next;
    }
}
