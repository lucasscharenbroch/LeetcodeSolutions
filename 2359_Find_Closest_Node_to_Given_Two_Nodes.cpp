class Solution {
public:
    int closestMeetingNode(vector<int>& edges, int node1, int node2) {
        const int UNVISITED = 0;
        const int VIS1 = 1;
        const int VIS2 = 2;
        unordered_map<int, int> vis;
        
        int curr1 = node1, curr2 = node2;
        
        // dfs from both nodes, and find the first intersection
        int result = INT_MAX;
        while((curr1 != -1 && vis[curr1] != VIS1) || (curr2 != -1 && vis[curr2] != VIS2)) {
            if(curr1 == -1 || vis[curr1] == VIS1);
            else if(vis[curr1] == VIS2) result = min(result, curr1);
            else vis[curr1] = VIS1;
            
            if(curr2 == -1 || vis[curr2] == VIS2);
            else if(vis[curr2] == VIS1) result = min(result, curr2);
            else vis[curr2] = VIS2;
            
            if(result != INT_MAX) return result;
            
            curr1 = curr1 == -1 ? -1 : edges[curr1];
            curr2 = curr2 == -1 ? -1 : edges[curr2];
        }
        
        return -1;
    }
};
