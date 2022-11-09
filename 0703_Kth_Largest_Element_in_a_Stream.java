class KthLargest {
    PriorityQueue<Integer> kLargest = new PriorityQueue<>();
    int k;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        
        for(int n : nums) {
            kLargest.add(n);
        }
        
        while(kLargest.size() > k) kLargest.poll();
    }
    
    public int add(int val) {
        if(kLargest.size() < k) {
            kLargest.add(val);
            return kLargest.peek();
        }
        if(kLargest.peek() > val) return kLargest.peek(); // val has no effect on k largest
        // otherwise update the k largest
        kLargest.add(val);
        kLargest.poll();
        return kLargest.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
