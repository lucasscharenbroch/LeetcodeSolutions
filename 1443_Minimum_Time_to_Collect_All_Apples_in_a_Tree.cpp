class Solution {
public:
    int minTime(int n, vector<vector<int>>& edges, vector<bool>& hasApple) {
        vector<vector<int>> cnx(n);
        int time = 0;
        
        // build graph
        for(vector<int>& edge : edges) {
            cnx[edge[0]].push_back(edge[1]);
            cnx[edge[1]].push_back(edge[0]);
        }
        
        dfs(0, -1, cnx, hasApple, time);
        
        return time;
    }
    
    // dfs: returns true if the subtree (of current) contains an apple;
    // for each of current's children, if their subtree contains apples, 2 is added to time.
    bool dfs(int current, int parent, vector<vector<int>>& cnx, vector<bool>& hasApple, int& time) {
        bool subtreeHasApple = hasApple[current];
        
        for(int& child : cnx[current]) {
            if(child == parent) continue;
            if(dfs(child, current, cnx, hasApple, time)) {
                subtreeHasApple = true;
                time += 2;
            }
        }
        
        return subtreeHasApple;
    }
};
