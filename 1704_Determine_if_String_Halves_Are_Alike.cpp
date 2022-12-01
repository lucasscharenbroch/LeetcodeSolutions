class Solution {
public:
    static bool isVowel(char c) {
        c = tolower(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
    
    bool halvesAreAlike(string s) {
        string::iterator mid = s.begin() + (s.length() / 2);
        return count_if(s.begin(), mid, isVowel) == count_if(mid, s.end(), isVowel);
    }
};
