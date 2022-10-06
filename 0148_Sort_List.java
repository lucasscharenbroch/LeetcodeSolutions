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
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode();
        ListNode current = dummyHead;
        
        while(l1 != null || l2 != null) {
            if(l1 != null && (l2 == null || l1.val < l2.val)) {
                current = current.next = l1;
                l1 = l1.next;
            } else {
                current = current.next = l2;
                l2 = l2.next;
            }
        }
        
        return dummyHead.next;
    }
    
    private ListNode split(ListNode head) { // assume head.next != null
        ListNode slow = new ListNode(0, head), fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        
        // slow points to the middle node.
        ListNode secondHalfHead = slow.next;
        slow.next = null;
        return secondHalfHead;
    }
    
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode secondHalf = split(head);
        
        head = sortList(head);
        secondHalf = sortList(secondHalf);
        
        return merge(head, secondHalf);
    }
}
