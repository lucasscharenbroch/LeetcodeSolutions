class Solution {
public:
    static constexpr int PRIME_MAX = 1e6; // upper bound to range of primes to consider
    
    // returns a vector of primes from [2, PRIME_MAX]
    vector<int> generatePrimes() {
        vector<int> primes = {2};
        bool sieve[PRIME_MAX];
        memset(sieve, false, sizeof(sieve));
        
        for(long long i = 3; i <= PRIME_MAX; i += 2) {
            if(sieve[i]) continue;
            primes.push_back(i);
            for(long long j = i * i; j <= PRIME_MAX; j += 2 * i) sieve[j] = true;
        }
        
        return primes;
    }
    
    vector<int> closestPrimes(int left, int right) {
        vector<int> primes = generatePrimes();
        
        auto leftPtr = lower_bound(primes.begin(), primes.end(), left);
        auto rightPtr = upper_bound(primes.begin(), primes.end(), right);
        
        vector<int> result = {-1, -1};
        
        if(rightPtr - leftPtr <= 1) return result;
        
        int best = INT_MAX; // smallest difference between primes in the range
        
        for(auto ptr = leftPtr; ptr + 1 != rightPtr; ptr++) {
            if(*(ptr + 1) - *(ptr) < best) {
                result[0] = *ptr;
                result[1] = *(ptr + 1);
                best = result[1] - result[0];
            }
        }
        
        return result;
    }
};
