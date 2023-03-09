class Solution {
public:
    vector<pair<int, int>> offsets = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, 
                                      {1, -1}, {1, 0}, {1, 1}};
    
    void gameOfLife(vector<vector<int>>& board) {
        // even number => dead in last iteration
        // odd number => alive in last iteration
        // 2 => dead in last iteration, alive in this iteration
        // 3 => alive in last iteration, dead in this iteration
        
        // use 2 and 3 as placeholders for changed values.
        // we can iterate again to change them to 1/0
        
        for(int i = 0; i < board.size(); i++) {
            for(int j = 0; j < board[i].size(); j++) {
                int neighbors_alive = 0;
                
                for(auto& offset : offsets) {
                    int i1 = i + offset.first, j1 = j + offset.second;
                    
                    if(i1 < 0 || i1 >= board.size() || j1 < 0 || j1 >= board[i].size()) continue;
                    if(board[i1][j1] % 2) neighbors_alive++;
                }
                
                if(board[i][j] == 0 && neighbors_alive == 3) board[i][j] = 2;
                else if(board[i][j] == 1 && (neighbors_alive < 2 ||
                                             neighbors_alive > 3)) board[i][j] = 3;
            }
        }
        
        // change 2 and 3 to 1 and 0
        for(int i = 0; i < board.size(); i++)
            for(int j = 0; j < board[i].size(); j++)
               if(board[i][j] > 1) board[i][j] = (board[i][j] - 1) % 2;
    }
};
