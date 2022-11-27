// Math, O(1)
class Solution {
    public int pivotInteger(int n) {
        // 1 + 2 + 3 + ... + x = (0 + x) + (1 + x-1) + (2 + x-2) + ... = (x * (x + 1)) / 2
        // x + (x+1) + (x+2) + (x+3) + ... + (x+n) = (1 + ... + n) - (1 + ... + (x-1))
        
        // we want to find x such that 
        // (1 + ... + n) - (1 + ... + (x-1)) == (1 + ... + x)
        // thus
        // (1 + ... + n) == 2 * (1 + ... + (x-1)) + x
        // (n * (n + 1)) / 2 == ((x - 1) * x) + x
        // ...
        // (1 + ... + n) == x^2
        
        int sum = (n * (n + 1)) / 2;
        int x = (int) Math.sqrt(sum);
        if(x * x == sum) return x;
        return -1;
    }
}
