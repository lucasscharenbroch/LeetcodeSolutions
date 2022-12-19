class Solution {
public:
    bool validPath(int n, vector<vector<int>>& edges, int source, int destination) {
        // build adjacency list
        unordered_map<int, vector<int>> cnx;
        for(vector<int>& edge : edges) {
            cnx[edge[0]].push_back(edge[1]);
            cnx[edge[1]].push_back(edge[0]);
        }
        
        unordered_set<int> seen;
        return dfs(cnx, seen, source, destination);
    }
    
    bool dfs(unordered_map<int, vector<int>>& cnx, unordered_set<int>& seen, int source, int destination) {
        if(source == destination) return true;
        if(seen.find(source) != seen.end()) return false;
        seen.insert(source);
        
        for(int& neighbor : cnx[source]) if(dfs(cnx, seen, neighbor, destination)) return true;
        
        return false;
    }
};
