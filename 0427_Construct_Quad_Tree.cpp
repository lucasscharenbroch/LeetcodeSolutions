/*
// Definition for a QuadTree node.
class Node {
public:
    bool val;
    bool isLeaf;
    Node* topLeft;
    Node* topRight;
    Node* bottomLeft;
    Node* bottomRight;
    
    Node() {
        val = false;
        isLeaf = false;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }
    
    Node(bool _val, bool _isLeaf) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }
    
    Node(bool _val, bool _isLeaf, Node* _topLeft, Node* _topRight, Node* _bottomLeft, Node* _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/

class Solution {
public:
    Node* construct(vector<vector<int>>& grid) {
        return construct(grid, 0, grid.size() - 1, 0, grid.size() - 1);
    }
    
    Node* construct(vector<vector<int>>& grid, int startR, int endR, int startC, int endC) {
        int val = grid[startR][startC];
        bool vals_are_same = true;
        
        for(int i = startR; i <= endR; i++) {
            for(int j = startC; j <= endC; j++) {
                if(grid[i][j] != val) {
                    vals_are_same = false;
                    break;
                }
            }
        }
        
        if(vals_are_same) return new Node(val, true); // leaf node (all vals are the same)
        
        return new Node(1, false, 
                        construct(grid, startR, (startR + endR) / 2, startC, (startC + endC) / 2),
                        construct(grid, startR, (startR + endR) / 2, (startC + endC + 1) / 2, endC),
                        construct(grid, (startR + endR + 1) / 2, endR, startC, (startC + endC) / 2),
                        construct(grid, (startR + endR + 1) / 2, endR, (startC + endC + 1) / 2, endC)
                       );
    }
};
