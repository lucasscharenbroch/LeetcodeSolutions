class Solution {
    private int digitAt(int x, long i) {
        if(i == 0) {
            return x % 10;
        }
        
        i = (long) Math.pow(10, ++i);
        return (int) ((x % i) / (i / 10));
    }
    
    public boolean isPalindrome(int x) {
        // negatives cannot be palindromes
        if(x < 0) {
            return false;
        }
        
        // count the digits in x
        int digits = 0;
        for(long digitTester = 1; x / digitTester != 0; digitTester *= 10)  {
            digits++;        
        }
        
        for(int i = 0; i < digits / 2; i++) {
            if(digitAt(x, i) != digitAt(x, digits - i - 1)) {
                return false;
            }    
        }
        
        return true;
        
    }
}
