class Solution {
    private int min(Integer... nums) {
        int result = nums[0];
        for(int num : nums) {
            result = Math.min(result, num);
        }
        return result;
    }
    
    private int max(Integer... nums) {
        int result = nums[0];
        for(int num : nums) {
            result = Math.max(result, num);
        }
        return result;
    }
    
    public int maxProduct(int[] nums) {
        int maxProduct = Integer.MIN_VALUE;
        int prevMin = 0, prevMax = 0, currentMin, currentMax;
        
        for(int i = 0; i < nums.length; i++) {
            if(i == 0) {
                currentMin = nums[i];
                currentMax = nums[i];
            } else {
                currentMin = min(nums[i], nums[i] * prevMin, nums[i] * prevMax);
                currentMax = max(nums[i], nums[i] * prevMin, nums[i] * prevMax);
            }
            maxProduct = max(maxProduct, currentMax);
            prevMin = currentMin;
            prevMax = currentMax;
        }
        
        return maxProduct;
    }
}
