class Solution {
public:
    vector<int> countSubTrees(int n, vector<vector<int>>& edges, string labels) {
        vector<vector<int>> cnx(n);
        for(vector<int>& edge : edges) {
            cnx[edge[0]].push_back(edge[1]);
            cnx[edge[1]].push_back(edge[0]);
        }
        
        vector<int> charCounts(26, 0);
        vector<int> result(n);
        
        dfs(0, -1, labels, cnx, charCounts, result);
        
        return result;
    }
    
    void dfs(int current, int parent, string& labels, vector<vector<int>>& cnx, 
                                      vector<int>& charCounts, vector<int>& result) {
        
        int initialCount = charCounts[labels[current] - 'a']++; // count of label outside of subtree
        
        for(int& neigh : cnx[current]) {
            if(neigh == parent) continue;
            dfs(neigh, current, labels, cnx, charCounts, result);
        }
        
        result[current] = charCounts[labels[current] - 'a'] - initialCount;
    }
};
