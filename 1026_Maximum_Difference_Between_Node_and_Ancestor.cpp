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
    int maxAncestorDiff(TreeNode* root) {
        if(root == nullptr) return 0;
        return maxAncestorDiff(root, root->val, root->val);
    }
    
    int maxAncestorDiff(TreeNode* node, int maxA, int minA) {
        if(node == nullptr) return 0;
        
        int currentDiff = max(abs(node->val - maxA), abs(node->val - minA));
        
        maxA = max(maxA, node->val);
        minA = min(minA, node->val);
        int maxDiffInRest =  max(maxAncestorDiff(node->left, maxA, minA),
                                 maxAncestorDiff(node->right, maxA, minA));
        
        return max(currentDiff, maxDiffInRest);
    }
};
