class Solution {
    public int divide(int dividend, int divisor) {
        // edge cases ("unsigned comparison" of Integer.MIN_VAL and 1 causes
        // unexpected behavior (Integer.MIN_VAL - 1 => 0))
        if(dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        } else if(dividend == Integer.MIN_VALUE && divisor == 1) {
            return Integer.MIN_VALUE;
        }
        
        // Math.abs(Integer_MIN_VALUE) => Integer.MIN_VALUE - this will work,
        // because Integer.MIN_VALUE is 0b1000000000... , and thus its unsigned
        // value is the same as its signed absolute value
        int d = Math.abs(dividend);
        int s = Math.abs(divisor);
        
        int quotient = 0;
        
        while(d - s >= 0) { // unsigned d >= s
            int i = 0;
            while(d - (s << ++i) >= 0); // d >= s * 2^i 
            i--; // correct overshoot
            quotient += 1 << i;
            d -= s << i;
        }
        
        if((dividend > 0) != (divisor > 0)) { // opposite signs;
            return -quotient;
        } else {
            return quotient;
        }
    }
}
