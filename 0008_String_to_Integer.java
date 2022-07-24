class Solution {
    public int myAtoi(String s) {
        int i = 0; // iterator for s
        
        // skip leading whitespace (spaces)
        while(s.length() > i && s.charAt(i) == ' ') {
            i++;    
        }
        
        // get sign
        int sign = 1;
        if(s.length() > i && s.charAt(i) == '+') {
            i++;
        } else if(s.length() > i && s.charAt(i) == '-')  {
            sign = -1;
            i++;
        }
        
        int result = 0;
        for(; s.length() > i; i++) {
            if(!Character.isDigit(s.charAt(i))) {
                break;
            }
            int digitValue = s.charAt(i) - '0';
            
            // check for overflow/ underflow
            if(sign == 1) {
                if(result > Integer.MAX_VALUE / 10 || result * 10 + digitValue < 0) {
                    return Integer.MAX_VALUE;
                } 
            } else {
                if(-result < Integer.MIN_VALUE / 10 || -result * 10 - digitValue > 0) {
                    return Integer.MIN_VALUE;
                }
            }
            
            result *= 10;
            result += digitValue;
        }
        
        return sign * result;
    }
}
