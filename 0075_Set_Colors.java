class Solution {
    public void sortColors(int[] nums) {
        final int numColors = 3;
        
        // scan the array, counting the frequencies of each color
        int[] counts = new int[numColors];
        for(int color : nums) {
            counts[color]++;
        }
        
        // fill the array based on the frequencies
        int i = 0;
        for(int c = 0; c < numColors; c++) {
            while(counts[c]-- > 0) nums[i++] = c;
        }
    }
}
