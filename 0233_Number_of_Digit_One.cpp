class Solution {
public:
    int countDigitOne(int n) {
        int result = 0;
        long long place = 1; // holds 10^x, where x is the number of iterations.
        int m = 0; // holds the visited (rightmost x) digits of the original n
        int num_digits = 0;
        
        // iterate through digits, right-to-left; for each digit, assume all digits to the right
        // are 0; calculate the number of 1s in numbers between (digit * place) and 0.
        
        for(; n; m += (n % 10) * place, n /= 10, place *= 10, num_digits++) {
            if(n % 10 == 0) continue; // no 1s in the range [0, 0{0}]
            
            result += (n % 10 == 1) ? m + 1 : place; // number of cases where the current digit is 1
            int lower_digit_count = (n % 10) * (place / 10); // number of times each subsequent digit is 1
            result += lower_digit_count * num_digits;
        }
        
        return result;
    }
};
