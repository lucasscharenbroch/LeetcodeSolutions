class Solution {
    public int removeDuplicates(int[] nums) {
        int insertionPoint = 0;
        
        for(int num: nums) {
            if(insertionPoint > 1 && nums[insertionPoint - 2] == num)
                continue; // don't insert more than 2 of the same number
            nums[insertionPoint++] = num; // insert num
        }
        
        return insertionPoint;
    }
}
