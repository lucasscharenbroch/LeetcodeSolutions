// Copy and Mod
class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        vector<int> copy = nums;
        k %= nums.size();
        for(int i = 0; i < nums.size(); i++)  nums[i] = copy[(i - k + nums.size()) % nums.size()];
    }
};

// Swaps
class Solution {
public:
    void rotate(vector<int>& nums, int k) {
        // swaps will make a circut from 0 to 0, so continue swapping with 0 and
        // adding k until 0 is reached again. Then repeat the process 1 to the right until
        // all elements are shfited.
        
        int numShifted = 0;
        
        for(int offset = 0; numShifted < nums.size(); offset++) {
            int current = (offset + k) % nums.size();
            numShifted++;
            while(current != offset) {
                int temp = nums[offset];
                nums[offset] = nums[current];
                nums[current] = temp;
                numShifted++;

                current = (current + k) % nums.size();
            }
        }
    }
};
