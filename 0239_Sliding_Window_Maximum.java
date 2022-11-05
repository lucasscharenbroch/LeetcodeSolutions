// Max-Heap, O((n-k)*k)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> windowValues = new PriorityQueue<>((a, b) -> b - a); // Max-Heap
        int[] result = new int[1 + nums.length - k];
        
        for(int i = 0; i < k; i++) windowValues.add(nums[i]); // add initial window values to heap
        
        int left = 0, right = k;
        
        for(int i = 0; i < result.length - 1; i++) {
            result[i] = windowValues.peek();
            windowValues.remove(nums[left++]);
            windowValues.add(nums[right++]);
        }
        result[result.length - 1] = windowValues.peek();
            
        return result;
    }
}
