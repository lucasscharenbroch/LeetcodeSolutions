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
    vector<string> result;
    
    vector<string> binaryTreePaths(TreeNode* root) {
        dfs(root);
       return result; 
    }
    
    void dfs(TreeNode* curr, string s = "") {
        if(curr->left == nullptr && curr->right == nullptr) 
            result.push_back(s + to_string(curr->val));
        
        if(curr->left != nullptr) dfs(curr->left, s + to_string(curr->val) + "->");
        if(curr->right != nullptr) dfs(curr->right, s + to_string(curr->val) + "->");
    }
};
