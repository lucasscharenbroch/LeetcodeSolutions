class Solution {
public:
    int longestPath(vector<int>& parent, string s) {
        vector<vector<int>> children(parent.size());
        for(int i = 1; i < parent.size(); i++) children[parent[i]].push_back(i);
        
        int result = 0;
        
        dfs(0, children, s, result);
        
        return result;
    }
    
    // dfs: recursively finds the largest valid paths within the given subtree;
    // return the largest path that contains the root of the subtree.
    int dfs(int root, vector<vector<int>>& children, string& s, int& result) {
        int largest = 0, secondLargest = 0; // the two largest paths containing children
        
        for(int& child : children[root]) {
            int childPath = dfs(child, children, s, result); // largest path in child's
                                                             // subtree that contains child
            if(s[root] == s[child]) continue; // chars match: can't be in same path
            if(childPath > largest) {
                secondLargest = largest;
                largest = childPath;
            } else if(childPath > secondLargest) secondLargest = childPath;
        }
        
        result = max(result, 1 + largest + secondLargest);
        return 1 + max(largest, secondLargest);
    }
};
