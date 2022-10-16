class TrieNode {
    TrieNode[] children = new TrieNode[27]; // a-z and $
    String word;
    
    TrieNode(String word) {
        this.word = word;
    }
    
    TrieNode() {
    }
    
    public void add(String word) {
        TrieNode current = this;
        for(char c : word.toCharArray()) {
            if(current.children[c - 'a'] == null) current.children[c - 'a'] = new TrieNode();
            current = current.children[c - 'a'];
        }
        current.children[26] = new TrieNode(word); // $
    }
    
    public static TrieNode buildTrie(String[] words) {
        TrieNode trie = new TrieNode();
        
        for(String word : words) {
            trie.add(word);
        }
        
        return trie;
    }
}

class Solution {
    ArrayList<String> result = new ArrayList<>();
    boolean[][] visited;
    
    private void search(char[][] board, TrieNode currentNode, int r, int c) {
        if(r < 0 || r >= board.length || c < 0 || c >= board[0].length || visited[r][c]) 
            return; // stop searching if the cell is visited or out-of-bounds
        
        currentNode = currentNode.children[board[r][c] - 'a'];
        if(currentNode == null) return; // no word that fufills the current order of cells
        
        if(currentNode.children[26] != null) { // found a word
            result.add(currentNode.children[26].word);
            currentNode.children[26] = null; // remove word from trie to prevent duplicates
        }
        
        // search all surrounding cells
        visited[r][c] = true;
        search(board, currentNode, r + 1, c);
        search(board, currentNode, r - 1, c);
        search(board, currentNode, r, c + 1);
        search(board, currentNode, r, c - 1);
        visited[r][c] = false;
    }
        
    public List<String> findWords(char[][] board, String[] words) {
        TrieNode trie = TrieNode.buildTrie(words);
        visited = new boolean[board.length][board[0].length];
        
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[i].length; j++) {
                search(board, trie, i, j);
            }
        }
        
        return result;
    }
}
