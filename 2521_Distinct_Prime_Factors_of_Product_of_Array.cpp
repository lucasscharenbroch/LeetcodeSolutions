class Solution {
public:
    int distinctPrimeFactors(vector<int>& nums) {
        bool isFactor[10001] = {false};
        int result = 0;
        
        // product of (the prime factors of each element) is the prime factorization 
        // of (the product of all elements)
        
        for(int n : nums) { // find the prime factors of each n in nums
            int factor = 2; // candidate for a factor
            
            while(n != 1) { // smallest factor is always prime
                while(n % factor != 0) factor++;
                n /= factor;
                if(!isFactor[factor]) {
                    isFactor[factor] = true;
                    result++;
                }
            }
        }
        
        return result;
    }
};
