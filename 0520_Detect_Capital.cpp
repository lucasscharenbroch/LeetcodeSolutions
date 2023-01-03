class Solution {
public:
    bool stringIsLower(string word, int i = 0) {
        for(; i < word.size(); i++) if(isupper(word[i])) return false;
        return true;
    }
    
    bool stringIsCapital(string word, int i = 0) {
        for(; i < word.size(); i++) if(islower(word[i])) return false;
        return true;
    }
    
    bool detectCapitalUse(string word) {
        return stringIsLower(word) || stringIsCapital(word) || (isupper(word[0]) && stringIsLower(word, 1));
    }
};
