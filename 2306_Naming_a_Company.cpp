class Solution {
public:
    long long distinctNames(vector<string>& ideas) {
        int leadingCharCounts['z' + 1]['z' + 1] = {0}; // leadingCharCounts[i][j] = x => 
                                                       // x words accept a swap with char(i)
                                                       // and begin with char(j)
        
        vector<vector<char>> swappable_chars(ideas.size()); // swappable_chars[i][j] => ideas[i] 
                                                            // can be swapped with char(j)
        
        unordered_set<string> idea_set(ideas.begin(), ideas.end());
        
        // populate swappable_chars
        for(int i = 0; i < ideas.size(); i++) {
            string idea = ideas[i];
            for(char c = 'a'; c <= 'z'; c++) {
                idea[0] = c;
                if(idea_set.find(idea) != idea_set.end()) continue; // found collision
                swappable_chars[i].push_back(c);
                leadingCharCounts[c][ideas[i][0]]++;
            }
        }
        
        long long result = 0;
        
        for(int i = 0; i < ideas.size(); i++) {
            // add all valid swaps to result
            for(char c : swappable_chars[i]) {
                result += leadingCharCounts[ideas[i][0]][c];
            }
        }
        
        return result;
    }
};
