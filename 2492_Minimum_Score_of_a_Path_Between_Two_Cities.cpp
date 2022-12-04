class Solution {
public:
    int minScore(int n, vector<vector<int>>& roads) {
        // find the smallest road reachable by either city. (they will be the same, since there is
        // at least one path between them)
        
        // build graph
        unordered_map<int, vector<vector<int>>> cnx;
        for(auto& road : roads) {
            cnx[road[0]].push_back(vector<int> {road[1], road[2]});
            cnx[road[1]].push_back(vector<int> {road[0], road[2]});
        }
        
        // find shortest reachable edge from city 1
        vector<bool> vis(n, false);
        return shortestReachableEdge(cnx, 1, vis);
    }
    
    int shortestReachableEdge(unordered_map<int, vector<vector<int>>>& cnx, int c, vector<bool>& vis) {
        if(vis[c]) return INT_MAX;
        vis[c] = true;
        
        int minEdge = INT_MAX;
        for(auto& cn : cnx[c]) {
            minEdge = min(minEdge, cn[1]);
            minEdge = min(minEdge, shortestReachableEdge(cnx, cn[0], vis));
        }
        
        return minEdge;
    }
};
