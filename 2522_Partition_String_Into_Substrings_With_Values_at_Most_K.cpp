class Solution {
public:
    int minimumPartition(string s, int k) {
        // greedy: form the largest possible substrings while keeping their values under k
        
        int result = 0;
        
        long long current = 0; // value of the current substring
        
        for(int i = 0; i < s.length(); i++) {
            if(s[i] - '0' > k) return -1; // this digit is too large to put in any substring
            
            current *= 10;
            current += s[i] - '0';
            if(current > k) { // if the substring has become too large, end it here.
                result++;
                current = s[i] - '0';
            }
        }

        return result + 1;
    }
};
