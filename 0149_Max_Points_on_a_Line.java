// brute-force O(n^3)
class Solution {
    private boolean doublesEqual(double a, double b) {
        final double epsilon = 0.00000001;
        return Math.abs(a - b) < epsilon;
    }
    
    public int maxPoints(int[][] points) {
        int result = Math.min(2, points.length);
        
        for(int p1 = 0; p1 < points.length; p1++) {
            int x1 = points[p1][0];
            int y1 = points[p1][1];
            for(int p2 = p1 + 1; p2 < points.length; p2++) {
                int x2 = points[p2][0];
                int y2 = points[p2][1];
                
                int rise = y2 - y1;
                int run = x2 - x1;
                
                // check if any other points are on this line
                int numPointsOnLine = 2;
                for(int p3 = 0; p3 < points.length; p3++) {
                    if(p1 == p3 || p2 == p3) continue;
                    int x3 = points[p3][0];
                    int y3 = points[p3][1];
                    
                    if(run == 0) {
                        if(x3 == x1) numPointsOnLine++;
                    } else if(rise == 0) {
                        if(y3 == y1) numPointsOnLine++;
                    } else {
                        double slope = (rise * 1.0) / (run * 1.0);
                        if(doublesEqual(y3 * 1.0 - y1 * 1.0,  slope * (1.0 * x3 - 1.0 * x1)))
                            numPointsOnLine++;
                    }
                }
                result = Math.max(result, numPointsOnLine);
            }
        }
        
        return result;
    }
}
