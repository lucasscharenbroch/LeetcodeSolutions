class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];
        
        // assign result[n] to the product of nums[... n - 1]
        int product = 1;
        for(int i = 0; i < nums.length; i++) {
            result[i] = product;
            product *= nums[i];
        }
        
        // multiply result[n] by nums[n + 1 ...]
        product = 1;
        for(int i = nums.length - 1; i >= 0; i--) {
            result[i] *= product;
            product *= nums[i];
        }
        
        return result;
    }
}
