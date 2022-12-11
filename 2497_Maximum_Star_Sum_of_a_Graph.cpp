class Solution {
public:
    int maxStarSum(vector<int>& vals, vector<vector<int>>& edges, int k) {
        // build graph
        unordered_map<int, vector<int>> cnx;
        for(auto& edge : edges) {
            cnx[edge[0]].push_back(edge[1]);
            cnx[edge[1]].push_back(edge[0]);
        }
        
        int result = INT_MIN;
        
        // for each center node,
        for(int n = 0; n < vals.size(); n++) {
            // sort neighbors by value, decreasing (highest values first)
            sort(cnx[n].begin(), cnx[n].end(), [&vals](int& a, int& b) { return vals[a] > vals[b]; });
            
            // sum the k largest nonnegative neighbors
            int kSum = vals[n];
            for(int i = 0; i < k && i < cnx[n].size(); i++) kSum += max(0, vals[cnx[n][i]]);
            
            result = max(result, kSum); // update result
        }
        
        return result;
    }
};
