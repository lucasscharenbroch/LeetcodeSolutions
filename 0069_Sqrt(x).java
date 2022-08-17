class Solution {
    public int mySqrt(int x) {
        // "binary search" between 0 and 300,000 for the square root
        
        int min = 0;
        int max = 300_000;
        
        while(min != max) {
            long mid = (min + max + 1) / 2;
            if(mid * mid == x) return (int) mid;
            else if(mid * mid > x) max = (int) mid - 1;
            else min = (int) mid;
        }
        
        return min;
    }
}
