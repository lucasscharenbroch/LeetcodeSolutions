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
    public void reorderList(ListNode head) {
        // record all nodes
        ArrayList<ListNode> nodeList = new ArrayList<>();
        for(ListNode current = head; current != null; current = current.next) {
            nodeList.add(current);
        }
        
        ListNode dummyHead = new ListNode();
        ListNode current = dummyHead;
        
        int i = 0;
        int j = nodeList.size() - 1;
        
        while(i <= j) {
            current = current.next = nodeList.get(i++);
            if(i > j) break;
            current = current.next = nodeList.get(j--);
        }
        
        current.next = null; // truncate
    }
}
