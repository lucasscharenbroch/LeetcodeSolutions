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

// Tree-Search (TLE)
class Solution {
public:
    int getMin(TreeNode* root, int q) { // largest value in tree that is <= q
        if(root == nullptr) return INT_MIN;
        
        if(root->val == q) return q;
        if(root->val > q) return getMin(root->left, q);
        else return max(root->val, getMin(root->right, q)); // root->val < q
    }
    
    int getMax(TreeNode* root, int q) { // smallest value in tree that is >= q
        if(root == nullptr) return INT_MAX;
        
        if(root->val == q) return q;
        if(root->val < q) return getMax(root->right, q);
        else return min(root->val, getMax(root->left, q)); // root-> val > q
    }
    
    vector<vector<int>> closestNodes(TreeNode* root, vector<int>& queries) {
        vector<vector<int>> result;
        
        for(int& q : queries) {
            int min = getMin(root, q);
            int max = getMax(root, q);
            if(min == INT_MIN) min = -1;
            if(max == INT_MAX) max = -1;
            
            result.push_back(vector<int> {min, max});
        }
        
        return result;
    }
};

// Inorder-Traversal
class Solution {
public:
    void bstToArray(vector<int>& arr, TreeNode* root) {
        if(root == nullptr) return;
        bstToArray(arr, root->left);
        arr.push_back(root->val);
        bstToArray(arr, root->right);
    }
    
    const int Q_MAX = 1000000;
    
    vector<vector<int>> closestNodes(TreeNode* root, vector<int>& queries) {
        // convert root to a sorted array, then cache the answer to every possible query.
        vector<int> arr;
        bstToArray(arr, root);
        int i = 0; // pointer to position in arr
        
        int mins[Q_MAX + 1];
        int maxes[Q_MAX + 1];
        
        for(int q = 0; q <= Q_MAX; q++) {
            while(i < arr.size() && arr[i] < q) i++;
            if(i < arr.size() && arr[i] == q) {
                maxes[q] = mins[q] = q;
            } else {
                maxes[q] = (i == arr.size()) ? -1 : arr[i];
                mins[q] = (i == 0) ? -1 : arr[i - 1];
            }
        }
        
        vector<vector<int>> result;
        for(int& q : queries) {
            result.push_back(vector<int> {mins[q], maxes[q]});
        }
        
        return result;
    }
};
