class Solution {
public:
    int n;
    vector<vector<int>> result;
    
    vector<vector<int>> allPathsSourceTarget(vector<vector<int>>& graph) {
        n = graph.size();
        vector<int> currentPath;
        dfs(graph, 0, currentPath);
        return result;
    }
    
    void dfs(vector<vector<int>>& graph, int current, vector<int>& currentPath) {
        currentPath.push_back(current);
        if(current == n - 1) {
            result.push_back(currentPath);
        } else {
            for(int& neigh : graph[current]) {
                dfs(graph, neigh, currentPath);
            }
        }
        currentPath.pop_back();
    }
};
