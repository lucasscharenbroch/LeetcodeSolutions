class TrieNode {
    public TrieNode[] children;
    
    public TrieNode() {
        children = new TrieNode[27]; // 27 = a-z, $
    }
}

class Trie {
    TrieNode tree;

    public Trie() {
        tree = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode current = tree;
        for(int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if(current.children[c] == null) current.children[c] = new TrieNode();
            current = current.children[c];
        }
        if(current.children[26] == null) current.children[26] = new TrieNode(); // add $
    }
    
    public boolean search(String word) {
        TrieNode current = tree;
        
        for(int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            
            if(current.children[c] == null) return false;
            current = current.children[c];
        }
        
        return current.children[26] != null;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode current = tree;
        
        for(int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            
            if(current.children[c] == null) return false;
            current = current.children[c];
        }
        
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
