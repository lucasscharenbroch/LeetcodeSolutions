class Solution {
public:
    bool containsNearbyAlmostDuplicate(vector<int>& nums, int indexDiff, int valueDiff) {
        multiset<int> s; // set for all numbers in the window
        
        // slide window across (of size indexDiff) nums, adding values to s, and finding the
        // nearest element to each new value.
        
        for(int i = 0; i < nums.size(); i++) {
            if(i > indexDiff) s.erase(nums[i - indexDiff - 1]); // remove passed elem (if applicable)
            
            auto it = s.insert(nums[i]); // iterator to inserted number
            auto prev = it, next = it; // next and prev, in sorted order.
            prev--, next++;
            
            if(it != s.begin() && abs(*prev - nums[i]) <= valueDiff) return true;
            if(next != s.end() && abs(*next - nums[i]) <= valueDiff) return true;
        }
        
        return false;
        
    }
};
