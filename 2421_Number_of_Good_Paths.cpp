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
    int numberOfGoodPaths(vector<int>& vals, vector<vector<int>>& edges) {
        int n = vals.size();
        int result = n;
        UnionFind uf(n);
        
        // set all edges to be (largerValNode -> smallerValNode)
        for(vector<int>& edge : edges) {
            if(vals[edge[0]] < vals[edge[1]]) {
                int tmp = edge[0];
                edge[0] = edge[1];
                edge[1] = tmp;
            }
        }
        
        // sort edges by their largest nodeValue
        sort(edges.begin(), edges.end(), [&](auto& a, auto& b){ return vals[a[0]] < vals[b[0]]; });
        
        // We will iterate through edges (in sorted order), uniting groups of connected edges as
        // they are seen. Any time we encounter a node, its connections to nodes of lesser value
        // will already be explored. We can thus count the new "good" paths its connections adds.
        // In order to do this, we can keep a count of the number of nodes with the given value
        // in each group. Instead of counting instances of every number, we can simply count the
        // instances of the value currently in question. Thus counts[i] = {value, count}.
        
        vector<pair<int, int>> counts;
        for(int& val : vals) counts.push_back({val, 1});
        
        for(vector<int>& edge : edges) {
            int v = vals[edge[0]]; // target val
            int g1 = uf.group(edge[0]);
            int g2 = uf.group(edge[1]);
            
            int c1 = counts[g1].second;
            int c2 = (counts[g2].first == v) * counts[g2].second;
            result += c1 * c2;
            uf.unite(edge[0], edge[1], g1);
            counts[g1] = {v, c1 + c2};
        }
        
        return result;
    }
};
