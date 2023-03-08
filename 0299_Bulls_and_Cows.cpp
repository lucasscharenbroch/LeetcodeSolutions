class Solution {
public:
    string getHint(string secret, string guess) {
        int n = secret.size();
        int bulls = 0, cows = 0;
        
        vector<int> counts(10);
        vector<bool> is_bull(n);
        
        // mark bulls, and add other digits to counts
        for(int i = 0; i < n; i++) {
            if(secret[i] == guess[i]) {
                bulls++;
                is_bull[i] = true;
            } else counts[secret[i] - '0']++;
        }
        
        // count cows
        for(int i = 0; i < n; i++) {
            if(is_bull[i]) continue;
            else if(counts[guess[i] - '0']-- > 0) cows++;
        }
        
        return to_string(bulls) + "A" + to_string(cows) + "B";
    }
};
