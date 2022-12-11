class Solution {
public:
    bool isNum(string& s) {
        for(int i = 0; i < s.length(); i++) if(s[i] < '0' || s[i] > '9') return false;
        return true;
    }
    
    int maximumValue(vector<string>& strs) {
        int result = INT_MIN;
        
        for(string s : strs) {
            if(isNum(s)) result = max(result, stoi(s));
            else result = max(result, (int) s.length());
        }
        
        return result;
    }
};
