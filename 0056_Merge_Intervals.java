class Solution {
    
    private int compareIntervals(int[] a, int[] b) {
        if(a[0] != b[0]) return a[0] - b[0];
        else return a[1] - b[1];
    } 
    
    public int[][] merge(int[][] intervals) {
        // sort intervals by first then second value
        Arrays.sort(intervals, this::compareIntervals);
        ArrayList<int[]> result = new ArrayList<>();
        
        // combine intervals and add them to result
        for(int i = 0; i < intervals.length; i++) {
            if(i != intervals.length - 1 && intervals[i][0] == intervals[i + 1][0]) { // shared start bound
                continue; // [i][1] < [i + 1][1] (gaurenteed because of sort)
            } else if(i != intervals.length - 1 && intervals[i][1] >= intervals[i + 1][0]) { // overlapping interval
                intervals[i + 1][0] = intervals[i][0]; // set lower bound to lowest
                intervals[i + 1][1] = Math.max(intervals[i][1], intervals[i + 1][1]); // high bound to highest
            } else { // non-overlapping interval
                result.add(intervals[i]);
            }
        }
        
        // convert result to an int[][]
        int[][] merged = new int[result.size()][];
        for(int i = 0; i < result.size(); i++) {
            merged[i] = result.get(i);
        }
        
        return merged;
    }
}
