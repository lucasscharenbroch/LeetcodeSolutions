class Solution {
public:
    int trap(vector<int>& height) {
        int N = height.size();
        vector<int> left(N), right(N); // largest on left/right respectively

        for(int i = 1; i < height.size(); i++) left[i] = max(left[i - 1], height[i - 1]);
        for(int i = N - 2; i >= 0; i--) right[i] = max(right[i + 1], height[i + 1]);

        int result = 0;
        for(int i = 0; i < N; i++) result += max(0, min(left[i], right[i]) - height[i]);
        return result;
    }
};
