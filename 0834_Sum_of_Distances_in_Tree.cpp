class Solution {
public:
    vector<int> sumOfDistancesInTree(int n, vector<vector<int>>& edges) {
        vector<int> result(n, 0);
        int subtreeSizes[30000] = {0};
        
        // build graph
        vector<vector<int>> cnx(n);
        for(vector<int>& edge : edges) {
            cnx[edge[0]].push_back(edge[1]);
            cnx[edge[1]].push_back(edge[0]);
        }
        
        int subtreeSizeSum = 0; // sum of all subtree sizes
        calculateSubtreeSizes(subtreeSizes, cnx, 0, subtreeSizeSum);
        int nodeZeroSum = subtreeSizeSum - subtreeSizes[0];
        
        result[0] = nodeZeroSum;
        calculateDistanceSums(subtreeSizes, cnx, result, 0);
        
        return result;
    }
    
    // dfs down the tree, storing the sizes of each subtree in sizes
    // also store the sum of all subtree sizes in sum
    int calculateSubtreeSizes(int sizes[], vector<vector<int>>& cnx, int current, int& sum) {
        if(sizes[current] != 0) return 0; // already calculated this subree's size
        
        sizes[current] = 1;
        for(int& neighbor : cnx[current]) {
            sizes[current] += calculateSubtreeSizes(sizes, cnx, neighbor, sum);
        }
        
        sum += sizes[current];
        return sizes[current];
    }
    
    // given a node (whose sum is evaluated), recursively calculate the rest of the nodes' sums
    void calculateDistanceSums(int sizes[], vector<vector<int>>& cnx, vector<int>& result,
                              int current) {
        for(int& neighbor : cnx[current]) {
            if(result[neighbor] != 0) continue; // result already evaluated
            result[neighbor] = result[current]   // distances are the same as neighbor, except...
                               - sizes[neighbor] // all of neighbor's children are 1 closer
                               + (sizes[0] - sizes[neighbor]); // all other nodes are 1 farther
            calculateDistanceSums(sizes, cnx, result, neighbor);
        }
    }
};
