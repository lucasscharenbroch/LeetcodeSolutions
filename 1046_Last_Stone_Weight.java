class Solution {
    public int lastStoneWeight(int[] stones) {
        // simulation, use heap for constant-time access of top and lg(n) removal
        PriorityQueue<Integer> weights = new PriorityQueue<>();
        
        for(int s : stones) weights.add(-s); // add -s so the largest weights are removed first
        
        while(weights.size() > 1) {
            int y = -weights.poll();
            int x = -weights.poll();
            if(x == y); // both stones are destroyed
            else weights.add(x - y); // add the difference back into weights
        }
        
        if(weights.size() == 0) return 0;
        else return -weights.poll();
    }
}
