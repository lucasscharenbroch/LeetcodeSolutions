// Brute-Force, O(n^2)
class Solution {
public:
    string shortestPalindrome(string s) {
        if(s.length() <= 1) return s;
        
        int l, r; // will be assigned to the beginning/end of the first valid palindrome that spans
                  // the entire string (which likely runs off the left side.)
                  // e.g.        l r
                  //             V V 
                  //              abcd
                  //              0123
                  // (pivot around 'a'; since l fell off the string, it "matches" all other chars)
        
        for(int i = s.length(); i >= 1; i--) { // i = current palindrome size
            int p = (i - 1) / 2; // pivot index
            bool isEvenPivot = i % 2 == 0; // pivot around two indices? (p, p + 1)
            
            l = p - 1 + isEvenPivot;
            r = p + 1;
            
            while(l >= 0 && s[l] == s[r]) l--, r++;
            
            if(l < 0) break;
        }
        
        s.insert(s.begin(), s.rbegin(), s.rend() - r);
            
        return s;
    }
};
