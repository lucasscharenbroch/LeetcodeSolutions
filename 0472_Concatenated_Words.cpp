struct TrieNode {
    TrieNode *children[27] = {nullptr};
};

struct Trie {
    static constexpr int IS_WORD = 26;
    
    TrieNode root;
    
    void add(string& w) {
        TrieNode* current = &root;
        for(int i = 0; i < w.size(); i++) {
            if(current->children[w[i] - 'a'] == nullptr) current->children[w[i] - 'a'] = new TrieNode();
            current = current->children[w[i] - 'a'];
        }
        
        current->children[IS_WORD] = new TrieNode;
    }
    
    bool isConcatenatedWord(string& w, int i = 0, bool hasPartialMatch = false) {
        TrieNode* current = &root;
        
        for(; i != w.size(); i++) {
            if(current->children[IS_WORD] != nullptr && isConcatenatedWord(w, i, true)) return true;
            if(current->children[w[i] - 'a'] == nullptr) return false;
            current = current->children[w[i] - 'a'];
        }
        
        return hasPartialMatch && current->children[IS_WORD] != nullptr;
    }
};

class Solution {
public:
    vector<string> findAllConcatenatedWordsInADict(vector<string>& words) {
        Trie t;
        vector<string> result;
        
        for(string& w : words) t.add(w);
        for(string& w : words) if(t.isConcatenatedWord(w)) result.push_back(w);
            
        return result;
    }
};
