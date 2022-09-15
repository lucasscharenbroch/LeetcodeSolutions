// pre-assembled adjacency list BFS
class Solution {
    private boolean differByOne(String a, String b) {
        if(a.length() != b.length()) return false;
        boolean isOneCharDifferent = false;
        
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) == b.charAt(i)) continue;
            
            if(isOneCharDifferent) return false;
            else isOneCharDifferent = true;
        }
        
        return isOneCharDifferent;
    }
    
    private void adjListAdd(HashMap<String, List<String>> adjList, String k, String v) {
        if(adjList.get(k) != null) adjList.get(k).add(v);
        else {
            adjList.put(k, new ArrayList<String>());
            adjList.get(k).add(v);
        }
    }
    
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int result = 1; // 1 for endWord (beginWord will be counted at beginning of BFS) 
        if(!wordList.contains(beginWord)) wordList.add(beginWord); // add beginWord to wordList
        
        HashMap<String, List<String>> adjList = new HashMap<>();
        // populate adjList (connect all words that differ by one char)
        for(int i = 0; i < wordList.size(); i++) {
            for(int j = i + 1; j < wordList.size(); j++) {
                if(differByOne(wordList.get(i), wordList.get(j))) {
                    adjListAdd(adjList, wordList.get(i), wordList.get(j));
                    adjListAdd(adjList, wordList.get(j), wordList.get(i));
                }
            }
        }
        
        LinkedList<String> queue = new LinkedList<>();
        queue.add(beginWord);
        queue.add(null);
        
        // BFS
        while(!queue.isEmpty()) {
            String current = queue.remove();
            if(current == null) {
                if(queue.isEmpty()) return 0; // no such transformation sequence
                result++; // one layer is complete, therefore another word is in the transformation sequence
                queue.add(null);
                continue;
            }
            
            if(current.equals(endWord)) return result;
            
            if(adjList.get(current) == null) continue; // no neighbors
            for(String neighbor : adjList.get(current)) {
                queue.add(neighbor);
            }
            adjList.put(current, null); // remove neighbors so they aren't traversed again
        }
        
        return 0; // (this return should never be reached)
    }
}

// graph-assembly during traversal
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> wordSet = new HashSet<>(wordList);
        
        LinkedList<String> queue = new LinkedList<>();
        queue.add(beginWord);
        int result = 0;
        
        while(!queue.isEmpty()) {
            result++;
            int size = queue.size();
            while(size-- > 0) {
                String current = queue.remove();
                if(current.equals(endWord)) return result;
                
                // add each differing-by-one word to the queue 
                // (check set for every possible word that differs by one character)
                
                for(int i = 0; i < current.length(); i++) { // for each char in current
                    char[] possibleTransformArr = current.toCharArray();
                    for(char c = 'a'; c <= 'z'; c++) { // for each value that char could be
                        if(c == current.charAt(i)) continue;
                        possibleTransformArr[i] = c;
                        String possibleTransform = new String(possibleTransformArr);
                        if(wordSet.contains(possibleTransform)) { // if it exists in the word set
                            queue.add(possibleTransform); // add is to the queue
                            wordSet.remove(possibleTransform); // and remove it from the set (avoid re-traversing)
                        }
                    }
                }
            }
        }
        
        return 0;
    }
}
