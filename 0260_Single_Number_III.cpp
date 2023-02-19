class Solution {
public:
    vector<int> singleNumber(vector<int>& nums) {
        long long x = 0; // the xor of every num
        
        for(int n : nums) x ^= n;
        
        // now x = a ^ b (since all duplicates cancel themselves out)
        
        // x has at least one set bit (since it is given that a != b)
        // we can take an arbitrary one of those bits, and use it to devide
        // nums into two groups (nums that have that bit set, and nums that
        // have that bit clear) taking the xor of each of those groups
        // will result in a and b.
        
        int a = 0, b = 0; // the xor of the two groups
        
        x &= -x ; // set x to its least significant set bit.
        
        for(int n : nums) {
            if(x & n) a ^= n;
            else b ^= n;
        }
        
        return vector<int>{a, b};
    }
};
