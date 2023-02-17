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
    vector<int> values;
        
    void inorderTraversal(TreeNode* root) {
        if(root == nullptr) return;
        
        inorderTraversal(root->left);
        values.push_back(root->val);
        inorderTraversal(root->right);
    }
    
    int minDiffInBST(TreeNode* root) {
        inorderTraversal(root);
        int result = INT_MAX;
        
        for(int i = 1; i < values.size(); i++) {
            result = min(result, values[i] - values[i - 1]);
        }
        
        return result;
    }
};
