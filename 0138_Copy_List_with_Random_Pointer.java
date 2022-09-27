/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Integer> indexOfOriginal = new HashMap<>();
        HashMap<Integer, Node> copies = new HashMap<>();
        
        Node current = head;
        
        // record values and index numbers
        for(int i = 0; current != null; i++) {
            copies.put(i, new Node(current.val));
            indexOfOriginal.put(current, i);
            current = current.next;
        }
        
        current = head;
        
        for(int i = 0; current != null; i++) {
            copies.get(i).next = copies.get(i + 1); // attach next
            
            if(current.random != null) { // attach random
                copies.get(i).random = copies.get(indexOfOriginal.get(current.random));
            }
            
            current = current.next;
        }
        
        return copies.get(0);
    }
}
