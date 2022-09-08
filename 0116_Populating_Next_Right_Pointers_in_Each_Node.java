/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}
    
    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};
*/

class Solution {
    public Node connect(Node root) {
        if(root == null) return null; // zero-length edge-case
        
        ArrayList<Node> bfs = new ArrayList<>();
        bfs.add(root);
        bfs.add(null); // null signifies a new level
        
        for(int i = 0; i < bfs.size() - 1; i++) {
            if(bfs.get(i) == null) {
                bfs.add(null);
                continue;
            }
            
            bfs.get(i).next = bfs.get(i + 1);
            
            if(bfs.get(i).left != null) {
                bfs.add(bfs.get(i).left);
                bfs.add(bfs.get(i).right);
            }
        }
        
        return root;
    }
}
