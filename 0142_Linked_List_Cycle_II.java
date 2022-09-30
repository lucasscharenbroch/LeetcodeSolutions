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
public class Solution {
    public ListNode detectCycle(ListNode head) {
        HashSet<ListNode> inList = new HashSet<>();
        
        while(head != null) {
            if(inList.contains(head)) return head;
            inList.add(head);
            head = head.next;
        }
        
        return null;
    }
}
