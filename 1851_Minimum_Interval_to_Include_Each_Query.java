class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        // sort intervals by start, then iterate through sorted queries, maintaining a heap
        // of all intervals that queries[j] might contain.
        
        int[] queriesSorted = queries.clone();
        Arrays.sort(queriesSorted);
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]); // sort by starting point
        HashMap<Integer, Integer> solutions = new HashMap<>();
        
        // heap prioritizes the smallest intervals
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> (a[1] - a[0]) - (b[1] - b[0]));
        int i = 0; // keeps track of which intervals have been added to heap
        
        for(int q : queriesSorted) {
            while(i < intervals.length && intervals[i][0] <= q) heap.add(intervals[i++]);
            while(heap.peek() != null && heap.peek()[1] < q) heap.remove(); // remove top elements 
                                                                            // that don't contain q
            solutions.put(q, heap.peek() == null ? -1 : (heap.peek()[1] - heap.peek()[0] + 1));
        }
        
        // assemble result
        int[] result = new int[queries.length];
        for(int j = 0; j < queries.length; j++) result[j] = solutions.get(queries[j]);
        return result;
    }
}
