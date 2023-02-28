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
    unordered_map<string, int> str_to_id;        // {node_string ("val,left_id,right_id") : id}
    unordered_map<string, TreeNode*> duplicates; // {id : TreeNode*}
    int new_id = 1; // next unused id
    
    vector<TreeNode*> findDuplicateSubtrees(TreeNode* root) {
        // assign each unique subtree an id based on its value, and the ids of its children.
        
        get_id(root); // fills str_to_id and duplicates
        
        // return a vector of all values in duplicates (one TreeNode* per duplicate subtree)
        vector<TreeNode*> result;
        for(auto it : duplicates) {
            result.push_back(it.second);
        }
        return result;
    }
    
    string get_id(TreeNode* node) {
        if(node == nullptr) return "";
        
        string left_id = get_id(node->left);
        string right_id = get_id(node->right);
        
        string node_string = to_string(node->val) + "," + left_id + "," + right_id;
        
        int id = str_to_id[node_string];
        if(id == 0) {
            id = str_to_id[node_string] = new_id++;
        } else { // duplicate found
            duplicates[to_string(id)] = node; // this will overwrite any old duplicates, which 
                                              // ensures only one TreeNode* per duplicate subtree
        }
        
        return to_string(id);
    }
};
