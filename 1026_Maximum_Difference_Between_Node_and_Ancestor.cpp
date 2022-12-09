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
    int maxAncestorDiff(TreeNode* node, int maxA = INT_MIN, int minA = INT_MAX) {
        if(node == nullptr) return maxA - minA;
        
        maxA = max(maxA, node->val);
        minA = min(minA, node->val);
        
        return max(maxAncestorDiff(node->left, maxA, minA),
                   maxAncestorDiff(node->right, maxA, minA));
    }
};
