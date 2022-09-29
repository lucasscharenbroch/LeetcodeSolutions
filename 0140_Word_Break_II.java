class Solution {
    ArrayList<String> result = new ArrayList<String>();
    
    public List<String> wordBreak(String s, List<String> wordDict) {
        // wordAdjList[i] holds a list of words that fit in s[..i - 1]
        HashMap<Integer, List<Integer>> wordAdjList = new HashMap<>(); 
        wordAdjList.put(0, Arrays.asList(-1));
        
        // find each sentence
        for(int i = 0; i < s.length(); i++) {
            if(wordAdjList.get(i) == null) continue; // can't start any words here
            for(int j = 0; j < wordDict.size(); j++) {
                if(s.indexOf(wordDict.get(j), i) == i) { // if s[i..i + word.length] == word
                    int endIndex = i + wordDict.get(j).length();
                    if(wordAdjList.get(endIndex) == null) wordAdjList.put(endIndex, new ArrayList<>());
                    wordAdjList.get(endIndex).add(j);
                }
            }
        }
        
        // rebuild each sentence with dfs
        buildList(wordAdjList, wordDict, s.length(), "");
        return result;
    }
    
    // builds a list of sentences given a wordAdjList
    void buildList(HashMap<Integer, List<Integer>> wordAdjList, 
                   List<String> wordDict, int i, String current) {
        if(wordAdjList.get(i) == null) return; // this happens when there are no solutions
        
        for(int w : wordAdjList.get(i)) {
            if(w == -1) { // index 0 is reached
                result.add(current);
                return;
            }
            String space = (current.length() == 0) ? "" : " ";
            buildList(wordAdjList, wordDict, i - wordDict.get(w).length(), 
                      wordDict.get(w) + space + current);
        }
    }
}
