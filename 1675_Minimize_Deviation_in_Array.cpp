class Solution {
public:
    int minimumDeviation(vector<int>& nums) {
        // add all nums to s, multiplying odd numbers by two so that all numbers are even.
        set<int> s;
        for(int n : nums) s.insert(n % 2 == 1 ? n * 2 : n);
        
        int min_gap = INT_MAX;
      
        // now only one operation can be performed: division by 2
        // thus the only way to reduce the deviation is to divide the largest element by 2
        while(true) {
            int current = *(--s.end()); // the largest element in the set
            s.erase(--s.end());
            
            if(current % 2 == 1) break; // largest element is odd: cannot reduce gap
            
            s.insert(current / 2);
            min_gap = min(min_gap, *(--s.end()) - *(s.begin())); // update min_gap
        }
        
        return min_gap;
    }
};
