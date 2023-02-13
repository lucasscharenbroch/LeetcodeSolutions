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
        
        return (is_neg ? -1 : 1) * result;
    }
    
    int primitive_calculate(string s) { // calculates a string with the four operations (see #227)
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
    
    int calculate(string s1) {
        string s2;
        
        // copy s1 to s2, recursively handling all parenthesis
        for(int i = 0; i < s1.length(); i++) {
            if(s1[i] == '(') {
                string parenthetical = "";
                int paren_layers = 1;
                for(i = i + 1; paren_layers; i++) {
                    if(s1[i] == '(') paren_layers++;
                    else if(s1[i] == ')') paren_layers--;
                    if(!paren_layers) break;
                    parenthetical += s1[i];
                }
                
                long long paren_value = calculate(parenthetical);
                s2 += "1*" + to_string(paren_value); // hack to handle "--" after things like 1 - (-2)
            } else s2 += s1[i];
        }
        
        return primitive_calculate(s2);
    }
};
