// pure swappage
class Solution {
    public ListNode insertionSortList(ListNode head) {
        for(ListNode nodeToInsert = head.next; nodeToInsert != null; 
            nodeToInsert = nodeToInsert.next) {
            for(ListNode current = head; current != null; current = current.next) {
                if(nodeToInsert.val < current.val) {
                    // swap values of nodeToInsert and current
                    int temp = nodeToInsert.val;
                    nodeToInsert.val = current.val;
                    current.val = temp;
                }
            }
        }
        
        return head;
    }
}
