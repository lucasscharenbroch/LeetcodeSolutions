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

// hashing O(n + m)
class Solution {
    // checks if h[i..i+n.length] matches n
    private boolean stringMatch(String h, int i, String n) {
        for(int j = i; j < i + n.length(); j++) {
            if(h.charAt(j) != n.charAt(j - i)) return false;
        }
        
        return true;
    }
    
    public int strStr(String haystack, String needle) {
        final int P = 31; // arbitrary prime larger than 26
        final int H = haystack.length(), N = needle.length();
        
        // let hash(String s) =  for(i = 0 to s.len): (s[i] - 'a')*(p^i)
        
        int[] pPow = new int[Math.max(H, N) + 1]; // pPow[i] = p^i
        pPow[0] = 1;
        
        for(int i = 1; i < pPow.length; i++) {
            pPow[i] = pPow[i - 1] * P;
        }
        
        int[] hHash = new int[H + 1]; // hHash[i] = hash(h[0..i])
        for(int i = 0; i < H; i++) {
            hHash[i + 1] = hHash[i] + (haystack.charAt(i) - 'a') * pPow[i];
        }
        
        int nHash = 0;
        for(int i = 0; i < N; i++) {
            nHash += (needle.charAt(i) - 'a') * pPow[i];
        }
        
        // search for matches
        for(int i = 0; i < H - N + 1; i++) {
            int hash = (hHash[i + N] - hHash[i]);
            if(hash == nHash * pPow[i] && stringMatch(haystack, i, needle)) return i;
        }
        
        return -1;
    }
}
