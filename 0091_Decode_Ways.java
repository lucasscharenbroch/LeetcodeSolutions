// recursion
class Solution {
    private int numDecodings(String s, int i) {
        if(i == s.length()) return 1; // base-case
        if(s.charAt(i) == '0') return 0; // leading zero- no possible decodings
        int result = 0;
        
        for(int l = 1; l < 27; l++) { // for each letter
            if(l < 10) { // one-digit-code letters
                if((int) s.charAt(i) == l + '0') result += numDecodings(s, i + 1); // recursively find # matches
            } else { // two-digit-code letters
                if(i == s.length() - 1) break; // only one digit to match
                if((int) s.charAt(i) == (l / 10) + '0' && (int) s.charAt(i + 1) == (l % 10) + '0')
                    result += numDecodings(s, i + 2);
            }
        }
        
        return result;
    }
    
    public int numDecodings(String s) {
        return numDecodings(s, 0); // start at index 0
    }
}

// iteration
class Solution {
    public int numDecodings(String s) {
        int[] decodingCounts = new int[s.length() + 1]; // dC[i] holds the # of decodings of substring s[i:end]
        
        decodingCounts[s.length()] = 1; // any decoding that reaches the end of s counts as a single decoding
        
        for(int i = s.length() - 1; i >= 0; i--) { // iterate through s backwards
            if(s.charAt(i) == '0') continue;  // 0 cannot be decoded.
            
            // s[i] is a valid 1-digit-code - there are dC[i + 1] decodings if s[i] is 
            // interpreted as a 1-digit-code
            decodingCounts[i] += decodingCounts[i + 1]; 
            
            // check if s[i -> i + 1] is a valid 2-digit-code
            if(i == s.length() - 1) continue; // cannot be a 2-digit-code
            if(s.charAt(i) == '1' || (s.charAt(i) == '2' && s.charAt(i + 1) < '7')) {
                decodingCounts[i] += decodingCounts[i + 2]; // there are dC[i + 2] decodings if s[i] is
                                                            // interpreted as a 2-digit-code
            }
        }
        
        return decodingCounts[0];
    }
}
