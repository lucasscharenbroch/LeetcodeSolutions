/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

// BFS
class Solution {
    public Node cloneGraph(Node node) {
        if(node == null) return null;
            
        HashMap<Integer, Node> nodes = new HashMap<>();
        HashSet<Integer> visited = new HashSet<>();
        LinkedList<Node> queue = new LinkedList<>();
        
        visited.add(node.val);
        queue.add(node);
        nodes.put(node.val, new Node(node.val));
        
        while(!queue.isEmpty()) {
            Node currentOriginal = queue.poll();
            int currentId = currentOriginal.val;
            
            for(Node neighborOriginal : currentOriginal.neighbors) {
                int neighborId = neighborOriginal.val;
                // attach neighbor's copy to current's copy
                if(nodes.get(neighborId) == null) nodes.put(neighborId, new Node(neighborId));
                nodes.get(currentId).neighbors.add(nodes.get(neighborId));
                
                if(!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    queue.add(neighborOriginal);
                }
            }
        }
        
        return nodes.get(node.val);
    }
}

// DFS
class Solution {
    Node[] copies = new Node[101];
    
    public Node cloneGraph(Node node) {
        if(node == null) return null;
        
        Node copy = new Node(node.val);
        copies[copy.val] = copy;
        for(Node neighbor : node.neighbors) {
            if(copies[neighbor.val] == null) {
                cloneGraph(neighbor);
            }
            copy.neighbors.add(copies[neighbor.val]);
        }
        
        return copy;
    }
}
