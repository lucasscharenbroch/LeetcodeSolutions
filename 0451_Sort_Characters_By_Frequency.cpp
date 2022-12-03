class Solution {
public:
    struct CharCount {
        char c;
        int n;

        CharCount() {
            n = 0;
        }
    };
    
    string frequencySort(string s) {
        // count characters, sort the counts, then reassemble the string.
        CharCount counts[CHAR_MAX];
        
        // count chars
        for(int i = 0; i < s.length(); i++) {
            counts[s[i]].n++;
            counts[s[i]].c = s[i];
        }
        
        // sort counts
        sort(begin(counts), end(counts), [](CharCount a, CharCount b) { return a.n > b.n; });
        
        // reassemble string
        int p = 0; // position in s
        for(int i = 0; i < CHAR_MAX; i++) {
            if(counts[i].n == 0) break;
            for(int j = 0; j < counts[i].n; j++) {
                s[p++] = counts[i].c;
            }
        }
        
        return s;
    }
};
