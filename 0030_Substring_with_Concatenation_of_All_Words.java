// recursive hash-map-cloning solution (fails)
class Solution {
    private HashMap<String, HashMap<Integer, Boolean>> startIndices = new HashMap<>();
    
    private boolean comboExists(LinkedList<String> words, String s, int startIndex) {
        if(words.size() == 0) return true;
        for(int i = 0; i < words.size(); i++) {
            String currentWord = words.get(i);
            Boolean hasThisStartIndex = startIndices.get(currentWord).get(startIndex);
            if(hasThisStartIndex != null && hasThisStartIndex) {
                // check if all other words follow this one    
                words.remove(i);
                if(comboExists(words, s, startIndex + currentWord.length())) return true;
                // if not, put currentWord back into the linked list, and continue
                words.add(i, currentWord);
            }
        }    
        return false;
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        // find all start indices of each word, and add them to startIndices
        for(String word : words) {
            startIndices.put(word, new HashMap<Integer, Boolean>());
            HashMap<Integer, Boolean> indices = startIndices.get(word);
            int index;
            int i = 0;
            while((index = s.indexOf(word, i)) != -1) {
                indices.put(index, true);
                i = index + 1;        
            }
        }
        
        ArrayList<Integer> solution = new ArrayList();
        
        // check each index
        for(int i = 0; i < s.length(); i++) {
            if(comboExists(new LinkedList<String>(Arrays.asList(words)), s, i)) {
                solution.add(i);    
            }
        }
        
        return solution;
    }
}

// recursive hash-map-reuse solution
class Solution {
    private HashMap<String, Integer> wordCounts = new HashMap<>();
    
    private boolean isValidConcat(String s, int startIndex, int concatLength, int wordLength) {
        HashMap<String, Integer> counts = (HashMap<String, Integer>) wordCounts.clone();
        
        String currentWord;
        for(int i = startIndex; i < startIndex + concatLength; i += wordLength) {
            currentWord = s.substring(i, i + wordLength);
            if(counts.get(currentWord) == null || counts.get(currentWord) < 1) {
                return false;
            }
            counts.put(currentWord, counts.get(currentWord) - 1);
        }
        
        return true;
    }
    
    public List<Integer> findSubstring(String s, String[] words) {
        // fill wordCounts
        for(String word: words) {
            if(wordCounts.get(word) == null) {
                wordCounts.put(word, 1);
            } else {
                wordCounts.put(word, wordCounts.get(word) + 1);
            }
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        
        // try each starting index
        int concatLength = words.length * words[0].length();
        for(int i = 0; i < s.length() - concatLength + 1; i++) {
            if(isValidConcat(s, i, concatLength, words[0].length())) {
                result.add(i);
            } 
        }
        
        return result;
    }
}

// sliding window solution (fastest)
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        ArrayList<Integer> solutions = new ArrayList<Integer>();
        
        //if(s.length() == 0 || words.length == 0) return solutions; // base cases (N/A)
        
        HashMap<String, Integer> wordCounts = new HashMap<>();    
        // fill wordCounts
        for(String word: words) {
            // wordCounts[word]++;
            wordCounts.put(word, (wordCounts.get(word) == null) ? 1 : wordCounts.get(word) + 1);
        }
        
        int wordLength = words[0].length();
        
        for(int i = 0; i < wordLength; i++) {
            // keeps track of WHICH words were removed (to avoid double counts)
            HashMap<String, Integer> remaining = (HashMap<String, Integer>) wordCounts.clone(); 
            
            int correctWords = 0; // keeps track of HOW MANY words were removed 
            int windowStart = i; // start index of potential solution
            int windowEnd = i + words.length * wordLength; // end index of potential solution
            
            // get initial count in window (the last word will be handled in the following loop)
            for(int j = windowStart; j < windowEnd - wordLength; j += wordLength) {
                String current = s.substring(j, j + wordLength); 
                if(remaining.get(current) != null) {
                    remaining.put(current, remaining.get(current) - 1); // decrement 
                    if(remaining.get(current) >= 0) correctWords++;
                } 
            }
            
            while(windowEnd <= s.length()) {
                // add last (newly added) word
                String newWord = s.substring(windowEnd - wordLength, windowEnd);
                if(remaining.get(newWord) != null) {
                    remaining.put(newWord, remaining.get(newWord) - 1);    
                    if(remaining.get(newWord) >= 0) correctWords++;
                }
                
                // check if solution is valid
                if(correctWords == words.length) solutions.add(windowStart);
                
                // remove first word
                String oldWord = s.substring(windowStart, windowStart + wordLength); 
                if(remaining.get(oldWord) != null) {
                    remaining.put(oldWord, remaining.get(oldWord) + 1);
                    if(remaining.get(oldWord) > 0) correctWords--;
                }
                
                // advance window
                windowStart += wordLength;
                windowEnd += wordLength;
            }
            
        }
        
        return solutions;
    }
}
