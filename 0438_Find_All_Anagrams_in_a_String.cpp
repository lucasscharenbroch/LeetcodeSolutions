class Solution {
public:
    vector<int> findAnagrams(string s, string p) {
        if(s.length() < p.length()) return {};
        
        vector<int> result;
        vector<int> s_char_counts('z' + 1, 0), p_char_counts('z' + 1, 0);
        int num_matching = 0;
        
        for(char& c : p) p_char_counts[c]++; // count chars in p
        
        // count chars in first window in s
        for(int i = 0; i < p.size(); i++) {
            s_char_counts[s[i]]++;
            if(s_char_counts[s[i]] <= p_char_counts[s[i]]) num_matching++;
        }
        
        // slide window over s
        for(int i = 0; i <= s.length() - p.length(); i++) {
            if(num_matching == p.length()) result.push_back(i);
            
            int j = i + p.length(); // end index
            if(j == s.length()) break;
            // update char counts to next window, update num_matching
            if(--s_char_counts[s[i]] < p_char_counts[s[i]]) num_matching--;
            if(++s_char_counts[s[j]] <= p_char_counts[s[j]]) num_matching++;
        }
        
        return result;
    }
};
