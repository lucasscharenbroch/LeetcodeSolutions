class Solution {
    private double dist(int[] point) {
        return Math.sqrt(point[0] * point[0] + point[1] * point[1]);
    }
    
    public int[][] kClosest(int[][] points, int k) {
        Arrays.sort(points, (a, b) -> dist(a) > dist(b) ? 1 : -1);
        
        int[][] result = new int[k][];
        for(int i = 0; i < k; i++) {
            result[i] = points[i];
        }
        
        return result;
    }
}
