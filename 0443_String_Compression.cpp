class Solution {
public:
    int compress(vector<char>& chars) {
        int pos = 0; // writing position in chars
        int i = 0; // reading position in chars
        
        while(i < chars.size()) {
            chars[pos++] = chars[i++]; // write first unique char
            int count = 1;
            while(i != chars.size() && chars[i] == chars[i - 1]) i++, count++; // count duplicates
            if(count != 1) for(char j : to_string(count)) chars[pos++] = j; // write count (if count > 1)
        }
        
        return pos;
    }
};
