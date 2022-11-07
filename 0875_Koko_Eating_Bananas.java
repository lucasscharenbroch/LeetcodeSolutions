class Solution {
    // returns true if Koko can eat all bananas with speed k within h hours
    private boolean isPossible(int[] piles, int h, int k) {
        int time = 0;
        
        for(int p : piles) {
            time += (p / k) + ((p % k) != 0 ? 1 : 0);
        }
        
        return time <= h;
    }
    
    public int minEatingSpeed(int[] piles, int h) {
        // binary-search-the-answer
        int left = 1, right = Integer.MAX_VALUE;
        
        while(left < right) {
            int mid = (int) (((long)left + (long)right) / 2L);
            
            if(isPossible(piles, h, mid)) right = mid;
            else left = mid + 1;
        }
        
        return left;
    }
}
