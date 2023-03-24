class Solution {
public:
    int minReorder(int n, vector<vector<int>>& edges) {
        // bfs from node 0 through the tree, as if it were an undirected
        // graph; the result is the count of edges traversed through
        // in the correct (directed) direction
        
        unordered_map<int, vector<pair<int, bool>>> cnx; // {node : {neigh, is_forward_edge}}
        
        for(auto& e : edges) {
            cnx[e[0]].push_back(pair<int, bool>(e[1], true));
            cnx[e[1]].push_back(pair<int, bool>(e[0], false));
        }
        
        // bfs from 0
        queue<int> q;
        vector<bool> vis(n);
        q.push(0); vis[0] = true;
        int res = 0;
        
        while(!q.empty()) {
            int curr = q.front(); q.pop();

            for(auto& [neigh, is_forward] : cnx[curr]) {
                if(vis[neigh]) continue;
                vis[neigh] = true;
                q.push(neigh);
                if(is_forward) res++;
            }
        }
        
        return res;
    }
};
