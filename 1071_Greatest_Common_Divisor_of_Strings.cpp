// Brute-Force, O(n^2)
class Solution {
public:
    bool divides(string& t, string& s) {
        if(s.length() % t.length()) return false;
        
        for(int i = 0; i < s.length(); i += t.length()) {
            for(int j = i; j < i + t.length(); j++) if(s[j] != t[j - i]) return false;
        }
        
        return true;
    }
    
    string gcdOfStrings(string str1, string str2) {
        for(int i = min(str1.length(), str2.length()); i > 0; i--) {
            string divisor = str1.substr(0, i);
            if(divides(divisor, str1) && divides(divisor, str2)) return divisor;
        }
        
        return  "";
    }
};

// O(n)
class Solution {
public:
    string gcdOfStrings(string str1, string str2) {
        if(str1 + str2 != str2 + str1) return ""; // can't have a gcd
        return str1.substr(0, gcd(str1.length(), str2.length()));
    }
};
