class Solution {
public:
    const vector<vector<int>> offsets = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    
    vector<int> maxPoints(vector<vector<int>>& grid, vector<int>& queries) {
        const int MAX_Q = 1000000;
        
        // find the answer to any possible query with BFS
        int answers[MAX_Q + 1];
        // prioritize smallest elements
        auto compare = [&grid](auto& a, auto& b) { return grid[a.first][a.second] > grid[b.first][b.second]; };
        priority_queue<pair<int, int>, vector<pair<int, int>>, decltype(compare)> q(compare);
        bool vis[1000][1000] = {false}; // denotes if cell [r][c] has entered the queue
        q.push(pair<int, int> (0, 0));
        vis[0][0] = true;
        int currentScore = 0;
        
        for(int a = 0; a < MAX_Q; a++) {
            while(!q.empty()) {
                pair<int, int> current = q.top();
                if(grid[current.first][current.second] >= a) break; // can't explore anymore with a
                q.pop(); // remove current from queue
                currentScore++;
                // explore current
                for(const vector<int>& offset : offsets) {
                    int r = current.first + offset[0];
                    int c = current.second + offset[1];
                    
                    if(r < 0 || r >= grid.size() || c < 0 || c >= grid[0].size()) continue; // invalid cell
                    if(!vis[r][c]) {
                        vis[r][c] = true;
                        q.push(pair<int, int> (r, c));
                    }
                }
            }
            
            answers[a] = currentScore;
        }
        
        // fill result
        vector<int> result;
        for(int& q : queries) {
            result.push_back(answers[q]);
        }
        
        return result;
    }
};
