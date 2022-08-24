// memoization
class Solution {
    HashMap<String, Boolean> resultTable = new HashMap();
    
    public boolean isScramble(String s1, String s2) {
        if(s1.hashCode() == s2.hashCode()) return true;
        if(resultTable.get(s1 + s2) != null) return resultTable.get(s1 + s2);
        
        int length = s1.length(); // the length of both strings
        if(length == 1) return false;
        
        for(int split = 1; split < length; split++) {
            // recursively check if s1 can scramble into s2 
            if((isScramble(s1.substring(0, split), s2.substring(length - split)) &&
               isScramble(s1.substring(split), s2.substring(0, length - split)))
               ||
               (isScramble(s1.substring(0, split), s2.substring(0, split)) &&
               isScramble(s1.substring(split), s2.substring(split)))) {
                resultTable.put(s1 + s2, true); // memoize
                return true;
            }
        }
        
        resultTable.put(s1 + s2, false); // memoize
        return false;
    }
}

// pruining and memoization
class Solution {
    // increments the integer value at map[key]
    private <K> void hashMapInc(HashMap<K, Integer> map, K key){
        if(map.get(key) == null) map.put(key, 1);
        else map.put(key, map.get(key) + 1);
    }
    
    HashMap<String, Boolean> results = new HashMap<>(); // for memoization
    
    private boolean dfs(String s1, String s2) {
        if(results.get(s1 + s2) != null) return results.get(s1 + s2); // recall prev. solutions 
        int length = s1.length();
        if(length == 1) return s1.charAt(0) == s2.charAt(0);
        
        // the following hash maps keep track of chars in s1 and s2
        // from (beginning -> split), or ((length - split - 1) -> end), respectively
        HashMap<Character, Integer> s1b = new HashMap<>(), // string 1 beginning
                                    s1e = new HashMap<>(), // string 1 end
                                    s2b = new HashMap<>(), // string 2 ...
                                    s2e = new HashMap<>();
        
        for(int split = 1; split < length; split++) {
            // update hash maps
            hashMapInc(s1b, s1.charAt(split - 1)); hashMapInc(s1e, s1.charAt(length - split));
            hashMapInc(s2b, s2.charAt(split - 1)); hashMapInc(s2e, s2.charAt(length - split));
            
            // scrambling without swapping:
            // check if the beginning segments of s1 and s2 have the same char
            if(s1b.equals(s2b)) {
                // if so, recursively check if each side can be scrambled to form s2
                if(dfs(s1.substring(0, split), s2.substring(0, split)) &&
                   dfs(s1.substring(split), s2.substring(split))) {
                    results.put(s1 + s2, true); // memoize
                    return true;
                }
            }
            
            // scrambling with swapping:
            // check if the beginning and ending segments' char frequencies match
            if(s1b.equals(s2e) || s2b.equals(s1e)) {
                // recursively check if each side (compared to the opposite) can be scrambled to form s2
                if(dfs(s1.substring(0, split), s2.substring(length - split)) &&
                   dfs(s1.substring(split), s2.substring(0, (length - split)))) {
                    results.put(s1 + s2, true); // memoize
                    return true;
                }
            }
        }
        
        results.put(s1 + s2, false); // memoize
        return false;
    }
    
    public boolean isScramble(String s1, String s2) {
        // return false if s1 and s2 have different character frequencies
        HashMap<Character, Integer> s1counts = new HashMap<>(), s2counts = new HashMap<>();
        for(int i = 0; i < s1.length(); i++) {
            hashMapInc(s1counts, s1.charAt(i));
            hashMapInc(s2counts, s2.charAt(i));
        }
        
        if(!s1counts.equals(s2counts)) return false;
        return dfs(s1, s2);
    }
}
