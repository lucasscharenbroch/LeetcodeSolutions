class Solution {
    public int reverse(int x) {
        int result = 0;    
        int sign = (x > 0) ? 1 : -1;

        if(sign == -1) {
            x = -x;
        } 

        while(x != 0) {
            // check for overflow
            if(result > Integer.MAX_VALUE / 10 || result < Integer.MIN_VALUE / 10) {
                return 0; 
            }

            result *= 10;
            result += (x % 10) * sign;
            x /= 10;
        }

        return result;
    }
}
