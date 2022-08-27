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
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummyHead = new ListNode(0, head); // points to head
        ListNode current = dummyHead;
        
        // advance past the first (left - 2) nodes (-> the last unreversed node)
        for(int i = 0; i < left - 1; i++) current = current.next;
        ListNode lastUnreversedNode = current;
        
        // advance one more (current -> first reversed node)
        current = current.next;
        ListNode reversedTail = current;
        
        // reverse (left - right + 1) nodes
        ListNode reversedHead = null, temp;
        // reverse the sublist
        for(int i = left; i <= right; i++) {
            temp = current;
            current = current.next;
            temp.next = reversedHead;
            reversedHead = temp;
        }
        
        // re-attach the reversed list
        lastUnreversedNode.next = reversedHead;
        reversedTail.next = current;
        
        return dummyHead.next;
    }
}
