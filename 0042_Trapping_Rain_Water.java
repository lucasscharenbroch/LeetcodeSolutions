class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int currentCapacity = 0;
        int result = 0;
        
        while(left < right) {
            if(height[left] < height[right]) { // advance left
                result += Math.max(0, currentCapacity - height[left]);
                currentCapacity = Math.max(currentCapacity, height[left++]);
            } else if(height[right] < height[left]) { // advance right
                result += Math.max(0, currentCapacity - height[right]);
                currentCapacity = Math.max(currentCapacity, height[right--]);
            } else { // advance both
                result += Math.max(0, 2 * (currentCapacity - height[left]));
                currentCapacity = Math.max(currentCapacity, height[left]);
                left++; right--;
            }
        }
        
        if(left == right) result += Math.max(0, currentCapacity - height[left]);
        
        return result;
    }
}
