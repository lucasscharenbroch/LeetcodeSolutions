class Solution {
public:
    int distinctIntegers(int n) {
        return 1 + max(0, n - 2);
    }
};
