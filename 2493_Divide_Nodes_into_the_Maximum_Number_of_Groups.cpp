class Solution {
    static constexpr int RED = 1;
    static constexpr int BLUE = 2;
public:
    bool isBipartite(const int& n, const vector<vector<int>>& cnx) {
        int colors[500] = {0};
        
        for(int i = 0; i < n; i++) if(colors[i] == 0 && !bipartiteDfs(i, cnx, RED, colors)) return false;
        
        return true;
    }
    
    // fills colors array accordingly; returns false if the graph is not bipartite
    bool bipartiteDfs(int n, const vector<vector<int>>& cnx, int color, int colors[]) {
        if(colors[n] != 0) return colors[n] == color; // visited check and verifies that color is valid
        colors[n] = color;
        
        for(const int& neigh : cnx[n]) {
            if(!bipartiteDfs(neigh, cnx, color == RED ? BLUE : RED, colors)) return false;
        }
        
        return true;
    }
    
    int magnificentSets(int n, vector<vector<int>>& edges) {
        
        // build adjacency list
        vector<vector<int>> cnx(n);
        for(const vector<int>& edge: edges) {
            cnx[edge[0] - 1].push_back(edge[1] - 1);
            cnx[edge[1] - 1].push_back(edge[0] - 1);
        }
            
        if(!isBipartite(n, cnx)) return -1;
        
        int dist[500] = {0}; // dist[i] = maximum distance between nodes in group i
        int groups[500]; // group[j] = the group which node j belongs to
        memset(groups, -1, sizeof(groups));
        int numGroups = 0;
        
        for(int startNode = 0; startNode < n; startNode++) {
            if(groups[startNode] == -1) groups[startNode] = numGroups++;
            
            dist[groups[startNode]] = max(dist[groups[startNode]], bfsLength(startNode, cnx, groups));
        }
        
        return accumulate(dist, dist + numGroups, 0);
    }
    
    // sets the distance and group ids between all nodes in the component
    int bfsLength(int n, const vector<vector<int>>& cnx, int groups[]) {
        bool vis[500] = {false};
        vis[n] = true;
        
        queue<int> q;
        q.push(n);
        
        int breadths = 0;
        
        while(!q.empty()) {
            breadths++;
            int s = q.size();
            
            while(s--) {
                int current = q.front();
                q.pop();
                groups[current] = groups[n]; // set all nodes in component to have same group
                
                for(const int& neigh : cnx[current]) {
                    if(vis[neigh]) continue;
                    vis[neigh] = true;
                    q.push(neigh);
                }
            }
        }
        
        return breadths;
    }
};
