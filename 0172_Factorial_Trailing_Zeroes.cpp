// Brute-Force, O(n)
class Solution {
public:
    int num_fives = 0;
    int num_twos = 0;
    
    int trailingZeroes(int n) {
        if(n == 0) return 0;
        if(n > 1) trailingZeroes(n - 1);
        
        while(n % 5 == 0) n /= 5, num_fives++;
        while(n % 2 == 0) n /= 2, num_twos++;
        
        return min(num_fives, num_twos);
    }
};

// O(lg(n))
class Solution {
public:
    int trailingZeroes(int n) {
        int num_fives = 0;
        int i = n;
        
        while(i / 5) {
            num_fives += i / 5;
            i /= 5;
        }
        
        return num_fives;
    }
};
