class Solution {
public:
    vector<int> cycleLengthQueries(int n, vector<vector<int>>& queries) {
        vector<int> result;
        for(vector<int>& query : queries) {
            int node1 = query[0];
            int node2 = query[1];
            
            // find lowest common ancestor, count the number of levels traversed
            int cycleLength = 1; // 1 => edge between the two nodes
            
            while(node1 != node2) {
                int& bigNode = node1 > node2 ? node1 : node2;
                bigNode >>= 1;
                cycleLength++;
            }
            
            result.push_back(cycleLength);
        }
        
        return result;
    }
};
