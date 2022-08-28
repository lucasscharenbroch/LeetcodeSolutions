class Solution {
    public int numTrees(int n) {
        int[] solutions = new int[n + 1]; // solutions[x] contains the # of unique BSTs of length x
        solutions[0] = 1; solutions[1] = 1; // base-cases
        
        for(int i = 2; i <= n; i++) {
            // iterate through an i-length array, simulating a BST assembly
            for(int j = 0; j < i; j++) { // for each possible head-node
                // add numUniqueLeftSides * numUniqueRightSides to the solution of length i
                solutions[i] += solutions[j] * solutions[i - j - 1];
            }
        }
        
        return solutions[n];
    }
}
