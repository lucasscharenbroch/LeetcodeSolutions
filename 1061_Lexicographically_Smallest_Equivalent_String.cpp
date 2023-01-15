struct UnionFind {
    vector<int> parent;
    
    UnionFind(int size): parent(size) {
        for(int i = 0; i < size; i++) parent[i] = i;
    }
    
    int group(int i) {
        while(parent[i] != i) i = parent[i];
        return i;
    }
    
    void unite(int i, int j, int newParent) {
        while(i != parent[i]) {
            int oldParent = parent[i];
            parent[i] = newParent;
            i = oldParent;
        }
        parent[i] = newParent;
        
        while(j != parent[j]) {
            int oldParent = parent[j];
            parent[j] = newParent;
            j = oldParent;
        }
        parent[j] = newParent;
    }
};

class Solution {
public:
    string smallestEquivalentString(string s1, string s2, string baseStr) {
        UnionFind uf(26);
        for(int i = 0; i < s1.length(); i++) uf.unite(s1[i] - 'a', s2[i] - 'a',
                                                      min(uf.group(s1[i] - 'a'), uf.group(s2[i] - 'a')));
        
        string result;
        for(int i = 0; i < baseStr.length(); i++) {
            result += uf.group(baseStr[i] - 'a') + 'a';
        }
        
        return result;
    }
};
