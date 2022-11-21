// Prim's Algorithm
class Solution {
    class Edge {
        public int to, from, weight;
        
        public Edge(int to, int from, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }
    }
    
    private int dist(int[] p1, int[] p2) {
        int dx = Math.abs(p2[0] - p1[0]);
        int dy = Math.abs(p2[1] - p1[1]);
        return dx + dy;
    }
    
    public int minCostConnectPoints(int[][] points) {
        PriorityQueue<Edge> edgeQ = new PriorityQueue<>((a, b) -> a.weight - b.weight);
        edgeQ.add(new Edge(0, 0, 0)); // start by visiting node 0 (an edge from 0 to 0 with cost 0)
        
        HashSet<Integer> visited = new HashSet<>();
        int minDist = 0;
        
        while(visited.size() != points.length) {
            Edge currentEdge = edgeQ.poll();
            if(visited.contains(currentEdge.to)) continue;
            
            int currentNode = currentEdge.to;
            minDist += currentEdge.weight;
            visited.add(currentNode);
            // "explore" currentNode
            for(int p = 0; p < points.length; p++) {
                if(p != currentNode && !visited.contains(p)) {
                    edgeQ.add(new Edge(p, currentNode, dist(points[p], points[currentNode])));
                }
            }
        }
        
        return minDist;
    }
}

// Kruskal's Algorithm
class Solution {
    class Edge {
        public int to, from, weight;
        
        public Edge(int to, int from, int weight) {
            this.to = to;
            this.from = from;
            this.weight = weight;
        }
    }
    
    class UnionFind {
        private int[] parent;
        private int[] size;
        public int numComponents;
        
        public UnionFind(int numElements) {
            parent = new int[numElements];
            size = new int[numElements];
            numComponents = numElements;
            
            for(int i = 0; i < numElements; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public int find(int n) {
            // find root of n
            int r = n;
            while(parent[r] != r) r = parent[r];
            
            // compress paths
            while(n != r) {
                int next = parent[n];
                parent[n] = r;
                n = next;
            }
            
            return r;
        }
        
        public void union(int n1, int n2) {
            int r1 = find(n1);
            int r2 = find(n2);
            
            if(r1 == r2) return;
            numComponents--;
            
            if(size[r1] > size[r2]) {
                size[r1] += size[r2];
                parent[r2] = r1;
            } else {
                size[r2] += size[r1];
                parent[r1] = r2;
            }
        }
    }
    
    private int dist(int[] p1, int[] p2) {
        int dx = Math.abs(p2[0] - p1[0]);
        int dy = Math.abs(p2[1] - p1[1]);
        return dx + dy;
    }
    
    public int minCostConnectPoints(int[][] points) {
        ArrayList<Edge> edges = new ArrayList<>();
        UnionFind components = new UnionFind(points.length);
        
        int minDist = 0;
        
        // add each edge to edgeQ
        for(int p1 = 0; p1 < points.length; p1++) {
            for(int p2 = p1 + 1; p2 < points.length; p2++) {
                edges.add(new Edge(p1, p2, dist(points[p1], points[p2])));
            }
        }
        
        edges.sort((a, b) -> (a.weight - b.weight)); // sort edges by weight
        
        
        for(Edge e : edges) {
            if(components.numComponents == 1) break; // all points are already connected
            
            int r1 = components.find(e.to);
            int r2 = components.find(e.from);
            if(r1 == r2) continue; // edge is redundant
            
            // otherwise edge is optimal
            minDist += e.weight;
            components.union(e.to, e.from);
        }
        
        return minDist;
    }
}
