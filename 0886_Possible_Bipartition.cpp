class Solution {
public:
    static constexpr int NOT_SEATED = 0, GROUP_1 = 1, GROUP_2 = 2;
    
    bool possibleBipartition(int n, vector<vector<int>>& dislikes) {
        vector<int> status(n, NOT_SEATED);
        
        // build graph
        vector<vector<int>> cnx(n);
        for(vector<int>& cn : dislikes) {
            cnx[cn[0] - 1].push_back(cn[1] - 1);
            cnx[cn[1] - 1].push_back(cn[0] - 1);
        }
        
        // Choose an arbitrary node, and use dfs to seat all of the nodes connected to it
        // (the nodes that rely on its group); repeat this process until all nodes are grouped.
        for(int i = 0; i < n; i++) {
            if(status[i] != NOT_SEATED) continue; // i is already seated
            if(!seat(i, GROUP_1, status, cnx)) return false;
        }
        
        return true;
    }
    
    // assigns a group to the given %node% and recursively seats all of its neighbors, and 
    // their neighbors (all nodes that rely on the position of %node%, directly or indirectly)
    // returns false if this can't be done without violating the bipartite invariant.
    bool seat(int node, int group, vector<int>&status, vector<vector<int>>& cnx) {
        status[node] = group;
        int neighborsGroup = (group == GROUP_1) ? GROUP_2 : GROUP_1;
        for(int& neigh : cnx[node]) {
            if(status[neigh] == group) return false; // neighbor is in same group: not bipartite
            else if(status[neigh] == NOT_SEATED) seat(neigh, neighborsGroup, status, cnx);
        }
        return true;
    }
};
