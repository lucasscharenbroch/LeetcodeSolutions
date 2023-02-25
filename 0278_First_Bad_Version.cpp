// The API isBadVersion is defined for you.
// bool isBadVersion(int version);

class Solution {
public:
    int firstBadVersion(int n) {
       // binary-search-the-answer
        
        long long left = 1, right = n;
        
        while(left < right) {
            long long mid = (left + right) / 2;
            
            if(isBadVersion(mid)) right = mid;
            else left = mid + 1;
        }
        
        return left;
    }
};
