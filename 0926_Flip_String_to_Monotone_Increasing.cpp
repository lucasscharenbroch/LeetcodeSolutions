class Solution {
public:
    int minFlipsMonoIncr(string s) {
        // we must decide some index i where the 0s end and the 1s begin.
        // we must make s[0..i] == '0' && s[i..] == '1'
        // thus cost(i) = count(s[0:i], '1') + count(s[i:], '0')
        // find the cost for each i, and take the minimum.
        
        int result = INT_MAX;
        int numOnesLeft = 0, numZeroesRight = count(s.begin(), s.end(), '0');
        
        for(int i = 0; i < s.length(); i++) {
            result = min(result, numOnesLeft + numZeroesRight);
            numOnesLeft += s[i] == '1';
            numZeroesRight -= s[i] == '0';
        }
        result = min(result, numOnesLeft + numZeroesRight);
        
        return result;
    }
};
