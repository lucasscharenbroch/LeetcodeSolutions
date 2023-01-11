class Solution {
public:
    // returns a vector of primes from [2, numPrimes]
    vector<int> generatePrimes(int numPrimes) {
        vector<int> primes = {2};
        vector<bool> sieve(numPrimes, false);
        
        for(long long i = 3; i <= numPrimes; i += 2) {
            if(sieve[i]) continue;
            primes.push_back(i);
            for(long long j = i * i; j <= numPrimes; j += 2 * i) sieve[j] = true;
        }
        
        return primes;
    }
    
    int countPrimes(int n) {
        if(n <= 2) return 0;
        return generatePrimes(n - 1).size();
    }
};
