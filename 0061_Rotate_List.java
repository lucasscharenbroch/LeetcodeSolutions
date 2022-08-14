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
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null) return null; // 0-length edgecase
        
        // find lastNode and listLength
        ListNode lastNode = head;
        int listLength = 1;
        while(lastNode.next != null) {
            listLength++;
            lastNode = lastNode.next;
        }
        
        if((k %= listLength) == 0) return head;
        
        // find newTail ((listlength - k - 1)th node) and newHead ((listLength - k)th node)
        ListNode newTail = head;
        for(int i = 0; i < listLength - k - 1; i++) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        
        // juggle some pointers
        newTail.next = null;
        lastNode.next = head;
        
        return newHead;
    }
}
