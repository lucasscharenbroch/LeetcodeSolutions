// Consecutive comparison (O(n))
class Solution {
public:
    int singleNonDuplicate(vector<int>& nums) {
        if(nums.size() == 1) return nums[0];
            
        for(int i = 1; i < nums.size() - 1; i++) 
            if(nums[i] != nums[i - 1] && nums[i] != nums[i + 1]) return nums[i];
        
        return nums[0] == nums[1] ? nums[nums.size() - 1] : nums[0];
    }
};

// Xor (O(n))
class Solution {
public:
    int singleNonDuplicate(vector<int>& nums) {
        int result = 0;
        
        for(int& n : nums) result ^= n;
        
        return result;
    }
};

// Binary Search (O(lg(n)))
class Solution {
public:
    int singleNonDuplicate(vector<int>& nums) {
        if(nums.size() == 1) return nums[0];
        
        // if we pick some arbitrary midpoint, we can determine 
        // which side the single element is on, because there will
        // be an odd number of elements on that side 
        
        int left = 0, right = nums.size() - 1;
        
        while(left < right) {
            int mid = (left + right) / 2;
            
            int num_on_left;
            
            if(nums[mid] != nums[mid + 1] && (mid == left || nums[mid] != nums[mid - 1]))
                return nums[mid]; // found single element
            else if(nums[mid] == nums[mid + 1]) num_on_left = mid - left;
            else num_on_left = mid - (left - 1);
            
            if(num_on_left % 2) right = (nums[mid] == nums[mid - 1]) ? mid - 2 : mid - 1;
            else left = (nums[mid] == nums[mid + 1]) ? mid + 2 : mid + 1;
        }
        
        return nums[left];
    }
};
