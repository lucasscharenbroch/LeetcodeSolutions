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

// find minimum node, add it to the sorted list
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        // convert lists into a List (so elements can be removed)
        List<ListNode> listsList = new ArrayList(Arrays.asList(lists));
        
        // remove empty lists
        for(int i = 0; i < listsList.size(); i++){
            if(listsList.get(i) == null) {
                listsList.remove(i--);
            }
        }
        
        ListNode head = new ListNode(); // dummy head node (head->next is the effective head)
        ListNode current = head;
        
        while(!listsList.isEmpty()) {
            int minimumNodeIndex = 0;
            for(int i = 1; i < listsList.size(); i++) {
                minimumNodeIndex = (listsList.get(minimumNodeIndex).val < listsList.get(i).val) ? 
                                    minimumNodeIndex : i;
            } 
            
            current = current.next = listsList.get(minimumNodeIndex);
            listsList.set(minimumNodeIndex, listsList.get(minimumNodeIndex).next);
            if(listsList.get(minimumNodeIndex) == null) {
                listsList.remove(minimumNodeIndex);    
            }
        }
        
        return head.next;
    }
}

// merge two lists k - 1 times
class Solution {
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        
        ListNode head = new ListNode(); // dummy starting node
        ListNode current = head;
        
        while(list1 != null && list2 != null){
            if(list1.val < list2.val) {
                current = current.next = list1;
                list1 = list1.next;
            } else {
                current = current.next = list2;
                list2 = list2.next;
            }
        }
        
        if(list1 != null) {
            current.next = list1;
        } else if(list2 != null) {
            current.next = list2;
        }
        
        return head.next;
    }
    
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) {
            return null;
        }
        
        for(int i = 0; i < lists.length - 1; i++) {
            lists[i + 1] = mergeTwoLists(lists[i], lists[i + 1]);    
        }
        
        return lists[lists.length - 1];
    }
}
