class Solution {
    HashSet<Integer> visitedStartPoints = new HashSet<>();
    
    private boolean wordBreak(String s, List<String> wordDict, int start) {
        if(visitedStartPoints.contains(start)) return false;
        visitedStartPoints.add(start);
        
        if(start == s.length()) return true; // reached the end of s
        
        for(String word : wordDict) {
            if(s.indexOf(word, start) == start) {
                if(wordBreak(s, wordDict, start + word.length())) return true;
            }
        }
        
        return false;
    }
    
    public boolean wordBreak(String s, List<String> wordDict) {
        return wordBreak(s, wordDict, 0);
    }
}
