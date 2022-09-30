/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

// O(n) space - hash set
public class Solution {
    public boolean hasCycle(ListNode head) {
        HashSet<ListNode> inList = new HashSet<>();
        
        while(head != null) {
            if(inList.contains(head)) return true;
            inList.add(head);
            head = head.next;
        }
        
        return false;
    }
}

// O(1) space - two pointers
public class Solution {
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            
            if(slow == fast) return true;
        }
        
        return false;
    }
}
