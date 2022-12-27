class Solution {
public:
    int takeCharacters(string s, int k) {
        // find the largest window that excludes k of {'a', 'b', 'c'} (each)
        
        // first, check that there are sufficient counts of each letter
        int counts['d'] = {0};
        for(int i = 0; i < s.length(); i++) counts[s[i]]++;
        if(counts['a'] < k || counts['b'] < k || counts['c'] < k) return -1;
        
        int anticounts['d'] = {0}; // counts inside sliding window that are subtracted from counts
        int result = INT_MAX;
        int j = 0; // end position of window
        
        // for each starting position, i
        for(int i = 0; i < s.length(); i++) {
            // advance the end of the window while doing so keeps the window valid.
            while(j < s.length() && counts[s[j]] - anticounts[s[j]] > k) anticounts[s[j++]]++;
            
            result = min(result, (int) s.size() - (j - i)); // result = min(result, num_chars_outside_window)
            
            anticounts[s[i]]--; // remove i from anticounts
        }

        return result;
    }
};
