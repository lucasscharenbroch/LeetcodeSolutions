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
    vector<int> level_sums;
    
    void preorder(TreeNode* node, int level = 0) {
        if(node == nullptr) return;
        
        if(level_sums.size() == level) level_sums.push_back(0);
        level_sums[level] += node->val;
        preorder(node->left, level + 1);
        preorder(node->right, level + 1);
    }
    
    int maxLevelSum(TreeNode* root) {
        preorder(root);
        return 1 + (max_element(level_sums.begin(), level_sums.end()) - level_sums.begin());
    }
};
