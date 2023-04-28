class Solution {
public:
    int num_groups;
    
    int find(vector<int>& group, int x) {
        return group[x] == x ? x : group[x] = find(group, group[x]);
    }
    
    void unite(vector<int>& group, int a, int b) {
        a = find(group, a), b = find(group, b);
        if(a != b) num_groups--;
        group[a] = b;
    }
    
    bool is_similar(string& a, string& b) {
        int diff = 0;
        for(int i = 0; i < a.size(); i++) diff += a[i] != b[i];
        return diff == 0 || diff == 2;
    }
    
    int numSimilarGroups(vector<string>& strs) {
        int n = strs.size();
        num_groups = n;
        vector<int> group(n);
        for(int i = 0; i < n; i++) group[i] = i;
        
        // brute-force, unite the groups of two words when they are similar
        for(int i = 0; i < strs.size(); i++) {
            for(int j = i + 1; j < strs.size(); j++) {
                if(is_similar(strs[i], strs[j])) unite(group, i, j);
            }
        }
        
        return num_groups;
    }
};
