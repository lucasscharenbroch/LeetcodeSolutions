// recursive solution (exponential)
class Solution {
    public int climbStairs(int n) {
        System.out.println(n);
        if(n <= 1) return 1;
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
}

// iterative solution (linear)
class Solution {
    public int climbStairs(int n) {
        // find fib(n) through iteration
        int secondBehind = 1;
        int firstBehind = 1;
        int result = 1;
        
        for(int i = 1; i < n; i++) {
            result = firstBehind + secondBehind;
            secondBehind = firstBehind;
            firstBehind = result;
        }
        
        return result;
    }
}
