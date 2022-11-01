// Binary-Indexed_Tree O(nlg(n))
class Solution {
    private class MaxBIT {
        int[] maxes;
        
        public MaxBIT(int capacity) {
            maxes = new int[capacity + 1];
        }
        
        public void updateMax(int i, int n) { // update maxes from [1..=i]
            i++;
            while(i < maxes.length) {
                maxes[i] = Math.max(maxes[i], n);
                i += (i & -i);
            }
        }
        
        public int getMax(int i) { // find max from [1..=i]
            i++;
            int max = 0;
            while(i != 0) {
                max = Math.max(max, maxes[i]);
                i -= (i & -i);
            }
            return max;
        }
    }
    
    public int eraseOverlapIntervals(int[][] intervals) {
        int START_MIN = -100000; // smallest interval start
        int END_MAX = 100000;    // largest interval end
        
        MaxBIT tree = new MaxBIT(END_MAX - START_MIN); // BIT.getMax(i) = most non-overlapping intervals on START_MIN..i
        
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // sort intervals by end-time
        
        int max = 0;
        
        for(int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];
            
            int currentMax = 1 + tree.getMax(start - START_MIN);
            tree.updateMax(end - START_MIN, currentMax);
            max = Math.max(max, currentMax);
        }
        
        return intervals.length - max;
    }
}

// Greedy O(nlg(n))
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int START_MIN = -100000; // smallest interval start
        
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]); // sort intervals by end-time
        
        int max = 0;
        int end = START_MIN; // end of current set of intervals
        
        // iterate over intervals, and build a "set" (only remember the last
        // interval's end-time because intervals is now sorted by end-time).
        // always add a new interval to the set when it doesn't conflict with the other in the set
        // never add it if it does conflict
        
        for(int[] interval : intervals) {
            if(interval[0] >= end) { // interval doesn't conflict with current intervals
                max++;
                end = interval[1];
            }
        }
        
        return intervals.length - max;
    }
}
