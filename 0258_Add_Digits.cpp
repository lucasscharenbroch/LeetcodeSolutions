class Solution {
public:
    int addDigits(int num) {
        if(num == num % 10) return num;
        int digit_sum = 0;
        for(; num; num /= 10) digit_sum += num % 10;
        return addDigits(digit_sum);
    }
};
