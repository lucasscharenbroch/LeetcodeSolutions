class Solution {
public:
    string fractionToDecimal(long long n, long long d) {
        if(n == 0) return "0";
        string result = (n < 0) != (d < 0) ? "-" : "";
        n = abs(n), d = abs(d);
        result += to_string(n / d);
        if(n % d == 0) return result; // result is integer
        result += '.';
        
        n = (n % d) * 10;
        
        string fraction = "";
        unordered_map<long long, long long> remainder_index; // remainder_index[i]
                                                             // contains the index of %fraction%
                                                             // where the quotient from the
                                                             // remainder i begins
        
        while(true) {
            if(remainder_index.find(n) != remainder_index.end()) { // found repeated remainder
                int idx = remainder_index[n];
                result += fraction.substr(0, idx); // copy remainder before repeated portion
                result += '(' + fraction.substr(idx) + ')'; // repeated portion in parens
                break;
            } else remainder_index[n] = fraction.size();
            
            if(d > n) {
                n *= 10;
                fraction += '0';
                continue;
            }
            
            fraction += ((int)'0' + (n / d));
            n = 10 * (n % d);
            
            if(n == 0) {
                result += fraction;
                break;
            }
        }
        
        return result;
    }
};
