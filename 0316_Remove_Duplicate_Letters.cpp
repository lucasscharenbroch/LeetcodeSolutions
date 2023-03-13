// Greedy, O(26^3 + n)
class Solution {
public:
    char minimal_char(vector<list<int>>& char_indices) {
        for(int i = 0; i < 26; i++) { // set i to the minimal placeable char
            if(char_indices[i].size() == 0) continue;
            int min_i_idx = char_indices[i].front();
            
            for(int j = i + 1; j < 26; j++) {
                if(!char_indices[j].size()) continue;
                int max_j_idx = char_indices[j].back();
                if(min_i_idx > max_j_idx)  goto different_i;
            }
            
            // found valid i
            char_indices[i].clear();
            for(int j = 0; j < 26; j++) // remove all start indices before min_i_idx
                while(char_indices[j].size() && char_indices[j].front() < min_i_idx) 
                    char_indices[j].pop_front();
            return i + 'a';
            
            different_i:;
        }
        
        return 0;
    }
    
    string removeDuplicateLetters(string s) {
        vector<list<int>> char_indices(26);
        for(int i = 0; i < s.length(); i++) char_indices[s[i] - 'a'].push_back(i);
        
        string result;
        
        // repeatedly choose the minimal char who can be placed
        // before all other remaining chars
        char c;
        while((c = minimal_char(char_indices)) != 0) result += c;
        
        return result;
    }
};

// Stack, O(2n)
class Solution {
public:
    string removeDuplicateLetters(string s) {
        unordered_map<char, int> counts;
        unordered_map<char, bool> on_stack;
        for(char& c : s) counts[c]++;
        stack<char> stack;
        
        // add each char in s to stack, remove chars from the top of the stack
        // if they are less than the current char and exist later in s
        
        for(char& c : s) {
            if(on_stack[c]) {
                counts[c]--;
                continue;
            }
            
            while(stack.size() && stack.top() > c && counts[stack.top()] > 1) {
                on_stack[stack.top()] = false;
                counts[stack.top()]--; stack.pop();
            }
            
            stack.push(c);
            on_stack[c] = true;
        }
        
        string result(stack.size(), ' ');
        while(stack.size()) {
            result[stack.size() - 1] = stack.top(); stack.pop();
        }
        
        return result;
    }
};
