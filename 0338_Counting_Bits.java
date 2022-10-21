class Solution {
    public int[] countBits(int n) {
        int[] popCounts = new int[n + 1];
        for(int i = 0; i <= n; i++) {
            // i & 1 is the lowest bit (1 or 0)
            // popCounts[(i >> 1)] is the rest of the bits.
            popCounts[i] = (i & 1) + popCounts[(i >> 1)];
        }
        return popCounts;
    }
}
