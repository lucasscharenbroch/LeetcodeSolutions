class Solution {
public:
    int findJudge(int n, vector<vector<int>>& trust) {
        vector<int> indegrees(n, 0);  // # of others who trust this person
        vector<int> outdegrees(n, 0); // # of others this person trusts
        for(vector<int>& t : trust) indegrees[t[1] - 1]++, outdegrees[t[0] - 1]++;
        
        int numJudges = 0;
        int lastSeenJudge = -1;
        
        // a person is a judge if indegrees(person) == n - 1 && outdegrees(person) == 0
        for(int i = 0; i < n; i++) {
            if(indegrees[i] == n - 1 && outdegrees[i] == 0) {
                numJudges++;
                lastSeenJudge = i;
            }
        }
        
        return numJudges == 1 ? lastSeenJudge + 1: -1;
    }
};
