class Solution {
public:
    void moveZeroes(vector<int>& nums) {
        int zeroIndex = 0;
        int nonzeroIndex = 0;
        
        // repeatedly move nonzero elements forward into the first zero in the array
        while(true) {
            while(zeroIndex != nums.size() && nums[zeroIndex] != 0) zeroIndex++;
            while(nonzeroIndex != nums.size() && nums[nonzeroIndex] == 0 || nonzeroIndex < zeroIndex)
                nonzeroIndex++;
            if(zeroIndex == nums.size() || nonzeroIndex == nums.size()) break;
            nums[zeroIndex] = nums[nonzeroIndex];
            nums[nonzeroIndex] = 0;
        }
    }
};
