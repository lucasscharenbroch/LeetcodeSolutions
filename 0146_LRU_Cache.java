class ListNode { 
    int val;
    ListNode prev, next;
    
    ListNode(int val) {
        this.val = val;
    }
}

class DoublyLinkedList {
    public ListNode head, tail;
    
    public void insertAtBeginning(ListNode n) {
        if(head == null) {
            head = tail = n;
            return;
        }
        n.next = head;
        head.prev = n;
        head = n;
        n.prev = null;
    }
    
    public void removeNode(ListNode n) {
        if(head == n && tail == n) {
            head = tail = null;
        } else if(head == n) {
            head = n.next;
            head.prev = null;
        } else if(tail == n) {
            tail = n.prev;
            tail.next = null;
        } else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
        }
    }
}

class LRUCache {
    int capacity, size;
    HashMap<Integer, Integer> valueTable = new HashMap<>();
    HashMap<Integer, ListNode> nodeTable = new HashMap<>();
    DoublyLinkedList nodeList = new DoublyLinkedList();
    
    private void moveToFrontOfNodeList(ListNode n) {
        nodeList.removeNode(n);
        nodeList.insertAtBeginning(n);
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        // if key isn't in cache, return -1
        if(valueTable.get(key) == null) return -1;
        moveToFrontOfNodeList(nodeTable.get(key)); // update priority
        return valueTable.get(key);
    }
    
    public void put(int key, int value) {
        if(valueTable.get(key) != null) { // key is already in cache
            moveToFrontOfNodeList(nodeTable.get(key)); // update priority
            valueTable.put(key, value); // update value
            return;
        }
        
        if(size == capacity) {
            // evict the node at the end of nodeList
            ListNode evictedNode = nodeList.tail;
            nodeTable.put(evictedNode.val, null);
            valueTable.put(evictedNode.val, null);
            nodeList.removeNode(evictedNode);
        } else size++;
        
        // assign new node, update priority
        nodeTable.put(key, new ListNode(key));
        nodeList.insertAtBeginning(nodeTable.get(key));
        // update value
        valueTable.put(key, value);
    }
}
