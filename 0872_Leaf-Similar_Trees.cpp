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
    bool leafSimilar(TreeNode* root1, TreeNode* root2) {
        vector<int> leaves1, leaves2;
        getLeaves(root1, leaves1);
        getLeaves(root2, leaves2);
        return leaves1 == leaves2;
    }
    
    void getLeaves(TreeNode* curr, vector<int>& leafList) {
        if(curr == nullptr) return;
        
        if(curr->left == nullptr && curr->right == nullptr) { // found leaf
            leafList.push_back(curr->val);
        } else {
            getLeaves(curr->left, leafList);
            getLeaves(curr->right, leafList);
        }
    }
};
