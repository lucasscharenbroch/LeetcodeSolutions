class Solution {
    public double myPow(double x, int n) {
        if(n == 0) return 1;
        if(n == Integer.MIN_VALUE) return 1.0 / myPow(x * x, Integer.MAX_VALUE); // int_min edgecase
        if(n < 0) return 1.0 / myPow(x, -n);
        
        // given: n > 0
        int m = 0; // number of self-multiplications executed
        double result = 1.0;
        
        while(m < n) {
            int numMultiplications = 1;
            double currentProduct = x;
            while(numMultiplications * 2 < n - m && numMultiplications * 2 > 0) {
                numMultiplications *= 2;
                currentProduct *= currentProduct;
            }
            result *= currentProduct;
            m += numMultiplications;
        }
        
        return result;
    }
}
