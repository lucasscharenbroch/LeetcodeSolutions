// Brute-Force O(n^3)
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        int total = 0;
        
        for(int i = 0; i < nums.size(); i++) {
            for(int j = 0; j < i; j++) {
                for(int k = 0; k < j; k++) {
                    if(nums[i] == nums[j] || nums[j] == nums[k] || nums[i] == nums[k]) continue; // matching vals
                    total++;
                }
            }
        }
        
        return total;
    }
};

// Pair-Counting (DP), O(n)
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        int counts[1001] = {0};
        int numPairs = 0; // total pairwise-distince pairs in nums[0..i]
        int result = 0;
        for(int i = 0; i < nums.size(); i++) {
            int& num = nums[i];
            int pairsWithNum = counts[num] * (i - counts[num]); // number of pairs that contain num
            int pairsWithoutNum = numPairs - pairsWithNum;
            result += pairsWithoutNum; // each (pairWithoutNum) with num creates a new triplet
            
            numPairs += i - counts[num]; // number of seen elements that != num
            counts[num]++;
        }
        
        return result;
    }
};
