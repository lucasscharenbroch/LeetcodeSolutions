/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<vector<int>> levels;
    
    void inorderTraverse(TreeNode* node, int breadth = 0) {
        if(node == nullptr) return;
        while(levels.size() <= breadth) levels.push_back({}); // fill levels with blank arrays, as needed
        
        inorderTraverse(node->left, breadth + 1);
        levels[breadth].push_back(node->val);
        inorderTraverse(node->right, breadth + 1);
    }
    
    vector<vector<int>> zigzagLevelOrder(TreeNode* root) {
        inorderTraverse(root); // fill %levels% with nodes, left-to-right
        
        for(int i = 1; i < levels.size(); i += 2) { // reverse every odd row
            reverse(levels[i].begin(), levels[i].end());
        }
        
        return levels;
    }
};
