class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        // use dijkstra's shortest path algorithm
        
        k--; // adjust k to be zero-indexed
        
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]); // prioritize low costs
        HashSet<Integer> visited = new HashSet<>();
        
        // populate adjacency list
        HashMap<Integer, List<int[]>> cnx = new HashMap<>(); // node : {{neigh1, weight1}, ...}
        for(int[] time : times) {
            cnx.putIfAbsent(time[0] - 1, new ArrayList<int[]>());
            cnx.get(time[0] - 1).add(new int[] {time[1] - 1, time[2]});
        }
        
        int numVisited = 0;
        int maxCost = 0;
        
        q.add(new int[] {k, 0}); // start q with k, which has cost 0
        
        while(!q.isEmpty()) {
            int[] currentPair = q.poll();
            int current = currentPair[0];
            int currentCost = currentPair[1];
            
            if(visited.contains(current)) continue;
            numVisited++;
            maxCost = Math.max(maxCost, currentCost);
            visited.add(current);
            
            // update costs of neighbors
            if(cnx.get(current) == null) continue;
            for(int[] cn : cnx.get(current)) {
                int neigh = cn[0];
                int weight = cn[1];
                
                if(!visited.contains(neigh)) q.add(new int[] {neigh, currentCost + weight});
            }
        }
        
        return (numVisited == n) ? maxCost : -1;
    }
}
