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
    int height(const TreeNode* root) {
        return root == nullptr ? 0 : 1 + height(root->left);
    }
    
    bool hasNthLeaf(const TreeNode* root, int height, int n) {
        if(height == 1) return root != nullptr;
        
        int numLeaves = 1 << (height - 1);
        if(n > numLeaves / 2) return hasNthLeaf(root->right, height - 1, n - (numLeaves / 2));
        else return hasNthLeaf(root->left, height - 1, n);
    }
    
    int countNodes(TreeNode* root) {
        int h = height(root);
        if(h <= 1) return h;
        
        // answer is somewhere between (2^height - 1) and (2^(height) - 1)
        // binary-search-the-answer.
        int left =  (1 << (h - 1)) - 1;
        int right = (1 << (h)    ) - 1;
        
        while(left < right) {
            int mid = (left + right + 1) / 2;
            int numLeaves = mid + 1 - (1 << (h - 1)); // number of "leaves" if mid is the number of nodes
            if(hasNthLeaf(root, h, numLeaves)) left = mid;
            else right = mid - 1;
        }
        
        return left;
    }
};
