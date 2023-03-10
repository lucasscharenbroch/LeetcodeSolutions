/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Codec {
public:
    
    int read_int(string& s, int& i) {
        int result = 0;
        bool is_neg = false;
        
        if(s[i] == '-') i++, is_neg = true;
        
        for(; i < s.length() && isdigit(s[i]); i++) {
            result *= 10;
            result += s[i] - '0';
        }
        
        return (is_neg ? -1 : 1) * result;
    }

    // Encodes a tree to a single string.
    string serialize(TreeNode* root) {
        // do preorder traversal, use ';' to represent the
        // termination of a tree (including null-trees)
        
        if(root == nullptr) return ";";
        
        return to_string(root->val) + " " + serialize(root->left) + " " + 
                                            serialize(root->right) + ";";
    }
    
    int i = 0;

    // Decodes your encoded data to tree.
    TreeNode* deserialize(string data) {
        if(i == data.size()) return nullptr;
        while(data[i] == ' ') i++; // skip spaces
        if(data[i] == ';') return nullptr;
        
        TreeNode* node = new TreeNode(read_int(data, i));
        
        node->left = deserialize(data);
        i++; // skip over trailing semicolon of left subtree
        node->right = deserialize(data);
        i++; // skip over trailing semicolon of left subtree
            
        return node;
    }
};

// Your Codec object will be instantiated and called as such:
// Codec ser, deser;
// TreeNode* ans = deser.deserialize(ser.serialize(root));
