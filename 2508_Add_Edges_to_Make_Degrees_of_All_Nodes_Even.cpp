class Solution {
public:
    bool isPossible(int n, vector<vector<int>>& edges) {
        int degrees[100000] = {0};
        unordered_map<int, unordered_set<int>> cnx;
        for(vector<int>& edge : edges) {
            cnx[edge[0] - 1].insert(edge[1] - 1);
            cnx[edge[1] - 1].insert(edge[0] - 1);
        }
        
        vector<int> oddDegNodes;
        vector<int> evenDegNodes;
        for(int i = 0; i < n; i++) {
            if(cnx[i].size() % 2 == 1) oddDegNodes.push_back(i);
            else evenDegNodes.push_back(i);
        }
        
        if(oddDegNodes.size() == 0) return true; // no nodes to fix
        else if(oddDegNodes.size() == 2) { // either match the two nodes, or find an even-degreed node
                                           // with no connection with either
            int node1 = oddDegNodes[0];
            int node2 = oddDegNodes[1];
            
            if(cnx[node1].find(node2) == cnx[node1].end()) return true; // no connection between the two
            
            // otherwise try to find an even-degreed node with a connection to neither
            for(int& node3 : evenDegNodes) {
                if(cnx[node3].find(node1) == cnx[node3].end() &&
                   cnx[node3].find(node2) == cnx[node3].end()) return true;
            }
        } else if(oddDegNodes.size() == 4) { // must find a non-existing conneciton between all four nodes
            int node1 = oddDegNodes[0];
            int node2 = oddDegNodes[1];
            int node3 = oddDegNodes[2];
            int node4 = oddDegNodes[3];
            
            // (1, 2), (3, 4)
            if(cnx[node1].find(node2) == cnx[node1].end() && 
               cnx[node3].find(node4) == cnx[node3].end()) return true;
            // (1, 3), (2, 4)
            if(cnx[node1].find(node3) == cnx[node1].end() && 
               cnx[node2].find(node4) == cnx[node2].end()) return true;
            // (1, 4), (2, 3)
            if(cnx[node1].find(node4) == cnx[node1].end() && 
               cnx[node2].find(node3) == cnx[node2].end()) return true;
        }
        
        return false; // can't handle the other situations (more than 4 nodes or an odd number of odd-degreed
                      // nodes)
    }
};
