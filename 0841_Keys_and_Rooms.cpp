class Solution {
public:
    bool canVisitAllRooms(vector<vector<int>>& rooms) {
        // BFS
        queue<int> q;
        unordered_set<int> vis;
        q.push(0);
        vis.insert(0);
        
        int numVisited = 1;
        
        while(!q.empty()) {
            int current = q.front();
            q.pop();
            
            for(int& neigh : rooms[current]) {
                if(vis.find(neigh) != vis.end()) continue; // already in queue/explored
                q.push(neigh);
                vis.insert(neigh);
                if(++numVisited == rooms.size()) return true;
            }
        }
        
        return false;
    }
};
