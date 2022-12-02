class Solution {
public:
    bool closeStrings(string word1, string word2) {
        int word1Counts[26] = {0}, word2Counts[26] = {0};
        
        // count chars in both words
        for(int i = 0; i < word1.length(); i++) word1Counts[word1[i] - 'a']++;
        for(int i = 0; i < word2.length(); i++) word2Counts[word2[i] - 'a']++;
        
        // verify that arrays contain the same set of characters
        for(int i = 0; i < 26; i++) {
            // if only one word contains this character ((both words contain) != (one word contains))
            if((word1Counts[i] && word2Counts[i]) != (word1Counts[i] || word2Counts[i])) return false;
        }
        
        // verify that arrays contain the same character counts
        sort(begin(word1Counts), end(word1Counts));
        sort(begin(word2Counts), end(word2Counts));
        for(int i = 0; i < 26; i++) if(word1Counts[i] != word2Counts[i]) return false;
        
        return true;
    }
};
