class Solution {
public:
    static const int MAX_NUMS_SIZE = 100000;
    
    int maximumGap(vector<int>& nums) {
        if(nums.size() < 2) return 0;
        
        int minNum = nums[0];
        int maxNum = nums[0];
        for(int& num : nums) {
            minNum = min(minNum, num);
            maxNum = max(maxNum, num);
        }
        
        // Divide nums (except min and max) into (nums.size() - 1) holes.
        // By the pigenhold principle, one or more holes must be empty,
        // so the two farthest-apart elements cannot be in the same hole.
        int numHoles = nums.size() - 1;
        int holeSize = ceil((maxNum - minNum) / (double)numHoles);
        
        // iterate through nums, adding them to their respective holes
        // (only keep track of each hole's max and min)
        int holes[MAX_NUMS_SIZE][2]; // holes[i][0] = min in hole, [i][1] = max in hole
        memset(holes, -1, sizeof(holes)); // holes[i][j] = -1;
        
        for(int& num : nums) {
            if(num == minNum || num == maxNum) continue;
            
            int holeNumber = (num - minNum) / holeSize;
            if(holes[holeNumber][0] == -1) holes[holeNumber][0] = holes[holeNumber][1] = num;
            else {
                holes[holeNumber][0] = min(holes[holeNumber][0], num);
                holes[holeNumber][1] = max(holes[holeNumber][1], num);
            }
        }
        
        // iterate through each hole, and look at mins/maxes to determine the largest gap
        int maxGap = 0;
        int prevMax = minNum;
        for(int i = 0; i <= numHoles; i++) {
            if(holes[i][0] == -1) continue;
            maxGap = max(maxGap, holes[i][0] - prevMax);
            prevMax = holes[i][1];
        }
        maxGap = max(maxGap, maxNum - prevMax);
        
        return maxGap;
    }
};
