class Solution {
public:
    int closetTarget(vector<string>& words, string target, int startIndex) {
        if(words[startIndex] == target) return 0;
        
        int l = startIndex, r = startIndex;
        int dist = 0;
        do {
            l = ((l - 1) + words.size()) % words.size();
            r = ((r + 1) + words.size()) % words.size();
            dist++;
            if(words[l] == target || words[r] == target) return dist;
        } while(l != r);
        
        return -1;
    }
};
