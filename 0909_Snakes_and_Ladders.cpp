class Solution {
public:
    int row(int cellId, int n) {
        cellId--;
        return n - (cellId / n) - 1;
    }
    
    int col(int cellId, int n) {
        cellId--;
        bool rowIsBackwards = (cellId / n) % 2 == 1;
        return (!rowIsBackwards) ? cellId % n : n - (cellId % n) - 1;
    }
    
    int snakesAndLadders(vector<vector<int>>& board) {
        // treat the board as a graph: find the shortest path from cell (1) to cell (n * n). Use BFS.
        
        int n = board.size();
        
        unordered_map<int, bool> vis;
        queue<int> q;
        q.push(1);
        vis[1] = true;
        
        int breadth = 0;
        
        while(!q.empty()) {
            int s = q.size();
            breadth++;
            
            while(s--) {
                int current = q.front(); q.pop();
                
                // add all dice moves
                for(int i = current + 1; i <= n * n && i <= current + 6; i++) {
                    int dest = board[row(i, n)][col(i, n)];
                    if(dest == -1) dest = i;
                    
                    if(!vis[dest]) {
                        vis[dest] = true;
                        q.push(dest);
                    }
                }
            }
            
            if(vis[n * n]) return breadth;
        }
        
        return -1;
    }
};
