class Solution {
    private void addToListMap(HashMap<String, List<String>> map, String k, String s) {
        if(map.get(k) == null) {
            map.put(k, new ArrayList<String>());
        }
        map.get(k).add(s);
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        
        HashSet<String> wordSet = new HashSet<>(wordList);
        LinkedList<String> wordQueue = new LinkedList<>();
        HashMap<String, Integer> distFromBegin = new HashMap<>();
        HashMap<String, List<String>> parentRungs = new HashMap<>();
        int dist = 0;
        boolean foundEndWord = false;
        
        wordQueue.add(beginWord);
        distFromBegin.put(beginWord, 0);
        
        // BFS
        while(!wordQueue.isEmpty() && !foundEndWord) {
            int size = wordQueue.size();
            dist++;
            
            while(size-- > 0) {
                String currentWord = wordQueue.remove();
                
                // for every string that differs by one
                for(int i = 0; i < currentWord.length(); i++) {
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(c == currentWord.charAt(i)) continue;
                        char[] possibleNeighborAsArr = currentWord.toCharArray();
                        possibleNeighborAsArr[i] = c;
                        String possibleNeighbor = String.valueOf(possibleNeighborAsArr);
                        if(!wordSet.contains(possibleNeighbor)) continue;
                        
                        if(distFromBegin.get(possibleNeighbor) == null) {
                            wordQueue.add(possibleNeighbor);
                            distFromBegin.put(possibleNeighbor, dist);
                        }
                        if(distFromBegin.get(possibleNeighbor) == dist) {
                            addToListMap(parentRungs, possibleNeighbor, currentWord);
                            if(possibleNeighbor.equals(endWord)) foundEndWord = true;
                        }
                    }
                }
            }
        }
        
        if(!foundEndWord) return new LinkedList<List<String>>();
        return getLadders(parentRungs, endWord);
    }
    
    List<List<String>> getLadders(HashMap<String, List<String>> parentRungs, String current) {
        // when beginWord is reached, return {{beginWord}}
        if(parentRungs.get(current) == null) return Arrays.asList(Arrays.asList(current));
        
        List<List<String>> result = new ArrayList<>();
        
        for(String parentRung : parentRungs.get(current)) {
            List<List<String>> parentLadders = getLadders(parentRungs, parentRung);
            for(List<String> ladder : parentLadders) {
                ArrayList<String> newLadder = new ArrayList<>(ladder);
                newLadder.add(current);
                result.add(newLadder);
            }
        }
        
        return result;
    } 
}
