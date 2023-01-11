class Solution {
public:
    bool isIsomorphic(string s, string t) {
        if(s.length() != t.length()) return false;
        
        char charMapS[128] = {0};
        char charMapT[128] = {0};
        
        for(int i = 0; i < s.length(); i++) {
            if((charMapS[s[i]] == 0) != (charMapT[t[i]] == 0)) return false;
            
            if(charMapS[s[i]] == 0) {
                charMapS[s[i]] = t[i];
                charMapT[t[i]] = s[i];
            } else if(charMapS[s[i]] != t[i] || charMapT[t[i]] != s[i]) return false;
        }
        
        return true;
    }
};
