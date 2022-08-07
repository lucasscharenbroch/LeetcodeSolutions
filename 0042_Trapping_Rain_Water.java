class Solution {
    public int trap(int[] height) {
        int total = 0;
        int left = 0;
        int right = height.length - 1;
        while(left < right - 1) {
            if(height[left] <= height[right]) { // advance left
                int i = left;
                while(height[++i] < height[left]) total += height[left] - height[i];
                left = i;
            } else { // advance right
                int i = right;
                while(height[--i] < height[right]) total += height[right] - height[i];
                right = i;
            }
        }
        
        return total;
    }
}
