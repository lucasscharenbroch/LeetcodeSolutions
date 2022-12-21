class Solution {
public:
    static constexpr int MOD_CONST = 1000000007;
    
    int beautifulPartitions(string s, int k, int minLength) {
        bool isPrime[1000];
        for(int i = 0; i < s.length(); i++) isPrime[i] = (s[i] == '2' || s[i] == '3' || 
                                                          s[i] == '5' || s[i] == '7');
        
        if(!isPrime[0]) return 0; // edgecase: first digit is not prime
        
        int dp[1000][1001]; // dp[i][j] = number of ways to divide s[0..=i] into j beautiful parts
        for(int i = 0; i < s.length(); i++) { // populate dp[i][1] (is s[0..=i] valid ? 1 : 0)
            dp[i][1] = !isPrime[i] && i + 1 >= minLength;
        }
        
        for(int i = 0; i < s.length(); i++) {
            for(int j = 2; j <= k; j++) {
                dp[i][j] = 0;
                if(isPrime[i]) continue; // can't end a part with a prime digit
                
                // for each possible part that ends at i, add the number of ways
                // [0..=i] can form j beautiful parts with with part[k..=i] begin the Jth
                for(int k = minLength * (j - 1); k <= i - minLength + 1; k++) {
                    if(!isPrime[k]) continue; // can't start a part with non-prime k
                    dp[i][j] += dp[k - 1][j - 1];
                    dp[i][j] %= MOD_CONST;
                }
            }
        }
            
        return dp[s.length() - 1][k];
    }
};
