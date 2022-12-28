class Solution {
public:
    string decodeString(string s, int start = 0) {
        string result;
        
        int num = 0;
        stack<pair<int, string>> stack;
        
        for(int i = start; i < s.length(); i++) {
            if(s[i] == '[') {
                stack.push(pair<int, string> {num, ""});
                num = 0;
            } else if(s[i] == ']') {
                string s;
                while(stack.top().first--) s += stack.top().second;
                stack.pop();
                if(!stack.empty()) stack.top().second += s;
                else result += s;
            } else if(isdigit(s[i])){
                num = num * 10 + s[i] - '0';
            } else {
                if(!stack.empty()) stack.top().second += s[i];
                else result += s[i];
            }
        }
        
        return result;
    }
};
