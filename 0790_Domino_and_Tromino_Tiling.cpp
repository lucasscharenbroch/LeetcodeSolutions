class Solution {
public:
    static constexpr int MOD_CONST = 1000000007;
        
    int numTilings(int n) {
        vector<int> flat(n + 1, 0);   // flat[i] = number of ways to get to row i with a flat edge
        vector<int> topGap(n + 1, 0); // number of ways to get to row i with a top-Gap edge (in row i - 1)
        vector<int> botGap(n + 1, 0); // number of ways to get to row i with a bot-gap edge (in row i - 1)
        
        flat[0] = 1; // one way to make a 0-row flat edge (nothing)
        flat[1] = 1; // one way to make a 1-row flat edge (single vertical domino)
        
        for(int i = 2; i <= n; i++) {
            flat[i] = (
                      (long long) flat[i - 1] +    // single vertical domino
                      (long long) flat[i - 2] +    // two horizontal dominos
                      (long long) topGap[i - 1] +  // single tromino
                      (long long) botGap[i - 1]    // single tromino
                      ) % MOD_CONST;
            
            topGap[i] = (
                        (long long) flat[i - 2] +  // single tromino
                        (long long) botGap[i - 1]  // horizontal domino
                        ) % MOD_CONST;
            
            botGap[i] = (
                        (long long) flat[i - 2] +  // single tromino
                        (long long) topGap[i - 1]  // horizontal domino
                        ) % MOD_CONST;
        }
        
        return flat[n];
    }
};
