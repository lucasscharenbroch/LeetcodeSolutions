class Solution {
public:
    int minimumOneBitOperations(int n) {
        // dp[i] = cost to flip bit i when bits (i-1)..0 are 0
        // dp[0] = 1
        // dp[i] = (1 + i) + dp[0] + ... + dp[i - 2] + dp[i - 1] 
        //       = 1 + (i + dp[0] + ... + dp[i - 2]) + dp[i - 1]
        //       = 1 + 2 * (dp[i - 1])
        
        vector<int> dp(32);
        for(int i = 0; i < 31; i++) // i = 31 causes overflow
            dp[i] = i == 0 ? 1 : 1 + 2 * dp[i - 1];
        
        vector<int> bits;
        for(int i = 31; n != 0; i--) {
            if(n & INT_MIN) bits.push_back(i);
            n <<= 1;
        }
        
        int result = 0;
        
        for(int i = 0; i < bits.size(); i++) {
            if(bits.size() - i == 1) { // add cost of clearing n[bits[i]]
                result += dp[bits[i]];
            } else { // add cost of clearing both n[bits[i]] and n[bits[i + 1]]
                     // (assume all other bits are clear)
                for(int j = bits[i] - 1; j >= bits[i + 1]; j--)
                    result += dp[j] + 1;
                i++; // skip next iteration
            }
        }
        
        return result;
    }
};
