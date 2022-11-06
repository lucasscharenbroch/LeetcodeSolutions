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

// Dequeue, O(n)
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        // q is a dequeue that contains a decreasing subsequence of the current window
        // therefore, at any time, the maximum is the first element of q
        LinkedList<Integer> q = new LinkedList<Integer>();
        
        // fill q with initial window
        for(int i = 0; i < k; i++) {
            while(q.peekLast() != null && q.peekLast() < nums[i]) q.removeLast();
            q.addLast(nums[i]);
        }
            
        int left = 0, right = k;
        int[] result = new int[nums.length - k + 1];
        for(int i = 0; right <= nums.length; i++) {
            result[i] = q.getFirst();
            
            if(nums[left] == q.getFirst()) q.removeFirst(); // remove left (if necessary)
            if(right == nums.length) break;
            while(q.peekLast() != null && q.peekLast() < nums[right]) q.removeLast(); // add right
            q.addLast(nums[right]);
            
            left++; right++;
        }
        
        return result;
    }
}
