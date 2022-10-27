class Solution {
    public int getSum(int a, int b) {
        if(a == 0) return b;
        
        // ((a & b) << 1) - sum of all bits that "carry"
        // (a ^ b) - sum of bits that don't "carry"
        return getSum((a & b) << 1, a ^ b);
    }
}
