class Solution {
public:
    vector<int> partitionLabels(string s) {
        int lastIndex['z' + 1]; // lastIndex[c] contains the index of the last occurrence of
                                // char c in s 
        for(int i = 0; i < s.length(); i++) lastIndex[s[i]] = i;
        
        vector<int> partitionSizes;
        
        int lastPartitionEnd = -1;
        int partitionEnd = lastIndex[s[0]]; // candidate for end of current partition
        
        for(int i = 0; i < s.length(); i++) {
            if(partitionEnd == i) {
                partitionSizes.push_back(i - lastPartitionEnd);
                lastPartitionEnd = i;
                if(i != s.length() - 1) partitionEnd = lastIndex[s[i + 1]];
            } else {
                partitionEnd = max(partitionEnd, lastIndex[s[i]]);
            }
        }
        
        return partitionSizes;
    }
};
