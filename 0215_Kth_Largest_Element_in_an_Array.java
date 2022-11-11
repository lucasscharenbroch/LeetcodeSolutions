class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> topK = new PriorityQueue<>();
        
        for(int num : nums) {
            if(topK.size() < k) {
                topK.add(num);
                continue;
            }
            
            if(topK.peek() < num) {
                topK.remove();
                topK.add(num);
            }
        }
        
        return topK.peek();
    }
}
