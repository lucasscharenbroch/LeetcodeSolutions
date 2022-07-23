class Solution {
    public int reverse(int x) {
        int result = 0;    
        int sign = (x > 0) ? 1 : -1;
        
        if(sign == -1) {
            x = -x;
        } 
        
        while(x != 0) {
            int digitValue = (x % 10) * sign;
            
            // check for overflow
            if(sign == 1) {
                if(result > Integer.MAX_VALUE / 10 || result * 10 + digitValue < 0) {
                    return 0;
                }
            } else {
                if(result < Integer.MIN_VALUE / 10 || result * 10 + digitValue > 0) {
                    return 0;
                }
            }
            
            result *= 10;
            result += digitValue;
            x /= 10;
        }
        
        return result;
    }
}
