class Solution {
    private boolean isDescending(int[] nums, int start) {
        for(int i = start; i < nums.length - 1; i++) {
            if(nums[i] < nums[i + 1]) return false; 
        }        
        return true;
    }
    
    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
    
    // reverses nums (starting at index start)
    private void reverse(int[] nums, int start) {
        for(int i = start; i < nums.length / 2; i++) {
            swap(nums, i, nums.length - i - 1);
        }    
    }
    
    private void nextPermutation(int[] nums, int start) {
        if(nums.length - start <= 1) return; // edgecases
        
        if(isDescending(nums, start + 1)) {
            if(nums[start] >= nums[start + 1]){ // entire array is in descending
                reverse(nums, start);
            } else {
                // sort the rest of the array
                Arrays.sort(nums, start + 1, nums.length);    
                // swap nums[start] with the next higher number
                // progress i to the number immediately higher than nums[start]
                int i;
                for(i = start + 1; i < nums.length && nums[i] <= nums[start]; i++);
                while(nums[i] == nums[start]) i++;
                
                swap(nums, i, start);
            }
        } else {
            nextPermutation(nums, start + 1);    
        }
    }
    
    public void nextPermutation(int[] nums) {
        nextPermutation(nums, 0);
    }
}
