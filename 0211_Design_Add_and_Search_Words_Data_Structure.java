class TrieNode {
    TrieNode[] children;
    
    TrieNode() {
        children = new TrieNode[27];
    }
}

class WordDictionary {
    TrieNode trie;

    public WordDictionary() {
        trie = new TrieNode();
    }
    
    public void addWord(String word) {
        TrieNode current = trie;
        
        for(int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            
            if(current.children[c] == null) current.children[c] = new TrieNode();
            current = current.children[c];
        }
        
        current.children[26] = new TrieNode(); // add $
    }
    
    public boolean search(String word) {
        return search(word, 0, trie);
    }
    
    // starts search at word[i] and TrieNode current
    private boolean search(String word, int i, TrieNode current) {
        if(i == word.length()) return current.children[26] != null;
        
        int c = word.charAt(i);
        if(c != '.') {
            if(current.children[c - 'a'] == null) return false;
            return search(word, i + 1, current.children[c - 'a']);
        }

        // word[i] is wildcard ('.')
        // find all non-null children of current and recurse
        for(int j = 0; j < 26; j++) {
            if(current.children[j] != null && search(word, i + 1, current.children[j])) return true;
        }
        
        return false;
    }
}
