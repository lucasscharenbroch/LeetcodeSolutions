class Solution {
public:
    bool checkValidString(string s) {
        int opened = 0;
        int wildcards = 0;
        
        // iterate forwards, ensure that closed parens are opened
        for(int i = 0; i < s.length(); i++) {
            if(s[i] == '(') opened++;
            else if(s[i] == '*') wildcards++;
            else { // s[i] == ')'
                if(!opened && !wildcards) return false;
                if(opened) opened--;
                else wildcards--;
            }
        }
        
        // iterate backwards, ensure that opened parens are closed
        wildcards = 0;
        int closed = 0;
        for(int i = s.length() - 1; i >= 0; i--) {
            if(s[i] == ')') closed++;
            else if(s[i] == '*') wildcards++;
            else {
                if(!closed && !wildcards) return false;
                if(closed) closed--;
                else wildcards--;
            }
        }
        
        return true;
    }
};
