// cheap solution
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}

// iterative solution O(nm)
class Solution {
    public int strStr(String haystack, String needle) {
        for(int i = 0; i <= haystack.length() - needle.length(); i++) {
            int j; // pointer to compare needle and haystack
            for(j = 0; j < needle.length(); j++) {
                if(needle.charAt(j) != haystack.charAt(i + j)) {
                    break;
                }
            }    
            
            if(j == needle.length()) return i;
        }
        
        return -1;
    }
}

// KMP O(n + m)
class Solution {
    private int[] findLongestPrefixSuffix(String s) {
        int[] LPS = new int[s.length()];
        int j = 0;
        int i = 1;
        
        while(i < s.length()) {
            if(s.charAt(i) == s.charAt(j)) {
                LPS[i] = j + 1;
                i++;
                j++;
            } else {
                if(j == 0) i++;
                else j = LPS[j - 1];
            }
        }
        
        return LPS;
    }
    
    public int strStr(String haystack, String needle) {
        int[] LPS = findLongestPrefixSuffix(needle);
        
        int j = 0;
        int i = 0;
        
        while(i < haystack.length()) {
            if(j == needle.length()) return i - j; // found needle
            
            if(haystack.charAt(i) == needle.charAt(j)) {
                j++;
                i++;
            } else { // try matching a prefix-suffix
                if(j == 0) { // no prefix-suffix
                    i++; // continue
                } else {
                    j = LPS[j - 1]; // consider same i with different j
                }
            }
        }
        
        return j == needle.length() ? haystack.length() - j : -1;
    }
}
