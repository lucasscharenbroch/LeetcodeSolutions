class Solution {
public:
    int maxDistance(vector<vector<int>>& grid) {
        int result = 0;
        
        queue<pair<int, int>> q;
        bool vis[100][100] = {false};
        
        
        for(int i = 0; i < grid.size(); i++) {
            for(int j = 0; j < grid[i].size(); j++) {
                if(grid[i][j] == 1) {
                    q.push({i, j});
                    vis[i][j] = true;
                }
            }
        }
        
        if(q.size() == 0 || q.size() == grid.size() * grid.size()) return -1;
        
        // BFS
        int breadth = 0;
        while(!q.empty()) {
            int s = q.size();
            breadth++;
            
            while(s--) {
                pair<int, int> coord = q.front(); q.pop();
                for(auto& offset : vector<pair<int, int>> {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}) {
                    int r = coord.first + offset.first, c = coord.second + offset.second;
                    if(r < 0 || r >= grid.size() || c < 0 || c >= grid.size()) continue;

                    if(!vis[r][c]) {
                        vis[r][c] = true;
                        q.push({r, c});
                        result = max(result, breadth);
                    }
                }
            }
        }
        
        return result;
    }
};
