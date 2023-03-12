class NumArray {
public:
    vector<int> prefix_sum;
    
    NumArray(vector<int>& nums) : prefix_sum(nums.size()) {
        for(int i = 0; i < nums.size(); i++) {
            if(i != 0) prefix_sum[i] += prefix_sum[i - 1];
            prefix_sum[i] += nums[i];
        }
    }
    
    int sumRange(int left, int right) {
        if(left == 0) return prefix_sum[right];
        return prefix_sum[right] - prefix_sum[left - 1];
    }
};

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray* obj = new NumArray(nums);
 * int param_1 = obj->sumRange(left,right);
 */
