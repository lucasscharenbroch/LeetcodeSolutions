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
    int highest = Integer.MIN_VALUE; // highest node seen to the right of current node after recursive call
    
    public ListNode removeNodes(ListNode head) {
        if(head == null) return null;
        head.next = removeNodes(head.next);
        if(head.val < highest) return head.next; // head is smaller then a value to the right of it, remove head
        highest = head.val;
        return head;
    }
}
