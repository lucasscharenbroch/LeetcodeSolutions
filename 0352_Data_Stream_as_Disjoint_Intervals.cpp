class SummaryRanges {
public:
    unordered_map<int, bool> exists; // exists[i] is true if i is in a disjoint interval
    unordered_map<int, int> parent; // parent[i] is the parent group of i
    map<int, vector<int>> intervals; // map of {group-id, interval}
    
    // finds the parent of node n
    int findParent(int n) {
        if(parent[n] == n) return n;
        else return findParent(parent[n]);
    }
    
    // unites the groups of two nodes
    void unite(int n1, int n2) {
        int p1 = findParent(n1), p2 = findParent(n2);
        
        // unite intervals
        vector<int> i1 = intervals[p1], i2 = intervals[p2];
        intervals[p1] = {min(i1[0], i2[0]), max(i1[1], i2[1])};
        intervals.erase(p2);
        parent[p2] = p1;
            
        int temp;
        
        for(int n : {n1, n2}) {
            while(parent[n] != n) {
                temp = parent[n];
                parent[n] = p1;
                n = temp;
            }
        }
    }
    
    void addNum(int value) {
        if(exists[value]) return;
        exists[value] = true;
        parent[value] = value;
        intervals[value] = {value, value};
        if(exists[value + 1]) unite(value, value + 1);
        if(exists[value - 1]) unite(value, value - 1);
    }
    
    vector<vector<int>> getIntervals() {
        vector<vector<int>> result;
        for(auto& pair : intervals) {
            result.push_back(pair.second);
        }
        return result;
    }
};

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges* obj = new SummaryRanges();
 * obj->addNum(value);
 * vector<vector<int>> param_2 = obj->getIntervals();
 */
