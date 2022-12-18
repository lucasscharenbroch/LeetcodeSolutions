class Solution {
public:
    int similarPairs(vector<string>& words) {
        // convert the vector<string> to a vector<int> with a bitset of the contained letters
        vector<int> bits;
        for(string& word : words) {
            int bitset = 0;
            for(int c = 0; c < word.length(); c++) {
                bitset |= (1 << (word[c] - 'a'));
            }
            bits.push_back(bitset);
        }
        
        unordered_map<int, int> seen; // map of seen bitsets
        int pairs = 0;
        for(int& bitset: bits) {
            pairs += seen[bitset];
            seen[bitset]++;
        }
        
        return pairs;
    }
};
