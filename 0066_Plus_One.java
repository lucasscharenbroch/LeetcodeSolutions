class Solution {
    public int[] plusOne(int[] digits) {
        int carry = 1;
        for(int i = digits.length - 1; i >= 0; i--) {
            if(carry == 0) break;
            digits[i] += carry;
            carry = digits[i] / 10;
            digits[i] %= 10;
        }
        
        if(carry != 0) {
            // result = [1 , ...(digits)]
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            for(int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        } else return digits;
    }
}
