class Solution {
public:
    int compare(string& word1, string& word2, int char_rank[]) {
        for(int i = 0; i < min(word1.length(), word2.length()); i++) {
            if(char_rank[word1[i]] < char_rank[word2[i]]) return -1;
            else if(char_rank[word1[i]] > char_rank[word2[i]]) return 1;
        }
        
        if(word1.length() == word2.length()) return 0;
        if(word1.length() > word2.length()) return 1;
        return -1;
    }
    
    bool isAlienSorted(vector<string>& words, string order) {
        int char_rank['z' + 1];
        
        for(int i = 0; i < order.size(); i++) {
            char_rank[order[i]] = i;
        }
        
        for(int i = 1; i < words.size(); i++) {
            if(compare(words[i - 1], words[i], char_rank) > 0) return false;
        }
        
        return true;
    }
};
