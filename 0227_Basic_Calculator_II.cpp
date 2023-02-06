class Solution {
public:
    long long read_int(string& s, int& i) {
        long long result = 0;
        bool is_neg = false;
        
        while(s[i] == ' ') i++;
        if(s[i] == '-') i++, is_neg = true;
        
        for(; i < s.length() && isdigit(s[i]); i++) {
            result *= 10;
            result += s[i] - '0';
        }
        
        return result;
    }
    
    int calculate(string s) {
        long long last = 0, sum = 0;
        
        for(int i = 0; i < s.length();) {
            while(i < s.length() && s[i] == ' ') i++; // skip whitespace
            if(i == s.length()) break;
            
            if(s[i] == '*') last *= read_int(s, ++i);
            else if(s[i] == '/') last /= read_int(s, ++i);
            else if(s[i] == '-') sum += last, last = -read_int(s, ++i);
            else if(s[i] == '+') sum += last, last = read_int(s, ++i);
            else sum += last, last = read_int(s, i); // number
        }
        
        return sum + last;
    }
};
