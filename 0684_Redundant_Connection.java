// DFS O(n^2)
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        // populate graph
        ArrayList<Integer>[] graph = new ArrayList[edges.length + 1];
        for(int i = 1; i <= edges.length; i++) graph[i] = new ArrayList<>();
        for(int i = 0; i < edges.length; i++) {
            if(canReach(graph, edges[i][0], -1, edges[i][1])) return edges[i];
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }
        
        return null; // no redundant edge!
    }
    
    private boolean canReach(ArrayList<Integer>[] graph, int current, int prev, int destination) {
        for(int neigh : graph[current]) {
            if(neigh == prev) continue; // avoid cycles because of undirected graph
            if(neigh == destination || canReach(graph, neigh, current, destination)) return true;
        }
        return false;
    }
}

// Union-Find O(n)
class UnionFind {
    int[] parents;
    private int[] sizes;
        
    public UnionFind(int numElements) {
        parents = new int[numElements];
        sizes = new int[numElements];
        for(int i = 0; i < numElements; i++) {
            parents[i] = i;
            sizes[i] = 1;
        }
    }
    
    public int getRoot(int a) {
        // first find the "set root" (whose parent is itself)
        int r = a;
        while(parents[r] != r) {
            r = parents[r];
        }
        
        // then compress the path
        while(parents[a] != r) {
            int next = parents[a];
            parents[a] = r;
            a = next;
        }
        
        return r; // return the "set root"
    }
    
    // join the groups that contain a and b
    public void unite(int a, int b) {
        int aRoot = getRoot(a);
        int bRoot = getRoot(b);
        
        if(sizes[aRoot] > sizes[bRoot]) { // merge B's group into A's group
            sizes[b] += sizes[a];
            parents[bRoot] = aRoot;
        } else {                          // merge A's group into B's group 
            sizes[a] += sizes[b];
            parents[aRoot] = bRoot;
        }
    }
}

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind groups = new UnionFind(edges.length + 1);
        
        for(int[] edge : edges) {
            int a = edge[0], b = edge[1];
            if(groups.getRoot(a) == groups.getRoot(b)) return edge;
            groups.unite(a, b);
        }
        
        return null; // no redundant connection
    }
}
