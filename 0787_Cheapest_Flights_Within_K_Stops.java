class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // use Bellman-Ford with (k + 1) interations
        
        int[][] pricesToReach = new int[k + 2][n]; // use 2d array to ensure edge ordering doesn't
                                                   // impact number of stops taken in one iteration
                                                   // (different count for each iteration)
        for(int j = 0; j < n; j++) { // populate pricesToReach with INT_MAX (and 0 for src)
            if(j != src) for(int i = 0; i < k + 2; i++) pricesToReach[i][j] = Integer.MAX_VALUE;
        }
        
        for(int i = 1; i < k + 2; i++) { // pricesToReach[i] => (i - 1) stops
            for(int[] edge : flights) {
                int from = edge[0], to = edge[1], price = edge[2];
                
                // relax edge
                if(pricesToReach[i - 1][from] != Integer.MAX_VALUE) 
                    pricesToReach[i][to] = Math.min(pricesToReach[i][to], 
                                                    pricesToReach[i - 1][from] + price);
            }
        }
        
        return pricesToReach[k + 1][dst] == Integer.MAX_VALUE ? -1 : pricesToReach[k + 1][dst];
    }
}
