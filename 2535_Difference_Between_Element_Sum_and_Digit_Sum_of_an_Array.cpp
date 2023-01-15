struct AddDigits {
    int operator()(int a, int b) {
        while(b != 0) {
            a += b % 10;
            b /= 10;
        }
        return a;
    }
};

class Solution {
public:
    int differenceOfSum(vector<int>& nums) {
        int elemSum = accumulate(nums.begin(), nums.end(), 0);
        int digitSum = accumulate(nums.begin(), nums.end(), 0, AddDigits());
        return abs(elemSum - digitSum);
    }
};
