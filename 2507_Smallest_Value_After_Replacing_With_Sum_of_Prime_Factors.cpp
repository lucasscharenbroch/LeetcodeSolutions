class Solution {
public:
    int smallestFactor(int n) {
        if(n <= 2) return n;
        if(n % 2 == 0) return 2;
        for(int i = 3; i < n; i += 2) {
            if(n % i == 0) return i;
        }
        return n;
    }
    
    int smallestValue(int n) {
        int s = smallestFactor(n); // smallest factor is always prime
        if(s == n) return n;
        
        int sum = 0;
        for(int i = n; i != 1;) {
            s = smallestFactor(i);
            sum += s;
            i /= s;
        }
        
        if(sum == n) return n;
        return smallestValue(sum);
    }
};
