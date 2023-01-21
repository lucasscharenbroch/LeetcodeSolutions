class Solution {
public:
    vector<string> result;
    
    vector<string> restoreIpAddresses(string s, int i = 0, int numInts = 4, string current = "") {
        if(numInts == 0 || i == s.length()) {
            if(i == s.length() && numInts == 0) result.push_back(current);
            return result;
        }
        
        int num = 0; // number represented by beginning chars of s [i..]
        for(; i < s.length(); i++) {
            num = (num * 10) + (s[i] - '0');
            if(num > 255) break;
            restoreIpAddresses(s, i + 1, numInts - 1, current + (numInts == 4 ? "" : ".") + to_string(num));
            if(s[i] == '0' && num == 0) break; // don't advance i because '0' will be ignored
        }
        
        return result;
    }
};
