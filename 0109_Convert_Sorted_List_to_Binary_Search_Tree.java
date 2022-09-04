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
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
        public TreeNode sortedListToBST(ListNode head) {
            if(head == null) return null;
            if(head.next == null) return new TreeNode(head.val);
            if(head.next.next == null) return new TreeNode(head.val, null, 
                                                           sortedListToBST(head.next));
            ListNode slow = new ListNode(0, head); // slow starts before head to ensure slow.next is the half-way node
            ListNode fast = head;
            
            while(fast.next != null && fast.next.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            
            // slow -> half-way node, fast -> last (or second last) node
            
            int value = slow.next.val;
            TreeNode right = sortedListToBST(slow.next.next);
            slow.next = null;
            TreeNode left = sortedListToBST(head);
            return new TreeNode(value, left, right);
    }
}
