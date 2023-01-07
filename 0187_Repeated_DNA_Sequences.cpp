class Solution {
public:
    vector<string> findRepeatedDnaSequences(string s) {
        // sliding window: count instances of each 10-letter-string
        
        unordered_map<string, int> counts; // counts of 10-letter-long sequences
        vector<string> result;
        
        for(int l = 0; l < (int) s.length() - 9; l++) {
            string current = s.substr(l, 10);
            if(++counts[current] == 2) result.push_back(current);
        }
        
        return result;
    }
};
