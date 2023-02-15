class Solution {
public:
    vector<int> addToArrayForm(vector<int>& num, int k) {
        reverse(num.begin(), num.end());
        int carry = 0;
        
        for(int i = 0; i < num.size(); i++, k /= 10) {
            cout << k << " " << carry << endl;
            num[i] += carry + (k % 10);
            carry = num[i] / 10;
            num[i] %= 10;
        }
        
        for(; k || carry; k /= 10) {
            carry += k % 10;
            num.push_back(carry % 10);
            carry  /= 10;
        }
        
        reverse(num.begin(), num.end());
        return num;
    }
};
