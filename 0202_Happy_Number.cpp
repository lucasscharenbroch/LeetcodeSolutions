class Solution {
public:
    bool isHappy(int n) {
        unordered_set<int> seen;
        
        while(n != 1) {
            if(seen.find(n) != seen.end()) return false; // loop encountered
            seen.insert(n);
            
            // seen := sum of the squares of its digits
            int temp = n;
            n = 0;
            while(temp != 0) {
                n += (temp % 10) * (temp % 10);
                temp /= 10;
            }
        }
        
        return true;
    }
};
