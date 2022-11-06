// Sort, then compare, O(nlg(n))
class Solution {
    // assumes that every k is unique, sorts k, and updates v's values so k[i] <=> v[i]
    // where i was the index before k was sorted.
    private void sortParallelArrays(int[] k, int[] v) {
        HashMap<Integer, Integer> kvPairs = new HashMap<>();
        for(int i = 0; i < k.length; i++) {
            kvPairs.put(k[i], v[i]);
        }
        
        Arrays.sort(k);
        
        for(int i = 0; i < k.length; i++) {
            v[i] = kvPairs.get(k[i]);
        }
    }
    
    // returns true if i collides with j before they get to target
    private boolean collides(int target, int[] positions, int[] speed, int i, int j) {
        double targetTimei = (target - positions[i]) / (double) speed[i];
        double targetTimej = (target - positions[j]) / (double) speed[j];
        double epsilon = 1.0e-8;
        return targetTimei - targetTimej - epsilon <= 0;
    }
        
    public int carFleet(int target, int[] position, int[] speed) {
        sortParallelArrays(position, speed);
        
        int numFleets = 1;
        int j = speed.length - 1; // index of slowest car in current fleet
        for(int i = speed.length - 2; i >= 0; i--) {
            if(!collides(target, position, speed, i, j)) { // i is not in j's fleet
                j = i;
                numFleets++;
            }
        }
        
        return numFleets;
    }
}
