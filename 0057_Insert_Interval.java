class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> result = new ArrayList<int[]>(intervals.length);
        
        int i = 0; // iterator for intervals
        
        // add intervals that end before newInterval starts 
        while(i < intervals.length && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);    
        }
        
        // expand the bounds of newInterval based on the intervals it overlaps, then add it
        while(i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        result.add(newInterval);
        
        // add intervals that start after newInterval ends
        while(i < intervals.length) {
            result.add(intervals[i++]);
        }
        
        // convert result to an int[][], then return 
        int[][] arrResult = new int[result.size()][];
        for(i = 0; i < result.size(); i++) {
            arrResult[i] = result.get(i);
        }
        
        return arrResult;
    }
}
