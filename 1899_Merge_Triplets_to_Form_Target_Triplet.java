// DP
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // we need to find a triplet to fufill each of the following three conditions:
        // 1.) t[0] == target[0], t[1] <= target[1], t[2] <= target[2]
        // 2.) t[1] == target[1], t[0] <= target[0], t[2] <= target[2]
        // 3.) t[2] == target[2], t[0] <= target[0], t[1] <= target[1]
        // (the three t's that fulfill these, when merged together, form the target triplet)
        boolean found1 = false, found2 = false, found3 = false; // indicate if condition is fulfilled
        
        // iterate through triplets, setting the booleans if a triplet is found that fulfills that condition
        for(int[] t : triplets) {
            if(t[0] == target[0] && t[1] <= target[1] && t[2] <= target[2]) found1 = true;
            if(t[1] == target[1] && t[0] <= target[0] && t[2] <= target[2]) found2 = true;
            if(t[2] == target[2] && t[0] <= target[0] && t[1] <= target[1]) found3 = true;
            if(found1 && found2 && found3) return true; // return true if all three conditions are fulfilled
        }
        
        return false;
    }
}

// Greedy
class Solution {
    public boolean mergeTriplets(int[][] triplets, int[] target) {
        // keep a running triplet, always take max operation if the result will stay under target
        int[] result = new int[3];
        
        for(int[] t : triplets) {
            if(t[0] <= target[0] && t[1] <= target[1] && t[2] <= target[2]) {
                result[0] = Math.max(result[0], t[0]);
                result[1] = Math.max(result[1], t[1]);
                result[2] = Math.max(result[2], t[2]);
            }
        }
        
        return result[0] == target[0] && result[1] == target[1] && result[2] == target[2];
    }
}
